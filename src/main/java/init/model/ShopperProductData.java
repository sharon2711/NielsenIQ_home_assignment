package init.model;

public class ShopperProductData {
    private String productId;
    private double relevancyScore;

    public ShopperProductData(){}

    public ShopperProductData(String productId, double relevancyScore) {
        this.productId = productId;
        this.relevancyScore = relevancyScore;
    }

    public String getProductId() {
        return productId;
    }

    public double getRelevancyScore() {
        return relevancyScore;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setRelevancyScore(double relevancyScore) {
        this.relevancyScore = relevancyScore;
    }
}
