package com.absym.servlet;

import com.absym.dao.*;
import com.absym.entity.*;
import com.absym.util.Page;
import com.absym.util.ReadPost;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private void pageDistributor(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException {

        if ("/pageBorrowerList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/listPage/pageBorrowerList.jsp").forward(request, response);
        } else if ("/pageAdminList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/listPage/pageAdminList.jsp").forward(request, response);
        } else if ("/pageBookList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/listPage/pageBookList.jsp").forward(request, response);
        } else if ("/pageBorrowList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/listPage/pageBorrowList.jsp").forward(request, response);
        } else if ("/pageLogList".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/listPage/pageLogList.jsp").forward(request, response);
        } else if ("/pagePersonal".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/modifyInf/pageModifyPersonal.jsp").forward(request, response);
        } else if ("/pageModifyBook".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/modifyInf/pageModifyBook.jsp").forward(request, response);
        } else if ("/pageModifyBorrower".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/modifyInf/pageModifyBorrower.jsp").forward(request, response);
        } else if ("/pageModifyBorrow".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/modifyInf/pageModifyBorrow.jsp").forward(request, response);
        }
    }

    private void getListDat(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        Map<String, Object> mapDat = new HashMap<>();
        Page page = new Page(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
        mapDat.put("code", "0");
        mapDat.put("msg", "query ok");
        Map<String, Object> map = new ReadPost().getPostContext(request);
        if ("/getListDatBorrower".equals(requestURI)) {
            BorrowerDao borrowerkDao = new BorrowerDao();
            mapDat.put("data", borrowerkDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", borrowerkDao.queryNumAll());
        } else if ("/getListDatBook".equals(requestURI)) {
            BookDao bookDao = new BookDao();
            mapDat.put("data", bookDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", bookDao.queryNumAll());
        } else if ("/getListDatAdmin".equals(requestURI)) {
            AdminDao adminkDao = new AdminDao();
            mapDat.put("data", adminkDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", adminkDao.queryNumAll());
        } else if ("/getListDatBorrow".equals(requestURI)) {
            BorrowDao borrowDao = new BorrowDao();
            mapDat.put("data", borrowDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", borrowDao.queryNumAll());
        } else if ("/getListDatLog".equals(requestURI)) {
            LogDao logDao = new LogDao();
            mapDat.put("data", logDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", logDao.queryNumAll());

        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void modifyInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException {
        Map<String, Object> dat = new HashMap<>();
        ReadPost readPost = new ReadPost();
        Map<String, Object> map = readPost.getPostContext(request);
        if ("/modifyBookInf".equals(requestURI)) {
            BookDao bookDao = new BookDao();
            Book book = new Book((String) map.get("bookISBN"));
            book = bookDao.excuteQuery(book);
            request.getSession().setAttribute("bookType", "modifyBookInf");
            request.getSession().setAttribute("modifyBookInf", book);
        } else if ("/modifyBorrowerInf".equals(requestURI)) {
            BorrowerDao borrowerDao = new BorrowerDao();
            Borrower borrower = new Borrower((String) map.get("borrowerAccount"));
            request.getSession().setAttribute("borrowerType", "modifyBorrowerInf");
            request.getSession().setAttribute("modifyBorrowerInf", borrowerDao.excuteQuery(borrower));
        }
        dat.put("state", "success");
        JSONObject json = new JSONObject(dat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void addInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException {
        Map<String, Object> mapDat = new HashMap<>();
        if ("/addPersonalInf".equals(requestURI)) {
            String adminAccount = request.getParameter("adminAccount");
            String oldpassword = request.getParameter("oldpassword");
            mapDat.put("msg", "密码错误，请重试");

            UserDao userDao = new UserDao();
            User user = userDao.excuteQuery(new User(adminAccount, oldpassword));
            if (user != null) {
                AdminDao adminkDao = new AdminDao();
                Admin admin = adminkDao.excuteQuery(new Admin(adminAccount));
                admin.setAdminName(request.getParameter("adminName"));
                admin.setEmail(request.getParameter("email"));
                admin.setAdminId(request.getParameter("adminId"));
                admin.setPhone(request.getParameter("phone"));
                user.setPassword(request.getParameter("newpassword"));
                user.setUsername(request.getParameter("adminName"));
                adminkDao.excuteUpdate(admin);
                userDao.excuteUpdate(user);
                request.getSession().setAttribute("admin", admin);
                mapDat.put("msg", "信息更新成功");
            }
        } else if ("/addBookInf".equals(requestURI)) {
            BookDao bookDao = new BookDao();
            Book book = new Book(request.getParameter("bookISBN"), request.getParameter("bookName"),
                    request.getParameter("per"), Double.parseDouble(request.getParameter("price")),
                    request.getParameter("publicer"), request.getParameter("publicTime"), request.getParameter("type"),
                    Integer.parseInt(request.getParameter("allNum")), Integer.parseInt(request.getParameter("inventoryNum")));
            if (bookDao.excuteQuery(book) == null) {
                bookDao.insert(book);
            } else {
                bookDao.update(book);
            }
            mapDat.put("msg", "提交成功");
        } else if ("/addBorrowerInf".equals(requestURI)) {
            BorrowerDao borrowerDao = new BorrowerDao();
            Borrower borrower = new Borrower(request.getParameter("borrowerName"), request.
                    getParameter("borrowerAccount"), request.getParameter("borrowerAddress"),
                    request.getParameter("phone"), request.getParameter("borrowerID"), request.getParameter("sex"),
                    Integer.parseInt(request.getParameter("maxBook")), Integer.parseInt(request.getParameter("borrowBook")));
            if (borrowerDao.excuteQuery(borrower) == null) {
                borrowerDao.insert(borrower);
            } else {
                borrowerDao.update(borrower);
            }
            mapDat.put("msg", "提交成功");
        } else if ("/addBorrowInf".equals(requestURI)) {
            BorrowInf borrow = new BorrowInf(request.getParameter("borrower"), request.getParameter("bookISBN"));
            BorrowDao borrowDao = new BorrowDao();
            Book book = new BookDao().excuteQuery(new Book(borrow.getBookISBN()));
            Borrower borrower = new BorrowerDao().excuteQuery(new Borrower(borrow.getBorrower()));
            if (book == null) {
                mapDat.put("msg", "不存在该书");
            } else if (borrower == null) {
                mapDat.put("msg", "不存在该用户");
            } else if (book.getInventoryNum() == 0) {
                mapDat.put("msg", "书籍库存不足");
            } else if (borrower.getMaxBook() - borrower.getBorrowBook() == 0) {
                mapDat.put("msg", "用户已达最大借阅量");
            } else {
                borrowDao.insert(borrow);
                mapDat.put("msg", "提交成功");
            }
        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void deleteInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        BorrowDao borrowDao = new BorrowDao();
        Map<String, Object> dat = new HashMap<>();
        if ("/deleteBorrows".equals(requestURI)) {
            String[] borrows = request.getParameterValues("borrows[]");
            for (String delete_id : borrows) {
                borrowDao.delete(delete_id);
            }
            dat.put("msg", "删除" + borrows.length + "条数据成功");
        } else if ("/deleteBooks".equals(requestURI)) {
            String[] books = request.getParameterValues("books[]");
            BookDao bookDao = new BookDao();
            StringBuffer buf = new StringBuffer();
            for (String ISBN : books) {
                if (borrowDao.queryBookNum(ISBN) == 0) {
                    bookDao.delete(ISBN);
                } else {
                    buf.append(ISBN).append("\n");
                }
            }
            if (buf.length() == 0) {
                dat.put("msg", "删除" + books.length + "条数据成功");
            } else {
                dat.put("msg", "下面书籍删除失败:\n" + buf.toString());
            }
        } else if ("/deleteBorrowers".equals(requestURI)) {
            String[] borrowers = request.getParameterValues("borrower[]");
            BorrowerDao borrowerDao = new BorrowerDao();
            StringBuffer buf = new StringBuffer();
            for (String account : borrowers) {
                if (borrowDao.queryBorrowerNum(account) == 0) {
                    borrowerDao.delete(account);
                } else {
                    buf.append(account).append("\n");
                }
            }
            if (buf.length() == 0) {
                dat.put("msg", "删除" + borrowers.length + "条数据成功");
            } else {
                dat.put("msg", "下面用户删除失败:\n" + buf.toString());
            }
        }
        JSONObject json = new JSONObject(dat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if ("ENABLE".equals((String) request.getSession().getAttribute("loginState")) &&
                "admin".equals((String) request.getSession().getAttribute("msg"))) {
            String requestURI = request.getRequestURI().substring(6);
            if (requestURI.isEmpty() || "/".equals(requestURI)) {
                request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/admin/admin.jsp").forward(request, response);
            } else if (requestURI.contains("page")) {
                this.pageDistributor(request, response, requestURI);
            } else if (requestURI.contains("getListDat")) {
                this.getListDat(request, response, requestURI);
            } else if (requestURI.contains("modify")) {
                this.modifyInf(request, response, requestURI);
            } else if (requestURI.contains("add")) {
                this.addInf(request, response, requestURI);
            } else if (requestURI.contains("delete")) {
                this.deleteInf(request, response, requestURI);
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
