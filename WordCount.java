package wordcount;

//import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.FileInputFormat;
//import org.apache.hadoop.mapreduce.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool
{

	@Override
	public int run(String[] args) throws Exception 
	{
		if(args.length<2)
		{
			System.out.println("plz check input output directory");
			return -1;
		}
		
		JobConf conf=new JobConf(WordCount.class);
		FileInputFormat.setInputPaths(conf,new Path("/Filein.txt"));
		FileOutputFormat.setOutputPath(conf,new Path("/Fileout.txt"));
		
		conf.setMapperClass(WordMapper.class);
		conf.setReducerClass(WordReducer.class);
		
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
		
		
		return 0;
	}
	
	public static void main(String args[]) throws Exception
	{
		int exitCode= ToolRunner.run(new WordCount(), args);
		System.exit(exitCode);
		
	}

}
