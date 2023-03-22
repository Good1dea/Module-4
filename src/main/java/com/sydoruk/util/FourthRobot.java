package com.sydoruk.util;

import com.sydoruk.model.Detail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class FourthRobot implements Runnable {

    final static Logger logger = LoggerFactory.getLogger("FourthRobot");
    private final Random random = new Random();
    private int brokenChips = 0;
    private int sum = 0;
    private boolean finished = false;
        private final CountDownLatch latch;
    private final CountDownLatch latch1;
    private final Detail detail;

    public FourthRobot(final CountDownLatch latch, final CountDownLatch latch1, final Detail detail){
        this.latch = latch;
        this.latch1 = latch1;
        this.detail = detail;
    }

    @Override
    public void run() {
        try {
            latch1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info(LocalTime.now().toString() + " Robot 4 started");
        while (!finished) {
            final int num = random.nextInt(25,36);
            sum += num;
            logger.info(LocalTime.now().toString() + " Robot 4 generated {}, chip is {} % build", num, sum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (sum >= 100) {
                finished = true;
            } else if (random.nextDouble() <= 0.3) {
                sum = 0;
                brokenChips ++;
                logger.info(LocalTime.now().toString() + " Robot 4 broken chip and starts work again, total broken chips {}", brokenChips);
            }
        }
        logger.info(LocalTime.now().toString() + " Robot 4 finished");
        detail.setBrokenChips(brokenChips);
        latch.countDown();
        Thread.interrupted();
    }
}