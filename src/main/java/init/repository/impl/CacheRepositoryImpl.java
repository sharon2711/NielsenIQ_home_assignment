package init.repository.impl;

import init.config.RedisDetailsConfig;
import init.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.Set;

@Repository
public class CacheRepositoryImpl implements CacheRepository {

    @Autowired
    JedisPooled jedisPool;

    @Autowired
    RedisDetailsConfig redisDetailsConfig;

    @Override
    public void insertSet(String key, String value){
        jedisPool.set(key, value);
        setExpireToKey(key);
    }

    @Override
    public String get(String key) {
        return jedisPool.get(key);
    }

    @Override
    public void delete(String key) {
        jedisPool.del(key);
    }

    @Override
    public Boolean isKeyExist(String key) {
        return jedisPool.exists(key);
    }

    @Override
    public void setExpireToKey(String key) {
        jedisPool.expire(key, redisDetailsConfig.getTtl());
    }

    @Override
    public void deleteKeysByPattern(String pattern) {
        Set<String> keys = jedisPool.keys(pattern);
        for (String key : keys) {
            delete(key);
        }
    }
}
