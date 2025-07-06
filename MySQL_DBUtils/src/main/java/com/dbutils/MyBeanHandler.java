package com.dbutils;

import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBeanHandler<T> implements ResultSetHandler {

    private Class clazz;

    public MyBeanHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        //创建Java对象
        Object object = null;
        try {
            Constructor constructor = clazz.getConstructor(null);
            object = constructor.newInstance(null);
            //给对象的属性赋值，类型要一致
            //从结果集里去取数据
            if (resultSet.next()) {
                //获取实体类的字段
                Field[] fields = clazz.getDeclaredFields();//getFields拿的是实体类里面的公有属性
                for (Field field : fields) {
                    String fieldName = field.getName();
                    String TypeName = field.getType().getName();
                    Object value = null;
                    switch (TypeName) {
                        case "java.lang.Integer":
                            value = resultSet.getInt(fieldName);
                            break;
                        case "java.lang.String":
                            value = resultSet.getString(fieldName);
                            break;
                    }
                    //赋值
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    //System.out.println(methodName);
                    Method method = clazz.getMethod(methodName, field.getType());
                    method.invoke(object, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object; //强转成泛型
    }

}
