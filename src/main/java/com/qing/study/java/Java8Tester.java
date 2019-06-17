package com.qing.study.java;


import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @Description:
 * @author: qing.zhang
 * @date: 2019-06-14
 */
public class Java8Tester {

    /**
     * // 1. 不需要参数,返回值为 5
     * () -> 5
     * <p>
     * // 2. 接收一个参数(数字类型),返回其2倍的值
     * x -> 2 * x
     * <p>
     * // 3. 接受2个参数(数字),并返回他们的差值
     * (x, y) -> x – y
     * <p>
     * // 4. 接收2个int型整数,返回他们的和
     * (int x, int y) -> x + y
     * <p>
     * // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
     * (String s) -> System.out.print(s)
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Callable<Integer> integerCallable = () -> 5;
        System.out.println(integerCallable.call());
        Java8Tester tester = new Java8Tester();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // {} 返回值
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (a, b) -> a / b;

        System.out.println("10+5=" + tester.operate(10, 5, addition));
        System.out.println("10-5=" + tester.operate(10, 5, subtraction));
        System.out.println("10*5=" + tester.operate(10, 5, multiplication));
        System.out.println("10/5=" + tester.operate(10, 5, division));

        GreetingService greetingService = message -> System.out.println(message);
        greetingService.sayMessage("hello world");
        GreetingService greetingService1 = (adf) -> System.out.println(adf);
        greetingService1.sayMessage("hello world all world");

        Converter converte = (message)-> System.out.println(1+message);
        converte.convert(1);




    }

    @Test
    public void testThread(){

        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

        //lambda 表达式
        new Thread(()-> System.out.println("hello world 11")).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world hello");
            }
        };

        runnable.run();

        Runnable runnable1 = ()-> System.out.println("hello world runable lambda");
        runnable1.run();
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    @Test
    public void  testSort(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        //Arrays.sort(players, new Comparator<String>() {
        //    @Override
        //    public int compare(String o1, String o2) {
        //        return o1.length()-o2.length();
        //    }
        //});

        Arrays.sort(players,(String::compareTo));
        System.out.println(Arrays.toString(players));

    }





}
