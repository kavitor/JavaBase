package com.kavito.binary;

import org.junit.Test;

import java.util.List;


/**
 * @Description:测试
 * @Author: kavito
 * @Date: 2020/8/3 4:16 下午
 */
public class BinaryUtilTest {


    @Test
    public void testWeekToNum(){
        int[] weeks={1,2,3,4,5,6,7};
        int num = BinaryUtils.weekToNum(weeks);
        // 127
        System.out.println(num);
    }

    @Test
    public void testNumToWeeks(){
        int num=27;
        List<Integer> weeks = BinaryUtils.numToWeeks(27);
        // [1, 2, 4, 5]
        System.out.println(weeks);
    }

    @Test
    public void testContraintWeek(){
        Boolean flag = BinaryUtils.containWeek(27, 4);
        // [one, two, three]
        System.out.println(flag);
    }


    /**-----------------------------------------------------------*/

    @Test
    public void testNumToCode(){
        List<String> codes = BinaryUtils.numToCode(7);
        // [one, two, three]
        System.out.println(codes);
    }

    @Test
    public void testContraintAll(){
        Boolean flag = BinaryUtils.containsAll(7, "three");
        // true
        System.out.println(flag);
    }

}
