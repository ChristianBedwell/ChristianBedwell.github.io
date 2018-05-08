import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level

object WordCount {
  def main(args: Array[String]) {
    
    // setting hadoop directory
    System.setProperty("hadoop.home.dir", "c:\\hadoop");
    
    // comment next two lines if you want to see all the logs
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)

    // connect to spark driver
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val spark = new SparkContext(conf)

    // load the text file
    val lines = spark.textFile("basketball_words_only.txt")
    
    // split the file into individual words
    val words = lines.flatMap(line => line.split(" "))
    
    // use MapReduce to count words
    val pairs = words.map(word => (word, 1))
    val counts = pairs.reduceByKey(_+_)
    val result = counts.collect()
    
    // find the words that have an average frequency greater than 3% 
    val denominator = counts.map(_._2).sum()
    print("Words that account for at least 3% are ")
    result.foreach(x => {
      if(x._2 / denominator > 0.03)
        print("\"" + x._1 + "\", ")
    })
    println("\n")
    
    // find the 4 most frequent words in the document and sort in descending
    val swapKeyValue = counts.map(_.swap)
    val sortDescending = swapKeyValue.sortByKey(false, 1)
    val topFour = sortDescending.take(4)
    topFour.foreach(x => println(x._2 + " appears " + x._1 + " times"))   
  }
}