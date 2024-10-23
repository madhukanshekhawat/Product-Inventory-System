import java.util.ArrayList;
import java.util.List;

public class Product {

    private int  id;
    private String name;
    private String category;
    private double price;
    private double rating;
    private int sales;
    private int stock;

    private List<Integer> salesHistory = new ArrayList<>();

    public Product(int id, String name, String category, double price, double rating, int sales, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.sales = sales;
        this.stock = stock;
    }

    public List<Integer> getSalesHistory() {
        return salesHistory;
    }

    public void addSales(int dailySales) {
        salesHistory.add(dailySales);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", sales=" + sales +
                ", stock=" + stock +
                ", salesHistory=" + salesHistory +
                '}';
    }
}
