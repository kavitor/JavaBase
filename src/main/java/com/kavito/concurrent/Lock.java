package com.kavito.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2020/11/6 10:48 上午
 */
public class Lock {




    public static void main(String[] args) {


        Thread a = new Thread(()->{

            System.out.println("a coming 。。。");

            // 被阻塞，需要凭证放行
            LockSupport.park();

            System.out.println("a 唤醒");

        },"a");
        a.start();

        try {TimeUnit.SECONDS.sleep(2L);} catch (InterruptedException e) {e.printStackTrace();}

        Thread b = new Thread(()->{
            System.out.println("b coming。。。");
            // 发放凭证，不能累加
            LockSupport.unpark(a);

        },"b");
        b.start();

    }
}
