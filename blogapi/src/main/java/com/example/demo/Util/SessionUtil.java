package com.example.demo.Util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.VO.UserVO;

public class SessionUtil {
	
    public static Object getAttribute(String name) {
        return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String name, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String name) {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static String getSessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
    
    public static HttpServletRequest getRequset() {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	return request;
    }
    
    public static String getServerIpPort() {
    	String addr = getRequset().getLocalAddr()+":"+getRequset().getLocalPort();
    	return addr;
    }
    
    /**
     * request에 있는 사용자정보(uInfo)를 가져오는 매써드
     * AjaxTokenChkInterceptor 에서 토큰 체크 후 uInfo를 request에 넣어서 전달
     * 
     * @methodName    : getUserInfo
     * @author        : Kenny Kim
     * @date          : 2021.02.04
     * @return UserVO
     */
    public static UserVO getUserInfo() {
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    	return (UserVO) request.getAttribute("uInfo");
    }
}
