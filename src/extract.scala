import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor
import org.apache.poi.hslf.HSLFSlideShow
import org.apache.poi.hslf.extractor.PowerPointExtractor
import org.apache.poi.xslf.usermodel.XMLSlideShow
import java.io.FileInputStream
import org.apache.tika.Tika

object extract {
 
	def main(args: Array[String]){
	  
//		val argss = "c:\\jot\\test.pptx" 
		val tika = new Tika()
		val t = tika.detect(args(0))		// =>args
		
		var is = new FileInputStream(args(0))		// =>args
	  
		
		t match{
		  
		  case "application/vnd.ms-powerpoint" => 
	  	    var ppt = new HSLFSlideShow(is)	//ppt
			var extract = new PowerPointExtractor(ppt)
	  	    System.out.println(extract.getText())
	  	    
	  	  case "application/vnd.openxmlformats-officedocument.presentationml.presentation" => 
	  	    var ppt = new XMLSlideShow(is)
			var extract = new XSLFPowerPointExtractor(ppt)	//pptx
	  	    System.out.println(extract.getText())
	  	    
	  	  case _ =>
	  	    System.out.println("no match")
			
		}
		
		
		
//		System.out.println(extract.getMetadataTextExtractor().getText()) => real metadata!
		
		
	}
}