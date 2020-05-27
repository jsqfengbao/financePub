//package com.finance.thread;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.CountDownLatch;
//
///**
// * author jinsq
// *
// * @date 2019/5/22 17:27
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CountDownLatchTest {
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    //模拟短时间内的并发请求量
//    private static final int threadNum =2000;
//    //倒计时器，用于模拟高并发
//    private CountDownLatch cdl = new CountDownLatch(threadNum);
//    private static int i = 0;
//
//    @Test
//    public void test(){
//        for(int i =1;i<=threadNum;i++){
//            MyThread myThread = new MyThread(cdl);
//            Thread thread = new Thread(myThread);
//            thread.start();
//        }
//        try {
//            cdl.await();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    class MyThread implements Runnable{
//        private CountDownLatch countDownLatch;
//        public MyThread(CountDownLatch countDownLatch){
//            this.countDownLatch = countDownLatch;
//        }
//        @Override
//        public void run(){
//            kafkaTemplate.send("result",(i++)+"");
//            countDownLatch.countDown();
//        }
//    }
//}
