package org.apache.bigtop.devtools

import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient.JiraException
import net.rcarz.jiraclient.JiraClient
import net.rcarz.jiraclient.BasicCredentials
import scala.collection.JavaConversions._
import net.rcarz.jiraclient.Attachment

object JIRAExample extends App {
  
  val time = System.currentTimeMillis();
 
  while(1==1){
      println("START !");
      
      val creds = new BasicCredentials("jayunit100", "xx");
      val client = new JiraClient("https://issues.apache.org/jira", creds);
      val issueResult = 
              client.
              searchIssues("project=BIGTOP AND status=OPEN AND updated > -1m");

  
      println(issueResult.total);
      println(issueResult.issues.size());
      (0 to issueResult.issues.size()-1).
          foreach((i:Int) =>
              println(i + " attachements: " +
                  issueResult.issues.get(i).getKey()+" (key)"+
                  issueResult.issues.get(i).getComponents().size()+" (comp)"+
                  issueResult.issues.get(i).getAttachments().size() + "(attach)"))
      
      issueResult.
          issues.
             foreach((i: Issue) => 
               i.getAttachments().
                 foreach(
                   (a: Attachment) => 
                     println(i + " DATE : "+ a.getCreatedDate())));
    
      println("DONE sleep 30.....");
      Thread.sleep(30000)
  }
}
