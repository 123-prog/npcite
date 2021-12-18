package com.web.npcite.dao;

import com.web.npcite.beans.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommodityDao {
    @Select("select * from commodity")
    public List<Commodity> getAllCommodity();

    @Select("select * from commodity where id=#{id}")
    public Commodity getCommodityById(@Param("id") int id);

    @Select("select * from commodity where owner=#{owner}")
    public List<Commodity> getCommodityByOwner(@Param("owner") int owner);

    @Select("select count(*) from commodity where id=#{id}")
    public int isCommodityExist(@Param("id") int id);

    @Insert("insert into commodity(name,msg,price,reserve,owner) values(#{name},#{msg},#{price},#{reserve},#{owner})")
    public int insertCommodity(@Param("name") String name,@Param("msg") String msg,@Param("price") int price,@Param("reserve") int reserve,@Param("owner") int owner);

    @Update("update commodity set name=#{name},msg=#{msg},price=#{price},reserve=#{reserve},owner=#{owner},sale_num=#{sale_num},income=#{income} where id = #{id}")
    public int updateCommodity(@Param("name") String name,@Param("msg") String msg,@Param("price") int price,@Param("reserve") int reserve,@Param("owner") int owner,@Param("sale_num") int sale_num,@Param("income") int income,@Param("id") int id);

    @Delete("delete from commodity where id=#{id}")
    public int deleteCommodity(@Param("id") int id);
}
