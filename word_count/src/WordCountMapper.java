import java.io.IOException;

import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    
    @Override
    protected  void map(LongWritable key,Text value,Context context) throws Exception{
        String text=value.toString();
        StringTokenizer words=new StringTokenizer(text," ,.?");

        while(words.hasMoreTokens()){
            String word=words.nextToken().toLowerCase();

            if(word.equals(""))continue;

            context.write(new Text(word),new IntWritable(1));
        }

    }
}