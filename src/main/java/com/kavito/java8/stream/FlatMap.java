package com.kavito.java8.stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2020/3/26 10:23 上午
 */
public class FlatMap {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        private  String addr;
    }

    @Test
    public void test(){
        List<User> uList = new ArrayList<> ();
        User u1 = new User();
        u1.setAddr("a1;a2;a3;a4;a5");

        User u2 = new User();
        u2.setAddr("b1;b2;b3;b4;b5");

        uList.add(u1);
        uList.add(u2);

        //List<String> addrList = uList.stream().map(x -> x.getAddr()).flatMap(x-> Arrays.stream(x.split(";"))).collect(Collectors.toList());
        //或者
        List<String> ridStrList = uList.stream().map(x -> x.getAddr()).map(x -> x.split(";")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(ridStrList);
    }
    @Test
    public void test1(){

                //char grade = args[0].charAt(0);
                char grade = 'C';

                switch(grade)
                {
                    case 'A' :
                        System.out.println("优秀");
                        break;
                    case 'B' :
                    case 'C' :
                        System.out.println("良好");
                        break;
                    case 'D' :
                        System.out.println("及格");
                        break;
                    case 'F' :
                        System.out.println("你需要再努力努力");
                        break;
                    default :
                        System.out.println("未知等级");
                }
                System.out.println("你的等级是 " + grade);
     }

/*
    @Test
    public void test1(){
        String[] arr = new String[]{"Hello","World"};
        List<String[]> list = Arrays.stream(arr)
                .map(x -> x.split(""))
                .distinct()
                .collect(Collectors.toList());

        list.forEach(x->{
        });
    }
*/

}
