package cn.auction.dao.impl;

import cn.auction.dao.AuctionDao;
import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;
import cn.auction.util.PageInfo;
import my.framework.dao.CommonDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 9:58
 **/
public class AuctionDaoImpl implements AuctionDao {
    private CommonDao dao = new CommonDao();

    @Override
    public void insert(Auction auction) throws SQLException {
        String aql = "insert into auction(auctionName ,auctionStartPrice ,auctionUpset ,auctionStartTime , auctionEndTime ,auctionPrc ,auctionPicType ,auctionDesc)" +
                "values (#{auctionName}, #{auctionStartPrice}, #{auctionUpset}, #{auctionStartTime}, #{auctionEndTime}, #{auctionPrc}, #{auctionPicType}, #{auctionDesc})";
        dao.executeUpdate(aql,auction);
    }

    @Override
    public void insert(AuctionRecord record) throws SQLException {
        String aql = "insert into auctionrecord(userId ,auctionId ,auctionTime ,auctionTime ,auctionPrice)" +
                "values (#{userId}, #{auctionId}, #{auctionTime}, #{auctionTime}, #{auctionPrice})";
        dao.executeUpdate(aql,record);
    }

    @Override
    public Auction selectAuctionById(Long auctionId)throws SQLException {
        String sql = "select * from auction where auctionId = ?";
        return (Auction) dao.executeQuery(Auction.class,sql,new Object[]{auctionId});
    }

    @Override
    public List<AuctionRecord> selectAuctionRecordByAuctionId(Long auctionId)throws SQLException {
        String sql = "select * from auctionrecord where auctionId = ? order by auctionPrice desc";
        return dao.executeQuery(AuctionRecord.class,sql,new Object[]{auctionId});
    }

    @Override
    public PageInfo<Auction> select(Auction condition , int pageIndex)throws SQLException{
        PageInfo<Auction> pageInfo = new PageInfo<>();
        StringBuffer sqlBuffer = new StringBuffer("from auction where 1=1 ");
        List<Object> params = new ArrayList<>();
        if (condition.getAuctionName() != null && !condition.getAuctionName().equals("")){
            sqlBuffer.append(" and auctionName like ?");
            params.add("%"+condition.getAuctionName()+"%");
        }
        if (condition.getAuctionDesc() != null && !condition.getAuctionDesc().equals("")){
            sqlBuffer.append(" and auctionDesc like ?");
            params.add("%"+condition.getAuctionDesc()+"%");
        }
        if (condition.getAuctionStartTime() !=null){
            sqlBuffer.append(" and auctionStartTime = ?");
            params.add(new java.sql.Timestamp(condition.getAuctionStartTime().getTime()));
        }
        if (condition.getAuctionEndTime() !=null){
            sqlBuffer.append(" and auctionEndTime = ?");
            params.add(new java.sql.Timestamp(condition.getAuctionEndTime().getTime()));
        }
        if (condition.getAuctionStartPrice() !=null){
            sqlBuffer.append(" and auctionStartPrice > =?");
            params.add(condition.getAuctionStartPrice());
        }
        int count = dao.executeQuery(Integer.class,"select count(auctionId) from "+sqlBuffer.toString(),params.toArray()).get(0);
        pageInfo.setCount(count);
        pageInfo.setPageNo(pageIndex);
        sqlBuffer.append(" order by auctionStartTime desc");
        sqlBuffer.append(" limit ?,?");
        params.add((pageIndex-1)*PageInfo.PAGE_SIZE);
        params.add(pageInfo.getPageSize());
        System.out.println("");
        List<Auction> list = dao.executeQuery(Auction.class,"select * "+sqlBuffer.toString(),params.toArray());
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public List<Auction> selectAuction(AuctionUser user)throws SQLException{
        String sql = "select distinct a.* from auction a inner join auctionrecord r on a.auctionId = r.auctionId where a.auctionEndTime < ? and a.userId = ?";
        return dao.executeQuery(Auction.class,sql,new Object[]{new java.sql.Timestamp(new Date().getTime()),user.getUserId()});
    }




}
