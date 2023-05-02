package init.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import init.model.Product;
import init.model.ProductRequest;
import init.model.ProductResponse;
import init.repository.ProductRepository;
import init.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String PRODUCT_TABLE_NAME = "product";
    private static final String SHELF_TABLE_NAME = "shelf";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createProduct(Product product) throws Exception {
        String sql = "INSERT INTO " + PRODUCT_TABLE_NAME + " (product_id, category, brand) values (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                product.getProductId(),
                product.getCategory(),
                product.getBrand()
        );
    }

    @Override
    public Product getProductById(String productId) {
        String sql = "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE product_id=?";
        try{
            Product product = jdbcTemplate.queryForObject(sql, new ProductMapper(), productId);
            return product;
        } catch (EmptyResultDataAccessException error){
            return null;
        }
    }

    @Override
    public List<Product> getProductsByShopper(String shopperId, String category, String brand, Integer limit) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + PRODUCT_TABLE_NAME + " p INNER JOIN " + SHELF_TABLE_NAME + " s ON p.product_id = s.product_id WHERE s.shopper_id=?");
        List<Object> params = new ArrayList<>();
        params.add(shopperId);
        if (category != null) {
            sql.append(" AND p.category=?");
            params.add(category);
        }
        if (brand != null) {
            sql.append(" AND p.brand=?");
            params.add(brand);
        }
        sql.append(" LIMIT ?");
        params.add(limit);
        try {
            return jdbcTemplate.query(sql.toString(), params.toArray(), new ProductMapper());
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }
}
