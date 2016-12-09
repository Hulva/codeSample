/**
 * 
 */
package newegg.mis.ec2.luva.learn.corejava.scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author lh10
 *
 */
public class ScannerDemoTest {
	@Test
	public void whenReadFileWithScanner_thenCorrect() throws IOException {
		Scanner scanner = new Scanner(new File(
				"src\\main\\java\\test.txt"));

		assertTrue(scanner.hasNext());
		assertEquals("Hello,", scanner.next());
		assertEquals("Luva!", scanner.next());

		scanner.close();
	}

	@Test
	public void whenConvertInputStreamToString_thenConverted()
			throws FileNotFoundException {
		String expectedValue = "Hello, Luva!";
		FileInputStream inputStream = new FileInputStream("D:\\workspace\\ForLearn\\src\\main\\java\\test.txt");
		Scanner scanner = new Scanner(inputStream);
		//scanner.useDelimiter(", ");

		String result = scanner.nextLine();
		System.out.print(result);
		assertEquals(expectedValue, result);

		scanner.close();
	}
}
