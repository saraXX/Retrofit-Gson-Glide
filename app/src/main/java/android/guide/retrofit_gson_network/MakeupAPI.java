package android.guide.retrofit_gson_network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MakeupAPI {
    @GET("api/v1/products.json")
    Call<List<Makeup>> getMakeup();

    @GET("api/v1/products.json")
    Call<List<Makeup>> getSomeMakeup(
//            brand = {"fenty","benefit"}
            @Query("brand") String brand,
//            type = "lipstick"
            @Query("product_type") String type);

}
