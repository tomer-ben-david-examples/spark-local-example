/**
 * @author tomerb
 * on 08/06/15
 */

import org.apache.spark.{SparkConf, SparkContext}

object Example {
    def main(args: Array[String]) {
        val logFile = "/home/tomerb/Downloads/donation/block_1.csv" // Change this to wherever your csv is.
        val conf = new SparkConf().setAppName("Simple Application")
              .setMaster("local[2]")
        val sc = new SparkContext(conf)
        val logData = sc.textFile(logFile, 2).cache()
        val numAs = logData.filter(line => line.contains("1")).count()
        val numBs = logData.filter(line => line.contains("b")).count()
        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        println(logData.first())
    }
}
