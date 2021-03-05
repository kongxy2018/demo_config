package com.mydemo.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.mydemo.util.redis.FastjsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author Administrator
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

  @Value("${redis.host}")
  private String host;

  @Value("${redis.port}")
  private int port;

  @Value("${redis.password}")
  private String password;

  @Value("${redis.dbindex}")
  private int dbindex;

  @Value("${redis.pool.max_total}")
  private int maxTotal;

  @Value("${redis.pool.max_idle}")
  private int maxIdle;

  @Value("${redis.pool.max_wait}")
  private int maxWait;

  @Value("${redis.redisson.conn.pool.size}")
  private int rConnPoolSize;

  @Value("${redis.redisson.conn.idle.size.min}")
  private int rConnMinimumIdleSize;

  @Value("${redis.redisson.conn.idle.timeout}")
  private int rConnIdleTimeout;

  @Bean
  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
    return new StringRedisTemplate(factory);
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {

    RedisTemplate<?, ?> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);

    StringRedisSerializer serializer = new StringRedisSerializer();
    RedisSerializer<Object> jsonSerializer = new FastjsonRedisSerializer();

    template.setKeySerializer(serializer);
    template.setValueSerializer(jsonSerializer);
    template.setHashKeySerializer(serializer);
    template.setHashValueSerializer(jsonSerializer);

    template.setDefaultSerializer(jsonSerializer);
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    return template;

  }

  /**
   * @Author kongxy
   * @Description //TODO 创建redis链接工厂bean
   * @Date
   **/
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxIdle(maxIdle);
    config.setMaxTotal(maxTotal);
    config.setMaxWaitMillis(maxWait);

    JedisConnectionFactory factory = new JedisConnectionFactory(config);
    factory.setHostName(host);
    factory.setPort(port);
    //factory.setPassword(password);
    factory.setDatabase(dbindex);

    return factory;
  }
}
