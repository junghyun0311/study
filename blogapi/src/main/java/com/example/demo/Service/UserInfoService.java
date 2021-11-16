package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.VO.UserVO;

@Service
public class UserInfoService {
	@Autowired
	public UserMapper mapper;

	public List<UserVO> selectAllUsers() {
        return mapper.selectAllUsers();
    }
//	UserVO selectUserById(UserVO param);
//	
//	void insertUser(UserVO param);
//	
//	int updateUser(UserVO param);
//	
//	int updateUserToken(UserVO param);
//	
//	int updateUserPwd(UserVO param);
}
