package com.kavito.regular;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2020/5/11 7:45 下午
 */
public class RegisterRegular {

    //public static final String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{7,19}$";

    //(?!\u4e00-\u9fa5+$)
    //public static final String PW_PATTERN="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]).{8,20}$";
    //public static final String PW_PATTERN="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]).{8,20}$";
    //^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-ZW_]+$)(?![a-z0-9]+$)(?![a-zW_]+$)(?![0-9\W_]+$)[a-zA-Z0-9W_]{8,}$
    public static final String PW_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,20}$";


    public static void main(String[] args) {
        String pw1 = "ABCDEFGHIG";
        String pw2 = "abcdefghig";
        String pw3 = "0123456789";
        String pw4 = "!@#$%^&*()";
        String pw5 = "ABCDEabcde";
        String pw6 = "ABCDE01234";
        String pw7 = "ABCDE!@#$%";
        String pw8 = "abcde01234";
        String pw9 = "abcde!@#$%";
        String pw10 = "01234!@#$%";
        String pw11 = "abcde01234!@#$%";
        String pw12 = "ABCDE01234!@#$%";
        String pw13 = "ABCDEabcde!@#$%";
        String pw14 = "ABCDEabcde01234";
        String pw15 = "Aa0!";
        String pw16 = "Aa0234567890123456789!";
        String pw17="ABCabc012!@#中文";
        //符合要求密码
        String pw18="12567_Aa";

        System.out.println(pw1.matches(PW_PATTERN));
        System.out.println(pw2.matches(PW_PATTERN));
        System.out.println(pw3.matches(PW_PATTERN));
        System.out.println(pw4.matches(PW_PATTERN));
        System.out.println(pw5.matches(PW_PATTERN));
        System.out.println(pw6.matches(PW_PATTERN));
        System.out.println(pw7.matches(PW_PATTERN));
        System.out.println(pw8.matches(PW_PATTERN));
        System.out.println(pw9.matches(PW_PATTERN));
        System.out.println(pw10.matches(PW_PATTERN));
        System.out.println(pw11.matches(PW_PATTERN));
        System.out.println(pw12.matches(PW_PATTERN));
        System.out.println(pw13.matches(PW_PATTERN));
        System.out.println(pw14.matches(PW_PATTERN));
        System.out.println(pw15.matches(PW_PATTERN));
        System.out.println(pw16.matches(PW_PATTERN));
        System.out.println(pw17.matches(PW_PATTERN));
        System.out.println(pw18.matches(PW_PATTERN));
    }
}