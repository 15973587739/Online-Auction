package cn.auction.web;

import cn.auction.biz.AuctionService;
import cn.auction.biz.impl.AuctionServiceImpl;
import cn.auction.entity.Auction;
import cn.auction.util.PageInfo;
import cn.auctoin.util.Tool;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 14:28
 **/
public class AuctionController {
    public String addAuction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String uploadFileName = "";
            String filedName = "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            String uploadFilePath = request.getServletContext().getRealPath("/upload/");
            File fullFile = null;
            String fileType = null;
            Auction auction = new Auction();
            if (isMultipart){
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()){
                    FileItem item =(FileItem)iter.next();
                    if (item.isFormField()){
                        filedName = item.getFieldName();
                        String value = item.getString("UTF-8");
                        if (filedName.equals("auctionName")){
                            auction.setAuctionName(value);
                        }
                        if (filedName.equals("startPrice")){
                            auction.setAuctionStartPrice(Double.valueOf(value));
                        }
                        if (filedName.equals("Upset")){
                            auction.setAuctionUpset(Double.valueOf(value));
                        }
                        if (filedName.equals("startTime")){
                            auction.setAuctionStartTime(Tool.parseStrToDate(value,"yyyy-MM-dd HH:mm:ss"));
                        }
                        if (filedName.equals("endTime")){
                            auction.setAuctionEndTime(Tool.parseStrToDate(value,"yyyy-MM-dd HH:mm:ss"));
                        }
                        if (filedName.equals("desc")){
                            auction.setAuctionDesc(value);
                        }

                    }else {
                        String fileName = item.getName();
                        if (fileName != null && !fileName.equals("")){
                            fullFile = new File(fileName);
                            File saveFile = new File(uploadFilePath,fullFile.getName());
                            item.write(saveFile);
                            uploadFileName = fullFile.getName();
                            fileType = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
                        }
                    }
                }
            }
            AuctionService biz = new AuctionServiceImpl();
            auction.setAuctionPic(fileType);
            biz.add(auction);
            return "/do/auction/auctionList";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "/error.jsp";
        }
    }
    public String auctionBid(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        return "";
    }
    public String auctionDetail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        return "";
    }
    public String auctionList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            AuctionService biz = new AuctionServiceImpl();
            String pageIndexStr = request.getParameter("pageIndex");
            String aucName = request.getParameter("auctionName");
            String aucDesc = request.getParameter("auctionDesc");
            String aucStarTime = request.getParameter("auctionStarTime");
            String aucEndTime = request.getParameter("auctionEndTime");
            String aucStarPrice = request.getParameter("auctionStarPrice");
            int pageIndex = 1;
            if (pageIndexStr != null){
                pageIndex = Integer.parseInt(pageIndexStr);
            }
            Auction condition = new Auction();
            if(aucName != null && !aucName.equals("")){
                condition.setAuctionName(aucName);
            }
            if (aucDesc != null && !aucName.equals("")){
                condition.setAuctionDesc(aucDesc);
            }
            if (aucStarTime != null && !aucStarTime.equals("")){
                condition.setAuctionStartTime(Tool.parseStrToDate(aucStarTime,"yyyy-MM-dd HH:mm:ss"));
            }
            if (aucEndTime != null && !aucEndTime.equals("")){
                condition.setAuctionEndTime(Tool.parseStrToDate(aucEndTime,"yyyy-MM-dd HH:mm:ss"));
            }
            if (aucStarPrice != null && !aucStarPrice.equals("")){} {
                condition.setAuctionStartPrice(new Double(aucStarPrice));
            }
            PageInfo<Auction> auctionInfo = biz.find(condition,pageIndex);
            request.setAttribute("auctionPageInfo",auctionInfo);
            request.setAttribute("actionName",aucName);
            request.setAttribute("aucDesc",aucDesc);
            request.setAttribute("aucStarTime",aucStarTime);
            request.setAttribute("aucEndTime",aucEndTime);
            request.setAttribute("aucStartPrice",aucStarPrice);
            return "/auctionList.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "/error.jsp";
        }
    }
    public String auctionResult(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        return "";
    }
}
