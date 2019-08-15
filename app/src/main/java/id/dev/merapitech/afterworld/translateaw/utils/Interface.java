package id.dev.merapitech.afterworld.translateaw.utils;

import id.dev.merapitech.afterworld.translateaw.request.RequestBahasa;
import id.dev.merapitech.afterworld.translateaw.request.RequestDetail;
import id.dev.merapitech.afterworld.translateaw.request.RequestEdit;
import id.dev.merapitech.afterworld.translateaw.request.RequestJenis;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by XGibar on 27/10/2016.
 */
public interface Interface {
    @POST("http://172.27.13.231:8001/api/bahasa")
    Call<ResponseBody> requestJenis(@Body RequestJenis requestJenis);
    @POST("http://172.27.13.231:8001/api/bahasa")
    Call<ResponseBody> requestBahasa(@Body RequestBahasa requestBahasa);
    @POST("http://172.27.13.231:8001/api/bahasa")
    Call<ResponseBody> requestDetail(@Body RequestDetail requestDetail);
    @POST("http://172.27.13.231:8001/api/bahasa")
    Call<ResponseBody> requestEdit(@Body RequestEdit requestEdit);
}


