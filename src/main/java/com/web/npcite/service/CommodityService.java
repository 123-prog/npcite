package com.web.npcite.service;

import com.web.npcite.beans.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityService {
    public List<Commodity> getAllCommodity();
    public Commodity getCommodityById(int id);
    public int isCommodityExist(int id);
    public List<Commodity> getCommodityByOwner(int owner);
    public int insertCommodity(String name,String msg,int price,int reserve,int owner);
    public int updateCommodity(String name,String msg,int price,int reserve,int owner,int sale_num,int income,int id);
    public int deleteCommodity(int id);
}
