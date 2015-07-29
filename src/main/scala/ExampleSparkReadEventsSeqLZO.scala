/**
 * @author tomerb
 * on 08/06/15
 */

import com.hadoop.mapreduce.LzoTextInputFormat
import org.apache.hadoop.io.{NullWritable, BytesWritable, Text}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Based on https://github.com/holdenk/learning-spark-examples/blob/master/src/main/java/com/oreilly/learningsparkexamples/java/BasicSaveSequenceFile.java
 */
object ExampleSparkReadEventsSeqLZO {
    def main(args: Array[String]) {
      val someFile = "someeventsfile.txt.seq.lzo"
      val conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
      val sc = new SparkContext(conf)
      val result = sc.sequenceFile[org.apache.hadoop.io.NullWritable,org.apache.hadoop.io.Text](someFile)
      result.foreach(x => println(new String(x._2.copyBytes())))
    }
}
