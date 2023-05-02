package init.repository.mapper;

import init.model.Shelf;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelfMapper implements RowMapper<Shelf> {

    @Override
    public Shelf mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Shelf(
                rs.getLong("id"),
                rs.getString("shopper_id"),
                rs.getString("product_id"),
                rs.getDouble("relevancy_score")
        );
    }
}
