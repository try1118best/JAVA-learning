package com.service;

import com.dbutils.ConnectionUtils;
import com.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserService {

    public List<User> list() throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "select * from user";
        List<User> list = queryRunner.query(connection, sql,
                new BeanListHandler<User>(User.class));
        return  list;
    }

    public void delete(int id) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = ConnectionUtils.getConnection();
        String sql = "delete from user where id=?";
        int i = queryRunner.update(connection, sql, id);//删除行
        System.out.println("删除了 " + i + " 行记录");
    }


}
