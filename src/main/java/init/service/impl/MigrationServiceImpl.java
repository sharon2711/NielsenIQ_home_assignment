package init.service.impl;

import init.model.ProductRequest;
import init.model.ShopperPersonalizedDataRequest;
import init.service.MigrationService;
import init.service.ProductService;
import init.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MigrationServiceImpl implements MigrationService {

    @Autowired
    ProductService productService;

    @Autowired
    ShopperService shopperService;

    @Override
    public void createProductMigration(List<ProductRequest> productRequests) throws Exception {
        for(ProductRequest productRequest : productRequests){
            productService.createProduct(productRequest);
        }
    }

    @Override
    public void createPersonalizedDataMigration(List<ShopperPersonalizedDataRequest> personalizedDataRequests) throws Exception {
        for(ShopperPersonalizedDataRequest personalizedDataRequest : personalizedDataRequests){
            shopperService.updateShopperPersonalizedData(personalizedDataRequest);
        }
    }
}
