package com.lugew.winsim.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * twitter snowflake
 * generate id like uuid but increase
 *
 * @author LuGew
 * @since 2020/5/1
 */
@UtilityClass
@Slf4j
public class Snowflake {
    private final long workerId = 1L;
    private final long dataCenterId = 1L;
    private final long startTimeMills = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long dataCenterIdBits = 5L;
    private final long sequenceBits = 12L;
    private final long maxWorkerId = ~(-1L << workerIdBits);
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private final long sequenceMask = ~(-1L << sequenceBits);
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    {
        ensureWorkerId();
        ensureDataCenterId();
        log.info("worker starting. timestamp left shift {}, data center id bits {}, worker id bits {}, sequence bits {}, worker id {}",
                timestampLeftShift, dataCenterIdBits, workerIdBits, sequenceBits, workerId);
    }

    private void ensureWorkerId() {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("worker Id can't be greater than {0} or less than 0,work id:{1}");
        }
    }

    private void ensureDataCenterId() {
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("data center Id can't be greater than {0} or less than 0,data center id:{1}");
        }

    }

    public synchronized long nextId() {
        long timestamp = getTimestamp();
        if (timestamp < lastTimestamp) {
            log.error("clock is moving backwards.  Rejecting requests until {}.", lastTimestamp);
            throw new IllegalArgumentException("Clock moved backwards.  Refusing to generate id for {0} milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - startTimeMills) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long getTimestamp() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = getTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }

}
