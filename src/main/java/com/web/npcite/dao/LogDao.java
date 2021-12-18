package com.web.npcite.dao;

import com.web.npcite.beans.log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface LogDao {
    @Select("select * from log where user_id=#{id} order by time")
    public List<log> selectLogByUserId(@Param("id") int id);

    @Insert("insert into log(user_id,msg,time) values(#{user_id},#{msg},#{time})")
    public int insertLog(@Param("user_id") int id, @Param("msg") String msg, @Param("time") Timestamp time);
}
