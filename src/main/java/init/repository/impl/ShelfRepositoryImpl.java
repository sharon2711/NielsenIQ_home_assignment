package init.repository.impl;

import init.model.Shelf;
import init.repository.CacheRepository;
import init.repository.ShelfRepository;
import init.repository.mapper.ShelfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShelfRepositoryImpl implements ShelfRepository {

    private static final String SHELF_TABLE_NAME = "shelf";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CacheRepository cacheRepository;

    @Override
    public void createShelf(Shelf shelf) {
        String sql = "INSERT INTO " + SHELF_TABLE_NAME + " (shopper_id, product_id, relevancy_score) values (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                shelf.getShopperId(),
                shelf.getProductId(),
                shelf.getRelevanceScore()
        );

        // Evict relevant cache keys
        String shopperPattern = String.format("products.shopper:%s.*", shelf.getShopperId());
        cacheRepository.deleteKeysByPattern(shopperPattern);
        String productPattern = String.format("shoppers.product:%s.*", shelf.getProductId());
        cacheRepository.deleteKeysByPattern(productPattern);
    }

    @Override
    public Shelf getShelfByShopperIdAndProductId(String shopperId, String productId) {
        String sql = "SELECT * FROM " + SHELF_TABLE_NAME + " WHERE shopper_id=? AND product_id=?";
        try {
            Shelf shelf = jdbcTemplate.queryForObject(sql, new ShelfMapper(), shopperId, productId);
            return shelf;
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }
}
