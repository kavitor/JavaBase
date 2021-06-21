package com.kavito.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:https://blog.csdn.net/vbirdbest/article/details/80216713
 * @Author: kavito
 * @Date: 2019/11/19 10:44 上午
 */
public class Collect {

    @Test
    public void testToCollection(){
        List<Integer> list = Arrays.asList(1, 2, 3);

        // [10, 20, 30]
        List<Integer> collect = list.stream().map(i -> i * 10).collect(Collectors.toList());

        // [20, 10, 30]
        Set<Integer> collect1 = list.stream().map(i -> i * 10).collect(Collectors.toSet());

        // {key1=value:10, key2=value:20, key3=value:30}
        Map<String, String> collect2 = list.stream().map(i -> i * 10).collect(Collectors.toMap(key -> "key" + key/10, value -> "value:" + value));

        // [1, 3, 4]
        TreeSet<Integer> collect3= Stream.of(1, 3, 4).collect(Collectors.toCollection(TreeSet::new));
    }



    @Data
    @ToString
    @AllArgsConstructor
    @RequiredArgsConstructor
    public class User {
        private Long id;
        private String username;
    }

    @Test
    public void testToMap() {
        List<User> userList = Arrays.asList(
                new User(1L, "mengday"),
                new User(2L, "mengdee"),
                new User(3L, "mengdy")
        );

        /**
         * toMap key可以指定为id,value既可以是对象本身也可以指定对象某个字段。
         * https://www.cnblogs.com/oskyhg/p/9860843.html
         * 1、Function.identity()获取这个对象本身
         * 2、Map<Long, String> userIdAndUsernameMap = userList.stream()
         *         .collect(Collectors.toMap(User::getId, User::getUsername));
         */
        /**串行收集*/
        Map<Long, User> userIdAndModelMap = userList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        User user = userIdAndModelMap.get(1L);
        // User(id=1, username=mengday)
        System.out.println(user);

        /**并行收集*/
        ConcurrentMap<Long, User> parallelMap = userList.stream()
                .parallel()
                .collect(Collectors.toConcurrentMap(User::getId, Function.identity()));
        System.out.println(parallelMap.get(1L));

        /**其他功能*/
       // 假如list转map时，list中的对象某个字段有重复的，也就是作为map的key值有重复的,可以加比较器,还能自定义Map对象
        LinkedHashMap<Long, User> linkedHashMap = userList.stream().collect(Collectors.toMap(
                User::getId,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(User::getUsername)),
                LinkedHashMap::new)
        );
    }


    /**
     * 聚合归约
     */
    @Test
    public void testJoining(){
        // [a,b,c]
        List<String> list2 = Arrays.asList("a", "b", "c");
        String result = list2.stream().collect(Collectors.joining(",","[","]"));
        // Collectors.joining(",")的结果是：a,b,c  然后再将结果 x + "d"操作, 最终返回a,b,cd
        String str= Stream.of("a", "b", "c").
                collect(Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d"));

        //result: [a,b,c]
        System.out.println("result: "+result);
        // str: a,b,cd
        System.out.println("str: "+str);
    }

    @Test
    public void test(){
        // 求最值 3
        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer maxValue = list.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((a, b) -> a - b), Optional::get));


        // 最小值 1
        Integer minValue = list.stream().collect(Collectors.collectingAndThen(Collectors.minBy((a, b) -> a - b), Optional::get));

        // 求和 6
        Integer sumValue = list.stream().collect(Collectors.summingInt(item -> item));

        // 平均值 2.0
        Double avg = list.stream().collect(Collectors.averagingDouble(x -> x));
    }


    @Test
    public void testMapping() {
        // 映射：先对集合中的元素进行映射，然后再对映射的结果使用Collectors操作
        // A,B,C
        Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(",")));
    }


    @Test
    public void testCalculation() {
        /**
         * 如果仅仅是为了计算，建议用第一种Stream.count()，Stream.min(),Stream.Max()的API,资源消耗少
         * 也可以用Collectors.counting(),collectors.minBy,collectors.maxBy()。
         */
        // count
        Long count = Stream.of(14, 0, 5, 3, 7, 9, -22, 23, 64).count();
        System.out.println("count: "+count);
        // counting
        Long counting = Stream.of(14, 0, 5, 3, 7, 9, -22, 23, 64).collect(Collectors.counting());
        System.out.println("counting: "+counting);


        /**其他高级场景*/
        // 64
        Stream.of(14, 0, 5, 3, 7, 9, -22, 23, 64)
                .collect(Collectors.maxBy(Integer::compareTo))
                .ifPresent(System.out::println);

        /** summarizingInt求总：IntSummaryStatistics对象：包含了统计、最大值、最小值，评价值，求和*/
        // IntSummaryStatistics{count=9, sum=103, min=-22, average=11.444444, max=64}
        IntSummaryStatistics intSummaryStatistics = Stream.of(14, 0, 5, 3, 7, 9, -22, 23, 64)
                .collect(Collectors.summarizingInt(Integer::valueOf));
        System.out.println("和: "+intSummaryStatistics.getSum());

    }

}
