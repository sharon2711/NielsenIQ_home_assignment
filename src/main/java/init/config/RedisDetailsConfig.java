package init.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("redis")
public class RedisDetailsConfig {
    private String host;
    private int port;
    private long ttl;

    public RedisDetailsConfig(String host, int port, long ttl) {
        this.host = host;
        this.port = port;
        this.ttl = ttl;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public long getTtl() {
        return ttl;
    }
}
