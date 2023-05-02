package init.repository;

import init.model.Product;
import init.model.ProductRequest;
import init.model.ProductResponse;

import java.util.List;

public interface ProductRepository {
    void createProduct(Product product) throws Exception;
    public Product getProductById(String productId);
    List<ProductResponse> getProductsByShopper(ProductRequest productRequest) throws Exception;
}
