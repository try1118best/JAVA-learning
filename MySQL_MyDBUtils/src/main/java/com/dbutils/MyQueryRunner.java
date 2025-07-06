package com.dbutils;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyQueryRunner {
    public Object query(Connection connection, String sql,
                        MyBeanHandler handler, Object... param) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);//传？参
            //判断param类型
            for (int i = 0; i < param.length; i++) {
                if (param[i] instanceof String) {
                    statement.setString(i + 1, (String) param[i]);
                }
                if (param[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param[i]);
                }
            }

            ResultSet resultSet = statement.executeQuery();//PreparedStatement无需传sql
            return handler.handle(resultSet);//resultSet传递给下一层BeanListHandler去处理
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object query(Connection connection, String sql, MyBeanListHandler handler) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();//PreparedStatement无需传sql
            return handler.handle(resultSet);//resultSet传递给下一层BeanListHandler去处理
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(Connection connection,String sql,Object ... param) {//增、删、改
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            //判断param类型
            for (int i = 0; i < param.length; i++) {
                if (param[i] instanceof String) {
                    statement.setString(i + 1, (String) param[i]);
                }
                if (param[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param[i]);
                }
            }
            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
