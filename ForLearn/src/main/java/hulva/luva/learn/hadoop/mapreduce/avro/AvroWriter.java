/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce.avro;

import org.apache.avro.Schema;
import org.apache.avro.mapred.AvroJob;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.mortbay.log.Log;

/**
 * @ClassName
 * @Description Use the MapReduce job to read a text file, and then serialize
 *              and persist the WeblogRecord object
 * @author Hulva Luva.H
 * @date 2016年12月16日
 *
 */
public class AvroWriter extends Configured implements Tool {

	@Override
	public int run(String[] arg0) throws Exception {
		Path inputPath = new Path(arg0[0]);
		Path outputPath = new Path(arg0[1]);

		Schema schema = ReflectData.get().getSchema(WeblogRecord.class);

		Configuration conf = getConf();
		JobConf weblogJob = new JobConf(conf, getClass());
		weblogJob.setJobName("Avro writer");
		weblogJob.setNumMapTasks(0);
		weblogJob.setMapperClass(WeblogMapper.class);
		weblogJob.setOutputKeyClass(AvroWrapper.class);
		weblogJob.setOutputValueClass(NullWritable.class);
		weblogJob.setInputFormat(TextInputFormat.class);
		AvroJob.setOutputSchema(weblogJob, schema);
		FileInputFormat.setInputPaths(weblogJob, inputPath);
		FileOutputFormat.setOutputPath(weblogJob, outputPath);

		RunningJob job = JobClient.runJob(weblogJob);
		if (job.isSuccessful()) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			Log.info("Usage: hadoop jar AvroWriter.jar [INPUT_PATH] [OUTPUT_PATH]");
			System.exit(0);
		}
		int returnCode = ToolRunner.run(new AvroWriter(), args);
		System.exit(returnCode);
	}

}
