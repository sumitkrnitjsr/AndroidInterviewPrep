package com.maskedgeek.designinterviewprep.concurrency;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*
Use pooled threads,
Uses Factory Design Pattern,
Can set size for pool, Reject tasks after all threads running throw exception
Kill threads after maxIdleTime
Allow Queuing of Threads
Shut Down Pool
*/

class DesignThreadPoolExecutor {

    // Atomic data - > workerCount, runState (RUNNING, SHUTDOWN, STOP, TIDYING, TERMINATED)
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
    private  static final int RUNNING  = -1 << COUNT_BITS;
    private  static final int SHUTDOWN  = 0 << COUNT_BITS;
    private  static final int STOP  = 1 << COUNT_BITS;
    private  static final int TIDYING  = 2 << COUNT_BITS;
    private  static final int TERMINATED  = 3 << COUNT_BITS;
    private final AtomicInteger wrkrCntStates = new AtomicInteger(RUNNING | 0);

    private static int runState(int c) { return c & -CAPACITY; }
    private static int workerCount(int c) { return c & CAPACITY; }
    private static boolean isRunning(int c) { return c < SHUTDOWN; }

    private boolean compareAndIncrementWorkerCount(int expect) {
        return wrkrCntStates.compareAndSet(expect, expect + 1);
    }

    private boolean compareAndDecrementWorkerCount(int expect) {
        return wrkrCntStates.compareAndSet(expect, expect + 1);
    }

    private void forceDecrementWorkerCount() {
        do {

        }while(!compareAndDecrementWorkerCount(wrkrCntStates.get()));
    }

    private  BlockingQueue<Runnable> tasksQueue;

    // Uncomment later
    //private final ReentrantLock mainLockWorkerAccess;

    private final HashSet<Worker> workers = new HashSet<Worker>();

    // Uncomment later
    // private final Condition awaitTermination = mainLockWorkerAccess.newCondition();

    private long completedTaskCount;

    private volatile ThreadFactory threadFactory;

    private volatile long keepAliveTime;

    private volatile int corePoolSize;

    private volatile int maxPoolSize;



    private final class Worker extends AbstractQueuedSynchronizer implements Runnable {
        // Uncomment later
        //final Thread thread;

        Runnable firstTask;

        volatile long completedTasks;

        Worker(Runnable runnable) {
            setState(-1);
            this.firstTask = runnable;
            // Uncomment later
            //this.thread = getThreadFactory().newThread(this);
        }

        @Override
        public void run() {
            // Uncomment later
            // runWorker(this);
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        @Override
        protected boolean tryAcquire(int unused) {
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
    }
}