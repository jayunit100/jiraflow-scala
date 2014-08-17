package org.apache.bigtop.devtools;

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

import scala.sys.process.ProcessIO

import org.apache.bigtop.itest.shell.Shell

class GitRepo(project: String ) {

  def exec(cmds: String*) : Int = {
    System.out.println("Running : " + cmds)
    def run(s : String) : Int = {
        	val sh = new Shell();
	    	sh.exec(s);
	    	System.out.println(sh.getRet()+"\t " + s + " ::: " + sh.getOut()+" " + sh.getErr())
	    	sh.getRet();
    }
    var ret=0;
    cmds.foreach(
    	(c:String) => {
    	  ret = ret + run(c)
    	}
    )
    System.out.println("FINAL RETURL VAL : " + ret);
    ret;
  }
  
  def createGitRepo() {
      val cloned = exec(
            s"rm -rf /tmp/$project",
            s"git clone https://github.com/apache/$project /tmp/$project"
          ) // <--- needs to be optimized someday.
      System.out.println(s"Result of clone $cloned");
      exec(s"ls /tmp/$project")
  }
  
  /**
   * Return last commit (example, 
   * this is used for unit testing that the git repo clones and applies a patch)
   */
  def gitlog() : String = {
	  val sh = new Shell();
	  sh.exec(s"cd /tmp/$project && git log -1");
	  System.out.println("************** " + sh.getOut() + sh.getErr());
	  return Array(sh.getOut(),sh.getErr()).mkString(" ");
  }
  
  
  //def apply(contents: Array[Byte]): (Int, String)
  def apply(contents: Array[Byte]): Int = {
    createGitRepo();
    val str = new String(contents);
    val location = FileSystems.getDefault().getPath("/tmp/patch" + System.currentTimeMillis());
    
    Files.write(location, contents);
    
    val abs = location.toAbsolutePath();
    
    System.out.println(s"Testing patch now $location");

    val patchCmd =        
      (if (str.contains("diff --git a/") )
        	"patch -p1 <" 
          else 
            "patch -p0 <")
    System.out.println("path ==== "+str+"======")
        
    val result = exec(
       Array(
          s"cd /tmp/$project",
          s"$patchCmd $location").
          mkString(" && "))
          
    return result;
  }
}