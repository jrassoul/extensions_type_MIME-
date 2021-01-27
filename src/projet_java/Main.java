package projet_java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		MimeTypeHashmap mt=new MimeTypeHashmap();
		Repertoirelister rp=new Repertoirelister();
//		String F="C:\\Users\\joker\\Desktop\\aa";
//		String Ff="F:\\logix_insta\\eclipse\\eclipse-workspace\\zprojet_java_1920\\objectif.txt";
		System.out.println(System.getProperty("user.dir"));
		
		//Affichage des methode d'analyse et utilisation
		if(args.length==0) {
			
			System.out.println("java -jar cli.jar (modes d’utilisation)\n"
				+ "java -jar cli.jar -d .\n"
				+ "java -jar cli.jar -f test.html\n"
				+ "java -jar cli.jar -d . -s analyse\n"
				+ "java -jar gui.jar");	
		
			//Analyse du repertoire courant (.)	et sauvegarde 
		}else if (args[0].equals("-d") && args[1].equals(".") && args.length==2) {
			String txt=rp.TraitRepertoireMimeExt(System.getProperty("user.dir"));
			System.out.println(txt);
			//Analyse du fichier entrer en argument 1	
		}else if (args[0].equals("-d") && args[1].equals(".") && args[2].equals("-s")){
			File file=new File("save.txt");
			String txt=rp.TraitRepertoireMimeExt(System.getProperty("user.dir"));
			System.out.println(rp.TraitRepertoireMimeExt(System.getProperty("user.dir")));
			
			try
			{
//			 String filename;
				FileWriter fw = new FileWriter(file,true);
			 fw.write(txt);
			 fw.close();
			}
			catch(IOException ioe)
			{
				System.err.println(ioe.getMessage());
			}
			
			//Analyse du repertoire courant (.)
		}
		else if (args[0].equals("-f")) {
			File file = new File(args[1]);
			File repertoire=new File(System.getProperty("user.dir"));
			String liste[] = repertoire.list();      
	 
			if (liste != null) {         
	        	for (int i = 0; i < liste.length; i++) {
	            	if(liste[i].equals(file.getName())) {
	                	System.out.println(mt.validationMimeWithExt(file.getAbsolutePath()));
	            	}
	        	}
			}
		}
	}
	
}