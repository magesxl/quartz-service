package com.example.pay.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DelayItem<T> implements Delayed {

    //
    private static final long NANO_ORIGIN = System.nanoTime();

    final static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    private static final AtomicLong sequencer = new AtomicLong(0);
    private final long sequenceNumber;   //订单号
    private final long expTime;   //过期时间
    private final T item;        //通知订单信息

    public DelayItem(T submit, long timeout) {
        this.expTime = now() + timeout;
        this.item = submit;
        this.sequenceNumber = sequencer.getAndIncrement();
    }

    public T getItem() {
        return this.item;
    }

    /**
     * 获得延时时间   用过期时间-当前时间
     *
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(expTime - now(), TimeUnit.NANOSECONDS);
        return d;
    }

    /**
     * 用于延时队列的内部比较排序   当前时间的延迟时间  --  比较对象的延迟时间
     *
     * @param other
     * @return
     */
    public int compareTo(Delayed other) {
        if (other == this) return 0;
        if (other instanceof DelayItem) {
            DelayItem<?> x = (DelayItem<?>) other;
            long diff = expTime - x.expTime;
            if (diff < 0) return -1;
            else if (diff > 0) return 1;
            else if (sequenceNumber < x.sequenceNumber) return -1;
            else return 1;
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }
}
