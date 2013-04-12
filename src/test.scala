/*
FileInputStream is = new FileInputStream("slideshow.ppt");
SlideShow ppt = new SlideShow(is);
is.close();

Dimension pgsize = ppt.getPageSize();

Slide[] slide = ppt.getSlides();
for (int i = 0; i < slide.length; i++) {

    BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = img.createGraphics();
    //clear the drawing area
    graphics.setPaint(Color.white);
    graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

    //render
    slide[i].draw(graphics);

    //save the output
    FileOutputStream out = new FileOutputStream("slide-"  + (i+1) + ".png");
    javax.imageio.ImageIO.write(img, "png", out);
    out.close();
}
*/




import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFSlide
import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.FileOutputStream
import java.io.FileInputStream
import java.awt.geom.Rectangle2D
import org.apache.poi.hslf.usermodel.SlideShow
import org.apache.poi.hslf.model.Slide
//package org.apache.poi.xslf.util




object pptx2png {
  
	def main(args: Array[String]) {
	  
	    val address = "c:\\jot\\3d.pptx"
	    
//	    val add = address.TypeDetector()
		
		val pos = address.lastIndexOf(".")
		val ext = address.substring( pos + 1 )
	
		ext match{
		  case "pptx" => pptx(address)
		  case "ppt" => ppt(address)
		}
		
		def pptx(args: String){
			var is = new FileInputStream(args)
		
			var ppt = new XMLSlideShow(is)
			is.close()
			
			val pgsize = ppt.getPageSize()
			val slide = ppt.getSlides()
			
			def myFunc(s: XSLFSlide, i: Int){
				val img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB)
				val graphics = img.createGraphics()
				
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		        
				img.createGraphics().setPaint(Color.white)
				img.createGraphics().fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height))
				
				
				s.draw(graphics)
				
				var out = new FileOutputStream("c:\\jot\\pptx-slide-"+i+".png")
				javax.imageio.ImageIO.write(img, "png", out)
				out.close()
			}
			
			
			val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
		}
		
		def ppt(args: String){
			var is = new FileInputStream(args)
			
			var ppt = new SlideShow(is)
			is.close()
			
			val pgsize = ppt.getPageSize()
			val slide = ppt.getSlides()
			

			def myFunc(s: Slide, i: Int){
				val img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB)
				val graphics = img.createGraphics()

				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);


				img.createGraphics().setPaint(Color.white)
				img.createGraphics().fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height))
				
				
				s.draw(graphics)
				
				var out = new FileOutputStream("c:\\jot\\ppt-slide-"+i+".png")
				javax.imageio.ImageIO.write(img, "png", out)
				out.close()
			}
			
			val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
		}
		
		
	}
}
