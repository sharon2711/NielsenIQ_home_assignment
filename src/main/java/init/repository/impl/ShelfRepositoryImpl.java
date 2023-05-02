package init.repository.impl;

import init.model.Shelf;
import init.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShelfRepositoryImpl implements ShelfRepository {

    private static final String SHELF_TABLE_NAME = "shelf";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createShelf(Shelf shelf) {
        String sql = "INSERT INTO " + SHELF_TABLE_NAME + " (shopper_id, product_id, relevancy_score) values (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                shelf.getShopperId(),
                shelf.getProductId(),
                shelf.getRelevanceScore()
        );
    }
}
