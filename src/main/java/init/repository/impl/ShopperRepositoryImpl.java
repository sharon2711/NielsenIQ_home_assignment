package init.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import init.model.Shopper;
import init.repository.ShopperRepository;
import init.repository.mapper.ShopperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShopperRepositoryImpl implements ShopperRepository {

    private static final String SHOPPER_TABLE_NAME = "shopper";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createShopper(Shopper shopper) {
        String sql = "INSERT INTO " + SHOPPER_TABLE_NAME + " (shopper_id) values (?)";
        jdbcTemplate.update(
                sql,
                shopper.getShopperId()
        );
    }

    @Override
    public Shopper getShopperById(String shopperId) {
        String sql = "SELECT * FROM " + SHOPPER_TABLE_NAME + " WHERE shopper_id=?";
        try{
            Shopper shopper = jdbcTemplate.queryForObject(sql, new ShopperMapper(), shopperId);
            return shopper;
        } catch (EmptyResultDataAccessException error){
            return null;
        }
    }
}
