package com.example.latihan.submissiondicodingusergithub.entity;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Parcel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity{
    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("id")
    public int id;


}
