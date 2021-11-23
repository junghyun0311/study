package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import com.example.demo.Util.SessionUtil;
import com.example.demo.VO.UserVO; 

@Controller
@RequestMapping(value = "/blog")
public class TestController { 
	
	@RequestMapping(value = "/testData", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addUser(@RequestBody UserVO param, HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		//param.setUserPwd(encUtil.shaEncrypt(param.getUserPwd()));

		//UserVO sessionInfo = SessionUtil.getUserInfo();
		//param.setCrtName(sessionInfo.getUserId());
		//param.setUptName(sessionInfo.getUserId());
		UserVO userVO = (UserVO)WebUtils.getSessionAttribute(req, "uInfo");
		
		Map<String, String> map = new HashMap<>();
		try {
			//userInfoService.insertUser(param);
			map.put("result", userVO.getAuthCode());
		}catch (Exception e) {
			map.put("result", "N");
		}
		
		return map;
	}
	
}
