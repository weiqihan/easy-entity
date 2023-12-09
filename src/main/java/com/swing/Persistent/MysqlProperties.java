package com.swing.Persistent;

import org.apache.commons.lang.StringUtils;

public class MysqlProperties {
    public static String url;
    public static String userName;
    public static String myPassword;

    public static boolean isInit(){
        if(StringUtils.isBlank(url) || StringUtils.isBlank(userName) ||
                StringUtils.isBlank(myPassword)){
            return false;
        }
        return true;
    }

}
