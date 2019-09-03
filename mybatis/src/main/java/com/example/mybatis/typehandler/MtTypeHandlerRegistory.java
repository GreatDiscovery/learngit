package com.example.mybatis.typehandler;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gavin
 * @date 2019/3/29 16:42
 */
public class MtTypeHandlerRegistory {
    public static Map<Type, MtTypeHandler> TYPE_HANDLER_MAP = new ConcurrentHashMap<>();

    public void regist(MtTypeHandler typeHandler) {
        TYPE_HANDLER_MAP.put(typeHandler.getJavaType(), typeHandler);
    }
}
