package init.service;

import init.model.ProductRequest;
import init.model.ShopperPersonalizedDataRequest;

import java.util.List;

public interface MigrationService {
    void createProductMigration(List<ProductRequest> productRequests) throws Exception;
    void createPersonalizedDataMigration(List<ShopperPersonalizedDataRequest> personalizedDataRequests) throws Exception;
}
