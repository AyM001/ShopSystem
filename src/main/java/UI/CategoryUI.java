package UI;


import model.CategoryModel;
import service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CategoryUI {

    private CategoryService categoryService = new CategoryService();

    private Scanner scanner = new Scanner(System.in);

    public void startCategoryUI() {
        int optiune = -1;
        while (optiune != 0) {
            categoryMenuUI();
            optiune = scanner.nextInt();
            scanner.nextLine();
            if (optiune == 1) {
                addCategoryUI();
            } else if (optiune == 2) {
                printCategories();
            }

        }
    }

    private void categoryMenuUI() {
        System.out.println("Category management");
        System.out.println("1.Add category");
        System.out.println("2.View categories");
        System.out.println("0.Exit");
    }

    public void addCategoryUI() {
        System.out.println("ADD NEW CATEGORY");
        System.out.println("----------------");
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        Optional<CategoryModel> optional = categoryService.findCategoryByName(categoryName);
        if (!optional.isPresent()) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setName(categoryName);
            categoryService.addCategory(categoryModel);
            System.out.println("Category added!");
        } else
            System.out.println("Category already exist!");

    }


    public void printCategories() {
        System.out.println("PRINT CATEGORIES");
        System.out.println("----------------");
        List<CategoryModel> categoryModelList = categoryService.getAllCategories();
        categoryModelList.forEach(category -> System.out.print(category.getName() + ","));
        System.out.println();

    }

}
