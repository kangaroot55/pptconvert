import org.apache.tika.Tika

object tika {

  def main(args: Array[String]){
    
	  	val tika = new Tika()
		val t = tika.detect("c:\\jot\\nslide.pdf")		// =>args

		t match{
	  	  
	  	  case "application/vnd.ms-powerpoint" => 
	  	    System.out.println("ppt")
	  	  case "application/vnd.openxmlformats-officedocument.presentationml.presentation" => 
	  	    System.out.println("pptx")
	  	  case "application/pdf" => 
	  	    System.out.println("pdf")
	  	  case _ =>
	  	    System.out.println("no match")
	  	    
	  	}
		
 
		//application/vnd.ms-powerpoint 	ppt   3d.ppt
		//application/vnd.openxmlformats-officedocument.presentationml.presentation  ppt를 pptx로 바꾼거 3dx.pptx
		// application/vnd.openxmlformats-officedocument.presentationml.presentation test.pptx
		// application/pdf	eval.pdf
		//application/pdf	nslide.pdf


		
  }
}