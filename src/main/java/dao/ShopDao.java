package dao;

import model.ShopModel;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDao {

    public static List<ShopModel> theShop = new ArrayList<>();

    public void add(ShopModel shopModel) {
        theShop.add(shopModel);

    }

}
