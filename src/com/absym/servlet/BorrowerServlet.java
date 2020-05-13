package com.absym.servlet;

import com.absym.dao.BookDao;
import com.absym.dao.BorrowDao;
import com.absym.dao.BorrowerDao;
import com.absym.dao.UserDao;
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
            BorrowDao borrowkDao = new BorrowDao();
            User user = (User) request.getSession().getAttribute("user");
            List<BorrowInf> dat = borrowkDao.queryList(user.getAccount(), page.getOffset(), page.getLimit());
            mapDat.put("data", dat);
            mapDat.put("count", dat.size());
        } else if ("/getListDatBook".equals(requestURI)) {
            BookDao bookDao = new BookDao();
            mapDat.put("data", bookDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", bookDao.queryNumAll());
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
            Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
            if (borrower.getMaxBook() - borrower.getBorrowBook() - books.length < 0) {
                mapDat.put("msg", "您已经达最大借阅量，这个问题交钱可以解决");
            } else {
                BookDao bookDao = new BookDao();
                BorrowDao borrowDao = new BorrowDao();
                StringBuffer buf = new StringBuffer();
                for (String book : books) {
                    if (bookDao.excuteQuery(book) == null) {
                        buf.append("不存在").append(book).append("\n");
                    } else {
                        borrowDao.insert(new BorrowInf(borrower.getBorrowerAccount(), book));
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
            BorrowDao borrowDao = new BorrowDao();
            String[] borrows = request.getParameterValues("borrows[]");
            for (String delete_id : borrows) {
                borrowDao.delete(delete_id);
            }
            mapDat.put("msg", "删除" + borrows.length + "条数据成功");
        }
        Borrower borrower = (Borrower) request.getSession().getAttribute("borrower");
        request.getSession().setAttribute("borrower", new BorrowerDao().excuteQuery(borrower));
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
            UserDao userDao = new UserDao();
            User user = userDao.excuteQuery(new User(borrowerAccount, oldpassword));
            mapDat.put("msg", "密码错误，请重试");
            if (user != null) {
                BorrowerDao borrowerDao = new BorrowerDao();
                Borrower borrower = borrowerDao.excuteQuery(new Borrower(borrowerAccount));
                borrower.setBorrowerID(request.getParameter("borrowerID"));
                borrower.setPhone(request.getParameter("phone"));
                borrower.setBorrowerName(request.getParameter("borrowerName"));
                borrower.setSex(request.getParameter("sex"));
                borrower.setBorrowerAddress(request.getParameter("borrowerAddress"));
                user.setPassword(request.getParameter("newpassword"));
                user.setUsername(request.getParameter("borrowerName"));
                borrowerDao.update(borrower);
                userDao.excuteUpdate(user);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
