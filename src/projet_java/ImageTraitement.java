package projet_java;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTraitement {

	public ImageTraitement() {
		super();
	}
	
	public String Imagesize(File Imagefile){
		
		Image img = null;
		try {
			img = ImageIO.read(Imagefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int widthImg = img.getWidth(null);
		int heightImg = img.getHeight(null);
		
		return "longueur"+heightImg+" largeure :"+widthImg;
	}
		

	
//	file = params[:f][:fichier]//je r�cup�re mon fichier
//			 
//			file.content_type[0..4] =="image" //je v�rifie si il est de type image

	
	
//	public static void main(String[] args) {
//	
//		File fileimage=new File("C:\\Users\\joker\\Desktop\\aa\\porsche.jpg");
//		ImageTraitement it=new ImageTraitement();
//		System.out.println(it.Imagesize(fileimage));
//}
}