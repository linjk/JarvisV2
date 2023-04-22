package cn.linjk.jarvis.common.bean;

/**
 * @author linjk
 */
public interface Uris {
    /**
     * 通过账号密码登录，获取后续接口访问的token
     */
    String GET_TOKEN_BY_LOGIN = "/oauth/token";
    /**
     * 根据accessToken获取当前登录用户信息
     */
    String USER_CURRENT = "/user/me";
    /**
     * 根据accessToken来退出登录
     */
    String USER_LOGOUT = "/user/logout";
}
