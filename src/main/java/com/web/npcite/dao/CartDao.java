package com.web.npcite.dao;

import com.web.npcite.beans.Commodity;
import com.web.npcite.beans.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDao {
    @Select("select * from shopping_cart where id = #{id}")
    public ShoppingCart getShoppingCartById(@Param("id") int id);

    @Select("select id from shopping_cart where user_id=#{user_id} and commodity_id=#{commodity_id}")
    public int getIdByUserIdAndCommodityID(@Param("user_id") int user_id,@Param("commodity_id") int commodity_id);

    @Select("select * from shopping_cart where user_id=#{user_id}")
    public List<ShoppingCart> getShoppingCartsByUserId(@Param("user_id") int user_id);

    @Insert("insert into shopping_cart(user_id,commodity_id,num,commodity_name) values(#{user_id},#{commodity_id},#{num},#{commodity_name})")
    public int insertShoppingCart(@Param("user_id") int user_id,@Param("commodity_id") int commodity_id,@Param("num") int num,@Param("commodity_name") String commodity_name);

    @Update("update shopping_cart set user_id=#{user_id},commodity_id=#{commodity_id},num=#{num} where id=#{id}")
    public int updateShoppingCart(@Param("user_id") int user_id,@Param("commodity_id") int commodity_id,@Param("num") int num,@Param("id") int id);

    @Delete("delete from shopping_cart where id=#{id}")
    public int deleteShoppingCart(@Param("id") int id);

    @Select("select count(*) from shopping_cart where user_id=#{user_id} and commodity_id=#{commodity_id}")
    public int isShoppingRecordExist(@Param("user_id") int user_id,@Param("commodity_id") int commodity_id);
}
