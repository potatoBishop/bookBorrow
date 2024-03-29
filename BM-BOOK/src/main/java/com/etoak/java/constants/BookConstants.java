package com.etoak.java.constants;

/**
 * 有 类， 属性， 方法
 * final 修饰符可以修饰哪些？
 * 三者都可以修饰，--+属性 --》赋值并无法再被修改
 *              --+类   --》不能被继承
 *              --+方法 --》方法无法被覆盖或重写
 */

public class BookConstants {

    public static final Integer BOOK_STATUS_KJC = 0;     //可借出   final 最终无法被修改  修饰符
    public static final Integer BOOK_STATUS_YJC = 1;     //已借出
    public static final Integer BOOK_STATUS_YDS = 2;     //已丢失

}
