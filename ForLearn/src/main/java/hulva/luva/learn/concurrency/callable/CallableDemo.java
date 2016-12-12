/**
 * 
 */
package hulva.luva.learn.concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName
 * @Description In the example, `FactorialCalculator` is type of `Callable`. It
 *              means override `call()` method and after calculation, it will
 *              return the result from `call()` method. And the result can be
 *              retrieved from `Future` reference held by main program.
 * @author Hulva Luva.H
 * @date 2016年12月7日
 *
 */
public class CallableDemo {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);

		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			// Send a `Callable` object to be executed in an executor using the
			// `submit()` method. This method receives a `Callable` object as a
			// parameter and returns a `Future` object that can be used with two
			// main objectives:
			// a) Control the status of the task: you can cancel the task and
			// check if it has finished. For this purpose, you have used the
			// `isDone()` method to check if the tasks had finished.
			// b) Get the result returned by the `call()` method.
			// c) The `Future` interface provides another version of the `get()`
			// method i.e. `get(long timeout, TimeUnit unit)`. If the result of
			// task isn't available, waits for it for the specified time. If the
			// specified period of time passes and the result isn't available
			// yet, the method will returns a `null` value.
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		for (Future<Integer> future : resultList) {
			try {
				System.out.println("Future result is - " + future.get() + " - "
						+ "; And Task done is " + future.isDone());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shut down the executor service now
		executor.shutdown();
	}
}
