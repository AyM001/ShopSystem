package model;

public class ProductModel extends Model {

    private int price;
    private int quantity;
    private int maxQuantity;

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    private CategoryModel category;


    public void setPrice(int price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public CategoryModel getCategory() {
        return category;
    }
}
