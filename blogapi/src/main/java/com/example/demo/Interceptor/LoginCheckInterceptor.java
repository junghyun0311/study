//package com.example.demo.Interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import javax.servlet.http.Cookie;
//
//import org.springframework.web.util.WebUtils;
//
//import com.example.demo.VO.UserVO;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//   	@Override
//   	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//   		String requestURI = request.getRequestURI();
//   		HttpSession session = request.getSession(false);
//   		Cookie tokenCookie = WebUtils.getCookie(request, "uToken");
//   		UserVO userVO = (UserVO)WebUtils.getSessionAttribute(request, "uInfo");
//   		if (userVO == null) {
//   			//log.info("미인증 사용자 요청");
//   			//로그인으로 redirect
//   			response.sendRedirect("/login?redirectURL=" + requestURI);
//   			return false;
//  		 }
//          return true;
//  	 }
//  }
