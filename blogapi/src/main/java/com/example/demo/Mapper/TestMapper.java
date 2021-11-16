package com.example.demo.Mapper;

import java.util.List;

import com.example.demo.VO.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface TestMapper {
    List<TestVO> selectTest();
}