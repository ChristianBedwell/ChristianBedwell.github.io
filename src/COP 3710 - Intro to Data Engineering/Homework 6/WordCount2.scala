import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level

object WordCount2 {
  def main(args: Array[String]) {
    
    // set hadoop directory
    System.setProperty("hadoop.home.dir", "c:\\hadoop");
    
    // comment next two lines if you want to see all the logs
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)

    // connect to spark driver
    val conf = new SparkConf().setAppName("WordCount2").setMaster("local")
    val spark = new SparkContext(conf)

    // load the text file
    val lines = spark.textFile("basketball_words_only.txt")
      
    // for each word in the file, find which word that follows the most
    val secondWord = lines.flatMap(line => line.split(" ").dropRight(1))
    val firstWord = lines.flatMap(line => line.split(" ").drop(1))
    val wordPair = secondWord zip firstWord
    val wordPairFreq = wordPair.map(word => (word,1)).reduceByKey(_+_)
    val sortedWordPairs = wordPairFreq.sortBy(w => w._2, false)
    sortedWordPairs.take(3).foreach(w => println("\"" + w._1._1 + "\"" + " is followed by " + "\"" + w._1._2 + "\" " + w._2 + " times."))
  }
}