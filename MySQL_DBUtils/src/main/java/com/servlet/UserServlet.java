package com.servlet;

import com.dbutils.ConnectionUtils;
import com.entity.User;
import com.service.UserService;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求编码为 UTF-8
        req.setCharacterEncoding("UTF-8");
        // 设置响应编码为 UTF-8
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        String method = req.getParameter("method");
        if (method == null) {
            method = "list";
        }

        try {
            switch (method) {
                case "list":
                    List<User> list = this.userService.list();
                    req.setAttribute("list", list);
                    System.out.println(list);
                    req.getRequestDispatcher("query.jsp").forward(req, resp);
                    break;
                case "delete":
                    String idStr = req.getParameter("id");
                    Integer id = Integer.parseInt(idStr);
                    if (userService.delete(id)) {
                        resp.sendRedirect("/user");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求编码为 UTF-8
        req.setCharacterEncoding("UTF-8");
        // 设置响应编码为 UTF-8
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String method = req.getParameter("method");
        if (method == null) {
            resp.sendRedirect("/user");
            return;
        }
        try {
            switch (method) {
                case "add":
                    User newUser = CreateUserFromRequest(req);
                    if (userService.addUser(newUser)) {
                        resp.sendRedirect("/user");
                    }
                    break;
                case "update":
                    User updateUser = CreateUserFromRequest(req);
                    updateUser.setId(Integer.parseInt(req.getParameter("id")));
                    if (userService.updateUser(updateUser)) {
                        resp.sendRedirect("/user");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User CreateUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setScore(Integer.parseInt(req.getParameter("score")));
        user.setMoney(Integer.parseInt(req.getParameter("money")));
        return user;
    }
}
