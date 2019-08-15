package com.merapitech.finance.utils;

import android.content.Context;

import com.merapitech.finance.R;
import com.merapitech.finance.model.ModelDoUnMonth;
import com.merapitech.finance.model.ModelDoUnYear;
import com.merapitech.finance.module.MainActivity;
import com.merapitech.finance.request.RequestApprovePlay;
import com.merapitech.finance.request.RequestApproveRAB;
import com.merapitech.finance.request.RequestLogin;
import com.merapitech.finance.request.RequestSigCode;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by palapabeta on 02/02/18.
 */

public class ParamReq {

    public static Context context;
    private static Interface APIInterface;

    public static MainActivity merapitech() {
        return (MainActivity) context;
    }

    public static Call<ResponseBody> req0101(String username, String password, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestLogin.Data data = new RequestLogin.Data(username, password);
        RequestLogin params = new RequestLogin(context.getString(R.string.login),  data,context.getString(R.string.signature));
        return APIInterface.requesLogin(params);
    }

    public static Call<ResponseBody> req000cs(String hCode,Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> reqApproveRAB(String id_rab, String status, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestApproveRAB.Data data = new RequestApproveRAB.Data(id_rab, status);
        RequestApproveRAB params = new RequestApproveRAB(context.getString(R.string.code_approve),  data,context.getString(R.string.signature));
        return APIInterface.requestApproveRAB(params);
    }

    public static Call<ResponseBody> reqAprRejctPlay(String id_rab, Context context, String code) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestApprovePlay.Data data = new RequestApprovePlay.Data(id_rab);
        RequestApprovePlay params = new RequestApprovePlay(code, data,context.getString(R.string.signature));
        return APIInterface.requestApprovePlay(params);
    }

    public static Call<ResponseBody> req0201(String hCode,Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0202(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0203(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0204(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0205(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0206(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0207(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0208(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

    public static Call<ResponseBody> req0209(String hCode, Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestSigCode params = new RequestSigCode(hCode, context.getString(R.string.signature));
        return APIInterface.requestSigCode(params);
    }

}
