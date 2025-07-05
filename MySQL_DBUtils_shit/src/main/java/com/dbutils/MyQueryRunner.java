package com.dbutils;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyQueryRunner{
    public Object query(Connection connection,String sql,
                        MyBeanHandler handler,Object param) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            //判断param类型



            if( param instanceof Integer ){
                statement.setInt(1, (Integer) param);
            }
            if( param instanceof String ){
                statement.setString(1, (String) param);
            }
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);//resultSet传递给下一层BeanListHandler去处理
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
