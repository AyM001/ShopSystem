package json;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.CategoryModel;
import model.ProductModel;
import model.ShopModel;
import model.UserModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import static dao.CategoryDao.categoryStoring;
import static dao.ProductDao.stockStoring;
import static dao.ShopDao.theShop;
import static dao.UserDao.clientsStoring;

@JsonSerialize
public class InputOutput {

    ObjectMapper objectMapper = new ObjectMapper();

    public void javaToJSON() {


            ShopModel shopModel = new ShopModel();
            shopModel.setStock(stockStoring);
            shopModel.setClients(clientsStoring);
            shopModel.setCategories(categoryStoring);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("exported.json") , shopModel);
            System.out.println("Exported to JSON!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void jsonToJAVA(){

        try {

            ShopModel shopModel = objectMapper.readValue(Paths.get("input.json").toFile(), ShopModel.class);

            List<ProductModel> pro = shopModel.getStock();
            List<UserModel> users = shopModel.getClients();
            List<CategoryModel> categ = shopModel.getCategories();
            stockStoring.addAll(pro);
            clientsStoring.addAll(users);
            categoryStoring.addAll(categ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

