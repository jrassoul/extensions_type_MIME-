package projet_java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MsOpenOfficeTraitement {
	
	public MsOpenOfficeTraitement() {
		super();
	}
	
	public static Map<String, String> fileExtensionMap; 

    static { 
     fileExtensionMap = new HashMap<String, String>(); 
     // MS Office 
     fileExtensionMap.put("doc", "application/msword"); 
     fileExtensionMap.put("dot", "application/msword"); 
     fileExtensionMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
     fileExtensionMap.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template"); 
     fileExtensionMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12"); 
     fileExtensionMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12"); 
     fileExtensionMap.put("xls", "application/vnd.ms-excel"); 
     fileExtensionMap.put("xlt", "application/vnd.ms-excel"); 
     fileExtensionMap.put("xla", "application/vnd.ms-excel"); 
     fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
     fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template"); 
     fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12"); 
     fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12"); 
     fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12"); 
     fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12"); 
     fileExtensionMap.put("ppt", "application/vnd.ms-powerpoint"); 
     fileExtensionMap.put("pot", "application/vnd.ms-powerpoint"); 
     fileExtensionMap.put("pps", "application/vnd.ms-powerpoint"); 
     fileExtensionMap.put("ppa", "application/vnd.ms-powerpoint"); 
     fileExtensionMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"); 
     fileExtensionMap.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template"); 
     fileExtensionMap.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow"); 
     fileExtensionMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12"); 
     fileExtensionMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12"); 
     fileExtensionMap.put("potm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12"); 
     fileExtensionMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12"); 
     // Open Office 
     fileExtensionMap.put("odt", "application/vnd.oasis.opendocument.text"); 
     fileExtensionMap.put("ott", "application/vnd.oasis.opendocument.text-template"); 
     fileExtensionMap.put("oth", "application/vnd.oasis.opendocument.text-web"); 
     fileExtensionMap.put("odm", "application/vnd.oasis.opendocument.text-master"); 
     fileExtensionMap.put("odg", "application/vnd.oasis.opendocument.graphics"); 
     fileExtensionMap.put("otg", "application/vnd.oasis.opendocument.graphics-template"); 
     fileExtensionMap.put("odp", "application/vnd.oasis.opendocument.presentation"); 
     fileExtensionMap.put("otp", "application/vnd.oasis.opendocument.presentation-template"); 
     fileExtensionMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet"); 
     fileExtensionMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template"); 
     fileExtensionMap.put("odc", "application/vnd.oasis.opendocument.chart"); 
     fileExtensionMap.put("odf", "application/vnd.oasis.opendocument.formula"); 
     fileExtensionMap.put("odb", "application/vnd.oasis.opendocument.database"); 
     fileExtensionMap.put("odi", "application/vnd.oasis.opendocument.image"); 
     fileExtensionMap.put("oxt", "application/vnd.openofficeorg.extension"); 

    } 
    
    public  ArrayList<String> tabextensionMSopOffice() {
    	
     	ArrayList<String> TabExtension=new ArrayList<String>();
        for (HashMap.Entry<String, String> entry : fileExtensionMap.entrySet())
        {
        	for (int i = 0; i < fileExtensionMap.size(); i++) {
        		TabExtension.add(entry.getKey());
    		}
        }
        return TabExtension;
    }
    	public static String TraitementXMLFile(File file, File folder) throws FileNotFoundException, IOException{
    		
            // création de la ZipInputStream qui va servir à lire les données du fichier zip
            ZipInputStream zis = new ZipInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file.getCanonicalFile())));

            // extractions des entrées du fichiers zip (i.e. le contenu du zip)
            ZipEntry ze = null;
            MimeTypeHashmap mt= new MimeTypeHashmap();
            String result="";	
            try {
                while((ze = zis.getNextEntry()) != null){

                    // Pour chaque entrée, on crée un fichier
                    // dans le répertoire de sortie "folder"
                    File f = new File(folder.getCanonicalPath(), ze.getName());
                    String cheminAbsFile = f.getAbsolutePath();
                    mt.MimeTypeFile(cheminAbsFile);
                    
                    
                    
                  
                    // Si l'entrée est un répertoire,
                    // on le crée dans le répertoire de sortie
                    // et on passe à l'entrée suivante (continue)
                    if (ze.isDirectory()) {
                        f.mkdirs();
                        continue;
                        
                    }

                    
                    // L'entrée est un fichier, on crée une OutputStream
                    // pour écrire le contenu du nouveau fichier
                    f.getParentFile().mkdirs();
                    OutputStream fos = new BufferedOutputStream(
                            new FileOutputStream(f));
                   
                    
                    // On écrit le contenu du nouveau fichier
                    // qu'on lit à partir de la ZipInputStream
                    // au moyen d'un buffer (byte[])
                    try {
                        try {
                            final byte[] buf = new byte[8192];
                            int bytesRead;
                            while (-1 != (bytesRead = zis.read(buf))) 
                                fos.write(buf, 0, bytesRead);
                         
                        }
                        
                        
                        finally {
                        	
                        	fos.close();
                        }
                        
                    	MsOpenOfficeTraitement ms=new MsOpenOfficeTraitement();                        
                    	if(f.getName().equals("[Content_Types].xml")) {
                    		result+=ms.ValidationMimeWithXMLFile(file, f);
                        }else if(f.getName().equals("manifest.xml")) {
                        //ici tu fais la meme chose pour les fichiers OpenOffice               
                        	result+=ms.ValidationMimeWithXMLFile(file, f);
                        }
                    }
                    catch (final IOException ioe) {
                        // en cas d'erreur on efface le fichier
                        f.delete();
                        throw ioe;
                    }catch (Exception e) {
						// TODO: handle exception
					}
                    
                	
                }

            }catch (FileNotFoundException e) {
          		System.err.println(e.getMessage());
          	}catch (Exception e) {
          		System.err.println(e.getMessage());
          	}
            
            finally {
            	
                // fermeture de la ZipInputStream
                zis.close();
                zis=null;
            }
			return result;
        }


    	
    	
    	
    	public String ValidationMimeWithXMLFile(File file,File filexml) {
    		// déclaration d'un fichier Java standard
    		// création d'un objet de type SAXReader 
    		 // lecture de ce fichier à l'aide de ce reader, et construction d'un objet Document
			// construction de l'élément racine du document XML
			 // lecture des sous-élément de la racine
    			
///*1*/    		File fichier =  new File("C:\\Users\\joker\\Desktop\\aa\\[Content_Types].xml");
/*2*/    		SAXReader reader =  new SAXReader() ;
/*3*/    			Document doc;
				String result="";
				try {
					//recuperation champs de text dans fichier xml qui contient le Mime
					doc = reader.read(filexml);
/*4*/    			Element root = doc.getRootElement() ;
/*5*/    			List elements = root.elements() ;
					
					//recuperation du mime de l'extention du fichier MSoffice
					MsOpenOfficeTraitement ms=new MsOpenOfficeTraitement();
					InputStream ips=new FileInputStream(file); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne="",S=ms.getMime(ms.getExtensionFile(file.getAbsolutePath()));

					String text = elements.toString();
//					String a="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
					String Extfile=file.getName().substring((file.getName().lastIndexOf(".")+1));
		    		String FMT =fileExtensionMap.get(Extfile);
					if (text.contains(S)) {
						result+="Fichier \""+file.getName()+"\": l'extension est conforme avec le Mime\n"
								+ "Extension du fichier      : "+Extfile+"\n"
								+ "Mime extension du fichier : "+FMT+"\n"
								+ "Mime du fichier           : "+fileExtensionMap.get(Extfile)+"\n";
					}else {
						result+="le fichier n'est pas conforme au mime"+"\n"+
						"Fichier \""+file.getName()+"\": l'extension n'est pas conforme avec le Mime\n"
								+ "Extension du fichier      : "+Extfile+"\n"
								+ "Mime extension du fichier : "+FMT+"\n";
					}
					
				



	
			
//    			String elem=elements.toString();
//    			
//    			System.out.println(elements.toString());
				
				
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result; 
    	}
    	
    	
    	public String getExtensionFile(String filename) {
    	    return FilenameUtils.getExtension(filename).toLowerCase();
    	}
    	public String getMime(String extension) {
	    	return fileExtensionMap.get(extension);
	    	
	    }
    
    public void recursifDelete(File path) throws IOException {
		if (!path.exists()) throw new IOException(
		"File not found '" + path.getAbsolutePath() + "'");
		if (path.isDirectory()) {
		File[] children = path.listFiles();
		for (int i=0; children != null && i<children.length; i++)
		recursifDelete(children[i]);
		if (!path.delete()) throw new IOException(
		"No delete path '" + path.getAbsolutePath() + "'");
		}
		else if (!path.delete()) throw new IOException(
		"No delete file '" + path.getAbsolutePath() + "'");
		}
    
//	public static void main(String[] args) throws IOException {
//		
//		String chemin="C:\\Users\\joker\\Desktop\\aa\\compression";
//		MsOpenOfficeTraitement ms=new MsOpenOfficeTraitement();
//		Path path =Paths.get(chemin);
//		File file=new File(chemin);
//		File file1=new File("C:\\Users\\joker\\Desktop\\aa\\FICHE.odt");
//		File filechemin=new File(chemin);
//
//		ms.TraitementXMLFile(file1, filechemin);
//		System.gc();
//		ms.recursifDelete(filechemin);
//		
//		
//	}	

}
