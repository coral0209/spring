package kr.or.ddit.ioc.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceImpl;


//스프링 프레임워크에게 해당 자바 파일이 스프링 설정 파일임을 알려준다. 
@Configuration()
@PropertySource(value = {"classpath:/kr/or/ddit/config/db/dbinfo.properties"})
public class IocJavaConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName; 
	
	@Value("${jdbc.url}")
	private String url; 
	
	
	@Value("${jdbc.username}")
	private String username; 
	
	
	@Value("${jdbc.password}")
	private String password; 
	
	
	
	//메소드 : 스프링 빈으로 만들 객체를 반환하는 메소드를 생성 
	// 메소드에 @Bean 이라고 하는 어노테이션을 적용한다. 
	// @ bean 어노테이션에 별다른 옵션을 적용하지 않으면 생성된 스프링 빈의 이름은 메소드 이름으로 적용된다. 
	//그 전에 component-scan 에서 별다른 옵션을 적용하지 않으면 앞글자만 소문자 이름으로 적용되었었는데. 
	
	 //<bean id="userDao" class="kr.or.ddit.user.repository.UserDaoImpl"/>
	
	@Bean
	public UserDao userDao() {
		
		return new UserDaoImpl(); 
		
	}
	
	
	/*
	 * <bean id="userService" class="kr.or.ddit.user.service.UserServiceImpl">
	 * <property name="userDao" ref="userDao" /> </bean>
	 */
	
	
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		
		userService.setUserDao(userDao());
		return userService;
	}
	
	
	/*
	 * <bean id="userServiceCons" class="kr.or.ddit.user.service.UserServiceImpl">
	 * <constructor-arg ref="userDao"></constructor-arg>
	 * 
	 * </bean>
	 */
	
	@Bean
	public UserServiceImpl userServiceCons() {
		
		UserServiceImpl userService = new UserServiceImpl(userDao());
		return userService;
	}
	
/*	
	<bean id="userServiceProtoType" class="kr.or.ddit.user.service.UserServiceImpl" scope="prototype">
	<property name="userDao" ref="userDao" />	 
</bean>*/

	
	
	
	@Scope("prototype")
	@Bean
	public UserServiceImpl userServiceProtoType() {
		
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return userService;
	}
	
	

	 
	
	
	@Bean
	public CollectionBean CollectionBean() {
	CollectionBean collectionBean = new CollectionBean(); 
		List<String> list = new ArrayList<String>(); 
		list.add("brown");
		list.add("sally");
		list.add("cony");
	
		collectionBean.setList(list);
		return collectionBean;
	}
	
	
	/*
	 * <context:property-placeholder
	 * location="classpath:kr/or/ddit/config/db/dbinfo.properties" /> <bean
	 * id="dbConfig" class="kr.or.ddit.ioc.DbConfig"> <property
	 * name="driverClassName" value="${jdbc.driverClassName}"></property> <property
	 * name="url" value="${jdbc.url}" ></property> <property name="username"
	 * value="${jdbc.username}"></property> <property name="password"
	 * value="${jdbc.password}"></property>
	 * 
	 * </bean>
	 */
	@Bean
	public DbConfig dbConfig(){
		 DbConfig dbConfig = new DbConfig(); 
		 dbConfig.setDriverClassName(driverClassName);
		 dbConfig.setPassword(password);
		 dbConfig.setUrl(url);
		 dbConfig.setUsername(username);
		 
		 return dbConfig;
	}
	
}
