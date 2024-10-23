import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

    // Use LinkedList to store products --- maintain insertion order
    private LinkedList<Product> productList = new LinkedList<>();

    // Use Set to track unique product categories
    private Set<String> productCategories = new HashSet<>();

    // Track stock quantity by product ID
    private Map<Integer, Integer> productStock = new HashMap<>();

    // Register a product in the inventory
    public void addProduct(Product product) {
        productList.add(product);
        productCategories.add(product.getCategory());
        productStock.put(product.getId(), product.getStock());

        System.out.println("Registered product: " + product);
    }

    // Delete a product using its id
    public void deleteProduct(int productId) {
        Optional<Product> productOpt = findProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            productList.remove(product);
            productStock.remove(productId);

            //check if the category still has other product
            boolean categoryStillUsed = productList.stream()
                    .anyMatch(p -> p.getCategory().equals(product.getCategory()));

            if (!categoryStillUsed) {
                productCategories.remove(product.getCategory());
            }

            System.out.println("Deleted product with ID: " + productId);
        } else {
            System.out.println("Product with ID " +  productId + "not found.");
        }
    }

    // Find a product by ID --- working as helper method
    private Optional<Product> findProductById(int productId) {
        return productList.stream().filter(p -> p.getId() == productId).findFirst();
    }

    // Modify stock quantity
    public void modifyStock(int productId, int newStock) {
        findProductById(productId).ifPresentOrElse(product -> {
            productStock.put(productId, newStock);
            product.setStock(newStock);
            System.out.println("Updated stock for product Id" + productId + "to " + newStock);
        }, () -> {
            System.out.println("Product with Id " + productId + "not found !");
        });
    }

    public int forecastStock(int productId, int weeks) throws ForecastingException, ForecastingException {

        // check if input is valid
        if(weeks <=0){
            throw new ForecastingException("Weeks must be grater then zero for forecasting");
        }

        //find the product by id
        Optional<Product> productOpt = findProductById(productId);

        if(productOpt.isEmpty()){
            throw new ForecastingException("Product with ID"+ productId + "not found");
        }

        Product product = productOpt.get();

        if(product.getSales() == 0){
            throw new ForecastingException("Insufficient sales data for product Id" + productId + ". ");

        }

        // calculate forecast based on average sales
        int averageWeeklySales = product.getSales() / 4; // 4 -- 4 weeks
        int forecastedStock = averageWeeklySales * weeks;

        System.out.println("Forecasted stock requirement productId " + productId + "for" + weeks + "weeks: "+ forecastedStock);

        return forecastedStock;
    }
    // Search products
    public List<Product> searchProducts(double minPrice, double maxPrice, double minRating) {
        return productList.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .filter(p -> p.getRating() >= minRating)
                .filter(p -> productStock.getOrDefault(p.getId(), 0) > 0)
                .collect(Collectors.toList());
    }

    // Update pricing
    public void updatePricingBasedOnSales(double percentage, int salesThreshold) {
        productList.stream()
                .filter(p -> p.getSales() > salesThreshold)
                .forEach(p -> p.setPrice(p.getPrice() * (1 - percentage / 100)));

        System.out.println( "Adjusted prices for products with sales " + salesThreshold);
    }

    // Analyze and log top-performing products by categories available
    public void analyzeCategoryPerformance() {
        productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .forEach((category, products) -> {
                    double totalSales = products.stream().mapToDouble(Product::getSales).sum();
                    double avgPrice = products.stream().mapToDouble(Product::getPrice).average().orElse(0.0);
                    Product topRated = products.stream()
                            .max(Comparator.comparingDouble(Product::getRating)).orElse(null);

                    System.out.println("Category:" + category + ", Total Sales: " +totalSales + "Avg Price: " + avgPrice + " Top Rated:" + topRated);

                });
    }

    // List all products --- stock levels
    public void listAllProducts() {
        productList.forEach(product -> {
            int stock = productStock.getOrDefault(product.getId(), 0);
            System.out.println("Product: " + product +" , Stock: " + stock);
        });
    }

    // Retrieve all avl categories
    public Set<String> getAllCategories() {
        return productCategories;
    }
}
