package init.repository;

import java.util.Set;

public interface CacheRepository {
    void insertSet(String key, String value);
    String get(String key);
    void delete(String key);
    Boolean isKeyExist(String key);
    void setExpireToKey(String key);
    void deleteKeysByPattern(String pattern);
}
