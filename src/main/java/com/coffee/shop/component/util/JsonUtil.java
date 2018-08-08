package com.coffee.shop.component.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author waylon
 * @date 2018/08/06
 **/
public class JsonUtil {

    /**
     * json字符串转bean
     * @param str
     * @param clazz
     * @return
     * @throws JsonMappingException
     * @throws IOException
     */
    public static Object jsonToBean(String str,Class clazz)throws JsonMappingException,IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper.readValue(str,clazz);
    }

}
