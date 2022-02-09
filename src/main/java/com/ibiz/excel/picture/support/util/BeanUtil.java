package com.ibiz.excel.picture.support.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * @author MinWeikai
 * @date 2022/2/9 9:31
 */
public class BeanUtil {

    /**
     * 反射获取对象属性，支持嵌套
     * 例如：
     * User user = new User();
     * Position position = new Position();
     * position.setName("fjjffj");
     * user.setPositionList(Lists.newArrayList(position));
     * Organization organization = new Organization();
     * organization.setName("1111111");
     * user.setOrganization(organization);
     * BaseCriteria criteria = new BaseCriteria();
     * Map<String, String> map = Maps.newHashMap();
     * map.put("bb","aaaaaaaaaaaaaaaa");
     * criteria.setStrValue(map);
     * user.setCriteria(criteria);
     * Object obj = getPropertyValue(user, "organization.name");
     * System.out.println(obj);
     * obj = getPropertyValue(user, "positionList[0].name");
     * System.out.println(obj);
     * obj = getPropertyValue(user, "criteria.strValue[bb]");
     * System.out.println(obj);
     * @param obj
     * @param key
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getPropertyValue(Object obj, String key){
        if (key.contains(".")){
            int dotMarkIndex = key.indexOf(".");
            String prefixKey = key.substring(0, dotMarkIndex);
            Object prefixObj = getSimplePropertyValue(obj, prefixKey);
            String suffixKey = key.substring(dotMarkIndex+1);
            return getPropertyValue(prefixObj, suffixKey);
        }
        return getSimplePropertyValue(obj, key);
    }

    private static Object getSimplePropertyValue(Object obj, String key){
        String prefixKey ;
        String suffixKey = null;
        Object returnValue ;
        if(key.matches("(\\w+)\\[(\\w+)\\]$")){
            int endIndex = key.indexOf("[");
            prefixKey = key.substring(0, endIndex);
            suffixKey = key.substring(endIndex+1, key.length() -1);
        }else{
            prefixKey = key;
        }
        if(obj instanceof Map){
            returnValue = ((Map) obj).get(prefixKey);
        }else {// obj instanceof Object
            PropertyDescriptor propertyDescriptor = org.springframework.beans.BeanUtils.getPropertyDescriptor(obj.getClass(), prefixKey);
            try {
                assert propertyDescriptor != null;
                returnValue = propertyDescriptor.getReadMethod().invoke(obj);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        if (StringUtils.isNotBlank(suffixKey)){
            try {
                int index = Integer.parseInt(suffixKey);//数字 -->> 数组/集合
                if(returnValue instanceof Collection){//集合转数组
                    returnValue = ((Collection) returnValue).toArray(new Object[0]);
                }
                returnValue = ((Object[])returnValue)[index];
            }catch (NumberFormatException e){//字符串 -->> 对象
                returnValue = getPropertyValue(returnValue, suffixKey);
            }
        }
        return returnValue;
    }
}
