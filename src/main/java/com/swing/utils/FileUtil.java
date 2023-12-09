package com.swing.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {

    public static final String logFilePath = "D:\\testCoverage.log";
    /**
     * 写入日志文件
     */
    public static void writeLogToFile(String content) {
        File file = new File(logFilePath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }
            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write(content);
            // 换行
            osw.write("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 删除日志文件
     */
    public static void deleteLogFile(){
        try {
            File file = new File(logFilePath);
            if (file.exists()) {
                file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}