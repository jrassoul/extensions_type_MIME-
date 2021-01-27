package projet_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

public class MimeTypeHashmap implements Serializable{

	private HashMap<String, String> Mimetypemap;
	
	
	
	
	//Constructeur HashMap<K, V> =<"extension","Mime">
	
	public MimeTypeHashmap() {
		
		Mimetypemap=new HashMap<String, String>();
		EnumMimeType[] mts = EnumMimeType.values();
		for(EnumMimeType mt:mts) {
			Mimetypemap.put(mt.name(), mt.getValue());
			
		}		
	}
	
	public  HashMap<String, String> getMimetypeh() {
		return Mimetypemap;
	}
	public String toString() {
		String result ="";
		for(Map.Entry<String, String> e: Mimetypemap.entrySet()) {
			result+=e.getKey()+" ("+e.getValue()+")\n";
		}
		return result;
	}
	
	
	public int getMimetypeSize() {
		return Mimetypemap.size(); 
	}
	
	
	//Methode trouve le Mime d'une extension d'un fichier
	public String FindMimeType(String extension) {
		if(Mimetypemap.get(extension).toLowerCase()!=null) {
			
			return Mimetypemap.get(extension).toLowerCase();
					
		}else {
			return Mimetypemap.get("UNKNOWN");
		}

	}
	
	
	//retourne l'extension d'un fichier 
	public String getExtensionFile(String filename) {
	    return FilenameUtils.getExtension(filename).toLowerCase();
	}
	
	//methode Recuperation le Mime du fichier 
	@SuppressWarnings("deprecation")
	public String MimeTypeFile(String filepath) {	
	    String mimetypef="";
		try {
	    	
	    	
	    	FileInputStream is = null;
	    	File f = new File(filepath);		      
	      is = new FileInputStream(f);
	      BodyContentHandler contenthandler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
	      Parser parser = new AutoDetectParser();
		     
	      // OOXMLParser parser = new OOXMLParser();
	      parser.parse(is, contenthandler, metadata);
	    
	      mimetypef= metadata.get(Metadata.CONTENT_TYPE);
	    }catch (Exception e) {
	    }
		return mimetypef;
	}
	
	public boolean estvalidéMimeWithExt(String filechemin) {
		MimeTypeHashmap ObjectMimetyph=new MimeTypeHashmap();
		String ExtFile=ObjectMimetyph.getExtensionFile(filechemin);
		String MimeExtFile=ObjectMimetyph.FindMimeType(ExtFile);
		String FMT=ObjectMimetyph.FindMimeType(ExtFile);
		FMT.equals(MimeExtFile);
		return true;
		
	}
	
	//Verification de la conformite du Mime de "l'extension du fichier" avec le "Mime du fichier" 
	public String validationMimeWithExt(String filechemin) {
		File file=new File(filechemin);
		MimeTypeHashmap ObjectMimetyph=new MimeTypeHashmap();
		String MimeFile=ObjectMimetyph.MimeTypeFile(filechemin);
		String MimeFileclass="_"+ObjectMimetyph.MimeTypeFile(filechemin);
		String ExtFile=ObjectMimetyph.getExtensionFile(filechemin);
		String MimeExtFile=ObjectMimetyph.FindMimeType(ExtFile);
		String FMT=ObjectMimetyph.FindMimeType(ExtFile);
		String FMTclasse=ObjectMimetyph.FindMimeType(ExtFile);
		
		String result="";
		
		try {
			
			if (file.length()==0) {
				result="Fichier \""+file.getName()+"\": le fichier est vide \n"
						+ "Extension du fichier      : "+ExtFile+"\n"
								+ "Mime extension du fichier : "+FMT+"\n"
								+ "Mime Type indéfini\n";
			
			}else if (MimeFile.equals(MimeExtFile) ) {
					
					result= "Fichier \""+file.getName()+"\": l'extension est conforme avec le Mime\n"
							+ "Extension du fichier      : "+ExtFile+"\n"
							+ "Mime extension du fichier : "+FMT+"\n"
							+ "Mime du fichier           : "+MimeFile+"\n";

			}else {
					result="Fichier \""+file.getName()+"\": l'extension n'est pas conforme avec le Mime du fichier\n"
							+ "Extension du fichier      : "+ExtFile+"\n"
									+ "Mime extension du fichier : "+FMT+"\n"
									+ "Mime du fichier           : "+MimeFile+"\n";
			} 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
		return result; 
		
		
	}
//	public static void main(String[] args) {
//		MimeTypeHashmap mt=new MimeTypeHashmap();
//		String S="";
//		System.out.println(mt.validationMimeWithExt("C:\\Users\\joker\\Desktop\\aa\\.classpath"));
//	}

}
