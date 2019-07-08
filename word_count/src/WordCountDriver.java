import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class WordCountDriver extends Configured implements Tool{
    
    
    public static void main(String[] args) throws Exception {

           int exitCode=ToolRunner.run(new WordCountDriver(),args);
            System.exit(exitCode);
    }   

    @Override
    public int run(String[] args)throws Exception {
        if(args.length<2){
            System.err.printf("Invalid arguments\n",getClass().getName());
            ToolRunner.printGenericCommandUsage(System.err);
            return 1;
        }

        String inputDir=args[0];
        String outputDir=args[1];

        Configuration config=new Configuration();
        Job job=Job.getInstance(config,"word count");

        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);

        job.setReducerClass(WordCountReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,new Path(inputDir));

        FileOutputFormat.setOutputPath(job,new Path(outputDir));

        boolean success= job.waitForCompletion(true);

        if(!success){
            throw new IllegalStateException("job word count faild");
        }

        return 0;
    }
}
