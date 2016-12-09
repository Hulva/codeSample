/**
 * 
 */
package newegg.mis.ec2.luva.learn.corejava.random;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

/**
 * @author lh10
 *
 */
public class RandomTest {

	@Test
	public void givenUsingPlainJava_whenGeneratingRandomLongUnbounded_thenCorrect() {
		// long generatedLong = new Random().nextLong();
		long leftLimit = 1L;
		long rightLimit = 10L;
		long generatedLong = leftLimit
				+ (long) (Math.random() * (rightLimit - leftLimit));
		System.out.print(generatedLong);
	}

	@Test
	public void givenUsingPlainJava_whenGeneratingRandomIntegerUnbounded_thenCorrect() {
		int generatedInteger = new Random().nextInt();
		System.out.print(generatedInteger);
	}

	/*
	 * @Test public void
	 * givenUsingApache_whenGeneratingRandomDoubleBounded_thenCorrect() { double
	 * leftLimit = 1D; double rightLimit = 100D; double generatedDouble = new
	 * RandomDataGenerator().nextUniform( leftLimit, rightLimit); }
	 */

	@Test
	public void givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
		byte[] array = new byte[10]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));

		System.out.println(generatedString);
	}
	
	@Test
	public void givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (new Random().nextFloat() * (rightLimit - leftLimit));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
	}
	
	@Test
	public void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {
	    int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	}
	
	@Test
	public void givenUsingApache_whenGeneratingRandomAlphabeticString_thenCorrect() {
	    String generatedString = RandomStringUtils.randomAlphabetic(10);
	 
	    System.out.println(generatedString);
	}
}
