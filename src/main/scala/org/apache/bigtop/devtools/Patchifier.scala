package org.apache.bigtop.devtools

import scala.collection.JavaConversions._
import scala.sys.process._
import java.util.Calendar
import java.util.TimeZone
import net.rcarz.jiraclient.JiraClient
import net.rcarz.jiraclient.BasicCredentials
import net.rcarz.jiraclient.Issue.SearchResult
import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.FileSystems
import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient.Attachment
import java.lang.Thread

/**
 * This is a JIRA Test application.
 * Inputs: A project name + date.
 * Outputs: A +1/-1.
 * Side effects: Auto review
 */
object Patchifier {
  
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
   
  def run(project : String, username : String, password : String) : Unit = {
    var lastQueryTime=Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();    	
    while(1==1){
    	new JiraScanner().scan(project, username, password, 1, 
    	    {
    			searchResult:SearchResult => 
		    	val issuesToProcess=searchResult.issues.withFilter(
		            (i:Issue)=>
		              if(i.getAttachments().size()>0) true else false)
		        val attachments = issuesToProcess.foreach(
		            (i:Issue)=>
		            	i.getAttachments().foreach(
		            		(a:Attachment)=>{}))
    	    })
      Thread.sleep(1000);
   }}
}