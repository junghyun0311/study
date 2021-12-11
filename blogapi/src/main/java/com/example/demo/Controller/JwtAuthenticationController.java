package com.example.demo.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JWT.JwtRequest;
import com.example.demo.JWT.JwtResponse;
import com.example.demo.JWT.JwtTokenUtil;
import com.example.demo.JWT.JwtUserDetailsService;
import com.example.demo.VO.UserVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    	
    	//UserVO result = userInfoService.selectUserById(param);
    	
    	
    	
    	
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());   //아이디 패스워드 받아옴
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        //return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));  //UsernamePasswordAuthenticationToken id pw 유효성검사
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
    
    //lowES 호출
  	@RequestMapping(value = "/search")
  	public @ResponseBody String test(HttpServletRequest req, HttpServletResponse res, Model model)
  			throws Exception {

  		Map<String, String> map = new HashMap<>();
  		String str = "";
  		try {
  			str = lowdatasearch();
  			map.put("result", str);
  		}catch (Exception e) {
  			map.put("result", "N");
  		}
  		return str;
  	}
  	
  	public String lowdatasearch() throws IOException {
		Map<String, Object> query = new HashMap();
		int size = 5;
		Map<String, Object> match = new HashMap();
		Map<String, Object> field = new HashMap();
		RestClient restClient= RestClient.builder(new HttpHost("3.36.159.251", 9200)).build();
		
		field.put("age", 32);
		match.put("match", field);
		query.put("query", match);
		query.put("size", size);
		
		Gson gson = new Gson();
		JsonObject json = gson.toJsonTree(query).getAsJsonObject();
		
		String query2 = json.toString();
		
		HttpEntity entity = new NStringEntity(query2, ContentType.APPLICATION_JSON);
		Request request = new Request("GET", "/bank/_search?pretty=true");   
		request.setEntity(entity);
		Response response = restClient.performRequest(request);

		String responseBody = EntityUtils.toString(response.getEntity());
		
		return responseBody;
	}
}

