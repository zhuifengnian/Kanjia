package com.kanjia.basic;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

//    @Autowired
//    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //用户编号
//        User user = userService.findUserByCode(principals.toString());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(user.getId()));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = ((UsernamePasswordToken) token);
        String code = usernamePasswordToken.getUsername();

//        User user = userService.findUserByCode(code);

//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }

        String credentials = new String(usernamePasswordToken.getPassword());

//        if(!user.getPassword().equals(credentials)){
//            throw new CredentialsException();
//        }

//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getCode(), //用户名
//                user.getPassword(), //密码
////                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
        );
        return authenticationInfo;
    }
}