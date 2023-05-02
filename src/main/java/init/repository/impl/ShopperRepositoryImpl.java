package init.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import init.model.Shopper;
import init.repository.CacheRepository;
import init.repository.ShopperRepository;
import init.repository.mapper.ShopperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ShopperRepositoryImpl implements ShopperRepository {

    private static final String SHOPPER_TABLE_NAME = "shopper";
    private static final String SHELF_TABLE_NAME = "shelf";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CacheRepository cacheRepository;

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

    @Override
    public List<Shopper> getShoppersByProduct(String productId, Integer limit) throws Exception {

        String cacheKey = String.format("shoppers.product:%s.limit:%s", productId, limit);

        if (cacheRepository.isKeyExist(cacheKey)) {
            String cachedData = cacheRepository.get(cacheKey);
            return objectMapper.readValue(cachedData, new TypeReference<List<Shopper>>() {});
        } else {
            List<Shopper> shopperList = handleGetShoppersByProductFromDB(productId, limit);
            if (!shopperList.isEmpty()) {
                String productListJson = objectMapper.writeValueAsString(shopperList);
                cacheRepository.insertSet(cacheKey, productListJson);
            }
            return shopperList;
        }
    }

    private List<Shopper> handleGetShoppersByProductFromDB(String productId, Integer limit){
        String sql = "SELECT * FROM " + SHOPPER_TABLE_NAME + " s INNER JOIN " + SHELF_TABLE_NAME + " sh ON s.shopper_id = sh.shopper_id WHERE sh.product_id=? LIMIT ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{productId, limit}, new ShopperMapper());
        } catch (EmptyResultDataAccessException error) {
            return Collections.emptyList();
        }
    }
}
