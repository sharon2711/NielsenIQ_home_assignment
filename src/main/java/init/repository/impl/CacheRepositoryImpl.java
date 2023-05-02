package init.repository.impl;

import init.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.Set;

@Repository
public class CacheRepositoryImpl implements CacheRepository {

    @Autowired
    JedisPooled jedisPool;

    @Override
    public void insertSet(String key, String value){
        jedisPool.set(key, value);
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
    public long insertSadd(String key, String value) {
        return jedisPool.sadd(key, value);
    }

    @Override
    public Set<String> getAllSaddByKey(String key) {
        return jedisPool.smembers(key);
    }

    @Override
    public Boolean isKeyExist(String key) {
        return jedisPool.exists(key);
    }

    @Override
    public void setExpireToKey(String key) {
        jedisPool.expire(key, 30);
    }

}
