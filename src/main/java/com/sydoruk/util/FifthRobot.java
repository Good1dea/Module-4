package com.sydoruk.util;

import com.sydoruk.model.Detail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class FifthRobot implements Runnable {

    final Logger logger = LoggerFactory.getLogger("FifthRobot");
    private final Random rand = new Random();
    private int usedFuel = 0;
    private final AtomicInteger extractedFuel;
    private final CountDownLatch latch;
    private final Detail detail;

    public FifthRobot(final CountDownLatch latch, final AtomicInteger extractedFuel, final Detail detail) {
        this.latch = latch;
        this.extractedFuel = extractedFuel;
        this.detail = detail;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info(LocalTime.now().toString() + " Robot 5 started");
        for (int i = 0; i < 100; i += 10) {
            final int num = rand.nextInt(350, 701);
            boolean success = false;
            while (!success) {
                final int prevSum = extractedFuel.get();
                final int newSum = prevSum - num;
                if (newSum < 0) {
                    logger.info(LocalTime.now().toString() + " Not enough fuel for robot 5, waiting 1 second");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    extractedFuel.set(newSum);
                    success = true;
                    usedFuel += num;
                    logger.info(LocalTime.now().toString() + " Robot 5 consumes {} fuel, fuel left {}", num, extractedFuel);
                }
            }
        }
        logger.info(LocalTime.now().toString() + " Robot 5 finished");
        detail.setUsedFuel(usedFuel);
        detail.setExtractedFuel(extractedFuel.get()+usedFuel);
        Thread.interrupted();
    }
}