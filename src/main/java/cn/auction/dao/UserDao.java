package cn.auction.dao;

import cn.auction.entity.AuctionUser;

import java.sql.SQLException;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 11:18
 **/
public interface UserDao {
    AuctionUser selectUser(String username, String userpassword, boolean b)throws SQLException;

    void insert(AuctionUser auctionUser)throws SQLException;
    public AuctionUser findById(Long id) throws SQLException;
}
