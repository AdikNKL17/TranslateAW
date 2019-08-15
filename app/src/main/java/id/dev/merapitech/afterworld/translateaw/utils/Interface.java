package com.merapitech.finance.utils;



import com.merapitech.finance.model.ModelDoUnMonth;
import com.merapitech.finance.model.ModelDoUnYear;
import com.merapitech.finance.request.RequestApprovePlay;
import com.merapitech.finance.request.RequestApproveRAB;
import com.merapitech.finance.request.RequestLogin;
import com.merapitech.finance.request.RequestSigCode;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by XGibar on 27/10/2016.
 */
public interface Interface {
    @POST("http://209.97.161.178:6002/api/v1")
    Call<ResponseBody> requesLogin(@Body RequestLogin requestLogin);
    @POST("http://209.97.161.178:6002/api/v1")
    Call<ResponseBody> requestSigCode(@Body RequestSigCode requestSigCode);
    @POST("http://209.97.161.178:6002/api/v1")
    Call<ResponseBody> requestApproveRAB(@Body RequestApproveRAB requestApproveRAB);
    @POST("http://209.97.161.178:6002/api/v1")
    Call<ResponseBody> requestApprovePlay(@Body RequestApprovePlay requestApprovePlay);
}


