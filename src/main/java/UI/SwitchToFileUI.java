package UI;

import model.CategoryModel;
import model.ProductModel;
import service.CategoryService;
import service.ProductService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SwitchToFileUI {
    Scanner scanner = new Scanner(System.in);
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private ProductUI productUI = new ProductUI();
    private CategoryUI categoryUI = new CategoryUI();



    public static PrintStream printStream;
    public static PrintStream endStream = System.out;

    {
        try {
            printStream = new PrintStream(new File("C:\\Users\\Aym\\IdeaProjects\\SimpleShopSystem\\output_file.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String moment(){
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss,ms]");
        Date resultdate = new Date(yourmilliseconds);
        String moment = sdf.format(resultdate);
        return moment;
    }


    public void startApp() {
        System.out.println("DISPLAY_MODE SWITCHED");
        String option = "";

        while (!option.equalsIgnoreCase("exit")) {
            option = scanner.nextLine();
            if (option.equalsIgnoreCase("help")) {
                System.out.println("------------------");
                System.out.println("1. PRINT PRODUCTS CATEGORY");
                System.out.println("2. PRINT PRODUCTS ALL");
                System.out.println("3. PRINT PRODUCT NAME");
                System.out.println("4. PRINT CATEGORIES");
                System.out.println("5. BUY");
                System.out.println("6. REPLENISH");
                System.out.println("7. ADD NEW CATEGORY");
                System.out.println("8. ADD NEW PRODUCT");
                System.out.println("9. REMOVE PRODUCT");
            } else if (option.equalsIgnoreCase("PRINT PRODUCTS CATEGORY")) {
                printProductsByCategory();
            } else if (option.equalsIgnoreCase("PRINT PRODUCTS ALL")) {
                printProductsAll();
            } else if (option.equalsIgnoreCase("PRINT PRODUCT NAME")) {
                printProductByName();
            } else if (option.equalsIgnoreCase("PRINT CATEGORIES")) {
                printCategories();
            } else if (option.equalsIgnoreCase("BUY")) {
                buy();
            } else if (option.equalsIgnoreCase("REPLENISH")) {
                replenishStock();
            } else if (option.equalsIgnoreCase("ADD NEW CATEGORY")) {
                addCategory();
            } else if (option.equalsIgnoreCase("ADD NEW PRODUCT")) {
                addProduct();
            } else if (option.equalsIgnoreCase("REMOVE PRODUCT")) {
                removeProduct();
            }
        }
    }

    private void printProductsByCategory() {

        System.out.println("Type Category:");
        String category = scanner.nextLine();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(category);
        AtomicInteger contor = new AtomicInteger(1);

        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        if (!optional.isPresent()) {
            printStream.println("Category not found!");
        } else {
            printStream.println("PRINT PRODUCTS CATEGORY" + " " + category);
            printStream.println("-----------------------");
            List<ProductModel> list = productService.getProductByCategoryStream(category);
            list.forEach(productModel -> printStream.println(moment() + " " +
                    contor.getAndIncrement() +
                    " " + productModel.getName()));
        }
        System.setOut(endStream);
    }

    private void printProductsAll() {
        long yourmilliseconds1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss,ms]");
        Date resultdate1 = new Date(yourmilliseconds1);
        String moment1 = sdf.format(resultdate1);


        List<ProductModel> productModelList = productService.getProducts();
        AtomicInteger contor = new AtomicInteger(1);
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println("PRINT PRODUCTS ALL");
        printStream.println("------------------");
        productModelList.forEach(productModel -> {

            printStream.println(moment() + " "
                    + contor.getAndIncrement() + " " + productModel.getName() + " "
                    + productModel.getQuantity() + " "
                    + productModel.getCategory().getName() + " "
                    + productModel.getPrice());
        });
        System.setOut(endStream);
    }

    private void printProductByName() {
        System.out.println("Type name of the product:");
        String productName = scanner.nextLine();
        Optional<ProductModel> optional = productService.findProductByName(productName);
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println("PRINT PRODUCT NAME");
        printStream.println("------------------");
        if (!optional.isPresent()) {
            printStream.println("Product not found!");
        } else {
            printStream.println("PRINT PRODUCT NAME");
            printStream.println("-----------------------");
            ProductModel found = optional.get();
            printStream.println(moment() + " " + found.getName() + " "
                    + found.getQuantity() + " "
                    + found.getPrice());
        }
        System.setOut(endStream);
    }


    private void printCategories() {

        List<CategoryModel> categoryModelList = categoryService.getAllCategories();
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println("PRINT CATEGORIES");
        printStream.println("------------------");
        printStream.print(moment());
        categoryModelList.forEach(category -> {
            printStream.print(category.getName() + ",");

        });
        printStream.println();
        System.setOut(endStream);
    }


    public void buy() {
        // productUI.printProducts();
        System.out.println("Product name:");
        String productName = scanner.nextLine();
        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name of client:");
        String username = scanner.nextLine();
        productService.buyForFile(productName, quantity, username);
    }


    private void replenishStock() {

        System.out.println("Name of product:");
        List<ProductModel> productModelList = productService.getProducts();
        productModelList.forEach(productModel ->
                System.out.println(productModel.getName()));
        String productName = scanner.nextLine();
        System.out.println("Quantity:");
        int cantitateaPrimita = scanner.nextInt();
        scanner.nextLine();
        productService.replenishToFile(productName, cantitateaPrimita);
    }

    private void addCategory() {
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryName);
        if (!optional.isPresent()) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setName(categoryName);
            categoryService.addCategory(categoryModel);
            printStream.println("ADD NEW CATEGORY");
            printStream.println("----------------");
            printStream.println(moment() + " " + "Category added!");
        } else {
            printStream.println(moment() + " " + "Category already exist!");
        }
        System.setOut(endStream);
    }

    private void addProduct() {

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
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        printStream.println("ADD NEW PRODUCT");
        printStream.println("---------------");
        if (!optional.isPresent()) {
            printStream.println(moment() + " " + "Category not available!");
        } else {

            CategoryModel categoryModel = optional.get();
            ProductModel productModel = new ProductModel();
            productModel.setCategory(categoryModel);
            productModel.setName(numeProdus);
            productModel.setQuantity(cantitate);
            productModel.setPrice(pret);
            productModel.setMaxQuantity(maxQuantity);
            productService.addProduct(productModel);
            printStream.println(moment() + " " + "Product added in stock!");
        }
        System.setOut(endStream);
    }

    private void removeProduct() {
        System.out.println("Name of product:");
        String nameProdus = scanner.nextLine();
        Optional<ProductModel> optional = productService.findProductByName(nameProdus);
        System.out.println("PRINTED TO FILE");
        System.setOut(printStream);
        if (!optional.isPresent()) {
            printStream.println(moment() + " " + "Product not found!");
        } else {
            ProductModel productModel = optional.get();
            if (productModel.getQuantity() != 0) {
                printStream.println(moment() + " " + "Cannot remove " + productModel.getName() +
                        " because quantity is not zero. Quantity is " + productModel.getQuantity());
            } else
                productService.removeProduct(nameProdus);
            printStream.println(moment() + " " + "Product removed!");
        }
        System.setOut(endStream);
    }
}
