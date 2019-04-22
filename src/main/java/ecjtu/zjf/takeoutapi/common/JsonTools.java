package ecjtu.zjf.takeoutapi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Map;

/**
 * JSON工具类，默认是用Jackson
 * <p>
 */
@Slf4j
public class JsonTools {
    /**
     * 同名(属性名)序列化
     */
    private static final ObjectMapper objectMapper;
    /**
     * 驼峰转下划线序列化
     */
    private static final ObjectMapper toUnderJSON = new ObjectMapper();

    static {
        objectMapper = SpringUtils.getBean(ObjectMapper.class);
        toUnderJSON.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        toUnderJSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        toUnderJSON.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 序列化
     *
     * @param data
     * @return
     */
    public static String serialize(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("serialize failure", e.getMessage());
        }

        return null;
    }

    /**
     * 反序列化
     *
     * @param dataString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String dataString, TypeReference type) {
        try {
            return objectMapper.readValue(dataString, type);
        } catch (IOException e) {
            log.error("deserialize failure", e.getMessage());
        }

        return null;
    }

    /**
     * 反序列化
     *
     * @param dataString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String dataString, Class<T> type) {
        try {
            return objectMapper.readValue(dataString, type);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("deserialize failure", e.getMessage());
        }

        return null;
    }

    /**
     * 转换数据类型
     *
     * @param data
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T convert(Object data, Class<T> type) {
        return JsonTools.deserialize(JsonTools.serialize(data), type);
    }

    /**
     * 转换数据类型
     *
     * @param data
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T convert(Object data, TypeReference type) {
        return JsonTools.deserialize(JsonTools.serialize(data), type);
    }

    /**
     * 转换数据类型，针对多层嵌套
     * @param ob
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T convertValue(Object ob, Class<T> cla) {
        return objectMapper.convertValue(ob, cla);
    }

    public static <T> T toUnderJSONToClass(Object object, Class<T> type) {
        try {
            String json = toUnderJSON.writeValueAsString(object);
            return toUnderJSON.readValue(json, type);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除分页参数并反序列化为Map
     * @param object
     * @return
     */
    public static Map<String,Object> toUnderJSONToClass(Object object) {
        try {
            String json = toUnderJSON.writeValueAsString(object);
            Map<String,Object> param = toUnderJSON.readValue(json, Map.class);
            param.remove("size");
            param.remove("current");
            return param;
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String objectToJsonStr(Object object){
        if(null == object){
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
