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
object ReadEventsLZO {
    def main(args: Array[String]) {
      val someFile = "someeventsfile.txt.seq.lzo"
      val conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
//      conf.set("io.compression.codecs","com.hadoop.compression.lzo.LzopCodec")
//      conf.set("io.compression.codec.lzo.class", "com.hadoop.compression.lzo.LzoCodec")
      val sc = new SparkContext(conf)
//      val result = sc.hadoopFile[Text, Text, LzoTextInputFormat](someFile)
      val result = sc.sequenceFile[org.apache.hadoop.io.NullWritable,org.apache.hadoop.io.Text](someFile)
      result.foreach(x => println(new String(x._2.copyBytes())))
//      println(result.take(10))
//        sc.newAPIHadoopFile(scoreFloorEventFile)(classOf[LzoJsonInputFormat], classOf[LongWritable], classOf[MapWritable])
    }

}