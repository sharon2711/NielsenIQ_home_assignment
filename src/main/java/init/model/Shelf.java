package init.model;

public class Shelf {
    private Long id;
    private String shopperId;
    private String productId;
    private Double relevancyScore;

    public Shelf(){}

    public Shelf(Long id, String shopperId, String productId, Double relevanceScore) {
        this.id = id;
        this.shopperId = shopperId;
        this.productId = productId;
        this.relevancyScore = relevanceScore;
    }

    public Long getId() {
        return id;
    }

    public String getShopperId() {
        return shopperId;
    }

    public String getProductId() {
        return productId;
    }

    public Double getRelevanceScore() {
        return relevancyScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setRelevanceScore(Double relevanceScore) {
        this.relevancyScore = relevanceScore;
    }
}
