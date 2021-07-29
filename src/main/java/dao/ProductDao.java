package dao;

import model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductDao {

   public static List<ProductModel> stockStoring = new ArrayList<>();


    public void add(ProductModel productModel) {
        stockStoring.add(productModel);

    }

    public void remove(String nume) {
        stockStoring.removeIf(productModel -> productModel.getName().equalsIgnoreCase(nume));
    }


    public Optional<ProductModel> findByName(String name) {
        return stockStoring.stream().filter(productModel -> productModel.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<ProductModel> getAll() {
        return stockStoring;
    }


}

