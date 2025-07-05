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
        String method = req.getParameter("method");
        if (method == null) {
            method = "list";
        }
        switch (method) {
            case "list":
                try {
                    List<User> list = this.userService.list();
                    req.setAttribute("list", list);
                    System.out.println(list);
                    req.getRequestDispatcher("query.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                UserService userService = new UserService();
                try {
                    userService.delete(id);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/user");
                break;
            case "update":
                String id1Str = req.getParameter("id");
                String name1 = req.getParameter("name");
                String ageStr = req.getParameter("age");
                String scoreStr = req.getParameter("score");
                String moneyStr = req.getParameter("money");
                Integer id1 = Integer.parseInt(id1Str);
                Integer age1 = Integer.parseInt(ageStr);
                Integer score1 = Integer.parseInt(scoreStr);
                Integer money1 = Integer.parseInt(moneyStr);

                QueryRunner queryRunner = new QueryRunner();
                try {
                    Connection connection = ConnectionUtils.getConnection();
                    String sql1 = "update user set name=?,age=?,score=?," +
                            "money=? where id=?";
                    int updated = queryRunner.update(connection, sql1, name1,
                            age1, score1,
                            money1, id1);
                    if (updated == 1) {
                        resp.sendRedirect("/user");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");
        String scoreStr = req.getParameter("score");
        String moneyStr = req.getParameter("money");
        Integer age = Integer.parseInt(ageStr);
        Integer score = Integer.parseInt(scoreStr);
        Integer money = Integer.parseInt(moneyStr);
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = ConnectionUtils.getConnection();
            String sql = "insert into user(name, age, score, money) values(?,?,?,?)";
            int update = queryRunner.update(connection, sql, name, age, score, money);
            if (update == 1) {
                resp.sendRedirect("/user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
