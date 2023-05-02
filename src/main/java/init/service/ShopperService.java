package init.service;

import init.model.Shopper;
import init.model.ShopperPersonalizedDataRequest;
import init.model.ShopperRequest;

import java.util.List;

public interface ShopperService {
    void createShopper(ShopperRequest shopperRequest) throws Exception;
    void updateShopperPersonalizedData(ShopperPersonalizedDataRequest shopperPersonalizedDataRequest) throws Exception;
    List<Shopper> getShoppersByProduct(String productId, Integer limit) throws Exception;
}
