import java.io.FileInputStream
import org.apache.poi.hslf.usermodel.SlideShow
import org.apache.poi.hslf.model.Slide
import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.geom.Rectangle2D
import java.io.FileOutputStream

object ppt2png {
  
  def main(args: Array[String]) {
		var is = new FileInputStream("c:\\jot\\3d.ppt")
		var ppt = new SlideShow(is)
		is.close()
		
		var pgsize = ppt.getPageSize()
		var slide = ppt.getSlides()
		val i = 1
		
		def myFunc(s: Slide, i: Int){
			var img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB)
			var graphics = img.createGraphics()
			graphics.setPaint(Color.white)
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height))
			
			
			s.draw(graphics)
			
			var out = new FileOutputStream("c:\\jot\\ppt-slide-"+i+".png")
			javax.imageio.ImageIO.write(img, "png", out);
			out.close();
		}
		
		val a = slide.zipWithIndex.map{ case(s, i) => myFunc(s, i)}
		
	}
  

}