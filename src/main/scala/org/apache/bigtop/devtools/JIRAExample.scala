package org.apache.bigtop.devtools


import java.nio.file.Files
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Paths
import scala.collection.JavaConversions._
import scala.sys.process._
import net.rcarz.jiraclient.Attachment
import net.rcarz.jiraclient.BasicCredentials
import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient.JiraClient
import java.nio.file.FileSystems
import java.io.File
import net.rcarz.jiraclient.IssueType
import net.rcarz.jiraclient.Issue.SearchResult
import java.util.Properties

object JIRAExample extends App {
/**
  val props = new Properties();
  val REPO= props.get("source.repo").toString();
  
  object CONSTANTS {
    val USER= props.getProperty("jira.username");
    val PASS= props.getProperty("jira.password");
  }

  def pollIssues ( ) : SearchResult = {
    val creds = new BasicCredentials(CONSTANTS.USER,CONSTANTS.PASS);
    val client = new JiraClient("https://issues.apache.org/jira", creds);
    val issueResult =
      client.
        searchIssues("project=BIGTOP AND status=OPEN AND updated > -1m AND attachments IS NOT EMPTY");
    
    return issueResult;
  }
  
  def checkGitRepo() {
    if(FileSystems.getDefault().getPath(CONSTANTS.REPO).toFile().exists()) {
    	System.out.println("found repo, not cloning.");
    }
    else {
       val repo = CONSTANTS.REPO;
       val cloned = "git clone https://github.com/apache/bigtop /tmp/bigtop-patch-test/bigtop" !
          
       System.out.println(s"Reulst of clone $cloned")
    }
  }
  
  def apply(path : String, contents : Array[Byte]) {
    checkGitRepo();
    
    val location = FileSystems.getDefault().getPath(path+"");
    Files.write(location , contents);
    val abs = location.toAbsolutePath();
    System.out.println(s"Testing patch now $location");
    val result =
        s"cd %s $CONSTANTS.REPO &&" +
        " git fetch origin &&"+
        " git clean -f &&" +
        " git reset --hard origin/master &&" +
        s"git am $location" ! 
    
    println("result = %s",result);
    return result;
  }

  val time = System.currentTimeMillis();

  checkGitRepo();
  while (1 == 1) {
    println("START !");

    val issueResult = pollIssues();
    
    for (i <- issueResult.issues) {
      
      //assume latest attachment is the only 
      //one we care about.
      for( a <- i.getAttachments()) {
    	val patch = if (a.getFileName().endsWith("patch")) a else null ;
    	
    	if(patch != null){
        	val doTest = if (Files.exists(Paths.get(patch.getFileName()))) true else false ;
        	if(doTest) {
        		val result = apply(patch.getId(),patch.download());
        	}
        }
      }
    }
    println(issueResult.total);
    println(issueResult.issues.size());
  }
**/
  }
