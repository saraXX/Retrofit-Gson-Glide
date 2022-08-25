package android.guide.retrofit_gson_network;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.stream.JsonReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {
    //    TODO 5 : WRITE DOWN THE URL'S CUZ I'VE TO TEST MANY API'S
    public static final String BASE_POST_URL = "https://jsonplaceholder.typicode.com/";
    public static final String BASE_MAKEUP_URL = "https://makeup-api.herokuapp.com/";
    public static final String BASE_PRODUCT_URL = "https://fakestoreapi.com/";

    TextView textView;
    jsonPlaceHolderAPI jsonAPI;
    MakeupAPI makeupAPI;
    ProductsAPI productsAPI;
    Retrofit retrofit;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imgHolder);

//      TODO 6 : Creating the Retrofit instance
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_MAKEUP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//     todo 6.1 change the baseUrl to match the proper method as follow
//      IF YOU USE BASE_FAKE_URL    ->   CALL getPosts()
//      IF YOU USE BASE_MAKEUP_URL  ->   CALL getMakeups()
//      IF YOU USE BASE_PRODUCT_URL ->   CALL CALL getProducts()

        getMakeups();
//        getPosts(); 
//        getProducts();

    }

    public void getPosts() {
        jsonAPI = retrofit.create(jsonPlaceHolderAPI.class);
        Call<List<Posts>> call = jsonAPI.getPosts();
//        enqueue mean running the call in background thread rather than the main thread
//        , so out main activity won't freeze and crash. this is all done by retrofit enqueue method.
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }

                List<Posts> postsList = response.body();
//                Log.d("TAG", "onResponse: "+makeupsList.get(0).getName());
                for (Posts posts : postsList) {
                    String content = "";
                    content += "id: " + posts.getId() + "\n";
                    content += "name: " + posts.getTitle() + "\n";
                    content += "user id: " + posts.getUserId() + "\n";
                    content += "body: " + posts.getBody() + "\n";
//                    content+="product type: "+posts.getProduct_type()+"\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                textView.setText("fail : " + t.getMessage());
            }
        });
    }


    public void getMakeups() {
        makeupAPI = retrofit.create(MakeupAPI.class);
//        get all makeup
//        Call<List<Makeup>> call = makeupAPI.getMakeup();
        Call<List<Makeup>> call = makeupAPI.getSomeMakeup("colourpop", "lipstick");
//        Call<List<Makeup>> call = makeupAPI.getOneMakeup(1048);


//        enqueue mean running the call in background thread rather than the main thread
//        , so out main activity won't freeze and crash. this is all done by retrofit enqueue method.
        call.enqueue(new Callback<List<Makeup>>() {
            @Override
            public void onResponse(Call<List<Makeup>> call, Response<List<Makeup>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }

                List<Makeup> MakeupList = response.body();
//                Log.d("TAG", "onResponse: "+makeupsList.get(0).getName());
                for (Makeup makeup : MakeupList) {
                    String content = "";
                    content += "id: " + makeup.getId() + "\n";
                    content += "name: " + makeup.getName() + "\n";
                    content += "brand: " + makeup.getBrand() + "\n";
                    content += "price: " + makeup.getPrice() + "USD" + "\n";
                    content += "product type: " + makeup.getProduct_type() + "\n";
                    content += "img url: " + makeup.getImage_link() + "\n";

                    textView.append(content);
//                    put image url using Glide lib
                    Glide.with(MainActivity.this).load(makeup.getImage_link()).into(imageView);

                }

            }

            @Override
            public void onFailure(Call<List<Makeup>> call, Throwable t) {
                textView.setText("fail : " + t.getMessage());
            }
        });
    }


    public void getProducts() {
//        TODO 7 : Accessing the API
        productsAPI = retrofit.create(ProductsAPI.class);
//        TODO 8 : CALL THE METHODS OF INTERFACE
//        comment out to get all products
//        Call<List<Products>> call = productsAPI.getProducts();
//        only get 3 products
        Call<List<Products>> call = productsAPI.getSomeProducts(3);

//        TODO 9 : RUN API asynchronously
//        enqueue mean running the call in background thread rather than the main thread
//        , so out main activity won't freeze and crash. this is all done by retrofit enqueue method.
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
//                check internet response code.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
//                get the response as a json
                List<Products> MakeupList = response.body();
                for (Products products : MakeupList) {
                    String content = "";
                    content += "id: " + products.getId() + "\n";
                    content += "name: " + products.getTitle() + "\n";
                    content += "brand: " + products.getCategory() + "\n";
                    content += "price: " + products.getPrice() + "USD" + "\n";
                    content += "product type: " + products.getDescription() + "\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                textView.setText("fail : " + t.getMessage());
            }
        });
    }
}