package init.service.impl;

import init.model.*;
import init.repository.ProductRepository;
import init.repository.ShelfRepository;
import init.repository.ShopperRepository;
import init.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopperServiceImpl implements ShopperService {

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public void createShopper(ShopperRequest shopperRequest) {
        shopperRepository.createShopper(shopperRequest.toShopper());
    }

    @Override
    public void updateShopperPersonalizedData(ShopperPersonalizedDataRequest shopperPersonalizedDataRequest) throws Exception {
        Shopper shopper = shopperRepository.getShopperById(shopperPersonalizedDataRequest.getShopperId());
        if(shopper != null){
            handleCreateShelf(shopperPersonalizedDataRequest);
        } else {
            ShopperRequest shopperRequest = new ShopperRequest(shopperPersonalizedDataRequest.getShopperId());
            shopperRepository.createShopper(shopperRequest.toShopper());
            handleCreateShelf(shopperPersonalizedDataRequest);
        }
    }

    private void handleCreateShelf(ShopperPersonalizedDataRequest shopperPersonalizedDataRequest) throws Exception {
        for(ShopperProductData shopperProductData : shopperPersonalizedDataRequest.getShelf()) {
            Product product = productRepository.getProductById(shopperProductData.getProductId());
            if (product != null) {
                Shelf shelfToCreate = new Shelf(null, shopperPersonalizedDataRequest.getShopperId(), product.getProductId(), shopperProductData.getRelevancyScore());
                shelfRepository.createShelf(shelfToCreate);
            } else {
                throw new Exception("Provided product id not exists");
            }
        }
    }
}
