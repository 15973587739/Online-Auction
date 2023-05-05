package cn.auction.biz;

import cn.auction.entity.Auction;
import cn.auction.util.PageInfo;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/5 10:57
 **/
public interface AuctionService {
    PageInfo<Auction> find(Auction condition, int pageIndex);

    void add(Auction auction);
}
