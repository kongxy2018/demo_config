package com.mydemo.util.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author gaigeshen
 * @since 08/24 2017
 */
public final class FastjsonRedisSerializer implements RedisSerializer<Object> {

  @Override
  public byte[] serialize(Object t) throws SerializationException {
    return JSON.toJSONBytes(t, SerializerFeature.WriteClassName);
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    if(bytes == null || bytes.length == 0){
        return null;
    }
    return JSON.parse(bytes);
    
  }

}
