package ecjtu.zjf.takeoutapi.common;

import java.util.List;
import java.util.Map;

/**
 * 判空
 * @author zhenzijun
 *
 */
public class Empty4JUtil {

	/** 
     * 判断字符串为空 
     *  
     * @param str 字符串 
     * @return 若字符串不存在 或者 字符串去空格后为空(length为0),返回true 
     */  
    public static boolean stringIsEmpty(String str) {  
        /** 
         * isEmpty()从JDK1.6开始 
         */  
        return str == null || str.trim().isEmpty() || "null".equals(str);
    }  
    
    /** 
     * 判断包装类为空
     *  
     * @param str 包装类
     * @return 若包装类不存在返回true
     */  
    public static boolean numberIsEmpty(Number str) {
        return null == str;  
    }  
      
    /** 
     * 判断包装类不为空 
     *  
     * @param str 字符串 
     * @return 若字符串存在 并且 包装类去空格后不为空(length>0),返回true 
     */  
    public static boolean numberIsNotEmpty(Number str) {
        return !numberIsEmpty(str);  
    }
    
    /** 
     * 判断字符串不为空 
     *  
     * @param str 字符串 
     * @return 若字符串存在 并且 字符串去空格后不为空(length>0),返回true 
     */  
    public static boolean stringIsNotEmpty(String str) {  
        return !stringIsEmpty(str);  
    } 
      
    /** 
     * 判断list列表为空 
     * @param <T> 泛型 
     *  
     * @param list List列表 
     * @return 若list列表不存在 或者 list列表不包含元素,返回true 
     */  
    public static <T> boolean listIsEmpty(List<T> list) {  
        return list == null || list.isEmpty();  
    }  
      
    /** 
     * 判断list列表不为空 
     * @param <T> 泛型 
     *  
     * @param list List列表 
     * @return 若list列表存在 并且 list列表包含元素,返回true 
     */  
    public static <T> boolean listIsNotEmpty(List<T> list) {  
        return !listIsEmpty(list);  
    }  
      
    /** 
     * 判断map映射为空 
     * @param <K> 泛型 
     * @param <V> 泛型 
     *  
     * @param map Map映射 
     * @return 若map映射不存在 或者map映射未包含键-值映射关系,返回true 
     */  
    public static <K, V> boolean mapIsEmpty(Map<K, V> map) {  
        return map == null || map.isEmpty();  
    }  
      
    /** 
     * 判断map映射不为空 
     * @param <K> 泛型 
     * @param <V> 泛型 
     *  
     * @param map Map映射 
     * @return 若map映射存在 并且map映射包含键-值映射关系,返回true 
     */  
    public static <K, V> boolean mapIsNotEmpty(Map<K, V> map) {  
        return !mapIsEmpty(map);  
    }  
	
}
