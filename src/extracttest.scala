import org.apache.poi.hslf.usermodel.SlideShow
import org.apache.poi.hslf.HSLFSlideShow
import java.io.FileInputStream
import org.apache.poi.hslf.model.Slide
import org.apache.poi.hslf.model.TextRun
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFSlide
import org.apache.poi.xslf.usermodel.XSLFTextRun
import org.apache.poi.xslf.usermodel.XSLFTextShape


object extracttest {

  	def ppt(args: String) = {

  		var is = new FileInputStream(args)
  		
		var pptex = new HSLFSlideShow(is) 
		var ppt = new SlideShow(pptex)
  		
  		val slide = ppt.getSlides()
  		val builder = new StringBuilder()
  	
  		def myFunc(s: Slide, i: Int) = {
  			
  			val runs = s.getTextRuns()
  	
  			System.out.println("@@@@@@@"+i+"@@@@@@@")
  			
  			def ext(r: TextRun, i: Int) = {
  				r match{
  				  case null =>
  				    
  				  case _ =>
  				  	val text = r.getText()
  				  	builder.append(text)
  				  	System.out.println(text)
  				}
  			}
  			
  			runs.zipWithIndex.map{ case(r, i) => ext(r, i)}
  			
		}
	
		val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
		
  	}
  	
  	def pptx(args: String) = {

  		var is = new FileInputStream(args)
  		
  		var ppt = new XMLSlideShow(is)
  		
  		val slide = ppt.getSlides()
  		val builder = new StringBuilder()
  	
  		def myFunc(s: XSLFSlide, i: Int) = {
  			

  			val xShape = s.getPlaceholders()
  			
  			
  			System.out.println("@@@@@@@"+i+"@@@@@@@")
  			
  			def shaping(sh: XSLFTextShape, a: Int) = {
  			  
  				val xTexts = sh.getTextParagraphs()
  				
	  			def ext(r: XSLFTextRun, i: Int) = {
	  				r match{
	  				  case null =>
	
	  				  case _ =>
	  				  	val text = r.getText()
	  				  	builder.append(text)
	  				  	System.out.println(text)
	  				}
	  			}

  				val list = xTexts.get(a).getTextRuns()
  				
	  			list.zipWithIndex.map{ case(r, i) => ext(r, i)}
  			
  			}
   			xShape.zipWithIndex.map{ case(sh, a) => shaping(sh, a)}
  			
		}
	
		val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
		
  	}
		
  	
		
	def main(args: Array[String]) {
	  
	    //val address = args(0)
	    val address = "C:\\jot\\3d.ppt"
	    
	    

		val pos = address.lastIndexOf(".")
		val ext = address.substring( pos + 1 )
	
		
		ext match{
		  case "ppt" => ppt(address)
		  case "pptx" => pptx(address)
		  case _ => System.out.println("no match")
		}
		
		
		
		
	}
  	
}
