/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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
public class HdfsReader extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Path inputPath = new Path(args[0]);
		String localOutputPath = args[1];
		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);
		InputStream is = fs.open(inputPath);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(
				localOutputPath));
		IOUtils.copyBytes(is, os, conf);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			Log.info("Usage: hadoop jar HdfsReader.jar [PATH_IN_HDFS] [LOCAL_PATH]");
			System.exit(0);
		}
		int returnCode = ToolRunner.run(new HdfsReader(), args);
		System.exit(returnCode);
	}

}
