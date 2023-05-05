package cn.auction.biz.impl;

import cn.auction.biz.AuctionService;
import cn.auction.entity.Auction;
import cn.auction.util.PageInfo;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/5 10:57
 **/
public class AuctionServiceImpl implements AuctionService {
    @Override
    public PageInfo<Auction> find(Auction condition, int pageIndex) {
        return null;
    }

    @Override
    public void add(Auction auction) {

    }
}
