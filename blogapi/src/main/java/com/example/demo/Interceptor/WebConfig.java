//package com.example.demo.Interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration 
//public class WebConfig implements WebMvcConfigurer { 
//	@Override 
//	public void addInterceptors(InterceptorRegistry registry) { 
//
//		registry.addInterceptor(new LoginCheckInterceptor()) 
//			.order(1) // 인터셉터 체인 순서 
//			.addPathPatterns("/**") // 모든 requestURL에 대해 적용 
//			.excludePathPatterns("/" // 제외하고 싶은 whitelist 
//					, "/login/**" 
//					, "/blog/**" 
//					, "/logout" 
//					, "/css/**" 
//					, "/*.ico" 
//					, "/error"); 
//		//ExampleInterceptor
////		registry.addInterceptor(new ExampleInterceptor()) 
////			.order(2) 
////			.addPathPatterns("/**") 
////			.excludePathPatterns("/css/**" 
////					, "/*.ico" 
////					, "/error"); 
//	} 
//}
//
//
