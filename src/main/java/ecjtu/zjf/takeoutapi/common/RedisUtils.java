package ecjtu.zjf.takeoutapi.common;

import com.bbdtek.bhtravel.commons.uitls.Empty4JUtil;
import com.bbdtek.bhtravel.commons.uitls.JsonTools;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Resource(name = "redisTemplate")
    private ValueOperations<Serializable, Object> operations;


    public List<String> getKeys(String pattern){
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            List<String> result = new ArrayList<>();
            result.addAll(keys);
            return result;
        }
    }

    /**
     * 添加map
     *
     * @param key
     * @param mapKey
     * @param value
     */
    public void hashAddOne(String key, String mapKey, Object value){
        hashOperations.put(key, mapKey, value);
    }

    /**
     * 添加所有
     *
     * @param key
     * @param values
     */
    public void hashAddAll(String key, Map<String, ?> values){
        hashOperations.putAll(key, values);
    }

    /**
     * 返回指定key对应的bean
     *
     * @param key
     * @param mapKey
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getHashValue(String key, String mapKey, TypeReference type){
        if (isKeyExistsByHash(key, mapKey)) {
            Object o = hashOperations.get(key, mapKey);
            return JsonTools.convert(o, type);
        } else {
            return null;
        }
    }

    public <T> T getHashValue(String key, String mapKey, Class<T> clazz){
        if (isKeyExistsByHash(key, mapKey)) {
            Object o = hashOperations.get(key, mapKey);
            return JsonTools.convert(o, clazz);
        } else {
            return null;
        }
    }

    public <T> T getHash(String key, String mapKey, Class<T> clazz){
        if (isKeyExistsByHash(key, mapKey)) {
            Object o = hashOperations.get(key, mapKey);
            return JsonTools.convertValue(o, clazz);
        } else {
            return null;
        }
    }

    /**
     * 删除hash
     *
     * @param key
     * @param mapKey
     */
    public void hashDelete(String key, String... mapKey){
        hashOperations.delete(key, mapKey);
    }

    /**
     * 判断是否有key
     *
     * @param key
     * @param mapKey
     * @return
     */
    public boolean isKeyExistsByHash(String key, String mapKey){
        return hashOperations.hasKey(key, mapKey);
    }

    public boolean isKeyExists(String key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 清空redis
     *
     * @param key
     */
    public void empty(String key){
        if (isKeyExists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 获取所有bean
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> getAllhash(String key, TypeReference type){
        List<Object> values = hashOperations.values(key);
        if (values != null && values.size() > 0) {
            return JsonTools.convert(values, type);
        } else {
            return Collections.emptyList();
        }
    }


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 从左侧加入
     *
     * @param key
     * @param value
     */
    public void listAddOneLeft(String key, Object value){
        listOperations.leftPush(key, value);
    }


    public void listAddAllLeft(String key, Collection collection){
        listOperations.leftPushAll(key, collection);
    }

    /**
     * 从右侧加入
     *
     * @param key
     * @param value
     */
    public void listAddOneRight(String key, Object value){
        listOperations.rightPush(key, value);
    }

    public void listAddAllRight(String key, Collection collection){
        listOperations.rightPushAll(key, collection);
    }

    /**
     * 获取所有
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> getListAll(String key, TypeReference type){
        return getList(key, 0, -1, type);
    }

    /**
     * @param key
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @param type
     * @param <T>
     * @return
     */

    public <T> List<T> getList(String key, long start, long end, TypeReference type){
        List<Object> range = listOperations.range(key, start, end);
        if (range != null && range.size() > 0) {
            return JsonTools.convert(range, type);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public void listRemoveOne(String key, Object value){
        listOperations.remove(key, 0, value);
    }

    /**
     * 写入key value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value){
        boolean result = false;
        try {
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入key value设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit){
        boolean result = false;
        try {
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置超时时间毫秒
     *
     * @param key
     * @param expireTime
     */
    public void setExpireMillisenconds(String key, Long expireTime){
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置超时时间秒
     *
     * @param key
     * @param expireTime
     */
    public void setExpireSenconds(String key, Long expireTime){
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 写入key value设置固定时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setAt(final String key, Object value, Date date){
        boolean result = false;
        try {
            operations.set(key, value);
            redisTemplate.expireAt(key, date);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys){
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public Boolean remove(final String key){
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    /**
     * 判断是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取
     *
     * @param key
     * @return
     */
    public Object get(final String key){
        Object result;
        result = operations.get(key);
        return result;
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public <T> T get(String key, Class<T> cls){
        try {
            Object val = operations.get(key);
            if (val == null) {
                return null;
            }
            return JsonTools.convertValue(val, cls);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean setnx(String key, Object val){
        return operations.setIfAbsent(key, val);
    }

    public Object getset(String key, Object val){
        return operations.getAndSet(key, val);
    }

    private boolean setIfAbsent(String key, String value){
        Boolean absent = operations.setIfAbsent(key, value);
        return null == absent ? false : absent;
    }

    /**
     * 获得锁
     * 使用while循环不断尝试锁的获取，并至多尝试timeout时长，在timeout时间内若成功则同时设置key的过期时间并返回true，否则返回false
     *
     * @param key     锁的key
     * @param expire  锁的超时时间
     * @param timeout 获取超时时间 毫秒
     * @return 是否成功
     */
    public boolean lockWithTimeout(String key, long expire, long timeout){
        if (Empty4JUtil.stringIsEmpty(key)) {
            return false;
        }
        Long waitMax = timeout;
        Long waitAlready = 0L;
        String random = UUID.randomUUID().toString();
        Long milliseconds = 10L;

        try {
            while (!setIfAbsent(key, random) && (waitAlready < waitMax)) {
                Thread.sleep(milliseconds);
                waitAlready += milliseconds;
            }
            if (waitAlready < waitMax) {
                redisTemplate.expire(key, expire, TimeUnit.SECONDS);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * @param key 锁的key
     * @return 释放结果
     */
    public boolean unlock(String key){
        Boolean deleteRes = redisTemplate.delete(key);
        return null == deleteRes ? false : deleteRes;
    }

    /**
     * 增加步长
     *
     * @param key
     * @return
     */
    public Long incrementByKey(String key){
        return operations.increment(key, 1);
    }

    /**
     * 增加指定长度
     *
     * @param key
     * @param value
     * @return
     */
    public long incrementByKey(String key, int value){
        if (redisTemplate.hasKey(key)) {
            return operations.increment(key, value);
        } else {
            operations.set(key, value);
            return value;
        }
    }


    /**
     * 增加指定长度
     *
     * @param key
     * @param value
     * @return
     */
    public Long decreaseByKey(String key, int value){
        if (redisTemplate.hasKey(key)) {
            return operations.increment(key, -value);
        }
        return null;
    }

    public Long decreaseByKey(String key){
        if (redisTemplate.hasKey(key)) {
            return operations.increment(key, -1);
        }
        return null;
    }
}
