package init.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    static ObjectMapper objectMapper = new ObjectMapper();

    public static String mapperObjectToString(Object obj) throws Exception  {
        try {
            return objectMapper.writer().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.info("Could not deserialize kafka message with error: " + e.getMessage());
            throw e;
        }
    }
}
