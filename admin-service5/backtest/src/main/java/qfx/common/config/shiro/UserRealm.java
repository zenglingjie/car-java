package qfx.common.config.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import qfx.common.Const;
import qfx.model.User;
import qfx.service.UserService;
import java.util.HashSet;
import java.util.Set;
/** 身份校验核心类*/
/*    1因子审核权限
      3因子类型管理权限>
      2新建编辑因子权限>
      4管理员>*/
public class UserRealm extends AuthorizingRealm {
    static final int PRI_AUDIT = 1;
    static final int PRI_FACTOR = 2;
    static final int PRI_FACTORTYPE=4;
    static final int PRI_ADMINISTRATOR=8;
    @Autowired
    UserService userService;
    @Override
    public String getName() {
        return "UserRealm";
    }
    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        System.out.print("进入了授权类");
        String username = (String)super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.selectpw(username);
        int privalige = user.getPrivalige();
        Set<String> permisions = new HashSet<>();
        if ((privalige & PRI_AUDIT) == PRI_AUDIT) {
            permisions.add("audit");
        }
        if ((privalige & PRI_FACTOR) == PRI_FACTOR) {
            permisions.add("factor:*");
        }
        if ((privalige & PRI_FACTORTYPE) == PRI_FACTOR) {
            permisions.add("factortype:*");
        }
        if ((privalige & PRI_ADMINISTRATOR) == PRI_ADMINISTRATOR) {
            permisions.add("administrator");
        }
        authorizationInfo.addStringPermissions(permisions);
        return authorizationInfo;
    }
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //该token来自于filter中，可以在filter中重写tokern方法，使得其可以验证更多信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        System.out.println(token.isRememberMe()+"进入了验证类");
        String username = token.getUsername();
        SecurityUtils.getSubject().getSession().setAttribute("username",username);
        User user = userService.selectpw(username);
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}