package cn.auction.dao;

import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;
import cn.auction.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 9:57
 **/
public interface AuctionDao {
    public void insert(Auction auction) throws SQLException;
    public void insert(AuctionRecord record) throws SQLException;
    public Auction selectAuctionById(Long auctionId)throws SQLException;
    public PageInfo<Auction> select(Auction condition , int pageIndex)throws SQLException;
    public List<Auction> selectAuction(AuctionUser user)throws SQLException;
    public List<AuctionRecord> selectAuctionRecordByAuctionId(Long auctionId)throws SQLException;


}
