# Synchronization
* Using `synchronized` keywords.
* Another mechanism fro the synchronization of blocks of code based on the `Lock` interface and classes that implement it(such as `ReentrantLock`)

## Lock interface
> A java.util.concurrent.locks.Lock is a thread synchronization mechanism just like synchronized blocks. A Lock is, however, more flexible and more sophisticated than a synchronized block. Since Lock is an interface, you need to use one of its implementations to use a Lock in your applications. ReentrantLock is one such implementation of Lock interface.

A simple use:
```
Lock lock = new ReentrantLock();
 
lock.lock();
 
// critical section
 
lock.unlock();
```

## Difference between Lock Interface and synchronized keyword

The main differences between a Lock and a synchronized block are:

1) Having a timeout trying to get access to a synchronized block is not possible. Using Lock.tryLock(long timeout, TimeUnit timeUnit), it is possible.
2) The synchronized block must be fully contained within a single method. A Lock can have it’s calls to lock() and unlock() in separate methods.

# Warn
> You have to be very careful with the use of Locks to avoid deadlocks . This situation occurs when two or more threads are blocked waiting for locks that never will be unlocked. For example, a thread (A) locks a Lock (X) and a thread (B) locks a Lock (Y). If now, the thread (A) tries to lock the Lock (Y) and the thread (B) simultaneously tries to lock the Lock (X), both threads will be blocked indefinitely, because they are waiting for locks that will never be liberated. Note that the problem occurs, because both threads try to get the locks in the opposite order.
