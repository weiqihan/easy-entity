package com.swing.utils;

import com.swing.Persistent.MysqlPersistentSettings;
import com.swing.Persistent.MysqlProperties;
import com.swing.model.ColumnDto;
import com.swing.model.TableDto;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {

    public static TableDto executeSql(String tableName,String database) throws SQLException, ClassNotFoundException {
        TableDto tableDto = new TableDto();
        List<ColumnDto> columnDtoList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库连接
            conn = DriverManager.getConnection(MysqlPersistentSettings.getInstance().url, MysqlPersistentSettings.getInstance().userName, MysqlPersistentSettings.getInstance().myPassword);
            // 创建Statement对象
            stmt = conn.createStatement();
            // 执行SQL语句
            rs = stmt.executeQuery("select table_name,table_comment from information_schema.`TABLES` where table_name='"+tableName+"' and table_schema= '"+database+"'");
            // 处理结果集
            while (rs.next()) {
                tableDto.setTableName(rs.getString(1));
                tableDto.setRemark(rs.getString(2));
            }
            if(StringUtils.isBlank(tableDto.getTableName())){
                AlertUtil.showText(tableName+"不存在");
                return null;
            }
            rs = stmt.executeQuery("select column_name,data_type,column_key,column_comment  from information_schema.`COLUMNS` where table_name='"+tableName+"' and table_schema= '"+database+"'");
            while (rs.next()) {
                ColumnDto columnDto = new ColumnDto();
                columnDto.setColumnName(rs.getString(1));
                columnDto.setColumnType(rs.getString(2));
                columnDto.setIsPrimary(rs.getString(3));
                columnDto.setRemark(rs.getString(4));
                columnDtoList.add(columnDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            // 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tableDto.setColumnDtoList(columnDtoList);
        return tableDto;
    }

    public static boolean testMysqlConnect(){

        boolean result = true;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库连接
            conn = DriverManager.getConnection(MysqlPersistentSettings.getInstance().url, MysqlPersistentSettings.getInstance().userName, MysqlPersistentSettings.getInstance().myPassword);
            // 创建Statement对象
            stmt = conn.createStatement();
        } catch (Exception e) {
            result = false;
        }finally {
            // 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
