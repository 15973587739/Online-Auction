package cn.auction.biz.impl;

import cn.auction.biz.UserService;
import cn.auction.dao.UserDao;
import cn.auction.dao.impl.UserDaoImpl;
import cn.auction.entity.AuctionUser;
import my.framework.dao.DatabaseUtil;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 10:53
 **/
public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();
    @Override
    public void register(AuctionUser auctionUser) throws Exception {
        try {
            dao.insert(auctionUser);
            DatabaseUtil.getConnection().commit();
        }catch (Exception e) {
            e.printStackTrace();
            DatabaseUtil.getConnection().rollback();
        }finally {
            DatabaseUtil.closeConnection();
        }

    }

    @Override
    public AuctionUser login(String username, String userpassword)throws Exception {
        try {
            return dao.selectUser(username,userpassword,true);
        }finally {
            DatabaseUtil.closeConnection();
        }
    }
    @Override
    public AuctionUser adminlogin(String username, String userpassword)throws Exception {
        try {
            return dao.selectUser(username,userpassword,true);
        }finally {
            DatabaseUtil.closeConnection();
        }
    }
}
