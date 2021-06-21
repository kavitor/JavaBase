package com.kavito.binary;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:将一组数字转换成二进制保存
 * 例如：要保存一周中那些
 * @Author: kavito
 * @Date: 2020/8/3 3:08 下午
 */
public class BinaryUtils {


    /**
     *  周转二进制数
     *  其实就是：将2的（n-1）次方相加
     * @param weeks
     * @return
     */
    public static int weekToNum(int ...weeks){
        Assert.assertNotNull("weeks不能为空",weeks);
        int num = 0;
        for(int week: weeks){
            num = num | (1<<(week-1));
        }
        return num;
    }


    /**
     * 二进制数字转周
     * @param num
     * @return
     */
    public static List<Integer> numToWeeks(int num){
        if (num == 0){
            return Collections.emptyList();
        }
        List<Integer> weeks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if((num>> (i-1)&1)==1){
                weeks.add(i);
            }
        }
        return weeks;
    }

    /**
     * 二进制数字是否包含该星期
     * @param num
     * @param week
     * @return
     */
    public static Boolean containWeek (int num,int ...week){
        int weekToNum = weekToNum(week);
        return (weekToNum & num)>0;
    }


    /** ------------------------------------------------------------------------------*/

    /**
     * code转二进制数字
     * @param codes
     * @return
     */
    public static int codeToNum(String... codes){
        Assert.assertNotNull("codes不能为空",codes);
        int num=0;
        for (String code:codes){
            NumEnum numEnum = NumEnum.getEnumBycode(code);
            Assert.assertTrue(null!=numEnum);
            num = num |(1<<(numEnum.getIndex()-1));
        }
        return num;
    }


    public static int codeToNum(List<String> codes){
        Assert.assertNotNull("codes不能为空",codes);
        int num=0;
        for (String code:codes){
            NumEnum numEnum = NumEnum.getEnumBycode(code);
            Assert.assertTrue(null!=numEnum);
            num = num |(1<<(numEnum.getIndex()-1));
        }
        return num;
    }


    /**
     * 根据二进制数字获取codes
     * @param num
     * @return
     */
    public static List<String> numToCode(int num){
        // 传入的数据异常：num
        Assert.assertTrue(0<= num && num<= ~(-1 << NumEnum.maxNum()));
        List<String> codes = new ArrayList<> ();
        for (NumEnum numEnum:NumEnum.values()){
            if((num >> (numEnum.getIndex() -1) & 1) == 1){
                codes.add(numEnum.getCode());
            }
        }
        return codes;
    }


    /**
     * 判断是否包含其中一个
     * @param num
     * @param codes
     * @return
     */
    public static boolean containsAny(int num,String... codes){
        int numCode = codeToNum(codes);
        return (numCode & num) >0;
    }

    /***
     * 判断是否包含所有
     * @param num
     * @param codes
     * @return
     */
    public static boolean containsAll(int num,String... codes){
        int numCode = codeToNum(codes);
        return (numCode & num) == numCode;
    }










}
