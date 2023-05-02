package init.service;

import init.model.Product;
import init.model.ProductRequest;
import init.model.ProductResponse;

import java.util.List;

public interface ProductService {
   void createProduct(ProductRequest productRequest) throws Exception;
   Product getProductById(String productId);
   List<ProductResponse> getProductsByShopper(ProductRequest productRequest) throws Exception;
}
