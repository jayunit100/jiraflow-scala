package org.apache.bigtop.devtools

import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient.JiraException
import net.rcarz.jiraclient.JiraClient
import net.rcarz.jiraclient.BasicCredentials
import scala.collection.JavaConversions._

object JIRAExample extends App {

  val creds = new BasicCredentials("jayunit100", "x");
  val client = new JiraClient("https://issues.apache.org/jira", creds);
  val issueResult = 
    client.
      searchIssues("project=BIGTOP AND status=OPEN AND updated > -320m");
  
  println(issueResult.total);
  
  issueResult.
      issues.
         foreach((i: Issue) => println(i));

}
