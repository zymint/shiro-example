package tp.softfly.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
//		ExtraSecurityToken token = (ExtraSecurityToken) authToken;
//		String authcode = token.getAuthcode();
		if ("".equals("")) {
//			String secretKey = "secret-key-for-google-authenticator";
//			if (!Authenticator.verify_code(authcode, secretKey, System.currentTimeMillis())) {
//				throw new AuthcodeException("error");
//			}
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("admin", "admin_password_hash", ByteSource.Util.bytes("salt"), getName());
			return info;
		}
		return null;
	}

}
