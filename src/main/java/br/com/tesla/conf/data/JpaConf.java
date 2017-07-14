package br.com.tesla.conf.data;

import java.util.Properties;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;


@Configuration
@ComponentScan


@EnableConfigurationProperties(AtomikosProperties.class)
@ConditionalOnClass({ JtaTransactionManager.class, UserTransactionManager.class })
@ConditionalOnMissingBean(PlatformTransactionManager.class)

@EnableTransactionManagement
@EnableSpringDataWebSupport
public class JpaConf {
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	@Bean(name="userTransactionImp", initMethod = "init", destroyMethod = "shutdownForce")
	@ConditionalOnMissingBean(UserTransactionService.class)
	public UserTransactionServiceImp userTransactionService(AtomikosProperties atomikosProperties) {
		Properties properties = new Properties();
		properties.putAll(atomikosProperties.asProperties());
		properties.setProperty("com.atomikos.icatch.log_base_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");
		properties.setProperty("com.atomikos.icatch.output_dir", "/home/ratisco/appservers/apache-tomcat-8.0.23/logs/");
		return new UserTransactionServiceImp(properties);
	}

	@Bean(name = "userTransaction")
	@DependsOn("userTransactionImp")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
	public TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		
		AtomikosJtaPlatform.transactionManager = userTransactionManager;

		return userTransactionManager;
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
	public PlatformTransactionManager transactionManager(UserTransactionService userTransactionService) throws Throwable {
		UserTransaction userTransaction = userTransaction();

		AtomikosJtaPlatform.transaction = userTransaction;

		TransactionManager atomikosTransactionManager = atomikosTransactionManager();
		return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
	}
}