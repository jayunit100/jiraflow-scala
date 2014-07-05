package org.apache.bigtop.devtools;

import org.junit.Assert._
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import net.rcarz.jiraclient.Issue.SearchResult
import org.junit.Assert

class Tests extends AssertionsForJUnit {
 
  /**
   * For this test to pass, System properties need to be 
   * defined for jira user/pass (JIRAusername,JIRApassword).
   */
  @Test def testJiraScanner() {
     Assert.assertTrue(System.getProperty("JIRAusername")!=null)
     Assert.assertTrue(System.getProperty("JIRApassword")!=null)
    
     def verifyResultsInRange(issuesMin:Int)(issuesMax:Int)(s:SearchResult) : Int = {
    		 Assert.assertTrue(s.issues.size()>=issuesMin)
    		 Assert.assertTrue(s.issues.size()<=issuesMax)
    		 0
     }
     
     //Test that we get at least 50 result if we go back
     // 700 days = 1000000 minutes (need to check my math)
     new JiraScanner().scan(
          "bigtop", 
          System.getProperty("JIRAusername"), 
          System.getProperty("JIRApassword"),
          -1000000,
          verifyResultsInRange(49)(Integer.MAX_VALUE));
     
     new JiraScanner().scan(
          "bigtop", 
          System.getProperty("JIRAusername"), 
          System.getProperty("JIRApassword"),
          -1,
          verifyResultsInRange(0)(0));
  }
  
  
  val patch=
    """From fc6e2bc075aecec5eff3d81de877abb6f3516305 Mon Sep 17 00:00:00 2001
From: Jay Vyas <jay@apache.org>
Date: Mon, 16 Jun 2014 20:43:42 -0400
Subject: [PATCH] patch simple

---
 aaa | 1 +
 1 file changed, 1 insertion(+)
 create mode 100644 aaa

diff --git a/aaa b/aaa
new file mode 100644
index 0000000..0966f8d
--- /dev/null
+++ b/aaa
@@ -0,0 +1 @@
+TEST PATCH
-- 
1.8.5.2 (Apple Git-48) xxx
"""
  
  @Test def testGitRepo() { 
    val repo = new GitRepo("bigtop")
    def exit = repo.apply(patch.getBytes());
    assertEquals(0,exit);
    //assert that the patch took.
    //assertTrue(
        //repo.gitlog().toString() + " Contains the commit message",
        //repo.gitlog().toString().contains("patch simple"));
  
  }

}