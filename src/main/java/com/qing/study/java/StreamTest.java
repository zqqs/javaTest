package com.qing.study.java;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: qing.zhang
 * @date: 2019-06-17
 */
public class StreamTest {

    public static void main(String[] args) {
        System.out.println("============================使用 java 7==================================");
        //计算空字符串
        List<String> strings = Arrays.asList("abc", "ab", "", "efg", "abcd", "", "jkl");
        System.out.println("列表：" + strings);
        long count = getCountEmptyStringUsingJava7(strings);
        System.out.println("空字符串数量为:" + count);
        count = getCountLength3UsingJava7(strings);
        System.out.println("字符串长度为 3 的数量为:" + count);
        //删除空字符串
        List<String> filtered = deleteEmptyStringUsingJava7(strings);
        System.out.println("筛选后列表：" + filtered);

        //删除空字符串，并使用逗号合并
        String mergedStringUsingJava7 = getMergedStringUsingJava7(strings);
        System.out.println("合并字符串：" + mergedStringUsingJava7);
        System.out.println("============================int==================================");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取平方数
        List<Integer> squares = getSquares(numbers);
        System.out.println("平方数列表:" + squares);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        System.out.println("============================int1==================================");
        System.out.println("列表：" + integers);
        System.out.println("列表最大的数：" + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("============================随机数==================================");

        //输出10个随机数
        Random random = new Random();
        for (int i=0;i<10;i++) {
            System.out.println(random.nextInt());
        }


        System.out.println("============================使用java8==================================");
        System.out.println("列表："+strings);

        long count1 = strings.stream().filter(String::isEmpty).count();
        System.out.println("空字符串数量为:"+count1);
        long count2 = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("长度为3的字符串为:"+count2);

        List<String> collect = strings.stream().filter(String::isEmpty).collect(Collectors.toList());
        System.out.println("空字符串过滤后的列表："+collect);

        String collect1 = strings.stream().filter(String::isEmpty).collect(Collectors.joining(","));
        System.out.println("逗号拼接后的字符串："+collect1);

        List<Integer> collect2 = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("平方后的列表："+collect2);
        System.out.println("列表："+integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数："+stats.getMax());
        System.out.println("列表中最小的数："+stats.getMin());
        System.out.println("所有数之和："+stats.getSum());
        System.out.println("平均数："+stats.getAverage());
        System.out.println("随机数：");

        random.ints().limit(10).sorted().forEach(System.out::println);

        //并行处理
        long count3 = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println("空字符串数量为："+count3);

    }

    private static String getAverage(List<Integer> integers) {
        int a = 0;
        for (Integer integer : integers) {
            a = a+integer;
        }
        return String.valueOf(a/integers.size());
    }

    private static String getSum(List<Integer> integers) {

        int a = 0;
        for (Integer integer : integers) {
            a = a+integer;
        }
        return String.valueOf(a);
    }

    private static String getMin(List<Integer> integers) {
        int a =0;
        for (int i = 0; i < integers.size(); i++) {
            a = integers.get(0);
            if(integers.get(i)<=a){
                a = integers.get(i);
            }
        }
        return String.valueOf(a);
    }

    private static String getMax(List<Integer> integers) {
        int a = 0;
        for (Integer integer : integers) {
            if (integer >= a) {
                a = integer;
            }
        }
        return String.valueOf(a);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> reslut = new ArrayList<>(numbers.size());
        for (Integer number : numbers) {
            reslut.add(number * number);
        }
        return reslut;
    }

    private static String getMergedStringUsingJava7(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            if (!StringUtils.isEmpty(s)) {
                sb.append(s).append(",");
            }
        }
        return sb.toString();
    }

    private static List<String> deleteEmptyStringUsingJava7(List<String> strings) {
        List<String> result = new ArrayList<>(strings.size());
        for (String s : strings) {
            if (StringUtils.isEmpty(s)) {
                result.add(s);
            }
        }
        return result;
    }

    private static long getCountEmptyStringUsingJava7(List<String> strings) {
        long n = 0l;
        for (String string : strings) {
            if (StringUtils.isEmpty(string)) {
                n = n + 1;
            }
        }
        return n;
    }

    private static long getCountLength3UsingJava7(List<String> strings) {
        long n = 0l;
        for (String string : strings) {
            if (string.length() == 3) {
                n = n + 1;
            }
        }
        return n;
    }
}
