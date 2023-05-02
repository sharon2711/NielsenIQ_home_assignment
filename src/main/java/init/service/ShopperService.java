package init.service;

import init.model.ShopperPersonalizedDataRequest;
import init.model.ShopperRequest;

public interface ShopperService {
    void createShopper(ShopperRequest shopperRequest) throws Exception;
    void updateShopperPersonalizedData(ShopperPersonalizedDataRequest shopperPersonalizedDataRequest) throws Exception;
}
