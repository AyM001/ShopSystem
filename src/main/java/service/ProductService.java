package service;


import UI.SwitchToFileUI;
import dao.UserDao;
import model.ProductModel;
import model.UserModel;
import dao.ProductDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static UI.SwitchToFileUI.*;

public class ProductService {


    private final ProductDao productDao = new ProductDao();

    private UserDao userDao = new UserDao();

    private final List<ProductModel> cos = new ArrayList<>();


    public void addProduct(ProductModel productModel) {
        productDao.add(productModel);
    }

    public List<ProductModel> getProducts() {
        return productDao.getAll();
    }


    public void buyFromService(String productName, int quantity, String username) {
        Optional<ProductModel> optional = productDao.findByName(productName);
        if (!optional.isPresent()) {
            System.out.println("Product not found!");
        } else {
            ProductModel produsDinStock = optional.get();
            ProductModel produsInCos = new ProductModel();
            produsInCos.setName(productName);
            produsInCos.setQuantity(quantity);
            Optional<UserModel> userOptional = userDao.findByName(username);
            if (!userOptional.isPresent()) {
                System.out.println("User not found!");
            } else {
                UserModel userModel = userOptional.get();
                int total = produsDinStock.getPrice() * quantity;
                if (produsDinStock.getQuantity() < quantity || userModel.getBalance() < total) {
                    System.out.println("In stock are only "
                            + produsDinStock.getQuantity() + " " + produsDinStock.getName() + " left and balance is: " + userModel.getBalance());
                } else {
                    cos.add(produsInCos);
                    System.out.println(username + " has bought " + quantity + " of " + productName);
                    ProductModel produsRamas = new ProductModel();
                    int cantitateVerificata = produsDinStock.getQuantity() - quantity;
                    if (cantitateVerificata >= 0)
                        produsRamas.setQuantity(cantitateVerificata);
                    produsRamas.setName(produsDinStock.getName());
                    produsRamas.setPrice(produsDinStock.getPrice());
                    produsRamas.setMaxQuantity(produsDinStock.getMaxQuantity());
                    produsRamas.setCategory(produsDinStock.getCategory());
                    productDao.remove(produsDinStock.getName());
                    productDao.add(produsRamas);
                }
            }
        }
    }

    public void buyForFile(String productName, int quantity, String username) {
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println(" CART ");
        printStream.println("------");
        Optional<ProductModel> optionalDinStock = productDao.findByName(productName);
        if (!optionalDinStock.isPresent()) {
            printStream.println(moment() + " " + "Product not found!");
        } else {
            ProductModel produsDinStock = optionalDinStock.get();
            ProductModel produsInCos = new ProductModel();
            produsInCos.setName(productName);
            produsInCos.setQuantity(quantity);
            Optional<UserModel> userOptional = userDao.findByName(username);
            if (!userOptional.isPresent()) {
                System.out.println(moment() + " " + "User not found!");
            } else {
                UserModel userModel = userOptional.get();
                int total = produsDinStock.getPrice() * quantity;
                if (produsDinStock.getQuantity() < quantity || userModel.getBalance() < total) {
                    printStream.println(moment() + " " + "In stock are only "
                            + produsDinStock.getQuantity() + " " + produsDinStock.getName() + " left and balance is: " + userModel.getBalance());
                } else {
                    cos.add(produsInCos);
                    printStream.println(moment() + " " + username + " has bought " + quantity + " of " + productName);
                    ProductModel produsRamas = new ProductModel();
                    int cantitateVerificata = produsDinStock.getQuantity() - quantity;
                    if (cantitateVerificata >= 0)
                        produsRamas.setQuantity(cantitateVerificata);
                    produsRamas.setName(produsDinStock.getName());
                    produsRamas.setPrice(produsDinStock.getPrice());
                    produsRamas.setMaxQuantity(produsDinStock.getMaxQuantity());
                    produsRamas.setCategory(produsDinStock.getCategory());
                    productDao.remove(produsDinStock.getName());
                    productDao.add(produsRamas);
                }
            }
        }
        System.setOut(endStream);
    }

    public void removeProduct(String nameProduct) {
        productDao.remove(nameProduct);
    }

    public Optional<ProductModel> findProductByName(String name) {
        List<ProductModel> list = productDao.getAll();
        return list.stream().filter(productModel -> productModel.getName().equalsIgnoreCase(name)).findFirst();
    }

    /*
    public List<ProductModel> getProduseAlphabetic() {
        return productDao.getAll().stream().sorted(
                Comparator.comparing(ProductModel::getNume))
                .collect(Collectors.toList());
    }

     */


    public void replenish(String productName, int cantitatea) {
        Optional<ProductModel> searchInStock = productDao.findByName(productName);
        if (!searchInStock.isPresent()) {
            System.out.println("Product not found!");
        }
        ProductModel productModel = searchInStock.get();
        ProductModel productReplenished = new ProductModel();
        int nouaCantitate = productModel.getQuantity() + cantitatea;
        if (nouaCantitate > productModel.getMaxQuantity()) {
            System.out.println("Max quantity for this product is: " + productModel.getMaxQuantity());
        } else {
            productReplenished.setQuantity(productModel.getQuantity() + cantitatea);
            productReplenished.setName(productModel.getName());
            productReplenished.setPrice(productModel.getPrice());
            productReplenished.setMaxQuantity(productModel.getMaxQuantity());
            productReplenished.setCategory(productModel.getCategory());
            productDao.remove(productModel.getName());
            productDao.add(productReplenished);
            System.out.println(productReplenished.getName() + " replenished with " + cantitatea);

        }
    }


    public void replenishToFile(String productName, int cantitatea) {
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println("REPLENISH");
        printStream.println("---------");
        Optional<ProductModel> searchInStock = productDao.findByName(productName);
        if (!searchInStock.isPresent()) {
            printStream.println(SwitchToFileUI.moment() + " " + "Product not found!");
        }
        ProductModel productModel = searchInStock.get();
        ProductModel productReplenished = new ProductModel();
        int nouaCantitate = productModel.getQuantity() + cantitatea;
        if (nouaCantitate > productModel.getMaxQuantity()) {
            printStream.println(moment() + " " + "Max quantity for this product is: " + productModel.getMaxQuantity());
        } else {
            productReplenished.setQuantity(productModel.getQuantity() + cantitatea);
            productReplenished.setName(productModel.getName());
            productReplenished.setPrice(productModel.getPrice());
            productReplenished.setMaxQuantity(productModel.getMaxQuantity());
            productReplenished.setCategory(productModel.getCategory());
            productDao.remove(productModel.getName());
            productDao.add(productReplenished);
            printStream.println(moment() + " " + productReplenished.getName() + " replenished with " + cantitatea);

        }
        System.setOut(endStream);
    }


    public List<ProductModel> getProductByCategoryStream(String name) {
        List<ProductModel> productModels = productDao.getAll();
        return productModels.stream().filter(productModel -> productModel.getCategory().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

    }

}
