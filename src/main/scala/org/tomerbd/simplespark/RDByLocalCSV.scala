package org.tomerbd.simplespark

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.rdd.RDD

/**
 * @author tomerb
 * on 26/10/15
 */
trait RDByLocalCSV {
  def readCSV(csvPath: String): RDD[String] = {
    val conf = new SparkConf().setAppName("Simple Local Spark App")
        .setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.textFile(csvPath, 2).cache()
  }
}
