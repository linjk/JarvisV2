package cn.linjk.jarvis.common.test.mock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ReentrantLock
 * @author: linjk
 * @date: 2021/9/10 上午12:26
 * @description:
 */
public class ReentrantLock implements Lock {
    /**
     * volatile 变量在多线程中的内存可见性
     * 0  - 未加锁
     * >0 - 当前lock是加锁状态
     */
    private volatile int state;

    /**
     * 当前独占锁的线程
     */
    private Thread exclusiveOwnerThread;

    /**
     * 当前占用锁的线程
     */
    private Node head;

    private Node tail;

    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    /**
     * 阻塞的线程
     */
    static final class Node {
        Node prev;
        Node next;
        Thread thread;
    }

    private void setHead(Node node) {
        this.head = node;
        // 获取锁成功，置空
        node.thread = null;
        node.prev = null;
    }

    public int getState() {
        return state;
    }

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe)f.get(null);

            stateOffset = unsafe.objectFieldOffset(ReentrantLock.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(ReentrantLock.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(ReentrantLock.class.getDeclaredField("tail"));
        }
        catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * 模拟公平锁
     */
    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

    /**
     * 竞争资源
     * 1. 尝试获取锁，成功返回
     * 2. 抢占锁失败，阻塞当前线程
     */
    private void acquire(int arg) {
        if (!tryAcquire(arg)) {
            //
        }
    }



    /**
     * 尝试获取锁，不阻塞线程
     */
    private boolean tryAcquire(int arg) {
        if (state == 0) {
            if (!hasQueuePredecessor() && compareAndSetState(0, arg)) {
                this.exclusiveOwnerThread = Thread.currentThread();
                return true;
            }
        }
        // 锁重入
        else if (Thread.currentThread() == this.exclusiveOwnerThread) {
            int c = getState();
            c = c + arg;
            // todo 越界判断
            this.state = c;

            return true;
        }
        /*
         * 获取锁失败
         * 1. CAS失败
         * 2. state > 0 且当前线程不是持有锁的线程
         */
        return false;
    }

    /**
     * 当前线程前面是否有等待线程
     */
    private boolean hasQueuePredecessor() {
        return false;
    }

    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
}
