# How it works in that demo

> In the `FolderProcessor` class, each task processes the content of a folder. The content would might has two kinds of  elements:

- Files
- Other folders

> If the task finds a folder, it creates another Task object to process that folder and sends it to the pool using the `fork()` method. This method sends the task to the pool that will execute it if it has a free worker-thread or it can create a new one. The method returns immediately, so the task can continue processing the content of the folder. For every file, a task compares its extension with the one it's looking for and, if they are equal, adds the name of the file to the list of results.

> Once the task has processed all the content of assigned folder, it waits for the finalization of all the tasks it sends to the pool using the `join()` method. This method called in a task waits for the finalization of its execution and returns the value returned by the `compute()` method. The task groups the results of all the tasks it sent with its own results and returns that list as a return value of the `compute()` method.
 
# Difference between Fork/Join Framework and ExecutorService

> 这两者最大的不同就是 the work-stealing algorithm. 与 Executor 框架不同，当任务正在等待其使用`join()`操作创建的子任务完成时，正在执行该任务的线程（称为工作线程）查找尚未执行的其他任务并执行。通过这种方式，线程充分利用其运行时间，从而提高应用程序的性能。

## 在JDK中存在的实现
- Java SE8中介绍道，`java.util.Arrays`类中`parallelSort()`方法就有相应实现。这些方法类似于`sort()`，但是是通过 fork/join框架来实现的并发。在多处理器系统上对大型数组进行排序时，使用并行排序（parallel sorting）比顺序排序更快。