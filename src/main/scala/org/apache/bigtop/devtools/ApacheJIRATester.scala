package org.apache.bigtop.devtools


import scala.sys.process._
import java.util.Calendar
import java.util.TimeZone
import net.rcarz.jiraclient.JiraClient
import net.rcarz.jiraclient.BasicCredentials
import net.rcarz.jiraclient.Issue.SearchResult
import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.FileSystems

/**
 * This is a JIRA Test application.
 * Inputs: A project name + date.
 * Outputs: A +1/-1.
 * Side effects: Auto review
 */
object ApacheJIRATester {
  
  object PatchReview extends Enumeration {
	  val Plus1, Minus1 = Value
  }
  /**
   * The JIRA project (i.e. bigtop).
   */
  val usage = """
     [project (i.e. bigtop,hadoop,flume,sqoop,cli,...)]
  """
  def main(args: Array[String]) {
    if (args.length == 0) println(usage)
    val arglist = args.toList

    /**
     * Parse out a map of the options.
     */
    def nextOption(map :  Map[String, String], list: List[String]) :  Map[String, String] = {
      def isSwitch(s : String) = (s(0) == '-')
      list match {
        case Nil => map
        case "project" :: value :: tail =>
                               nextOption(
                                   map ++ Map("project" -> value.toString()), 
                                   tail)
        case "username" :: value :: tail =>
		                       nextOption(
		                           map ++ Map("username" -> value.toString()), 
		                           tail)
        case "password" :: value :: tail =>
                               nextOption(
                                   map ++ Map("password" -> value.toString()), 
                                   tail)
        case option :: tail => println("Unknown option "+option) 
                               exit(1) 
      }
    }

    val map = nextOption(Map(), args.toList)
    run(map.get("project").toString(),
        map.get("username").toString(),
        map.get("password").toString())
  }
  
  def scan(project:String, username:String, password:String ) : SearchResult = {
    val creds = new BasicCredentials(username,password);
    val client = new JiraClient("https://issues.apache.org/jira", creds);
    val issueResult =
      client.
        searchIssues(s"project=$project AND status=OPEN AND updated > -1m AND attachments IS NOT EMPTY");
    
    return issueResult;
  }
 
  def evaluate(issues : SearchResult) : Any = {
    
    
  }
  
  def run(project : String, username : String, password : String) : Unit = {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    val time = cal.getTime();
    while(1==1){
    	val issues:SearchResult = scan(project, username, password)
        /**
         * Pseudo code to write tomorrow.
    	if(patch to review){
    		val review:PatchReview = evaluate(issues)
    		post comment
    	}
         */
    	Thread.sleep(10000);
    }
    
  }
}