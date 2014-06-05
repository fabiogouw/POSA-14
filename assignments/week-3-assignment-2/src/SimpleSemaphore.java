import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	_permits = permits;
    	_lock = new ReentrantLock(fair);
    	_condition = _lock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	_lock.lockInterruptibly();
    	try {
    		if(_count == _permits)
    			_condition.await();
    		_count++;
    	}
    	finally {
    		_lock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     * @throws InterruptedException 
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	_lock.lock();
    	try {
    		if(_count == _permits)
    			_condition.awaitUninterruptibly();
    		_count++;
    	}
    	finally {
    		_lock.unlock();
    	}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	_lock.lock();
    	try {
    		_count--;
    		_condition.signal();
    	}
    	finally {
    		_lock.unlock();
    	}
    }

    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private final ReentrantLock _lock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private final Condition _condition;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    private int _permits = 0;
    private int _count = 0;
}

