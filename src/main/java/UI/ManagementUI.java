package UI;


import json.InputOutput;

import java.util.Scanner;

public class ManagementUI {

    private CategoryUI categoryUI = new CategoryUI();
    private ProductUI productUI =  new ProductUI();
    private ShopUI shopUI =new ShopUI();
    private SwitchToFileUI switchToFileUI = new SwitchToFileUI();
    private InputOutput inputOutput = new InputOutput();


    public void startManagementUI(){
        Scanner scanner = new Scanner(System.in);

        String option = "";

        while(!option.equalsIgnoreCase("exit")){

            option = scanner.nextLine();

            if(option.equalsIgnoreCase("help")) {
                System.out.println("------OPTIONS------");
                System.out.println("-------------------");
                System.out.println("1. PRINT PRODUCTS CATEGORY");
                System.out.println("2. PRINT PRODUCTS ALL");
                System.out.println("3. PRINT PRODUCT NAME");
                System.out.println("4. PRINT CATEGORIES");
                System.out.println("5. BUY");
                System.out.println("6. REPLENISH");
                System.out.println("7. ADD NEW CATEGORY");
                System.out.println("8. ADD NEW PRODUCT");
                System.out.println("9. REMOVE PRODUCT");
                System.out.println("10.SWITCH DISPLAY_MODE FILE");
                System.out.println("11.EXPORT JSON");
            } else if (option.equalsIgnoreCase("PRINT PRODUCTS CATEGORY")) {
                productUI.printProductsByCategory();
            } else if (option.equalsIgnoreCase("PRINT PRODUCTS ALL")) {
                productUI.printProducts();
            } else if (option.equalsIgnoreCase("PRINT PRODUCT NAME")) {
                productUI.findProductByName();
            } else if (option.equalsIgnoreCase("PRINT CATEGORIES")) {
                categoryUI.printCategories();
            } else if (option.equalsIgnoreCase("BUY")) {
                shopUI.buy();
            } else if (option.equalsIgnoreCase("REPLENISH")) {
                productUI.replenishStock();
            } else if (option.equalsIgnoreCase("ADD NEW CATEGORY")) {
                categoryUI.addCategoryUI();
            } else if (option.equalsIgnoreCase("ADD NEW PRODUCT")) {
                productUI.addProduct();
            } else if (option.equalsIgnoreCase("REMOVE PRODUCT")) {
                productUI.removeProduct();
            } else if (option.equalsIgnoreCase("SWITCH DISPLAY_MODE FILE")) {
                switchToFileUI.startApp();
            } else if (option.equalsIgnoreCase("EXPORT JSON")){
                inputOutput.javaToJSON();
            }
        }
    }
}
