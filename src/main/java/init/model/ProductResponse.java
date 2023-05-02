package init.model;

public class ProductResponse {
    private String productId;
    private String category;
    private String brand;

    public ProductResponse(String productId, String category, String brand) {
        this.productId = productId;
        this.category = category;
        this.brand = brand;
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
