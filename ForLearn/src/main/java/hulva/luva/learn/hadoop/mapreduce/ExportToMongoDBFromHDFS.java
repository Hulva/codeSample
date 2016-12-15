/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月15日
 *
 */
public class ExportToMongoDBFromHDFS {
	private static final Log log = LogFactory
			.getLog(ExportToMongoDBFromHDFS.class);

	public static class ReadData extends
			Mapper<LongWritable, Text, ObjectId, BSONObject> {

		public void map(Text key, Text value, Context context)
				throws IOException, InterruptedException {
			log.info("Key: " + key);
			log.info("Value: " + value);

			String[] fields = value.toString().split("\t");

			String md5 = fields[0];
			String url = fields[1];
			String date = fields[2];
			String time = fields[3];
			String ip = fields[4];
			BSONObject b = new BasicBSONObject();
			b.put("md5", md5);
			b.put("url", url);
			b.put("date", date);
			b.put("time", time);
			b.put("ip", ip);

			context.write(new ObjectId(), b);
		}
	}

	private static String MONGODB_URL;
	private static String PATH_TO_HDFS;

	public static void main(String args[]) throws IOException,
			ClassNotFoundException, InterruptedException {
		if (args.length < 1) {
			log.info("Usage: hadoop jar ExportToMongoDBFromHDFS.jar [MONGODB_URL] [PATH_TO_HDFS]"
					+ "\n\t"
					+ "MONGODB_URL mongodb://<HOST>:<PORT>/test.weblogs"
					+ "\n\t" + "PATH_TO_HDFS /data/weblogs/mongo_import");
			System.exit(0);
		}
		
		final Configuration conf = new Configuration();
		MongoConfigUtil.setOutputURI(conf, MONGODB_URL);

		log.info("Configuration: " + conf);

		final Job job = Job.getInstance(conf, "Export to Mongo");

		Path in = new Path(PATH_TO_HDFS);
		FileInputFormat.setInputPaths(job, in);

		job.setJarByClass(ExportToMongoDBFromHDFS.class);
		job.setMapperClass(ReadData.class);

		job.setOutputKeyClass(ObjectId.class);
		job.setOutputValueClass(BSONObject.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(MongoOutputFormat.class);

		job.setNumReduceTasks(0);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
