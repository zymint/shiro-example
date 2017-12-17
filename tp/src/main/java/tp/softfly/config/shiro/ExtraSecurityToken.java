package tp.softfly.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ExtraSecurityToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 2749090974511494304L;
	
	private String authcode;

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public ExtraSecurityToken() {
		super();
	}

	public ExtraSecurityToken(String username, char[] password, boolean rememberMe, String host, String authcode) {
		super(username, password, rememberMe, host);
		this.authcode = authcode;
	}

}
