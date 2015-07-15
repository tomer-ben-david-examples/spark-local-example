/**
 * @author tomerb
 * on 08/06/15
 */

import java.io.{File, IOException}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file._
import org.apache.spark.api.java.function.PairFunction

import scala.throws
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapred.SequenceFileOutputFormat
import org.apache.spark.{SparkConf, SparkContext};

/**
 * Based on https://github.com/holdenk/learning-spark-examples/blob/master/src/main/java/com/oreilly/learningsparkexamples/java/BasicSaveSequenceFile.java
 */
object SparkExampleWriteSeqLZO {
    class ConvertToWritableTypes extends PairFunction[Tuple2[String, Integer], Text, IntWritable] {
      override def call(t: (String, Integer)): (Text, IntWritable) = (new Text(t._1), new IntWritable(t._2))
    }


    def main(args: Array[String]) {
        val dir = "output"
        deleteDirectory(dir)
        val conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
        val sc = new SparkContext(conf)
        val input = Seq(("coffee", 1), ("coffee", 2), ("pandas", 3))
        val inputRDD = sc.parallelize(input)
        inputRDD.saveAsSequenceFile(dir)
//        inputRDD.saveAsHadoopFile(dir, classOf[Text], classOf[IntWritable], classOf[SequenceFileOutputFormat[_,_]])
    }

    def recreateDirectory(path: String): Path = {
      deleteDirectory(path)
      Files.createDirectory(Paths.get(path))
    }

    def deleteDirectory(path: String) {
        val dir = Paths.get(path)
        if (Files.exists(dir)) {
          val target = Files.walkFileTree(dir, new SimpleFileVisitor[Path]() {
            @throws(classOf[IOException])
            override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
              Files.delete(file)
              FileVisitResult.CONTINUE
            }

            @throws(classOf[IOException])
            override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
              Files.delete(dir)
              FileVisitResult.CONTINUE
            }
          })
          println(s"$target has been deleted")
        }
    }
    

//    val testFile = "./test.txt"
//    Files.write(Paths.get(testFile), "lets work on this string".getBytes("UTF-8"))

}