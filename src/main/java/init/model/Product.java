package init.model;

public class Product {
    private Long id;
    private String productId;
    private String category;
    private String brand;

    public Product(Long id, String productId, String category, String brand) {
        this.id = id;
        this.productId = productId;
        this.category = category;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }
}
