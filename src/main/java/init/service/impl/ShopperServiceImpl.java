package init.service.impl;

import init.model.*;
import init.repository.ProductRepository;
import init.repository.ShelfRepository;
import init.repository.ShopperRepository;
import init.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Shopper> getShoppersByProduct(String productId, Integer limit) throws Exception {
        return shopperRepository.getShoppersByProduct(productId, limit);
    }

    private void handleCreateShelf(ShopperPersonalizedDataRequest shopperPersonalizedDataRequest) throws Exception {
        for(ShopperProductData shopperProductData : shopperPersonalizedDataRequest.getShelf()) {
            Product product = productRepository.getProductById(shopperProductData.getProductId());
            if (product != null) {
                Shelf existingShelf = shelfRepository.getShelfByShopperIdAndProductId(shopperPersonalizedDataRequest.getShopperId(), product.getProductId());
                if(existingShelf == null){
                    Shelf shelfToCreate = new Shelf(null, shopperPersonalizedDataRequest.getShopperId(), product.getProductId(), shopperProductData.getRelevancyScore());
                    shelfRepository.createShelf(shelfToCreate);
                } else {
                    throw new Exception("Provided shelf with shopper id: " + shopperPersonalizedDataRequest.getShopperId() + " and product id: " + product.getProductId() + " is already exists");
                }
            } else {
                throw new Exception("Provided product id: " + shopperProductData.getProductId() + " not exists");
            }
        }
    }
}
