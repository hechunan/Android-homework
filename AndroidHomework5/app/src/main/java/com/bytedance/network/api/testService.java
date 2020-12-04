package com.bytedance.network.api;

import com.bytedance.network.model.getInfo;
import com.bytedance.network.model.postInfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface testService {
    @GET("video")
    Call<getInfo> get();
    @Multipart
    @POST("video")
    Call<postInfo> post(@Query("student_id") String studentId,
                        @Query("user_name") String userName,
                        @Query("extra_value") String extraValue,
                        @Part MultipartBody.Part iamge,
                        @Part MultipartBody.Part video);
}
