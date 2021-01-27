package projet_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;

public class Repertoirelister implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5359551066153241462L;
	private File repertoire;
	public Repertoirelister() {
		File repertoire=null;
		
	}
	//"C:\\Users\\joker\\Desktop\\reptest"
	int nbtrait=0;
	int nbnontrait=0;
	
	public String TraitRepertoireMimeExt(String CheminRepertoire) {
	
		MimeTypeHashmap MimeTypehm = new MimeTypeHashmap();
		SignatureMimeValidation signatureMV=new SignatureMimeValidation();
		ZipTraitement ziptrait=new ZipTraitement();
		File repertoire = new File(CheminRepertoire);
		File[] files=repertoire.listFiles();
		String result="";
		

		try {
		for(int i=0;i<files.length;i++) {
			
			File filezip=new File(files[i].getAbsolutePath());
			String cheminfile=files[i].getAbsolutePath();
  			File file2= new File(files[i].getAbsolutePath().substring(0,files[i].getAbsolutePath().length()-files[i].getName().length())+"Decompres_"/*+files[i].getName().substring(0,files[i].getName().length()-4)*/);

  			//Variable Traitement fichiers XML,odt,...
    
    		FileNameMap filenamemap = URLConnection.getFileNameMap(); 
       		String extension = FilenameUtils.getExtension(files[i].getAbsolutePath());
       		ZipTraitement zt=new ZipTraitement();
//       		int nbtrait=0;
    		MsOpenOfficeTraitement msoo=new MsOpenOfficeTraitement();

       		//Operation recursive si c'etait un dossier
			if (files[i].isDirectory()) {
				result+=TraitRepertoireMimeExt(files[i].getAbsolutePath());
				
				//Validation des contunues de l'archive 	
			}else if (files[i]==null ||files[i].getName().equals("org.eclipse.jdt.core.prefs") || files[i].getName().indexOf(".")==0 || MimeTypehm.getExtensionFile(files[i].getAbsolutePath()).equals("class")) {
				nbnontrait++;
				continue;
				
			}else if(MimeTypehm.MimeTypeFile(files[i].getAbsolutePath()).equals("application/zip") && files[i].isDirectory()==false){
				result+=(ziptrait.unzipValidation(filezip, file2));
				nbtrait++;		
				System.gc();
				zt.recursifDelete(file2);
				
				//Validation des fichiers html, bash, shell avec leurs signature du fichier 
			}else if (signatureMV.laisserPasserPsignature(files[i].getAbsolutePath())==true){
				result+=signatureMV.valideMimeSignature(cheminfile);
				nbtrait++;
				//validation MS et OPen office files 
			}else if (msoo.tabextensionMSopOffice().contains(MimeTypehm.getExtensionFile(files[i].getAbsolutePath()))) {
				result+=msoo.TraitementXMLFile(files[i], file2);
				System.gc();
				zt.recursifDelete(file2);
				nbtrait++;
			}else {
				result+=MimeTypehm.validationMimeWithExt(files[i].getAbsolutePath());
				System.gc();
				
				nbtrait++;
				continue;
				
			}

		}
		
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
		
		return result;	
	}
		
		public int getnbFiletrait() {
			return nbtrait;
		}
		public int getnbnontrait() {
			return nbnontrait;
		}
		
		
	
////	C:\Users\joker\Desktop\aa\\a.zip
	public static void main(String[] args) {
		
		String chemzip="C:\\Users\\joker\\Desktop\\aa\\a12.zip";
		String chemin="C:\\Users\\joker\\Desktop\\aa";
		String S="F:\\logix_insta\\eclipse\\eclipse-workspace\\zprojet_java_1920";
		String sa="C:\\Users\\joker\\Desktop";
		
		Repertoirelister rl=new Repertoirelister();
		System.out.println(rl.TraitRepertoireMimeExt(sa));
//		System.out.println(System.getProperty("user.dir"));
//		rl.TraitRepertoireMimeExt(System.getProperty("user.dir"));
	}
}
