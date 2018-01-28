package com.cssrc.auth.mapper;

import com.cssrc.auth.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ClientMapper extends Mapper<Client> {
    @ResultType(String.class)
    List<String> selectAllowClient(String serviceId);
}