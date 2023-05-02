package init.model;

public class ShopperRequest {
    private String shopperId;

    public ShopperRequest(){}

    public ShopperRequest(String shopperId) {
        this.shopperId = shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public Shopper toShopper() {
        return new Shopper(null, this.shopperId);
    }
}
