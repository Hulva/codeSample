/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年2月7日
 *
 */
public class WordCount extends Configured implements Tool {

  public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    /**
     * the <code>key</code> is the byte offset in the file<br>
     * the <code>value</code> is the text of that line<br>
     * The mapper is executed once for each line of text in the input source, and every time it
     * takes the line and breaks it into words. It then uses the Context object to output (more
     * commonly known as emitting) each new key/value of the form (word, 1). These are our K2/V2
     * values.
     */
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      String[] words = value.toString().split(" ");
      for (String str : words) {
        word.set(str);
        context.write(word, one);
      }
    }
  }

  public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * the input to the reducer is a key and a corresponding list of values, and there is some magic
     * that happens between the map and reduce methods to collect the values for each key that
     * facilitates this—called the shuffle stage. Hadoop executes the reducer once for each key, and
     * the preceding reducer implementation simply counts the numbers in the Iterable object and
     * gives output for each word in the form of (word, count). These are our K3/V3 values.
     */
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int total = 0;
      for (IntWritable val : values) {
        total++;
      }
      context.write(key, new IntWritable(total));
    }

  }

  @Override
  public int run(String[] args) throws Exception {
    Configuration conf = getConf();

    args = new GenericOptionsParser(conf, args).getRemainingArgs();

    Job job = Job.getInstance(conf);

    job.setJarByClass(WordCount.class);
    job.setMapperClass(WordCountMapper.class);
    job.setReducerClass(WordCountReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    return (job.waitForCompletion(true) ? 0 : 1);
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new WordCount(), args);
    System.exit(exitCode);
  }

}
