package com.absym.servlet;

import com.absym.dao.AdminDao;
import com.absym.dao.BorrowerDao;
import com.absym.dao.UserDao;
import com.absym.entity.Admin;
import com.absym.entity.Borrower;
import com.absym.entity.User;
import com.absym.util.CaptchaUtil;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private Map<String, Object> loginVerity(HttpServletRequest request) {
        User userInf = new User(request.getParameter("account"), request.getParameter("password"));
        Map<String, Object> retValue = new HashMap<String, Object>();
        if (userInf.getAccount() == null || userInf.getPassword() == null) {
            retValue.put("msg", "系统错误");
            retValue.put("state", "error");
        } else if (userInf.getAccount().isEmpty()) {
            retValue.put("msg", "请输入用户名");
            retValue.put("state", "warning");
        } else if (userInf.getPassword().isEmpty()) {
            retValue.put("msg", "请输入密码");
            retValue.put("state", "warning");
        } else {
            UserDao userOperator = new UserDao();
            userInf = userOperator.excuteQuery(userInf);
            if (userInf == null) {
                retValue.put("msg", "用户不存在");
                retValue.put("state", "error");
            } else {
                request.getSession().setAttribute("user", userInf);
                request.getSession().setAttribute("loginState", "ENABLE");
                retValue.put("state", "success");
                if ("admin".equals(userInf.getType())) {
                    AdminDao adminOperator = new AdminDao();
                    Admin admin = new Admin(userInf.getAccount());
                    admin = adminOperator.excuteQuery(admin);
                    request.getSession().setAttribute("admin", admin);
                    request.getSession().setAttribute("msg", "admin");
                    retValue.put("msg", "admin");
                } else if ("borrower".equals(userInf.getType())) {
                    BorrowerDao borrowerOperator = new BorrowerDao();
                    Borrower borrower = new Borrower(userInf.getAccount());
                    borrower = borrowerOperator.excuteQuery(borrower);
                    request.getSession().setAttribute("borrower", borrower);
                    request.getSession().setAttribute("msg", "borrower");
                    retValue.put("msg", "borrower");
                }
            }
        }
        return retValue;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI().substring(6);
        if (requestURI.isEmpty() || "/".equals(requestURI)) {
            request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
        } else if ("/getCaptcheCodeImg".equals(requestURI)) {
            CaptchaUtil cap = new CaptchaUtil(5, 120, 30);
            request.getSession().setAttribute("catpcheCode", cap.generatorStrCaptch());
            BufferedImage image = cap.generatorRotateVCaptchaImage(true);
            ImageIO.write(image, "png", response.getOutputStream());
        } else if ("/login".equals(requestURI)) {
            Map<String, Object> dat = new HashMap<>();
            String srcCap = ((String) request.getSession().getAttribute("catpcheCode")).toUpperCase();
            String inputCap = ((String) request.getParameter("captche")).toUpperCase();
            if (srcCap.equals(inputCap)) {
                dat = this.loginVerity(request);
            } else {
                dat.put("msg", "验证码不正确，请重新输入");
                dat.put("state", "error");
            }
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject(dat);
            out.println(json);
            out.close();
        } else {
            request.getSession().removeAttribute(
                    (String) request.getSession().getAttribute("msg"));
            request.getSession().removeAttribute("loginState");
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("borrower");
            request.getSession().setAttribute("msg", "admin");
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
