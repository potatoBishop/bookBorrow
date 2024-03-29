package com.etoak.java.utils;

public class StringUtils {

    /**
     * 判断String是否为空
     * 空： true
     * 不为空且不为空串 ： false
     * @param str
     * @return
     */
    public static boolean isEmpty( String str ){
        //return ( str == null || "".equals(str) );
        return !( str != null && !"".equals(str) );
    }

    public static boolean isNotEmpty( String str ){
        return !isEmpty(str);
    }

}
