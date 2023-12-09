package com.swing.utils;

import com.swing.model.ColumnDto;
import com.swing.model.TableDto;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CreateEntityUtil {
    public static void createEntity(TableDto tableDto, String filePath) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        String fileName = getFormatName(tableDto.getTableName(),true)+"Entity.java";
        File file = new File(filePath+"/"+fileName);
        if(file.exists()){
            int i = JOptionPane.showConfirmDialog(null, fileName + "已存在,是否覆盖？");
            if(i!=0) {
                return;
            }
        }
        String packageStr = filePath.substring(filePath.indexOf("java/")+5);
        packageStr = packageStr.replaceAll("/",".");
        create(tableDto,stringBuffer,packageStr);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

    }
    private static void create(TableDto tableDto,StringBuffer stringBuffer,String packageStr){
        String className = getFormatName(tableDto.getTableName(),true);

        stringBuffer.append("package "+packageStr+";\n");
        stringBuffer.append("import com.baomidou.mybatisplus.annotation.IdType;\n");
        stringBuffer.append("import com.baomidou.mybatisplus.annotation.TableField;\n");
        stringBuffer.append("import com.baomidou.mybatisplus.annotation.TableId;\n");
        stringBuffer.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
        stringBuffer.append("import lombok.Data;\n");
        Set<String> importStr = new HashSet<>();
        StringBuffer field = new StringBuffer();
        for(ColumnDto dto : tableDto.getColumnDtoList()){
            setField(dto,field,importStr);
        }
        if(importStr.size()>0){
            for(String str:importStr) {
                stringBuffer.append(str);
            }
        }
        stringBuffer.append("\n");
        stringBuffer.append("@Data\n");
        stringBuffer.append("@TableName(\""+tableDto.getTableName()+"\")\n");
        stringBuffer.append("public class "+className+"Entity{\n");
        stringBuffer.append(field);
        stringBuffer.append("}");
    }

    private static void setField(ColumnDto dto,StringBuffer stringBuffer,Set<String> importStr){
        stringBuffer.append("   /**\n");
        stringBuffer.append("   *"+dto.getRemark()+"\n");
        stringBuffer.append("   */\n");
        if(StringUtils.isNotBlank(dto.getIsPrimary()) && dto.getIsPrimary().equals("PRI")){
            stringBuffer.append("   @TableId(value=\""+dto.getColumnName()+"\",type=IdType.AUTO)\n");
        }else{
            stringBuffer.append("   @TableField(value=\""+dto.getColumnName()+"\")\n");
        }
        String type = "";
        switch(dto.getColumnType()){
            case "bigint":
                type = "Long";
                break;
            case "year":
            case "time":
            case "date":
                type = "Date";
                importStr.add("import java.util.Date;\n");
                break;
            case "int":
            case "tinyint":
                type = "Integer";
                break;
            case "decimal":
            case "numeric":
                type = "BigDecimal";
                importStr.add("import java.math.BigDecimal;\n");
                break;
            case "datetime":
            case "timestamp":
                type = "Timestamp";
                importStr.add("import java.sql.Timestamp;\n");
                break;
            case "bit":
            case "longblob":
            case "blob":
                type = "byte[]";
                break;
            case "smallint":
                type = "int";
                break;
            case "float":
                type = "Float";
                break;
            case "double":
                type = "Double";
                break;
            default:
                type = "String";
                break;
        }
        stringBuffer.append("   private "+type+" "+getFormatName(dto.getColumnName(),false)+";\n");
    }
    /**
     * 获取首字母大写驼峰名称
     * @return
     */
    public static String getFormatName(String name,Boolean firstUp){
        StringBuffer str = new StringBuffer();
        char[] chars = name.toCharArray();
        boolean Up = false;
        for(int i=0;i<chars.length;i++){
            if(firstUp && i==0){
                str.append(Character.toUpperCase(chars[i]));
                continue;
            }
            if(chars[i] == '_'){
                Up = true;
            }else {
                if(Up){
                    str.append(Character.toUpperCase(chars[i]));
                    Up= false;
                }else {
                    str.append(chars[i]);
                }
            }
        }
        return str.toString();
    }
    // ip:port/库名
    public static String getDatabase(String jdbc){
        String substring = jdbc.substring( jdbc.lastIndexOf("/")+1);
        if(substring.contains("?")){
            substring= substring.substring(0,substring.indexOf("?"));
        }
        return substring;
    }

}
