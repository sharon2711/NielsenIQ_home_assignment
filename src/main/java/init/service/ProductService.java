package init.service;

import init.model.Product;
import init.model.ProductRequest;

import java.util.List;

public interface ProductService {
   void createProduct(ProductRequest productRequest) throws Exception;
   Product getProductById(String productId);
   List<Product> getProductsByShopper(String shopperId, String category, String brand, Integer limit) throws Exception;
}
