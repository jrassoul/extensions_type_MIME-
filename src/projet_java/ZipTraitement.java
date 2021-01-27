package projet_java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTraitement extends MimeTypeHashmap{

	
	public ZipTraitement() {
		super();
	}

	public static String unzipValidation(File zipfile, File folder) throws FileNotFoundException, IOException{

        // création de la ZipInputStream qui va servir à lire les données du fichier zip
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(
                        new FileInputStream(zipfile.getCanonicalFile())));

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
                   
                    if(mt.MimeTypeFile(f.getAbsolutePath()).equals("application/zip")) {
                		
            			File file2= new File(f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-f.getName().length())+"Decompres_"+f.getName().substring(0,f.getName().length()-4));
            			File file1=new File(f.getAbsolutePath());
            			
                		unzipValidation(file1, file2);
                			
                	}else {

//                		System.out.println(mt.validationMimeWithExt(f.getAbsolutePath()));
                		result+=mt.validationMimeWithExt(f.getAbsolutePath())+"\n";
                	}
                    
                }
                catch (final IOException ioe) {
                    // en cas d'erreur on efface le fichier
                    f.delete();
                    throw ioe;
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
//           zis=null;
        }
		return result;
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

	
//	
//	
//		public static void main(String[] args) {
//			String chem="C:\\Users\\joker\\Desktop\\aa\\a.zip";
//			ZipTraitement st=new ZipTraitement();
//			ZipTraitement st2=new ZipTraitement();
//			
//			File file1= new File("C:\\Users\\joker\\Desktop\\aa\\a12.zip");
//			File file2= new File("C:\\Users\\joker\\Desktop\\aa\\compressionxml");
//			file2.mkdir();
//			
//			try {
//				System.out.println(st.unzipValidation(file1, file2));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////la focntion miracle suppretion System.gc();
////			System.gc();
////			st.recursifDelete(file2);
//	}


}










// methode recurive archive unsip

