package com.example.demo.Service;

import com.example.demo.Mapper.TestMapper;
import com.example.demo.VO.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    public TestMapper mapper;

    public List<TestVO> selectTest() {
        return mapper.selectTest();
    }
}
