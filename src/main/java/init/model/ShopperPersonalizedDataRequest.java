package init.model;

import java.util.List;

public class ShopperPersonalizedDataRequest {
    private String shopperId;
    private List<ShopperProductData> shelf;

    public ShopperPersonalizedDataRequest(){}

    public ShopperPersonalizedDataRequest(String shopperId, List<ShopperProductData> shelf) {
        this.shopperId = shopperId;
        this.shelf = shelf;
    }

    public String getShopperId() {
        return shopperId;
    }

    public List<ShopperProductData> getShelf() {
        return shelf;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public void setShelf(List<ShopperProductData> shelf) {
        this.shelf = shelf;
    }
}
