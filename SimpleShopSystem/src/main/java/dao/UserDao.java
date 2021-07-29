package dao;

import model.UserModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class UserDao {

    public static List<UserModel> clientsStoring = new ArrayList<>();

    public void add(UserModel userModel) {
        clientsStoring.add(userModel);

    }

    public Optional<UserModel> findByName(String name) {
        return clientsStoring.stream().filter(productModel -> productModel.getName().equalsIgnoreCase(name)).findFirst();
    }
}
