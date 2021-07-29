package service;

import model.CategoryModel;
import dao.CategoryDao;

import java.util.*;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();


    public List<CategoryModel> getAllCategories() {
        return categoryDao.getAll();
    }

    public void addCategory(CategoryModel categoryModel) {
        categoryDao.add(categoryModel);

    }


    public Optional<CategoryModel> findCategoryByName(String name) {
        List<CategoryModel> myCategories = categoryDao.getAll();
        return myCategories.stream().filter(categoryModel1 -> categoryModel1.getName().equalsIgnoreCase(name)).findFirst();
    }

}
