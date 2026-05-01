class Product {
    private String productName;
    private double price;
    private String category;

    Product() {
        productName = "Unknown";
        price = 0.0;
        category = "Unknown";
    }

    Product(String productName, double price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    void setProductName(String productName) {
        this.productName = productName;
    }

    String getProductName() {
        return productName;
    }

    void setPrice(double price) {
        this.price = price;
    }

    double getPrice() {
        return price;
    }

    void setCategory(String category) {
        this.category = category;
    }

    String getCategory() {
        return category;
    }

    void showProductInfo() {
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
    }
}

class DiscountedProduct extends Product {
    private double discountPercent;
    private int stock;

    DiscountedProduct() {
        super();
        discountPercent = 0.0;
        stock = 0;
    }

    DiscountedProduct(String productName, double price, String category, double discountPercent, int stock) {
        super(productName, price, category);
        this.discountPercent = discountPercent;
        this.stock = stock;
    }

    void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    double getDiscountPercent() {
        return discountPercent;
    }

    void setStock(int stock) {
        this.stock = stock;
    }

    int getStock() {
        return stock;
    }

    double calculateDiscountPrice() {
        return getPrice() - (getPrice() * discountPercent / 100);
    }

    void showDiscountedProductInfo() {
        showProductInfo();
        System.out.println("Discount Percent: " + discountPercent);
        System.out.println("Stock: " + stock);
        System.out.println("Discounted Price: " + calculateDiscountPrice());
    }
}

public class Start {
    public static void main(String[] args) {
        DiscountedProduct p1 = new DiscountedProduct();
        DiscountedProduct p2 = new DiscountedProduct("Smartphone", 50000, "Electronics", 10, 50);

        p1.setProductName("Laptop");
        p1.setPrice(80000);
        p1.setCategory("Gadgets");
        p1.setDiscountPercent(15);
        p1.setStock(20);

        System.out.println("----- Product 1 Info -----");
        p1.showDiscountedProductInfo();

        System.out.println();

        System.out.println("----- Product 2 Info -----");
        p2.showDiscountedProductInfo();

        System.out.println();

        System.out.println("Using Getters:");
        System.out.println("Product 1 Name: " + p1.getProductName());
        System.out.println("Product 1 Price: " + p1.getPrice());
        System.out.println("Product 1 Discount Price: " + p1.calculateDiscountPrice());
    }
}