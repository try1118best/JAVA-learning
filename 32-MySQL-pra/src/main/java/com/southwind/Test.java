package com.southwind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Class.forName;

public class Test {
    //增
    public static void main1(String[] args) {

        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            //执行SQL
            String sql = "insert into user (name,age,score,money) values " +
                    "('629测试','23','99','665')";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //删
    public static void main2(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "delete from user where id=12";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //改
    public static void main3(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "update user set name='test11629',age=12,score=99," +
                    "money=100" + " where " + "id=10";
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                int score = resultSet.getInt(4);
                int money = resultSet.getInt(5);
                System.out.println("id:" + id + " name:" + name + " age:" + age
                        + " score:" + score + " money:" + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}