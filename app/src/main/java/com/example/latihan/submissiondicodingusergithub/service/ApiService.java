package com.example.latihan.submissiondicodingusergithub.service;

import com.example.latihan.submissiondicodingusergithub.entity.DetailUserEntity;
import com.example.latihan.submissiondicodingusergithub.entity.FollowersEntity;
import com.example.latihan.submissiondicodingusergithub.entity.FollowingEntity;
import com.example.latihan.submissiondicodingusergithub.entity.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token isi dengan token github")
    Call<UserResponse> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token isi dengan token github")
    Call<DetailUserEntity> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token isi denga token github")
    //menggunakan list karena kita akan menampilkan data banyak
    Call<List<FollowersEntity>> getFollowers(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    Call<List<FollowingEntity>> getFollowing(
            @Path("username") String username
    );

}
