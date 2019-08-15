package id.dev.merapitech.afterworld.translateaw.utils;

import android.content.Context;


import id.dev.merapitech.afterworld.translateaw.MainActivity;
import id.dev.merapitech.afterworld.translateaw.R;
import id.dev.merapitech.afterworld.translateaw.request.RequestBahasa;
import id.dev.merapitech.afterworld.translateaw.request.RequestDetail;
import id.dev.merapitech.afterworld.translateaw.request.RequestEdit;
import id.dev.merapitech.afterworld.translateaw.request.RequestJenis;
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

    public static Call<ResponseBody> req0201(Context context) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestJenis request = new RequestJenis(context.getString(R.string.code_jenis),context.getString(R.string.signature));
        return APIInterface.requestJenis(request);
    }
    public static Call<ResponseBody> req0202(Context context, String idlanguage) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestBahasa.DataBahasa data = new RequestBahasa.DataBahasa(idlanguage);
        RequestBahasa request = new RequestBahasa(context.getString(R.string.code_bahasa),context.getString(R.string.signature), data);
        return APIInterface.requestBahasa(request);
    }
    public static Call<ResponseBody> req0203(Context context, String idlanguage) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestDetail.DataDetail data = new RequestDetail.DataDetail(idlanguage);
        RequestDetail request = new RequestDetail(context.getString(R.string.code_detail),context.getString(R.string.signature), data);
        return APIInterface.requestDetail(request);
    }
    public static Call<ResponseBody> req0204(Context context, String idlanguage, String title) {
        APIInterface = Api.initRetrofit(Api.showLog);
        RequestEdit.DataEdit data = new RequestEdit.DataEdit(idlanguage, title);
        RequestEdit request = new RequestEdit(context.getString(R.string.code_edit),context.getString(R.string.signature), data);
        return APIInterface.requestEdit(request);
    }
}
