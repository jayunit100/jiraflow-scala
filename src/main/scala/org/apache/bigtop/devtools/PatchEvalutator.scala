package org.apache.bigtop.devtools

import net.rcarz.jiraclient.Attachment

class PatchEvalutator {
  def evaluate(a : Attachment) : (TestResult.Value, String) = {
     val repo = new GitRepo("bigtop")
     val returnCode = repo.apply(a.download());
	 val result = returnCode match {
	    case 0 => (TestResult.PASS,"test passed !")
	    case _ => (TestResult.FAIL,"test failed...")
	 }
	 result;
  }
  
  def testable(lastQueryTimeGMT: Long, a : Attachment ) : Boolean = {
    a.getCreatedDate().getTime()>lastQueryTimeGMT && 
    a.getFileName().contains("patch")
  }
}