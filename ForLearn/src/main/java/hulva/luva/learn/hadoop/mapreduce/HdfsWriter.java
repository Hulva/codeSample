/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.mortbay.log.Log;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月15日
 *
 */
public class HdfsWriter extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		String localInputPath = args[0];
		Path outputPath = new Path(args[1]);
		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);
		OutputStream os = fs.create(outputPath);
		InputStream is = new BufferedInputStream(new FileInputStream(
				localInputPath));
		IOUtils.copyBytes(is, os, conf);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			Log.info("Usage: hadoop jar HdfsWriter.jar [LOCAL_PATH] [PATH_IN_HDFS]");
			System.exit(0);
		}
		int returnCode = ToolRunner.run(new HdfsWriter(), args);
		System.exit(returnCode);
	}

}
