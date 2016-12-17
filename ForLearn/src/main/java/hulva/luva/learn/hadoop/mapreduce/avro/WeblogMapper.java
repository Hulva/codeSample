/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce.avro;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.avro.mapred.AvroWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * @ClassName
 * @Description Write a mapper that reads a row from weblog_entries.txt and create an instance of WeblogRecord.
 * @author Hulva Luva.H
 * @date 2016年12月16日
 *
 */
public class WeblogMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, AvroWrapper<WeblogRecord>, NullWritable> {

	private static final Log log = LogFactory.getLog(WeblogMapper.class);

	private AvroWrapper<WeblogRecord> outputRecord = new AvroWrapper<WeblogRecord>();
	private WeblogRecord weblogRecord = new WeblogRecord();

	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<AvroWrapper<WeblogRecord>, NullWritable> output,
			Reporter reporter) throws IOException {
		String[] tokens = value.toString().split("\t");
		String cookie = tokens[0];
		String page = tokens[1];
		String date = tokens[2];
		String time = tokens[3];
		String formattedDate = date + ":" + time;
		Date timestamp = null;
		try {
			timestamp = dateFormatter.parse(formattedDate);
		} catch (ParseException e) {
			log.error("date parse failed: " + e.getMessage() + "\n\t" + value);
			// e.printStackTrace(); ignore records with invalid dates
			return;
		}
		String ip = tokens[4];

		weblogRecord.setCookie(cookie);
		weblogRecord.setDate(timestamp);
		weblogRecord.setIp(ip);
		weblogRecord.setPage(page);
		outputRecord.datum(weblogRecord);
		output.collect(outputRecord, NullWritable.get());
	}

}
