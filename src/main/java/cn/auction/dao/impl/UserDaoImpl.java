package cn.auction.dao.impl;

import cn.auction.dao.UserDao;
import cn.auction.entity.AuctionUser;
import cn.auction.web.UserController;
import my.framework.dao.CommonDao;

import java.sql.SQLException;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 11:19
 **/
public class UserDaoImpl implements UserDao {
    private CommonDao dao = new CommonDao();

    @Override
    public AuctionUser selectUser(String username, String password, boolean isAdmin) throws SQLException {
        String userName = UserController.threadLocal.get();
        String sql = "select * from userAnswer where userName=? and userPassword =? ";
        return (AuctionUser) dao.executeQuery(AuctionUser.class,sql,new Object[]{username,password});
    }

    @Override
    public void insert(AuctionUser auctionUser) throws SQLException {
        String aql = "insert into auctionUser(userName ,userPassword ,userCardNo ,userTel , userAddress ,userPostNumber ,userIsAdmin ,userQuestion ,userAnswer)" +
                "values (#{userName}, #{userPassword}, #{userCardNo}, #{userTel}, #{userAddress}, #{userPostNumber}, #{userIsAdmin}, #{userQuestion}, #{userAnswer})";
        dao.executeUpdate(aql,auctionUser);
    }

    @Override
    public AuctionUser findById(Long id) throws SQLException {
        String sql = "select * from actionUser where userId = ? ";
        return (AuctionUser) dao.executeQuery(AuctionUser.class,sql,new Object[]{id});
    }
}
