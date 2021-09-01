package cn.linjk.jarvis.common.test;

import com.lmax.disruptor.EventHandler;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DataConsumer
 * @author: linjk
 * @date: 2021/9/1 下午11:12
 * @description:
 */
public class DataConsumer implements EventHandler<Data> {
    private long startTime;
    private long i;

    public DataConsumer() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(Data data, long seq, boolean b) throws Exception {
        i++;
        if (i == Constants.EVENT_NUM_OM) {
            long endTime = System.currentTimeMillis();
            System.out.println("Disruptor take time: " + (endTime - startTime) + "ms");
        }
    }
}
