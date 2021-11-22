package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Security.KnsfEncUtil;
import com.example.demo.Service.TestService;
import com.example.demo.Service.UserInfoService;
import com.example.demo.VO.TestVO;

import com.example.demo.Util.SessionUtil;
//import kr.co.gshs.dt.admin.cmm.service.UserInfoService;
//import kr.co.gshs.dt.admin.cmm.vo.UserVO;
 
import com.example.demo.VO.UserVO;
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	// @Autowired private UserInfoService userInfoService;

	@Autowired private KnsfEncUtil encUtil;
	 
	//@Autowired private UserInfoService userInfoService;
	
	@Autowired
	UserInfoService userInfoService;
	
	
	@RequestMapping(value = "/test",  method = RequestMethod.GET)
	public String progressLogin(HttpServletRequest req, HttpServletResponse resp, @ModelAttribute UserVO param, Model model) throws Exception {
		//userInfoService.selectUserList(param);
		List<UserVO> testList = userInfoService.selectAllUsers();
		int test = testList.size();
		return "";
	}
	
	
	@RequestMapping(value = "/addUser.json", produces="application/json;charset=UTF-8",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, String> addUser(@RequestBody UserVO param, HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		param.setUserPwd(encUtil.shaEncrypt(param.getUserPwd()));

		//UserVO sessionInfo = SessionUtil.getUserInfo();
		//param.setCrtName(sessionInfo.getUserId());
		//param.setUptName(sessionInfo.getUserId());
		
		Map<String, String> map = new HashMap<>();
		try {
			userInfoService.insertUser(param);
			map.put("result", "Y");
		}catch (Exception e) {
			map.put("result", "N");
		}
		
		return map;
	}
	
	@RequestMapping(value = "/postLogin",method = RequestMethod.POST)
	public @ResponseBody Map<String, String> postLogin(@RequestParam Map<String, String> map3, HttpServletRequest req, HttpServletResponse resp, @ModelAttribute UserVO param, Model model) throws Exception {
		String info = encUtil.shaEncrypt(param.getUserPwd());    //패스워드 암호화
		Map<String, String> map = new HashMap<>();
		UserVO result = userInfoService.selectUserById(param);
		
		if(result == null || !result.getUserPwd().equals(info)) {
			map.put("result", "N");
			return map;
		}else {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");  //고유키생성
			String encId = encUtil.aesEncrypt(result.getUserId());       //id 암호화
//			result.setUserToken(uuid);
//			userInfoService.updateUserToken(result);            //토큰 없데이트
//			SessionUtil.setAttribute("uInfo", result);     //request에 uInfo정보 설정
//			Cookie tokenCookie = new Cookie("uToken", uuid + encId);   //uuid id암호화 통합 토큰 발급
//		    tokenCookie.setDomain("testdemo");;     //도메인 세팅
//		    tokenCookie.setMaxAge(1200);    //쿠키 유지시간
//		    tokenCookie.setPath("/");	//쿠키 허용 경로
//		    
//			resp.addCookie(tokenCookie);       //쿠키 세팅

			map.put("result", "Y");
			map.put("userId", param.getUserId());
			map.put("uToken", uuid + encId );
		}
		return map;
	}
	/*List<UserVO> selectAllUsers()
	 * @Value("#{gsadmConfig['cookie.domain.name']}") private String cookieDomainNm;
	 */
	
	/**
	 * Login 페이지 이동
	 * @methodName    : authMgmtView
	 * @author        : Kenny Kim
	 * @date          : 2021.02.08
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/login.do") public String authMgmtView() throws
	 * Exception { return "/login/login"; }
	 */
	
	/**
	 * Login post action 
	 * @methodName    : progressLogin
	 * @author        : Kenny Kim
	 * @date          : 2021.02.08
	 * @param req
	 * @param resp
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/postLogin.do",  method = RequestMethod.POST)
//	public String progressLogin(HttpServletRequest req, HttpServletResponse resp, @ModelAttribute UserVO param, Model model) throws Exception {
//		String info = encUtil.shaEncrypt(param.getUserPwd());    //패스워드 암호화
//		
//		UserVO result = userInfoService.selectUserById(param);
//		if(result == null || !result.getUserPwd().equals(info)) {
//			model.addAttribute("msg", "아이디 혹은 비밀번호가 일치하지 않습니다.");
//			return "/login/login";
//		} else {
//			String uuid = UUID.randomUUID().toString().replaceAll("-", "");  //고유키생성
//			String encId = encUtil.aesEncrypt(result.getUserId());       //id 암호화
//			result.setUserToken(uuid);
//			userInfoService.updateUserToken(result);
//			SessionUtil.setAttribute("uInfo", result);
//			Cookie tokenCookie = new Cookie("uToken", uuid + encId);   //uuid id암호화 통합 토큰 발급
//		    tokenCookie.setDomain(cookieDomainNm);;     //도메인 세팅
//		    tokenCookie.setMaxAge(1200);    //쿠키 유지시간
//		    tokenCookie.setPath("/");	//쿠키 허용 경로
//		    
//			resp.addCookie(tokenCookie);       //쿠키 세팅
//			String url = null;
//			String redirectURL = null;
//			String authCd = result.getAuthCode();      //권한코드
//			String firstYn = result.getFirstLoginYn();   //패스워드 만료
//			
//			
//			// 관리자인 경우
//			if (!authCd.substring(0, 2).equals("00")) {
//				redirectURL = "sys_userMgmt";
//				url = "/sys/user/mgmtView.do";
//			} else {
//
//				if (authCd.substring(2, 3).equals("1")) {
//					// RSR MASTER
//					redirectURL = "rsr_dictionaryMgmt";
//					url = "/rsr/dictionary/mgmtView.do";
//				} else if (authCd.substring(3, 4).equals("1")) {
//					// RSR USER
//					redirectURL = "rsr_voteMgmt";
//					url = "/rsr/vote/mgmtView.do";
//				} else if (!authCd.substring(4, 6).equals("00")) {
//					// mcpc
//					redirectURL = "mcpc_statisticMgmt";
//					url = "/mcpc/statistic/mgmtView.do";
//				} else if (!authCd.substring(6).equals("00")) {
//					// live
//					redirectURL = "live_statisticMgmt";
//					url = "/live/statistic/mgmtView.do";
//				}
//			}
//
//			if (firstYn.equalsIgnoreCase("Y")) {
//				if(StringUtils.isNotEmpty(param.getUrl())) {
//					url = param.getUrl();
//				}
//				model.addAttribute("url", url);
//				return "/login/changePassword";
//			}
//
//			if (StringUtils.isNotEmpty(param.getUrl())) {
//				resp.sendRedirect(param.getUrl());
//			}
//
//			return redirectURL;
//		}
//	}
//	
//	/**
//	 * 로그아웃
//	 * @methodName    : logout
//	 * @author        : Kenny Kim
//	 * @date          : 2021.02.08
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/logout.do")
//	public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		SessionUtil.removeAttribute("uInfo");
//		
//		Cookie tokenCookie = new Cookie("uToken", "");
//	    tokenCookie.setDomain(cookieDomainNm);;
//	    tokenCookie.setMaxAge(0);
//	    tokenCookie.setPath("/");
//	    resp.addCookie(tokenCookie);
//		return "/login/login";
//	}
//	
//	/**
//	 * 패스워드 변경 화면 이동
//	 * @methodName    : changePassword
//	 * @author        : Kenny Kim
//	 * @date          : 2021.02.08
//	 * @param req
//	 * @param resp
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/changePassword.do")
//	public String changePassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		return "/login/changePassword";
//	}
	
}
