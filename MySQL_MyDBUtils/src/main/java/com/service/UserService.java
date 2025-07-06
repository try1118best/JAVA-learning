package com.service;

import com.dbutils.ConnectionUtils;
import com.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserService {

    private QueryRunner queryRunner = new QueryRunner();

    public List<User> list() throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "select * from user";
        List<User> list = queryRunner.query(connection, sql,
                new BeanListHandler<User>(User.class));
        return list;
    }

    public boolean delete(int id) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "delete from user where id=?";
        int deleteBool = queryRunner.update(connection, sql, id);
        return deleteBool > 0;
    }

    public boolean addUser(User user) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "insert into user(name, age, score, money) values(?,?,?,?)";
        int addUserBool = queryRunner.update(connection, sql, user.getName(), user.getAge(),
                user.getScore(), user.getMoney());
        return addUserBool > 0;
    }

    public boolean updateUser(User user) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        String sql = "update user set name=?,age=?,score=?,money=? where id=?";
        int updateUserBool = queryRunner.update(connection, sql, user.getName(),
                user.getAge(), user.getScore(), user.getMoney(), user.getId());
        return updateUserBool > 0;
    }
}
