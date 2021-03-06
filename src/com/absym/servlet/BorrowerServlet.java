package com.absym.servlet;

import com.absym.dao.*;
import com.absym.entity.BorrowInf;
import com.absym.entity.Borrower;
import com.absym.entity.User;
import com.absym.util.Page;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BorrowerServlet extends HttpServlet {

    private void pageDistributor(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException {
        if ("/pageBorrowList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/borrower/listPage/pageBorrowList.jsp").forward(request, response);
        } else if ("/pageBookList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/borrower/listPage/pageBookList.jsp").forward(request, response);
        } else if ("/pagePersonal".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/borrower/modifyInf/modifyPersonal.jsp").forward(request, response);
        }
    }

    private void getListDat(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        Map<String, Object> mapDat = new HashMap<>();
        Page page = new Page(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
        mapDat.put("code", "0");
        mapDat.put("msg", "query ok");
         if ("/getListDatBorrow".equals(requestURI)) {
             this.logRecord(request,"View List Of Borrow","View");
            User user = (User) request.getSession().getAttribute("user");
            List<BorrowInf> dat = BorrowDao.queryList(user.getAccount(), page.getOffset(), page.getLimit());
            mapDat.put("data", dat);
            mapDat.put("count", dat.size());
        } else if ("/getListDatBook".equals(requestURI)) {
             this.logRecord(request,"View List Of Book","View");
            mapDat.put("data", BookDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", BookDao.queryNumAll());
        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void addInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        Map<String, Object> mapDat = new HashMap<>();

        if ("/addBorrows".equals(requestURI)) {
            String[] books = request.getParameterValues("books[]");
            this.logRecord(request,"Try To Borrow Some Book" + books.toString(),"Borrow");
            Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
            if (borrower.getMaxBook() - borrower.getBorrowBook() - books.length < 0) {
                mapDat.put("msg", "您已经达最大借阅量，这个问题交钱可以解决");
            } else {
                StringBuffer buf = new StringBuffer();
                for (String book : books) {
                    if (BookDao.excuteQuery(book) == null) {
                        buf.append("不存在").append(book).append("\n");
                    } else {
                        BorrowDao.insert(new BorrowInf(borrower.getBorrowerAccount(), book));
                    }
                }
                if (buf.toString().isEmpty()) {
                    mapDat.put("msg", "借阅" + books.length + "本成功");
                } else {
                    mapDat.put("msg", "错误" + buf);
                }
            }
        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void deleteInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        Map<String, Object> mapDat = new HashMap<>();
        if ("/deleteBorrow".equals(requestURI)) {
            String[] borrows = request.getParameterValues("borrows[]");
            this.logRecord(request,"Try To Delete Some Borrows" +borrows.toString() ,"Delete");
            for (String delete_id : borrows) {
                BorrowDao.delete(delete_id);
            }
            mapDat.put("msg", "删除" + borrows.length + "条数据成功");
        }
        Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
        request.getSession().setAttribute("borrower", BorrowerDao.excuteQuery(borrower));
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void modifyInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException {
        Map<String, Object> mapDat = new HashMap<>();
        if ("/modifyPersonalInf".equals(requestURI)) {
            String borrowerAccount = request.getParameter("borrowerAccount");
            String oldpassword = request.getParameter("oldpassword");
            User user = UserDao.excuteQuery(new User(borrowerAccount, oldpassword));
            mapDat.put("msg", "密码错误，请重试");
            if (user != null) {
                this.logRecord(request,"Try To Modify Self Information","Modify");
                Borrower borrower = BorrowerDao.excuteQuery(new Borrower(borrowerAccount));
                borrower.setBorrowerID(request.getParameter("borrowerID"));
                borrower.setPhone(request.getParameter("phone"));
                borrower.setBorrowerName(request.getParameter("borrowerName"));
                borrower.setSex(request.getParameter("sex"));
                borrower.setBorrowerAddress(request.getParameter("borrowerAddress"));
                user.setPassword(request.getParameter("newpassword"));
                user.setUsername(request.getParameter("borrowerName"));
                BorrowerDao.update(borrower);
                UserDao.excuteUpdate(user);
                request.getSession().setAttribute("borrower", borrower);
                mapDat.put("msg", "信息更新成功");
            }
        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if ("ENABLE".equals((String) request.getSession().getAttribute("loginState")) &&
                "borrower".equals((String) request.getSession().getAttribute("msg"))) {
            String requestURI = request.getRequestURI().substring(9);
            if (requestURI.isEmpty() || "/".equals(requestURI)) {
                request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/borrower/borrower.jsp").forward(request, response);
            } else if (requestURI.contains("page")) {
                this.pageDistributor(request, response, requestURI);
            } else if (requestURI.contains("getListDat")) {
                this.getListDat(request, response, requestURI);
            } else if (requestURI.contains("delete")) {
                this.deleteInf(request, response, requestURI);
            } else if (requestURI.contains("add")) {
                this.addInf(request, response, requestURI);
            } else if (requestURI.contains("modify")) {
                this.modifyInf(request, response, requestURI);
            }
        } else {
            request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
        }
    }

    private void logRecord(HttpServletRequest request, String context, String type){
        User userInf =  (User)request.getSession().getAttribute("user");
        LogDao.insert(userInf.getAccount(),context,type);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
