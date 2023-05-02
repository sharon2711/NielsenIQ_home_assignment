package init.repository;

import init.model.Shopper;

public interface ShopperRepository {
    void createShopper(Shopper shopper);
    Shopper getShopperById(String shopperId);
}
