package com.sydoruk.util;

import com.sydoruk.model.Detail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class FactoryUtil {

    private final AtomicInteger totalFuel = new AtomicInteger(0);

    public Detail robotsWork() {

        final Detail detail = new Detail();
        final long startTime = System.currentTimeMillis();
        CountDownLatch latch1 = new CountDownLatch(2);
        CountDownLatch latch2 = new CountDownLatch(1);
        Thread thread1 = new Thread(new FirstRobot(totalFuel));
        Thread thread2 = new Thread(new SecondAndThirdRobots(latch1, 2));
        Thread thread3 = new Thread(new SecondAndThirdRobots(latch1, 3));
        Thread thread4 = new Thread(new FourthRobot(latch2, latch1, detail));
        Thread thread5 = new Thread(new FifthRobot(latch2, totalFuel, detail));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        final long endTime = System.currentTimeMillis();
        final double totalTimeSeconds = (endTime - startTime) / 1000;
        detail.setProductionTime(totalTimeSeconds);
        return  detail;
    }
}