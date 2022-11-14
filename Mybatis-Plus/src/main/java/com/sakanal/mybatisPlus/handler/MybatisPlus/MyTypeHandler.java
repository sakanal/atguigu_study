package com.sakanal.mybatisPlus.handler.MybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class MyTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setTimestamp(i, new Timestamp(date.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        log.info("start handler Date getNullableResult(ResultSet resultSet, String s)");
        Timestamp timestamp = resultSet.getTimestamp(s);
        if (timestamp!=null){
            try {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(String.valueOf(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        log.info("start handler getNullableResult(ResultSet resultSet, int i)");
        Timestamp timestamp = resultSet.getTimestamp(i);
        if (timestamp!=null){
            try {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(String.valueOf(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        log.info("start handler getNullableResult(CallableStatement callableStatement, int i)");
        Timestamp timestamp = callableStatement.getTimestamp(i);
        if (timestamp!=null){
            try {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(String.valueOf(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
