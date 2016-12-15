/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月15日
 *
 */
public class ImportDataFromMongo {
	private static final Log log = LogFactory.getLog(ImportDataFromMongo.class);

	public static class ReadDataFromMongo extends
			Mapper<Object, BSONObject, Text, Text> {
		public void map(Object key, BSONObject value, Context context)
				throws IOException, InterruptedException {
			log.info("Key: " + key);
			log.info("Value: " + value);

			String md5 = value.get("md5").toString();
			String url = value.get("url").toString();
			String date = value.get("date").toString();
			String time = value.get("time").toString();
			String ip = value.get("ip").toString();
			String output = "\t" + url + "\t" + date + "\t" + time + "\t" + ip;

			context.write(new Text(md5), new Text(output));
		}
	}

	private static String MONGODB_URL;
	private static String PATH_TO_HDFS;

	public static void main(String args[]) throws IOException,
			ClassNotFoundException, InterruptedException {

		if (args.length < 1) {
			log.info("Usage: hadoop jar ImportDataFromMongo.jar [MONGODB_URL] [PATH_TO_HDFS]"
					+ "\n\t"
					+ "MONGODB_URL mongodb://<HOST>:<PORT>/test.weblogs"
					+ "\n\t" + "PATH_TO_HDFS /data/weblogs/mongo_import");
			System.exit(0);
		}
		MONGODB_URL = args[0];

		final Configuration conf = new Configuration();
		MongoConfigUtil.setInputURI(conf, MONGODB_URL);
		MongoConfigUtil.setCreateInputSplits(conf, false);
		log.info("Configuration: " + conf);

		final Job job = Job.getInstance(conf, "Mongo Import");

		Path out = new Path(PATH_TO_HDFS);
		FileOutputFormat.setOutputPath(job, out);
		job.setJarByClass(ImportDataFromMongo.class);
		job.setMapperClass(ReadDataFromMongo.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setInputFormatClass(MongoInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setNumReduceTasks(0);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
