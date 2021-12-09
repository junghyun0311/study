package com.example.demo.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

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
import com.google.gson.Gson;
import com.google.gson.JsonObject; 

@Controller
@RequestMapping(value = "/blog")
public class TestController { 
	
	RestHighLevelClient client;
	
	
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
	
	//lowES 호출
	@RequestMapping(value = "/test")
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
	
	
	//lowES 호출
	@RequestMapping(value = "/test2")
	public @ResponseBody String test2(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {

		Map<String, String> map = new HashMap<>();
		String str = "";
		try {
			str = lowdatasearch2();
			map.put("result", str);
		}catch (Exception e) {
			map.put("result", "N");
		}
		return str;
	}
	
	
	public String lowdatasearch2() throws IOException {
		client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("3.36.159.251", 9200, "http"),
		                new HttpHost("3.36.159.251", 9300, "http")));

		List<Map<String, Object>> tttt= search_basic();
		
		
		
		return tttt.toString();
	}
	
	public List<Map<String, Object>> search_basic() throws IOException
	{
		//make Result Set
		List <Map<String, Object>> arrList = new ArrayList<>();
		
		//Create Search Request
		SearchRequest searchRequest = new SearchRequest("bank"); 
		//Using Search Source Builder
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder query = new BoolQueryBuilder();
		query.must(QueryBuilders.termQuery("age", 32));
		//query.must(QueryBuilders.termQuery("Name", "SK"));
		sourceBuilder.query(query);
		sourceBuilder.from(0); 
		sourceBuilder.size(1); 
		
		//Add Builder to Search Request
		searchRequest.source(sourceBuilder);
		
		//Execution(Sync)
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			for(SearchHit s:searchResponse.getHits().getHits())
			  {
				  Map<String, Object>
				  sourceMap = s.getSourceAsMap();
				  arrList.add(sourceMap);
			  }
			return arrList;
		} catch (IOException e) {
			System.err.println("Elastic search fail");
		}
		return null;
	}
}
