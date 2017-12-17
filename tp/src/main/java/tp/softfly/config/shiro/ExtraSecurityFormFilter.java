package tp.softfly.config.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class ExtraSecurityFormFilter extends FormAuthenticationFilter {

	protected String getAuthcode(ServletRequest request) {
		return WebUtils.getCleanParam(request, "authcode");
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String authcode = getAuthcode(request);
		return new ExtraSecurityToken(username, password.toCharArray(), rememberMe, host, authcode);
	}

}
