import org.apache.poi.hslf.usermodel.SlideShow
import org.apache.poi.hslf.HSLFSlideShow
import java.io.FileInputStream
import org.apache.poi.hslf.model.Slide
import org.apache.poi.hslf.model.TextRun

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
  			def myFunc2(r: TextRun, i: Int) = {
  				r match{
  				  case null =>
  				    
  				  case _ =>
  				  	val text = r.getText()
  				  	builder.append(text)
  				  	System.out.println(text)
  				}
  			}
  			
  			runs.zipWithIndex.map{ case(r, i) => myFunc2(r, i)}
  			
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
		  case _ =>
		}
		
		
		
		
	}
  	
}


/*
	for(int x=0; x < slides.length; x++)
    {
        TextRun[] runs = slides[x].getTextRuns();
        for(int j=0; j<runs.length; j++) {
            TextRun run = runs[j];
            if(run != null) {
                String text = run.getText();
                builder.append(text);
            }
        }
    }
	*/