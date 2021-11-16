package com.example.demo.VO;

public class UserVO extends CommonVO {
	private String userSeq;
	private String userId;
	private String userPwd;
	private String userToken;
	private String authCode;
	private String useYn;
	private String firstLoginYn;
	private String url;
	
	/**
	 * @return the firstLoginYn
	 */
	public String getFirstLoginYn() {
		return firstLoginYn;
	}

	/**
	 * @param firstLoginYn the firstLoginYn to set
	 */
	public void setFirstLoginYn(String firstLoginYn) {
		this.firstLoginYn = firstLoginYn;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
