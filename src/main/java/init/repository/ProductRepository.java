package init.repository;

import init.model.Product;

import java.util.List;

public interface ProductRepository {
    void createProduct(Product product);
    Product getProductById(String productId);
    List<Product> getProductsByShopper(String shopperId, String category, String brand, Integer limit) throws Exception;
}
