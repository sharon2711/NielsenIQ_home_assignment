package init.repository;

import java.util.Set;

public interface CacheRepository {
    void insertSet(String key, String value);
    String get(String key);
    void delete(String key);
    long insertSadd(String key, String value);
    Set<String> getAllSaddByKey(String key);
    Boolean isKeyExist(String key);
    void setExpireToKey(String key);
}
