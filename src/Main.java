import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Create an instance of Inventory
        Inventory inventory = new Inventory();

        // Create sample products
        Product product1 = new Product(1, "Pen", "Stationary",10, 4.5, 10 , 50);
        Product product2 = new Product(2, "CraftPaper","Stationary" , 25, 4.0, 38, 100);
        Product product3 = new Product(3, "Book", "Books", 599, 4.9, 288, 300);
        Product product4 = new Product(4, "Phone", "Electronics", 25000, 4.5, 90, 70);

        // Register products in the inventory
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
        inventory.addProduct(product4);

        //forecast stock
        try {
            inventory.forecastStock(1, 6);
        } catch (ForecastiongException e) {
            System.out.println("Forecasting error : "+ e.getMessage());
        }

        // List all products
        System.out.println("Listing all products:");
        inventory.listAllProducts();

        // Modify stock for a product
        inventory.modifyStock(2, 15);
        inventory.listAllProducts();

        // Search products based on criteria
        List<Product> filteredProducts = inventory.searchProducts(1000, 90000, 4.0);
        System.out.println("Filtered Products:" + filteredProducts);

        // Apply a 10% discount to products with more than 60 sales
        inventory.updatePricingBasedOnSales(10, 60);

        // Analyze category performance
        System.out.println("Category Performance Analysis:");
        inventory.analyzeCategoryPerformance();

        // Delete a product and list products again
        inventory.deleteProduct(3);
        System.out.println("After deletion:");
        inventory.listAllProducts();

        // Retrieve and display all unique product categories
        Set<String> categories = inventory.getAllCategories();
        System.out.println("Available Categories: {0}" +  categories);
    }
}

