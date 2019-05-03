package com.example.hamz.dedymizwarapp.api;

import com.example.hamz.dedymizwarapp.list.ActivityList;
import com.example.hamz.dedymizwarapp.list.AskList;
import com.example.hamz.dedymizwarapp.list.BannerList;
import com.example.hamz.dedymizwarapp.list.CityList;
import com.example.hamz.dedymizwarapp.list.DistrictList;
import com.example.hamz.dedymizwarapp.list.GalleryList;
import com.example.hamz.dedymizwarapp.list.LocationList;
import com.example.hamz.dedymizwarapp.list.MapList;
import com.example.hamz.dedymizwarapp.list.MessageList;
import com.example.hamz.dedymizwarapp.list.QuestionList;
import com.example.hamz.dedymizwarapp.list.UserList;
import com.example.hamz.dedymizwarapp.list.VideoList;
import com.example.hamz.dedymizwarapp.model.Location;
import com.example.hamz.dedymizwarapp.model.Profile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("activities")
    Call<ActivityList> listActivities();

    @GET("activities/{id}/detail")
    Call<ActivityList> activity(@Path("id") int id);

    @GET("galleries")
    Call<GalleryList> Galleries();

    @GET("videos")
    Call<VideoList> videos();

    @GET("questions")
    Call<QuestionList> questions();

    @GET("profile")
    Call<Profile> profile();

    @FormUrlEncoded
    @POST("auth/login")
    Call<UserList> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<UserList> register(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password);

    @GET("maps")
    Call<MapList> location();

    @GET("map/{id}")
    Call<MapList> locationId(@Path("id") int id);

    @FormUrlEncoded
    @POST("send")
    Call<MessageList> sendMessage(@Header("Authorization") String authorization, @Field("message") String message);

    @GET("messages")
    Call<MessageList> getMessage(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("ask")
    Call<AskList> sendAsk(@Field("name") String name,
                          @Field("email") String email,
                          @Field("telephone") String telepon,
                          @Field("ask") String ask);

    @GET("cities")
    Call<CityList> cities();

    @GET("districts/{id}")
    Call<DistrictList> districts(@Path("id") int id);

    @GET("location/{id}")
    Call<LocationList> singleLocation(@Path("id") int mapId);

    @GET("banners")
    Call<BannerList> banner();

}
