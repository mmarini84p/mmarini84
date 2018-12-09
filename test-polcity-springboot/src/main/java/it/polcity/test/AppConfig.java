package it.polcity.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author marco.marini Classe per le configurazioni del modulo
 */
@EnableTransactionManagement
@Configuration
public class AppConfig {

	@Autowired
	DataSource dataSource;

	private final static String DATASOURCE_PREIFX = "spring.datasource";

	/**
	 * Ritorna il riferimento al datasource corrente
	 * 
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = DATASOURCE_PREIFX)
	public DataSource dataSource() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	/**
	 * Ritorna il riferimento all'istanza SqlSessionManager, classe Thread-safe per
	 * le operazioni db
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionManager sqlSessionManager() throws Exception {

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sessionFactory.getObject());
		return sqlSessionManager;
	}

	/**
	 * Riferimento al TransactionManager utilizzato per il commit/rollback delle
	 * operazioni dei Service
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
