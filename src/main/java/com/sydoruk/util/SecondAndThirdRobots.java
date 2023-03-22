package com.sydoruk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SecondAndThirdRobots implements Runnable {

    final static Logger logger = LoggerFactory.getLogger("SecondAndThirdRobots");
    private static int construction;
    private final Random rand = new Random();
    private final CountDownLatch latch;
    private final int id;

    public SecondAndThirdRobots(final CountDownLatch latch, final int id) {
        construction = 0;
        this.latch = latch;
        this.id = id;
    }

    @Override
    public void run() {
        logger.info(LocalTime.now().toString() + " Robot {} started", id);
        while (construction < 100) {
            int num = rand.nextInt(10,21);
            construction += num;
            logger.info(LocalTime.now().toString() + " Robot {} generated {}, construction is {} % build", id, num, construction);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(LocalTime.now().toString() + " Construction is 100 % build, robot {} finished", id);
        latch.countDown();
        Thread.interrupted();
    }
}