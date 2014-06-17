package org.apache.bigtop.devtools;

import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit

class Test2 extends AssertionsForJUnit {
 
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
1.8.5.2 (Apple Git-48)"""
  
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