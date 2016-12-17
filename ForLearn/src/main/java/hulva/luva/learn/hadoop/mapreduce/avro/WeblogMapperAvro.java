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
 * @ClassName To read the Avro file format produced by AvroWritter job, we just
 *            need to change the input format and the mapper class. First, set
 *            the input format and the input schema.
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月16日
 *
 */
public class WeblogMapperAvro extends MapReduceBase implements
		Mapper<AvroWrapper<WeblogRecord>, NullWritable, Text, NullWritable> {

	private static final Log log = LogFactory.getLog(WeblogMapper.class);

	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

	@Override
	public void map(AvroWrapper<WeblogRecord> key, NullWritable value,
			OutputCollector<Text, NullWritable> output, Reporter reporter)
			throws IOException {
		WeblogRecord weblogRecord = key.datum();
		// process the weblogRecord
	}
}
