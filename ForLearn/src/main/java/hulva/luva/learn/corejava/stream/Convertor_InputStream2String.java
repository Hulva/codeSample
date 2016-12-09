/**
 * 
 */
package hulva.luva.learn.corejava.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.common.io.CharStreams;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 */
public class Convertor_InputStream2String {

	public static void main(String[] args) throws IOException {
		String originalString = randomAlphabetic(8);
		InputStream inputStream = new ByteArrayInputStream(
				originalString.getBytes());

		System.out.println(originalString);
		// With guava
		/*
		 * ByteSource byteSource = new ByteSource() {
		 * 
		 * @Override public InputStream openStream() throws IOException { return
		 * inputStream; } }; String text =
		 * byteSource.asCharSource(Charsets.UTF_8).read();
		 */
		// simpler way
		String text = null;
		// try-with-resources syntax
		try (final Reader reader = new InputStreamReader(inputStream)) {
			text = CharStreams.toString(reader);
		}
		System.out.println(text);

		// Convert with Apache Commons IO
		String textApacheIO = IOUtils.toString(inputStream,
				StandardCharsets.UTF_8.name());
		System.out.println(textApacheIO);
		// use StringWriter
		StringWriter writer = new StringWriter();
		String encoding = StandardCharsets.UTF_8.name();
		IOUtils.copy(inputStream, writer, encoding);
		System.out.println(writer.toString());

		// Convert with Plain Java
		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(new InputStreamReader(
				inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		}
		System.out.println(textBuilder.toString());
	}

	private static String randomAlphabetic(int i) {
		String charactors = "";
		StringBuilder result = new StringBuilder();
		for (char a = 'A'; a <= 'Z'; a++) {
			charactors += a;
		}
		for (char a = 'a'; a <= 'z'; a++) {
			charactors += a;
		}
		int index;
		for (int c = 0; c < i; c++) {
			index = (int) (Math.random() * charactors.length());
			result.append(charactors.charAt(index));
		}
		return result.toString();
	}
}
