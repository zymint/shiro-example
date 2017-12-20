package tp.softfly.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters(); 
		filters.put("extra", extraSecurityFormFilter()); // use a custom name for your filter,but not be authc
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/semantic/**", "anon");
		
		filterChainDefinitionMap.put("/logout", "logout");
		
		filterChainDefinitionMap.put("/login", "extra"); // use your custom filter on /login route
		// filterChainDefinitionMap.put("/**", "user");
		filterChainDefinitionMap.put("/**", "authc"); // now /** authc is ok
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/hello");
		shiroFilterFactoryBean.setUnauthorizedUrl("/auth.html");
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		return shiroFilterFactoryBean;
	}

	@Bean
	public ExtraSecurityFormFilter extraSecurityFormFilter() {
		ExtraSecurityFormFilter formFilter = new ExtraSecurityFormFilter();
		return formFilter;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	
	public static final int HASHITERATIONS = 2;
	public static final String HASHALGORITHMNAME = "md5";

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(HASHALGORITHMNAME);
		hashedCredentialsMatcher.setHashIterations(HASHITERATIONS);
		return hashedCredentialsMatcher;
	}
	
	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return shiroRealm;
	}

	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(60L * 60000L);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		return sessionManager;
	}

}
