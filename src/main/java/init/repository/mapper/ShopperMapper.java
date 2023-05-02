package init.repository.mapper;

import init.model.Shopper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopperMapper implements RowMapper<Shopper> {

    @Override
    public Shopper mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Shopper(
                rs.getLong("id"),
                rs.getString("shopper_id")
        );
    }
}
