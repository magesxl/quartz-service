package com.example.pay.delay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);//时间单元:十分钟
    private static final Long TIME_UNIT = 2L;
    private static final String RES_SUCCESS = "success";//返回结果
    private static final int maxNotifyTimes = 5;//返回结果
    private static Task instance = new Task();
    private static BlockingQueue<Runnable> queue1 = new ArrayBlockingQueue(100);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
            5,
            3L,
            TimeUnit.SECONDS,
            queue1,
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static Task getInstance() {
        return instance;
    }

    // DelayQueue队列没有大小限制,因此向队列插数据不会阻塞
    // DelayQueue中的元素只有当其指定的延迟时间到了,才能够从队列中获取到该元素。否则线程阻塞
    private static DelayQueue<DelayItem<Pair<String, RetMessage>>> queue = new DelayQueue<>();

    private Thread taskThread;

    private Task() {
        taskThread = new Thread(
                this::execute
        );
        taskThread.setName("Task Thread");
        taskThread.start();
    }

    private void execute() {
        for (; ; ) {
            try {
                DelayItem<Pair<String, RetMessage>> delayItem = queue.take();
                if (delayItem != null) {// 到期处理
                    Pair<String, RetMessage> pair = delayItem.getItem();
                    RetMessage msg = pair.getSecond();
                    if (!msg.isSuccess() && msg.getTimes() <= 5) {
                        Map<String, String> paramMap = new HashMap<>();
                        paramMap.put("reqData", msg.getReqData());
//                            String httpResult = HttpsUtil.getInstance().doPostRetString(msg.getUrl(), null, paramMap);
                        String httpResult = "successa";
                        LOGGER.info("第{}次异步回调,返回结果{},返回参数:{},响应结果:{}", msg.getTimes(), httpResult, paramMap.get("reqData"), RES_SUCCESS.equals(httpResult));
                        if (!RES_SUCCESS.equals(httpResult)) {
                            msg.setTimes(msg.getTimes() + 1);
                            msg.setSuccess(false);
                            Task.getInstance().put(pair.getFirst(), msg);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                LOGGER.warn(e.getMessage(), e);
                break;
            }
        }
    }

    /**
     * 添加通知对象 * * @param key * 唯一性key值,建议为:merchantNo + orderNo * @param msg * 响应报文
     */
    public void put(String key, RetMessage msg) {
        if (queue.contains(key)) {
            queue.remove(key);
        }
        //超时时间  秒转换位毫秒
//        long nanoTime = TIME_UNIT + TimeUnit.NANOSECONDS.convert((msg.getTimes() - 1) * TIME_UNIT, TimeUnit.SECONDS);
        double a = Math.pow(TIME_UNIT.doubleValue(), (double) msg.getTimes() - 1);
        long b = new Double(a).longValue();
        long nanoTime = TimeUnit.NANOSECONDS.convert(b, TimeUnit.SECONDS);
        queue.put(new DelayItem<>(new Pair<>(key, msg), nanoTime));
    }


    public static void main(String[] args) throws Exception {
        String orderNo = System.currentTimeMillis() + "";
        RetMessage msg = new RetMessage("www.baidu.com", "a=1&;b=2");
        Task.getInstance().put(orderNo, msg);
        double a = Math.pow(TIME_UNIT.doubleValue(), (double) msg.getTimes() - 1);
        long b = new Double(a).longValue();
        long nanoTime = TimeUnit.NANOSECONDS.convert(b, TimeUnit.SECONDS);
        System.out.println(a);
        System.out.println(b);
        System.out.println(nanoTime);
    }

    public void addToNotifyTaskDelayQueue(int notifyTimes,RetMessage msg) {
        if (notifyTimes < maxNotifyTimes) {
            // 未超过最大通知次数，继续下一次通知
            double a = Math.pow(TIME_UNIT.doubleValue(), (double) msg.getTimes() - 1);
            long b = new Double(a).longValue();
            long nanoTime = TimeUnit.NANOSECONDS.convert(b, TimeUnit.SECONDS);
        }
    }
}