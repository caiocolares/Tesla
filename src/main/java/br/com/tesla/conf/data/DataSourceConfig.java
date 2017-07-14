package br.com.tesla.conf.data;

import java.util.HashMap;

import javax.sql.DataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"br.com.tesla.repository",
									 "br.com.tesla.survey.repository",
									 "br.com.tesla.auth.repository",
									 "br.com.tesla.financial.repository"},
					   entityManagerFactoryRef = "entityManager", 
					   transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig {
	
	@Bean
	@Primary
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		return hibernateJpaVendorAdapter;
	}

	@Autowired
	private DataSourceProperties properties;
	
	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
	@Primary
	public DataSource dataSource() {
		PGXADataSource ds = new PGXADataSource();
		ds.setUser(properties.getUser());
		ds.setPassword(properties.getPassword());
		ds.setServerName(properties.getServer());
		ds.setDatabaseName(properties.getDatabase());
		
		
		AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setXaDataSourceClassName(PGXADataSource.class.getName());
		dataSource.setXaDataSource(ds);
		dataSource.setUniqueResourceName("xads");
		dataSource.setPoolSize(10);
		dataSource.setMaxPoolSize(100);
		dataSource.getXaProperties().put("com.atomikos.icatch.output_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");
		dataSource.getXaProperties().put("com.atomikos.icatch.log_base_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");

		return dataSource;
	}

	@Bean(name = "entityManager")
	@DependsOn("transactionManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("com.atomikos.icatch.output_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");
		properties.put("com.atomikos.icatch.log_base_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter());
		entityManager.setPackagesToScan("br.com.tesla.core.model.entities",
										"br.com.tesla.survey.model.entities",
										"br.com.tesla.bank.model",
										"br.com.tesla.material.model.entities",
										"br.com.tesla.manufacture.model.entities",
										"br.com.tesla.financial.model.entities",
										"br.com.tesla.auth.model.entities");
		entityManager.setPersistenceUnitName("persistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
