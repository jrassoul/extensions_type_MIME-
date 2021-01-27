package projet_java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SignatureMimeValidation {

	public SignatureMimeValidation() {
		super();
	}
	
	public boolean laisserPasserPsignature(String chemin) throws IOException {

		String ligne;	
		MimeTypeHashmap mthm=new MimeTypeHashmap();		 
		String MTfile=mthm.MimeTypeFile(chemin);		//Mime type du fichier
		InputStream flux;
		
			flux = new FileInputStream(chemin);
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			
			//creation d'un boolean pour simplifier le traitement dans la Class Repertoirelister
			if((mthm.MimeTypeFile(chemin).equals("text/html") && (ligne=buff.readLine())!=null&& ligne.substring(0).equals("<!DOCTYPE html>"))
				
				||((MTfile.equals("application/x-shell") || MTfile.equals("application/x-sh") || MTfile.equals("application/x-shellscript")) 
				&& (ligne=buff.readLine())!=null 
				&& (ligne.substring(0).equals("#!/bin/sh") || ligne.substring(0).equals("#!/bin/")))	
					){
				return true;
			}else {
				return false;
			}
		
		
		
	}
	
	public String valideMimeSignature(String chemin) {
		String result="";
		try {
		
		InputStream flux=new FileInputStream(chemin); 
		InputStreamReader lecture=new InputStreamReader(flux);
		BufferedReader buff=new BufferedReader(lecture);
		String ligne;	
		MimeTypeHashmap mthm=new MimeTypeHashmap();		 
		String MTfile=mthm.MimeTypeFile(chemin);		//Mime type du fichier
		
		
		if(mthm.MimeTypeFile(chemin).equals("text/html") && (ligne=buff.readLine())!=null&& ligne.substring(0).equals("<!DOCTYPE html>")){
			
			result +=mthm.validationMimeWithExt(chemin)+"\n"	
			+"Signature                 : <!DOCTYPE html>\n\n";
		
		}else if((MTfile.equals("application/x-shell") || MTfile.equals("application/x-sh") || MTfile.equals("application/x-shellscript")) 
					&& (ligne=buff.readLine())!=null 
						&& (ligne.substring(0).equals("#!/bin/sh") || ligne.substring(0).equals("#!/bin/"))) {
		
			result=mthm.validationMimeWithExt(chemin)+"\n"+
			"Signature                 : application/x-shell\n\n";
		}
		buff.close(); 
		}		
		
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return result;
	}

	
//	public static void main(String[] args) {
////		SignatureMimeValidation smv=new SignatureMimeValidation();
////		
////		smv.valideMimeSignature("C:\\Users\\joker\\Desktop\\aa\\googlepage.html");
////	
//		
//		String chemin="C:\\Users\\joker\\Desktop\\aa\\image.jpg";
//		String ligne;	
//		MimeTypeHashmap mthm=new MimeTypeHashmap();		 
//		String MTfile=mthm.MimeTypeFile(chemin);		//Mime type du fichier
//		InputStream flux;
//		try {
//			
//			flux = new FileInputStream(chemin);
//			InputStreamReader lecture=new InputStreamReader(flux);
//			BufferedReader buff=new BufferedReader(lecture);
//			
//			//creation d'un boolean pour simplifier le traitement dans la Class Repertoirelister
//			if((mthm.MimeTypeFile(chemin).equals("text/html") 
//			&& (ligne=buff.readLine())!=null&& ligne.substring(0).equals("<!DOCTYPE html>"))
//				
//			||((MTfile.equals("application/x-shell") || MTfile.equals("application/x-sh") || MTfile.equals("application/x-shellscript")) 
//			&& (ligne=buff.readLine())!=null 
//			&& (ligne.substring(0).equals("#!/bin/sh") || ligne.substring(0).equals("#!/bin/")))	
//					){
//			System.out.println(true);
//			}else {
//				System.out.println(false);
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}

//	}
}
	
	




//	//Permet de trouver le mime d'un fichier
//			String fileName = "C:\\Users\\joker\\Desktop\\aa\\googlepage.html";
//			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
//
//			// only by file name
//			String mimeType = mimeTypesMap.getContentType(fileName);
//
//			// or by actual File instance
//			File file = new File(fileName);
//			mimeType = mimeTypesMap.getContentType(file);
//			
//			System.out.println(mimeType);




