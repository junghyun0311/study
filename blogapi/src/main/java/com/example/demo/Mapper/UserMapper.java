package com.example.demo.Mapper;


import com.example.demo.VO.UserVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
	List<UserVO> selectAllUsers();
	void insertUser(UserVO param);
	UserVO selectUserById(UserVO param);
	int updateUserToken(UserVO param);
}
