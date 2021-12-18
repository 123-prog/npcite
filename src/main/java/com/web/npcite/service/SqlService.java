package com.web.npcite.service;

import com.web.npcite.beans.*;
import com.web.npcite.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SqlService implements UserSqlService,CommodityService,CartService,RoleService,LogService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private LogDao logDao;

    //User的sql操作
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public int isUserExist(String name) {
        return userDao.isUserExist(name);
    }

    @Override
    public int isEmailExist(String email) {
        return userDao.isEmailExist(email);
    }

    public User findUserById(int id) {
        return userDao.findUserById(id);
    }
    public int insertUser(String name, String pwd, String email) {
        return userDao.insertUser(name,pwd,email);
    }
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
    public int updateUser(int id, String username, String password, String email,int balance) {
        return userDao.updateUser(id,username,password,email,balance);
    }

    @Override
    public List<Commodity> getAllCommodity() {
        return commodityDao.getAllCommodity();
    }

    @Override
    public Commodity getCommodityById(int id) {
        return commodityDao.getCommodityById(id);
    }

    @Override
    public int isCommodityExist(int id) {
        return commodityDao.isCommodityExist(id);
    }

    @Override
    public List<Commodity> getCommodityByOwner(int owner) {
        return commodityDao.getCommodityByOwner(owner);
    }

    @Override
    public int insertCommodity(String name, String msg, int price, int reserve, int owner) {
        return commodityDao.insertCommodity(name,msg,price,reserve,owner);
    }

    @Override
    public int updateCommodity(String name, String msg, int price, int reserve, int owner,int id,int sale_num,int income) {
        return commodityDao.updateCommodity(name,msg,price,reserve,owner,id,sale_num,income);
    }

    @Override
    public int deleteCommodity(int id) {
        return commodityDao.deleteCommodity(id);
    }

    @Override
    public ShoppingCart getShoppingCartById(int id) {
        return cartDao.getShoppingCartById(id);
    }

    @Override
    public int getIdByUserIdAndCommodityID(int user_id, int commodity_id) {
        return cartDao.getIdByUserIdAndCommodityID(user_id,commodity_id);
    }

    @Override
    public List<ShoppingCart> getShoppingCartsByUserId(int user_id) {
        return cartDao.getShoppingCartsByUserId(user_id);
    }

    @Override
    public int insertShoppingCart(int user_id, int commodity_id, int num, String commodity_name) {
        return cartDao.insertShoppingCart(user_id,commodity_id,num,commodity_name);
    }

    @Override
    public int updateShoppingCart(int user_id, int commodity_id, int num,int id) {
        return cartDao.updateShoppingCart(user_id,commodity_id,num,id);
    }

    @Override
    public int deleteShoppingCart(int id) {
        return cartDao.deleteShoppingCart(id);
    }

    @Override
    public int isShoppingRecordExist(int user_id, int commodity_id) {
        return cartDao.isShoppingRecordExist(user_id,commodity_id);
    }

    @Override
    public List<Role> selectRoleById(int id) {
        return roleDao.selectRoleById(id);
    }

    @Override
    public int insertUserRole(int user_id, int role_id) {
        return roleDao.insertUserRole(user_id,role_id);
    }

    @Override
    public List<log> selectLogByUserId(int id) {
        return logDao.selectLogByUserId(id);
    }

    @Override
    public int insertLog(int id, String msg, Timestamp time) {
        return logDao.insertLog(id,msg,time);
    }
}
