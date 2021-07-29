package UI;


import service.ProductService;

import java.util.Scanner;

public class ShopUI {


    private ProductService productService = new ProductService();
    private ProductUI productUI = new ProductUI();

    public void buy() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("BUY");
       // productUI.printProducts();
        System.out.println("Name of product:");
        String productName = scanner.nextLine();
        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name of client:");
        String username = scanner.nextLine();
        productService.buyFromService(productName, quantity, username);
    }

}

