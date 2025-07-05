package com.ping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect_Test {
    //增
    public static void main1(String[] args) {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            //执行SQL
            String sql = "insert into user (name,age,score,money) values " +
                    "('user2','23','99','665')";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);//增删改都统一调这个方法
            System.out.println(i + "   执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //改
    public static void main2(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            String sql = "update user set name='user3',age=23,score=96," +
                    "money=100" + " " + "where" + " id=11";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删
    public static void main3(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            String sql = "delete from user where id=11";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查
    public static void main(String[] args) {
        try {
            //获取连接
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            String sql = "select * from user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int score = resultSet.getInt("score");
                int money = resultSet.getInt("money");
                System.out.println("id:" + id + " name:" + name + " age:" + age
                        + " score:" + score + " money:" + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
