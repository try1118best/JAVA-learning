package com.dbutils;

import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyBeanListHandler<T> implements ResultSetHandler {

    private Class clazz; //接收集合的泛型

    public MyBeanListHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> handle(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<T>();
        while (resultSet.next()) {
            try {
                Constructor constructor = clazz.getConstructor(null);
                Object object = constructor.newInstance(null);
                list.add((T) object);
                //给对象的属性赋值，类型要一致
                //从结果集里去取数据
                //获取实体类的字段
                Field[] declaredFields = clazz.getDeclaredFields();//getFields
                // 拿的是实体类里面的公有属性
                for (Field declarefield : declaredFields) {
                    String fieldName = declarefield.getName(); //获取字段名（如 "id", "name"）
                    String TypeName = declarefield.getType().getName(); //获取类型全限定名（如// "java.lang.Integer"）
                    //结果集值提取（动态类型转换）
                    Object value = null;
                    switch (TypeName) {
                        case "java.lang.Integer":
                            value = resultSet.getInt(fieldName);  //根据字段类型选择对应的 ResultSet 方法
                            break;
                        case "java.lang.String":
                            value = resultSet.getString(fieldName);
                            break;
                    }
                    /*
                    动态调用Setter方法（反射核心）
                     */
                    //赋值
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    //System.out.println(methodName);
                    Method method = clazz.getMethod(methodName, declarefield.getType());
                    method.invoke(object, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(11);
        return list;
    }
}