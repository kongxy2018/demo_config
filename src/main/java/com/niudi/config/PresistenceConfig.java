package com.niudi.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * @Author kongxy
 * @Description //TODO 持久化配置   数据源配置
 * @Date
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "**.dao")
@PropertySource("classpath:jdbc.properties")
public class PresistenceConfig {

  @Autowired
  private Environment env;

  @Bean(destroyMethod = "close")
  public DataSource dataSource() throws PropertyVetoException {

    ComboPooledDataSource ds = new ComboPooledDataSource();
    ds.setDriverClass(env.getRequiredProperty("jdbc.driver-class"));
    ds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
    ds.setUser(env.getRequiredProperty("jdbc.user"));
    ds.setPassword(env.getRequiredProperty("jdbc.password"));
    ds.setInitialPoolSize(env.getRequiredProperty("jdbc.pool.init-size", Integer.class));
    ds.setMinPoolSize(env.getRequiredProperty("jdbc.pool.min-size", Integer.class));
    ds.setMaxPoolSize(env.getRequiredProperty("jdbc.pool.max-size", Integer.class));
    ds.setAcquireIncrement(env.getRequiredProperty("jdbc.pool.acquire-incr", Integer.class));
    ds.setMaxIdleTime(env.getRequiredProperty("jdbc.pool.max-idle", Integer.class));
    ds.setMaxStatements(env.getRequiredProperty("jdbc.max-stmt", Integer.class));
    ds.setIdleConnectionTestPeriod(env.getRequiredProperty("jdbc.idle-conn-test-period", Integer.class));
    return ds;
  }


  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ResourcePatternResolver rpr) throws IOException {
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

    factory.setDataSource(dataSource);

    //factory.setTypeAliasesSuperType(Model.class);
    factory.setTypeAliasesPackage("com.niudi.domain");
    factory.setMapperLocations(rpr.getResources("classpath:**/dao/**/*.xml"));

    org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
    config.setUseGeneratedKeys(true);
    config.setMapUnderscoreToCamelCase(true);

    factory.setConfiguration(config);

    return factory;
  }

}
