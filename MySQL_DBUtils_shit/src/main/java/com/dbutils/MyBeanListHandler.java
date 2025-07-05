package com.dbutils;

import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBeanListHandler<T> implements ResultSetHandler<List<T>> {

    private  Class<T> clazz;

    public MyBeanListHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> handle(ResultSet resultSet) throws SQLException {
        List<T> resultList = new ArrayList<>();
        try {
            // 遍历结果集的所有行
            while (resultSet.next()) {
                // 通过反射创建JavaBean实例
                Constructor<T> constructor = clazz.getConstructor();
                T bean = constructor.newInstance();

                // 获取JavaBean的所有字段
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Class<?> fieldType = field.getType();

                    // 根据字段类型从结果集中获取值
                    Object value = null;
                    if (fieldType == Integer.class || fieldType == int.class) {
                        value = resultSet.getInt(fieldName);
                        // 处理基本类型int的默认值问题
                        if (resultSet.wasNull() && fieldType == int.class) {
                            value = 0;
                        }
                    } else if (fieldType == String.class) {
                        value = resultSet.getString(fieldName);
                    } else if (fieldType == Long.class || fieldType == long.class) {
                        value = resultSet.getLong(fieldName);
                        if (resultSet.wasNull() && fieldType == long.class) {
                            value = 0L;
                        }
                    } else if (fieldType == Double.class || fieldType == double.class) {
                        value = resultSet.getDouble(fieldName);
                        if (resultSet.wasNull() && fieldType == double.class) {
                            value = 0.0;
                        }
                    } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                        value = resultSet.getBoolean(fieldName);
                        if (resultSet.wasNull() && fieldType == boolean.class) {
                            value = false;
                        }
                    }
                    // 可以继续添加其他类型的支持

                    // 通过setter方法设置属性值
                    if (value != null) {
                        String setterName = "set" +
                                fieldName.substring(0, 1).toUpperCase() +
                                fieldName.substring(1);
                        Method setter = clazz.getMethod(setterName, fieldType);
                        setter.invoke(bean, value);
                    }
                }
                resultList.add(bean);
            }
        } catch (Exception e) {
            throw new SQLException("Error creating bean list", e);
        }
        return resultList;
    }
}