package com.kavito.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/12/20 3:22 下午
 */
public class TimeTest {

    /**
     * 获取当前时间与当天最后一秒的时间间隔
     * @param currentDate
     * @return
     */
    public static Long getRemainSecondsOnDay(Date currentDate){
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault())
                .plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("midnight:"+midnight);
        LocalDateTime dateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        System.out.println("dateTime:"+dateTime);
        Long seconds = ChronoUnit.SECONDS.between(dateTime,midnight);
        return seconds;
    }


    // 获取当前时间与当天最后一秒的时间间隔：前天的0点-当前时间
    @Test
    public void test(){
        Long remainSecondsOnDay = TimeTest.getRemainSecondsOnDay(new Date());
        System.out.println(remainSecondsOnDay);
    }

    // 获取当前时间与当天最后一秒的时间间隔：一天的总秒数-当前时间转化的秒数
    @Test
    public void test1(){
        long max = 24*60*60;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(max-now.toLocalTime().toSecondOfDay());
    }

    // 比较器值，如果更小则为负，如果更大则为正。
    @Test
    public void test3() {
        LocalDateTime date = LocalDateTime.parse("2017-02-03T12:30:30");
        System.out.println(date);
        LocalDateTime date1 = LocalDateTime.parse("2017-03-02T12:20:30");
        System.out.println(date1);
        // 1
        System.out.println(date1.compareTo(date));
        // true
        System.out.println(LocalDateTime.parse("2017-02-03T12:30:30").isBefore(LocalDateTime.parse("2017-03-02T12:20:30")));

    }

    @Test
    public void test4(){
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime znow = now.atZone(ZoneId.systemDefault());
        String result = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(znow);
        // 2020-03-25T16:33:31.612+08:00
        System.out.println(result);
        System.out.println(ZonedDateTime.parse(result).toLocalDateTime());
        System.out.println(now);
    }

    @Test
    public void test5(){
        LocalDate today = LocalDate.now();
        LocalDate playerDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("1992-06-25"));
        long age = ChronoUnit.YEARS.between(playerDate,today);

        System.out.println("age  : " + Integer.parseInt(String.valueOf(age)));
    }

    @Test
    public void test6(){
        Integer days = new Integer(2);
        LocalDate today = LocalDate.now();
        LocalDate localDate = today.plusDays(days);
        System.out.println("day  : " + localDate);
    }


    @Test
    public void test7(){
        Map<String,String> map = new HashMap<>();
        String s = map.get("1");
        System.out.println(s);
    }



    @Test
    public void test8(){

        LocalDate today = LocalDate.now();
        LocalDate firthDay = today.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate firthDayNextMonth = today.with(TemporalAdjusters.firstDayOfNextMonth());

        System.out.println("day  : " + firthDay+"--"+firthDayNextMonth);
    }




    @Test
    public void testList(){
        List<String> all = new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
        }};
        List<String> list1 = new ArrayList<String>(){{
            add("4");
            add("5");
            add("6");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
        }};
        List<String> list3 = new ArrayList<>();

        List<String> reduce1 = all.stream().filter(x -> !list1.contains(x)).collect(Collectors.toList());
        List<String> reduce2 = reduce1.stream().filter(x -> !list2.contains(x)).collect(Collectors.toList());
        List<String> list = reduce2.stream().filter(x -> !list3.contains(x)).collect(Collectors.toList());
        System.out.println(list);




    }
    
}
