package model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopModel implements Serializable {

    public  List<ProductModel> stock = new ArrayList<>();

    public  List<UserModel> clients = new ArrayList<>();

    public  List<CategoryModel> categories = new ArrayList<>();

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<ProductModel> getStock() {
        return stock;
    }

    public void setStock(List<ProductModel> stock) {
        this.stock = stock;
    }

    public List<UserModel> getClients() {
        return clients;
    }

    public void setClients(List<UserModel> clients) {
        this.clients = clients;
    }
}
