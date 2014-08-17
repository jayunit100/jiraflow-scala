package org.apache.bigtop.devtools

import net.rcarz.jiraclient.JiraClient
import net.rcarz.jiraclient.BasicCredentials
import net.rcarz.jiraclient.Issue.SearchResult
/**
 * PA = A "type" returned when we scan and process JIRAs.
 */
class JiraScanner[PA] {

  def scan(project:String, username:String, password:String, minutes:Int, f: SearchResult => PA) : PA = {
    val creds = new BasicCredentials(username, password);
    val client = new JiraClient("https://issues.apache.org/jira",creds);    
    val issueResult =
      client.
        searchIssues(s"project=$project AND status=OPEN AND updated > ${minutes}m AND attachments IS NOT EMPTY");

    f.apply(issueResult);
  }

  
}