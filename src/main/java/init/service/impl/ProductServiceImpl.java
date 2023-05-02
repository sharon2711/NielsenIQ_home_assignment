package init.service.impl;

import init.model.Product;
import init.model.ProductRequest;
import init.repository.ProductRepository;
import init.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) throws Exception {
        productRepository.createProduct(productRequest.toProduct());
    }

    @Override
    public Product getProductById(String productId) {
        return null;
    }

    @Override
    public List<Product> getProductsByShopper(String shopperId, String category, String brand, Integer limit) throws Exception {
        return productRepository.getProductsByShopper(shopperId, category, brand, limit);
    }
}
