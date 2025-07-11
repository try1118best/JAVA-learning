package com.test.mapper;

import com.test.entity.User;
import com.test.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //加载MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取实现接口的代理对象
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);

        //新增用户
//        User user = new User();
//        user.setUsername("李四");
//        user.setPassword("987654");
//        user.setAge(21);
//        System.out.println(userRepository.add(user));
//        sqlSession.commit();

        //查询用户
//        System.out.println(userRepository.getById(3));

        //修改用户
//        User user = userRepository.getById(3);
//        user.setUsername("王五");
//        user.setPassword("abc");
//        user.setAge(18);
//        System.out.println(userRepository.update(user));
//        sqlSession.commit();//除了查询都要提交

        //删除用户
        System.out.println(userRepository.delete(3));
        sqlSession.commit();
    }
}