package dao;

import model.CategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CategoryDao{

    public static List<CategoryModel> categoryStoring = new ArrayList<>();


    public void add(CategoryModel categoryModel) {
        categoryStoring.add(categoryModel);
    }


    public void remove(String nume) {
        categoryStoring.removeIf(categoryModel -> categoryModel.getName().equalsIgnoreCase(nume));
    }


    public Optional<CategoryModel> findByName(String name) {

        return categoryStoring.stream().filter(categoryModel -> categoryModel.getName().equalsIgnoreCase(name)).findFirst();
    }


    public List<CategoryModel> getAll() {
        return categoryStoring;
    }


}




