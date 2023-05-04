package cn.auction.web;

import cn.auction.biz.UserService;
import cn.auction.biz.impl.UserServiceImpl;
import cn.auction.entity.AuctionUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 10:34
 **/
public class UserController {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public String doRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            AuctionUser auctionUser = new AuctionUser();
            auctionUser.setUserName(request.getParameter("username"));
            auctionUser.setUserPassword(request.getParameter("userpassword"));
            auctionUser.setUserCardNo(request.getParameter("usercardno"));
            auctionUser.setUserTel(request.getParameter("usertel"));
            auctionUser.setUserAddress(request.getParameter("useraddress"));
            auctionUser.setUserPostNumber(request.getParameter("userpostnumber"));
            auctionUser.setUserIsAdmin(false);
            request.setAttribute("registerUser",auctionUser);
            Object numrand = request.getSession().getAttribute("numrand");
            if (numrand != null || !numrand.equals(request.getParameter("inputCode"))){
                return "/register.jsp?msg=validateCodeError";
            } else  {
                UserService biz = new UserServiceImpl();
                biz.register(auctionUser);
                return "register:/login.jsp?msg=registerSuccess";
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "forward:/error.jsp";
        }
    }

    public String doLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            threadLocal.set(username);
            String userpassword = request.getParameter("userpassword");

            request.setAttribute("username",username);
            request.setAttribute("userpassword",userpassword);

            if (!request.getSession().getAttribute("numrand").equals(request.getParameter("inputCode"))){
                return "/login.jsp?msg=validateCodeError";
            }else {
                UserService biz = new UserServiceImpl();
                AuctionUser user = biz.login(username,userpassword);

                if (user == null){
                    return "/login.jsp?msg=loginError";
                }else {
                    request.getSession().setAttribute("user",user);
                    return "register:/do/auction/auctionList";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "forward:/error.jsp";
        }
    }

    public String doAdminLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            threadLocal.set(username);
            String userpassword = request.getParameter("userpassword");

            request.setAttribute("username",username);
            request.setAttribute("userpassword",userpassword);

            if (!request.getSession().getAttribute("numrand").equals(request.getParameter("inputCode"))){
                return "/login.jsp?msg=validateCodeError";
            }else {
                UserService biz = new UserServiceImpl();
                AuctionUser user = biz.adminlogin(username,userpassword);

                if (user == null){
                    return "/login.jsp?msg=loginError";
                }else {
                    request.getSession().setAttribute("user",user);
                    return "register:/do/auction/auctionList";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "forward:/error.jsp";
        }
    }

    public String doLogout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {

            return "/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message",e.getMessage());
            return "forward:/error.jsp";
        }
    }

















}
