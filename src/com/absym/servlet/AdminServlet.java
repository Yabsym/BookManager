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
            this.logRecord(request,"View List of Borrower","View");
            mapDat.put("data", BorrowerDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", BorrowerDao.queryNumAll());
        } else if ("/getListDatBook".equals(requestURI)) {
            this.logRecord(request,"View List of Book","View");
            mapDat.put("data", BookDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", BookDao.queryNumAll());
        } else if ("/getListDatAdmin".equals(requestURI)) {
            this.logRecord(request,"View List of Admin","View");
            mapDat.put("data", AdminDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", AdminDao.queryNumAll());
        } else if ("/getListDatBorrow".equals(requestURI)) {
            this.logRecord(request,"View List of Borrow Information","View");
            mapDat.put("data", BorrowDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", BorrowDao.queryNumAll());
        } else if ("/getListDatLog".equals(requestURI)) {
            this.logRecord(request,"View List of Log","View");
            mapDat.put("data", LogDao.queryList(page.getOffset(), page.getLimit()));
            mapDat.put("count", LogDao.queryNumAll());

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
            Book book = new Book((String) map.get("bookISBN"));
            request.getSession().setAttribute("bookType", "modifyBookInf");
            request.getSession().setAttribute("modifyBookInf", BookDao.excuteQuery(book));
            this.logRecord(request,"Try to modify a Book Information which is "+book.getBookISBN(),"Modify");
        } else if ("/modifyBorrowerInf".equals(requestURI)) {
            Borrower borrower = new Borrower((String) map.get("borrowerAccount"));
            request.getSession().setAttribute("borrowerType", "modifyBorrowerInf");
            request.getSession().setAttribute("modifyBorrowerInf", BorrowerDao.excuteQuery(borrower));
            this.logRecord(request,"Try to modify a Borrower Information which is "+borrower.getBorrowerAccount(),"Modify");
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
            User user = UserDao.excuteQuery(new User(adminAccount, oldpassword));
            if (user != null) {
                this.logRecord(request,"Try To Modify Self Information","Modify");
                Admin admin = AdminDao.excuteQuery(new Admin(adminAccount));
                admin.setAdminName(request.getParameter("adminName"));
                admin.setEmail(request.getParameter("email"));
                admin.setAdminId(request.getParameter("adminId"));
                admin.setPhone(request.getParameter("phone"));
                user.setPassword(request.getParameter("newpassword"));
                user.setUsername(request.getParameter("adminName"));
                AdminDao.excuteUpdate(admin);
                UserDao.excuteUpdate(user);
                request.getSession().setAttribute("admin", admin);
                mapDat.put("msg", "信息更新成功");
            }
        } else if ("/addBookInf".equals(requestURI)) {
            Book book = new Book(request.getParameter("bookISBN"), request.getParameter("bookName"),
                    request.getParameter("per"), Double.parseDouble(request.getParameter("price")),
                    request.getParameter("publicer"), request.getParameter("publicTime"), request.getParameter("type"),
                    Integer.parseInt(request.getParameter("allNum")), Integer.parseInt(request.getParameter("inventoryNum")));
            if (BookDao.excuteQuery(book) == null) {
                this.logRecord(request,"Try To Add A New Book Information ISBN is "+book.getBookISBN(),"Add");
                BookDao.insert(book);
            } else {
                this.logRecord(request,"Try To Modify A New Book Information ISBN is "+book.getBookISBN(),"Modify");
                BookDao.update(book);
            }
            mapDat.put("msg", "提交成功");
        } else if ("/addBorrowerInf".equals(requestURI)) {
            Borrower borrower = new Borrower(request.getParameter("borrowerName"), request.
                    getParameter("borrowerAccount"), request.getParameter("borrowerAddress"),
                    request.getParameter("phone"), request.getParameter("borrowerID"), request.getParameter("sex"),
                    Integer.parseInt(request.getParameter("maxBook")), Integer.parseInt(request.getParameter("borrowBook")));
            if (BorrowerDao.excuteQuery(borrower) == null) {
                this.logRecord(request,"Try To Add A New Book Information Account is "+borrower.getBorrowerAccount(),"Modify");
                BorrowerDao.insert(borrower);
            } else {
                BorrowerDao.update(borrower);
                this.logRecord(request,"Try To Add A New Book Information Account is "+borrower.getBorrowerAccount(),"Add");
            }
            mapDat.put("msg", "提交成功");
        } else if ("/addBorrowInf".equals(requestURI)) {

            BorrowInf borrow = new BorrowInf(request.getParameter("borrower"), request.getParameter("bookISBN"));
            this.logRecord(request,"Try To Add A Recording Information","Add");
            Book book = BookDao.excuteQuery(new Book(borrow.getBookISBN()));
            Borrower borrower =  BorrowerDao.excuteQuery(new Borrower(borrow.getBorrower()));
            if (book == null) {
                mapDat.put("msg", "不存在该书");
            } else if (borrower == null) {
                mapDat.put("msg", "不存在该用户");
            } else if (book.getInventoryNum() == 0) {
                mapDat.put("msg", "书籍库存不足");
            } else if (borrower.getMaxBook() - borrower.getBorrowBook() == 0) {
                mapDat.put("msg", "用户已达最大借阅量");
            } else {
                BorrowDao.insert(borrow);
                mapDat.put("msg", "提交成功");
            }
        }
        JSONObject json = new JSONObject(mapDat);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }

    private void deleteInf(HttpServletRequest request, HttpServletResponse response, String requestURI) throws IOException {
        Map<String, Object> dat = new HashMap<>();
        if ("/deleteBorrows".equals(requestURI)) {

            String[] borrows = request.getParameterValues("borrows[]");
            this.logRecord(request,"Try To Delete Some Borrows" +borrows.toString() ,"Delete");
            for (String delete_id : borrows) {
                BorrowDao.delete(delete_id);
            }
            dat.put("msg", "删除" + borrows.length + "条数据成功");
        } else if ("/deleteBooks".equals(requestURI)) {
            String[] books = request.getParameterValues("books[]");
            this.logRecord(request,"Try To Delete Some Book" +books.toString() ,"Delete");
            StringBuffer buf = new StringBuffer();
            for (String ISBN : books) {
                if (BorrowDao.queryBookNum(ISBN) == 0) {
                    BookDao.delete(ISBN);
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
            StringBuffer buf = new StringBuffer();
            this.logRecord(request,"Try To Delete Some Borrower" +borrowers.toString() ,"Delete");
            for (String account : borrowers) {
                if (BorrowDao.queryBorrowerNum(account) == 0) {
                    BorrowerDao.delete(account);
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


    private void logRecord(HttpServletRequest request, String context, String type){
        User userInf =  (User)request.getSession().getAttribute("user");
        LogDao.insert(userInf.getAccount(),context,type);
    }
}
