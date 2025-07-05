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
public class User_P extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) {
            method = "list";
        }
        switch (method) {
            case "list":
                try {
                    List<User> list = this.userService.list();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("query.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "加载用户列表失败");
                }
                break;
            case "delete":
                try {
                    String idStr = req.getParameter("id");
                    if (idStr == null || idStr.isEmpty()) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少用户ID");
                        return;
                    }
                    Integer id = Integer.parseInt(idStr);
                    userService.delete(id);
                    resp.sendRedirect("/user");
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除用户失败: " + e.getMessage());
                }
                break;
            case "update":
                // 这里应该只负责显示更新表单，实际更新操作在doPost中处理
                // 在实际应用中，这里可以预填充表单数据
                req.getRequestDispatcher("update.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) {
            resp.sendRedirect("/user");
            return;
        }

        switch (method) {
            case "add":
                addUser(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
            default:
                resp.sendRedirect("/user");
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String ageStr = req.getParameter("age");
            String scoreStr = req.getParameter("score");
            String moneyStr = req.getParameter("money");

            // 验证参数
            if (name == null || ageStr == null || scoreStr == null || moneyStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少必要参数");
                return;
            }

            Integer age = Integer.parseInt(ageStr);
            Integer score = Integer.parseInt(scoreStr);
            Integer money = Integer.parseInt(moneyStr);

            QueryRunner queryRunner = new QueryRunner();
            Connection connection = ConnectionUtils.getConnection();
            String sql = "insert into user(name, age, score, money) values(?,?,?,?)";
            int update = queryRunner.update(connection, sql, name, age, score, money);

            if (update == 1) {
                resp.sendRedirect("/user");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加用户时出错: " + e.getMessage());
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            String name = req.getParameter("name");
            String ageStr = req.getParameter("age");
            String scoreStr = req.getParameter("score");
            String moneyStr = req.getParameter("money");

            // 验证参数
            if (idStr == null || name == null || ageStr == null || scoreStr == null || moneyStr == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少必要参数");
                return;
            }

            Integer id = Integer.parseInt(idStr);
            Integer age = Integer.parseInt(ageStr);
            Integer score = Integer.parseInt(scoreStr);
            Integer money = Integer.parseInt(moneyStr);

            QueryRunner queryRunner = new QueryRunner();
            Connection connection = ConnectionUtils.getConnection();
            String sql = "update user set name=?, age=?, score=?, money=? where id=?";
            int updated = queryRunner.update(connection, sql, name, age, score, money, id);

            if (updated == 1) {
                resp.sendRedirect("/user");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新用户失败，可能是用户不存在");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "参数格式错误");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新用户时出错: " + e.getMessage());
        }
    }
}