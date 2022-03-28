package Project.leaveManagement.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("Project.leaveManagement")
@PropertySource({ "classpath:persistence-mysql.properties" })
public class LeaveManagementConfig {
	@Autowired
	private Environment env;
	
	@Bean												// For DataSource...
	public DataSource myDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		dataSource.setInitialPoolSize(getIntProperty(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMaxPoolSize(getIntProperty(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMinPoolSize(getIntProperty(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxIdleTime(getIntProperty(env.getProperty("connection.pool.maxIdleTime")));
		
		return dataSource;
		
	}
	
	private int getIntProperty(String val) {
		return Integer.parseInt(val);
	}
	
	@Bean	
	public LocalSessionFactoryBean sessionFactory() {
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
				
		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
				
		return sessionFactory;
	}

	private Properties getHibernateProperties() {
		
		
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
	
	
	/*@Bean
	   public InternalResourceViewResolver viewResolver() {
	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	      resolver.setPrefix("/WEB-INF/view/");
	      resolver.setSuffix(".jsp");
	      return resolver;
	   }*/
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
}
