/**
 * 
 */
package hulva.luva.learn.corejava.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

import com.google.common.io.ByteStreams;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月30日
 *
 */
public class Stream2Array {

	// Convert using Plain Java
	@Test
	public void givenUsingPlainJava_whenConvertingAnInputStreamToByteArray()
			throws IOException {
		InputStream initialStream = new ByteArrayInputStream(new byte[] { 0, 1,
				2, 3, 4, 5 });
		byte[] targetArray = new byte[initialStream.available()];
		initialStream.read(targetArray);
		System.out.println(Arrays.toString(targetArray));

		byte[] targetArray1 = ByteStreams.toByteArray(initialStream);
		System.out.println(Arrays.toString(targetArray1));
	}

	@Test
	public void givenUsingPlainJavaOnUnknownSizeStream_whenConvertingAnInputStreamToAByteArray_thenCorrect()
			throws IOException {
		InputStream is = new ByteArrayInputStream(new byte[] { 0, 1, 2 }); 

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();
		byte[] byteArray = buffer.toByteArray();
		System.out.println(Arrays.toString(byteArray));
	}
}
