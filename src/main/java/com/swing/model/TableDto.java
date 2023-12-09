package com.swing.model;

import java.util.List;

public class TableDto {
    private String tableName;
    private String remark;
    private List<ColumnDto> columnDtoList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ColumnDto> getColumnDtoList() {
        return columnDtoList;
    }

    public void setColumnDtoList(List<ColumnDto> columnDtoList) {
        this.columnDtoList = columnDtoList;
    }

    @Override
    public String toString() {
        return "TableDto{" +
                "tableName='" + tableName + '\'' +
                ", remark='" + remark + '\'' +
                ", columnDtoList=" + columnDtoList +
                '}';
    }
}
