package id.dev.merapitech.afterworld.translateaw.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.dev.merapitech.afterworld.translateaw.response.ResponseBahasa;
import id.dev.merapitech.afterworld.translateaw.response.ResponseJenis;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by argaimac on 4/5/17.
 */

public class Api {
    private static Api instance;
    public static String BASE_URL = "http://209.97.161.178:6002/api/v1/";
    public static List<ResponseBahasa> bahasaList;
    public static List<ResponseJenis> jenisList;
    public static ArrayList<String> jenisBahasaList = new ArrayList<String>();
    public static ProgressDialog mProgressDialog;
    public static boolean showLog = true;
    private static NumberFormat formatter;
    private static DecimalFormat df;
    private static DateFormat humanDate;

    public static final int DEFAULT_RETRIES = 0;

    public static String getSignature(String signature) {
        String post = null;
        try {
            post = Helper.SHA1(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        formatter = new DecimalFormat("#,###,###");
        df = new DecimalFormat("####0.00");
        humanDate = new SimpleDateFormat("dd-MM-yyyy");
        return instance;
    }

    //INITIALIZE RETROFIT//
    public static Interface initRetrofit(boolean showLog) {
        Retrofit retrofit;
        if (showLog == true) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            final OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(Interface.class);
    }

    //RETRY DIALOG//
    public static void retryDialog(final Context context, final Call<ResponseBody> call, final Callback<ResponseBody> callback, final int tanda, final boolean dialog) {
        new AlertDialog.Builder(context)
                .setTitle("Gagal Terhubung")
                .setMessage("Pastikan sudah terhubung dengan jaringan yang stabil")
                .setCancelable(false)
                .setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                ((Activity) context).setResult(tanda);
                                ((Activity) context).finish();
                            }
                        }
                )
                .setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (dialog == true) {
                            mProgressDialog = new ProgressDialog(context);
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setCancelable(false);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.show();
                            call.clone().enqueue(new RetryableCallback<ResponseBody>(call) {

                                @Override
                                public void onFinalResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    mProgressDialog.dismiss();
                                    callback.onResponse(call, response);
                                }

                                @Override
                                public void onFinalFailure(Call<ResponseBody> call, Throwable t) {
                                    mProgressDialog.dismiss();
                                    callback.onFailure(call, t);
                                }
                            });
                        } else {
                            call.clone().enqueue(new RetryableCallback<ResponseBody>(call) {

                                @Override
                                public void onFinalResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    mProgressDialog.dismiss();
                                    callback.onResponse(call, response);
                                }

                                @Override
                                public void onFinalFailure(Call<ResponseBody> call, Throwable t) {
                                    mProgressDialog.dismiss();
                                    callback.onFailure(call, t);
                                }
                            });
                        }

                    }
                }).create().show();
    }

    //RETROFIT RETRIES//
    public static <T> void enqueueWithRetry(Context context, Call<T> call, final int retryCount, final Callback<T> callback, boolean dialog, String message) {
        if (dialog == true) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
            call.enqueue(new RetryableCallback<T>(call, retryCount) {

                @Override
                public void onFinalResponse(Call<T> call, Response<T> response) {
                    mProgressDialog.dismiss();
                    callback.onResponse(call, response);
                }

                @Override
                public void onFinalFailure(Call<T> call, Throwable t) {
                    mProgressDialog.dismiss();
                    callback.onFailure(call, t);
                }
            });
        } else {
            call.enqueue(new RetryableCallback<T>(call, retryCount) {

                @Override
                public void onFinalResponse(Call<T> call, Response<T> response) {
                    callback.onResponse(call, response);
                }

                @Override
                public void onFinalFailure(Call<T> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }
    }

    public static <T> void enqueueWithRetry(Context context, Call<T> call, final Callback<T> callback, boolean dialog, String message) {
        enqueueWithRetry(context, call, DEFAULT_RETRIES, callback, dialog, message);
    }

    public static boolean isCallSuccess(Response response) {
        int code = response.code();
        return (code >= 200 && code < 400);
    }
}

abstract class RetryableCallback<T> implements Callback<T> {

    private int totalRetries = 0;
    private static final String TAG = RetryableCallback.class.getSimpleName();
    private final Call<T> call;
    private int retryCount = 0;

    public RetryableCallback(Call<T> call, int totalRetries) {
        this.call = call;
        this.totalRetries = totalRetries;
    }

    public RetryableCallback(Call<T> call) {
        this.call = call;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!Api.isCallSuccess(response))
            if (retryCount++ < totalRetries) {
                retry();
            } else
                onFinalResponse(call, response);
        else
            onFinalResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
//        Log.e(TAG, t.getMessage());
        if (retryCount++ < totalRetries) {
            retry();
        } else
            onFinalFailure(call, t);
    }

    public void onFinalResponse(Call<T> call, Response<T> response) {

    }

    public void onFinalFailure(Call<T> call, Throwable t) {
    }

    private void retry() {
        call.clone().enqueue(this);
    }

}