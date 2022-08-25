package android.guide.retrofit_gson_network;

import com.google.gson.annotations.SerializedName;

public class Makeup {
//    TODO 3.1 : CREATE MODEL
    int id;
    String price, name, brand, image_link, product_type;

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }
//    @SerializedName("image_link")
    public String getImage_link() {
        return image_link;
    }

    public String getProduct_type() {
        return product_type;
    }
}
