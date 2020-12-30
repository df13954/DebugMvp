package com.debug.base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClazzUtils {
    /**
     * 解析一个类上面class信息
     *
     * @param object
     * @return
     */
    public static Class<?> analysisClazzInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    /**
     * 例如泛型在类上面,需要获取此泛型
     * public void onSucceed(string json){
     *     Gson gson = new Gson();
     *     T obj = (T)analysisClazzInfo(this);
     * }
     *
     */
}
