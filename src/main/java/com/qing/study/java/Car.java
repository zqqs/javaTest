package com.qing.study.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qing.zhang
 * @date: 2019-06-14
 */
public class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car){
        System.out.println("Car collide "+car.toString());
    }

    public void follow(final Car another){
        System.out.println("Following the"+another.toString());
    }

    public void repair(){
        System.out.println("Repaired "+this.toString());
    }

    @Test
    public void test(){
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);

    }
}
