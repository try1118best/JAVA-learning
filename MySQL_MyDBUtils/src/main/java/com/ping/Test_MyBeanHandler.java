package com.ping;

import com.dbutils.ConnectionUtils;
import com.dbutils.MyBeanHandler;
import com.dbutils.MyBeanListHandler;
import com.dbutils.MyQueryRunner;
import com.entity.User;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Test_MyBeanHandler {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
//            String sql = "select * from user where name=? and age=? and " +
//                    "score=? and money=?";
//            MyQueryRunner myQueryRunner = new MyQueryRunner();
//            User user =(User) myQueryRunner.query(connection, sql,
//                    new MyBeanHandler(User.class), "test2",18,100,1000);
//            System.out.println(user);
//            String sql = "insert into user(name,age,score,money) values" +
//                    "('user7061',18,?,?) ";
//            MyQueryRunner myQueryRunner = new MyQueryRunner();
//            int update = myQueryRunner.update(connection, sql,100,1000);
//            System.out.println(update);
//            String sql = "update user set name =?,age=?,score=?,money=? where" +
//                    " id = ?";
//            MyQueryRunner myQueryRunner = new MyQueryRunner();
//            int i = myQueryRunner.update(connection, sql, "王八", 60, 0,
//                    0, 1);
//            System.out.println(i);
            String sql = "delete from user where id=?";
            MyQueryRunner myQueryRunner = new MyQueryRunner();
            int update = myQueryRunner.update(connection, sql, 30);
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
