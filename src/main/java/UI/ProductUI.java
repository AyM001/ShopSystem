package UI;


import model.CategoryModel;
import model.ProductModel;
import service.CategoryService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ProductUI {

    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);
    private CategoryService categoryService = new CategoryService();
    private CategoryUI categoryUI = new CategoryUI();
    private List<ProductModel> cos = new ArrayList<>();


    public void replenishStock() {
        System.out.println("REPLENISH");
        System.out.println("Name of product:");
        List<ProductModel> productModelList = productService.getProducts();
        productModelList.forEach(productModel ->
                System.out.println(productModel.getName()));
        String productName = scanner.nextLine();
        System.out.println("Quantity for replenish:");
        int cantitateaPrimita = scanner.nextInt();
        scanner.nextLine();
        productService.replenish(productName, cantitateaPrimita);
    }


    public void findProductByName() {
        System.out.println("PRINT PRODUCT NAME");
        System.out.println("------------------");
        System.out.println("Type name of the product:");
        String productName = scanner.nextLine();
        Optional<ProductModel> optional = productService.findProductByName(productName);
        if (!optional.isPresent()) {
            System.out.println("Product not found!");
        } else {
            ProductModel found = optional.get();
            System.out.println(found.getName() + " " + found.getQuantity() + " " + found.getPrice());
        }
    }

    public void printProductsByCategory() {
        System.out.println("PRINT PRODUCTS CATEGORY");
        System.out.println("-----------------------");
        System.out.println("Type Category:");
        String category = scanner.nextLine();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(category);
        if (!optional.isPresent()) {
            System.out.println("Category not found!");
        } else {
            List<ProductModel> list = productService.getProductByCategoryStream(category);
            list.forEach(productModel -> System.out.println(productModel.getName()));
        }
    }

    public void printProducts() {
        System.out.println("PRINT PRODUCTS ALL");
        System.out.println("------------------");
        List<ProductModel> productModelList = productService.getProducts();
        productModelList.forEach(productModel -> {
            System.out.println(productModel.getName() + " "
                    + productModel.getPrice() + " "
                    + productModel.getQuantity() + " "
                    + productModel.getCategory().getName());
        });
    }


    public void removeProduct() {
        System.out.println("Name of product:");
        String nameProdus = scanner.nextLine();
        Optional<ProductModel> optional = productService.findProductByName(nameProdus);
        if (!optional.isPresent()) {
            System.out.println("Product not found!");
        } else {
            ProductModel productModel = optional.get();
            if (productModel.getQuantity() != 0) {
                System.out.println("Cannot remove " + productModel.getName() +
                        " because quantity is not zero. Quantity is " + productModel.getQuantity());
            } else if (productModel.getQuantity() == 0){
                productService.removeProduct(nameProdus);
                System.out.println("Product removed!");
            }
        }
    }

    public void addProduct() {
        System.out.println("ADD NEW PRODUCT");
        System.out.println("---------------");
        System.out.println("Product name:");
        String numeProdus = scanner.nextLine();
        System.out.println("Price:");
        int pret = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Quantity:");
        int cantitate = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MaxQuantity:");
        int maxQuantity = scanner.nextInt();
        scanner.nextLine();
        categoryUI.printCategories();
        System.out.println("Type category:");
        String categoryName = scanner.nextLine();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryName);
        if (!optional.isPresent()) {
            System.out.println("Category not available!");
        } else {

            CategoryModel categoryModel = optional.get();
            ProductModel productModel = new ProductModel();
            productModel.setCategory(categoryModel);
            productModel.setName(numeProdus);
            productModel.setQuantity(cantitate);
            productModel.setPrice(pret);
            productModel.setMaxQuantity(maxQuantity);
            productService.addProduct(productModel);
            System.out.println("Product added!");
        }
    }
}
