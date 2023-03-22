package com.sydoruk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstRobot implements Runnable {

    final static Logger logger = LoggerFactory.getLogger("FirstRobot");

    private final AtomicInteger totalFuel;
    private final Random rand = new Random();
    public FirstRobot (AtomicInteger totalFuel) {
        this.totalFuel = totalFuel;
    }

    @Override
    public void run() {
        logger.info(LocalTime.now().toString() + " Robot 1 started");
        while (!Thread.currentThread().isInterrupted()) {
            final int number = rand.nextInt(500, 1001);
            totalFuel.addAndGet(number);
            logger.info(LocalTime.now().toString() + " Robot 1 extracted {} fuel, total {} fuel", number, totalFuel);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        logger.info(LocalTime.now().toString() + " Robot 1 finished");
    }
}