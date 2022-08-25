package android.guide.retrofit_gson_network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface jsonPlaceHolderAPI {
//    TODO 4 CREATE ENDPOINTS INTERFACE
    @GET("posts")
    Call<List<Posts>> getPosts();
}
