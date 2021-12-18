package com.web.npcite.service;

import com.web.npcite.beans.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartService {
    public ShoppingCart getShoppingCartById(int id);
    public int getIdByUserIdAndCommodityID(int user_id, int commodity_id);
    public List<ShoppingCart> getShoppingCartsByUserId(int user_id);
    public int insertShoppingCart(int user_id, int commodity_id, int num,String commodity_name);
    public int updateShoppingCart(int user_id,int commodity_id,int num,int id);
    public int deleteShoppingCart(int id);
    public int isShoppingRecordExist(int user_id, int commodity_id);
}
