package com.example.pay.delay;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Test {
    private static final long NANO_ORIGIN = System.nanoTime();

    public static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    public static void main(String[] args) {
        System.out.println(NANO_ORIGIN);
        System.out.println(now());
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();
    }
}
