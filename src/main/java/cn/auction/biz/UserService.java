package cn.auction.biz;

import cn.auction.entity.AuctionUser;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 10:52
 **/
public interface UserService {
    void register(AuctionUser auctionUser)throws Exception;

    AuctionUser login(String username, String userpassword)throws Exception;
    public AuctionUser adminlogin(String username, String userpassword)throws Exception;
}
