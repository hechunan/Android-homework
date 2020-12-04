package com.bytedance.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Video {
    @SerializedName("_id")
    private  String _id;
    @SerializedName("student_id")
    private String studentId;

    // 仓库描述
    @SerializedName("user_name")
    private String userName;

    // 仓库 fork 数量
    @SerializedName("video_url")
    private String videoUrl;

    // 仓库 star 数量
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_w")
    private int imageW;

    @SerializedName("image_h")
    private int imageH;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("extra_value")
    private  String extraValue;
    public String getExtraValue(){return extraValue;}
    public String getStudentId() {
        return studentId;
    }
    public String getUserName() {
        return userName;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }


}
