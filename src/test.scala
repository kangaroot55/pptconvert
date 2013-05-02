


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
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor
import org.apache.poi.xslf.XSLFSlideShow
import org.apache.poi.hslf.extractor.PowerPointExtractor
import org.apache.poi.hslf.HSLFSlideShow
//package org.apache.poi.xslf.util




object pptx2png {

  		def pptx(args: String, saveFolder: String) : Array[String] = {
		  
			var is = new FileInputStream(args)

			
			var ppt = new XMLSlideShow(is)
			
			var extract = new XSLFPowerPointExtractor(ppt)
			
//			Metadata extract
//			System.out.println(extract.getText())
//			System.out.println(extract.getMetadataTextExtractor().getText())
			
			is.close()
			
			val pgsize = ppt.getPageSize()
			val slide = ppt.getSlides()
			
			def myFunc(s: XSLFSlide, i: Int) : String = {
				val img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB)
				val graphics = img.createGraphics()
				
				
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
		        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
		        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC)
				graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON)

		        
				img.createGraphics().setPaint(Color.white)
				img.createGraphics().fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height))
				
				s.draw(graphics)
				
				var out = new FileOutputStream(saveFolder+i+".png")
				
				javax.imageio.ImageIO.write(img, "png", out)
				out.close()
				
				return (saveFolder+i+".png")
			}
			
			
			val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
			
			return a
			
		}
		
		
		def ppt(args: String, saveFolder: String) : Array[String] = {
		  
			var is = new FileInputStream(args)
			
			var pptex = new HSLFSlideShow(is) 
			var ppt = new SlideShow(pptex)
			
			var extract = new PowerPointExtractor(pptex)
			
//			Metadata extract
//			System.out.println(extract.getText())
//			System.out.println(extract.getMetadataTextExtractor().getText())
			
			is.close()
			
			val pgsize = ppt.getPageSize()
			val slide = ppt.getSlides()
			
			println(pgsize)

			def myFunc(s: Slide, i: Int) : String = {
			  
				val img = new BufferedImage(pgsize.width*2, pgsize.height*2, BufferedImage.TYPE_INT_RGB)
				val graphics = img.createGraphics()

				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
				graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
				graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC)
				graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON)

				
				graphics.scale(2,2)
				img.createGraphics().setPaint(Color.white)
				img.createGraphics().fill(new Rectangle2D.Float(0, 0, pgsize.width * 2, pgsize.height * 2)) 
				
			
				
				s.draw(graphics)
				
				var out = new FileOutputStream(saveFolder+i+".png")
			
				javax.imageio.ImageIO.write(img, "png", out)
				out.close()
		
				return (saveFolder+i+".png")
			}
			
			val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
			
			return a
		}

		
	def main(args: Array[String]) {
	  
	    val address = args(0)
	    val saveFolder = args(1)
	    

		val pos = address.lastIndexOf(".")
		val ext = address.substring( pos + 1 )
	
		
		ext match{
		  case "pptx" => pptx(address, saveFolder)
		  case "ppt" => ppt(address, saveFolder)
		}
		
		
		
		
	}
}
