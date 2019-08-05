package com.zb.byb.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.byb.common.Commonconst;
import com.zb.byb.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @author xieli
 * @date 2019/8/1 17:26
 * @description 过滤器
 */
@Component
@Order(30)
public class OpenIdInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(OpenIdInterceptor.class);
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appsecret}")
    private String appsecret;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/api/push") || request.getRequestURI().contains("WxService") || request.getRequestURI().startsWith("/MP_verify_")) {
            logger.info("request from weixin server!");
            return true;
        }
        String path = request.getContextPath();
        int port = request.getServerPort();
        String basePath = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + request.getServerPort()) + path + "/";
        String code = request.getParameter("code");
        String openId = HttpUtils.getCookieByName(request, Commonconst.OPEN_ID);
        logger.info("OpenId from cookies: " + openId);
        if (openId == null || openId.length() == 0) {
            if (code == null || code.length() == 0) {
                String redirectUri = basePath + "index";
                logger.info("### get code from weixin server!  redirectUri;" + redirectUri);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
            } else {
                Map<String, String> params = new HashMap<>();
                params.put("secret", appsecret);
                params.put("appid", appId);
                params.put("grant_type", "authorization_code");
                params.put("code", code);
                String result = HttpUtils.httpRequestToString("https://api.weixin.qq.com/sns/oauth2/access_token", params);
                JsonNode jsonNode = objectMapper.readTree(result);
                openId = jsonNode.get("openid").textValue();
                logger.info("openId from http request: " + openId);
                if (openId != null && openId.length() > 0) {
                    response.addCookie(new Cookie(Commonconst.OPEN_ID, openId));
                    response.sendRedirect(basePath + "#/business");
                } else {
                    response.getWriter().print("获取用户信息失败！");
                }
            }
            return false;
        }
        return true;
    }
}
