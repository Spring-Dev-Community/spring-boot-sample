package com.relive.encrypt;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: ReLive
 * @date: 2022/6/21 10:56 上午
 */
@MappedJdbcTypes(JdbcType.VARCHAR) //数据库类型
@MappedTypes({String.class}) //java数据类型
public class EncryptTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String param, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, AESUtils.encrypt(param));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String result = resultSet.getString(columnName);
        return StringUtils.hasText(result) ? AESUtils.decrypt(result) : null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String result = resultSet.getString(columnIndex);
        return StringUtils.hasText(result) ? AESUtils.decrypt(result) : null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String result = callableStatement.getString(columnIndex);
        return StringUtils.hasText(result) ? AESUtils.decrypt(result) : null;
    }

}
