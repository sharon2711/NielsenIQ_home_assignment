package init.repository;

import init.model.Shopper;

import java.util.List;

public interface ShopperRepository {
    void createShopper(Shopper shopper);
    Shopper getShopperById(String shopperId);
    List<Shopper> getShoppersByProduct(String productId, Integer limit) throws Exception;
}
