package android.guide.retrofit_gson_network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductsAPI {
    @GET("products")
    Call<List<Products>> getProducts();

    @GET("products")
    Call<List<Products>> getSomeProducts(@Query("limit") int num);
}
