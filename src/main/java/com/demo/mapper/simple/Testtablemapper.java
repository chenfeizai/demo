package com.demo.mapper.simple;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;

import com.demo.model.simple.Testtable;
@Mapper
public interface Testtablemapper {
    public Testtable selectbyid(@Param("id") String id);
}
