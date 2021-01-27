package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import projet_java.MimeTypeHashmap;
import projet_java.Repertoirelister;
	
public class MimeTypeGui extends JFrame implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5746293871486116379L;
	
	private JButton button01=new JButton("Analyse Dossier ");
	private JButton button02=new JButton("Analyse Fichier");
	private JButton button03=new JButton("Sauvegarder");
	private JButton button04=new JButton("Restaurer");
	public JTextArea txtcontent = new JTextArea("vi");
	private JButton button05=new JButton("Enregistrer sous");
	private JLabel lblStatus1=new JLabel("");
	private JLabel lblStatus2=new JLabel("");
	private JLabel lblStatus3=new JLabel("");

	//	txtcontent
	Repertoirelister rep=new Repertoirelister();
	MimeTypeHashmap mthm=new MimeTypeHashmap();
//	RightPanel Rpanel=new RightPanel();
	
	
	public MimeTypeGui() {
		super("Mime Type File Traitement ");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		
	JPanel contentPane=(JPanel)this.getContentPane();	
	//confeguration des bouton
	//contentPane.setLayout(new BorderLayout());
	//confeguration des bouton
	//	contentPane.setLayout( null ); 
		contentPane.setBackground(Color.darkGray);
		contentPane.add(creatToolBar(),BorderLayout.NORTH);	
		try {
			serialdebut(); 
		} catch (Exception e) {
			System.out.println("Begin program");
		}
		
		txtcontent.setBackground(Color.DARK_GRAY);
		txtcontent.setForeground(Color.white);
		txtcontent.setEditable(true);
		JScrollPane scrContent =new JScrollPane(txtcontent);
//		this.txtcontentpath= new JTextArea("the content of this editor ");
		
		contentPane.add(scrContent);
		
		
		
		
		JPanel panel=creatRightPanel();
		contentPane.add(panel,BorderLayout.WEST);
		contentPane.add(creatStatusBar(),BorderLayout.SOUTH);
	
	
		this.addWindowListener(new WindowAdapter() {
	//Boutton fermer confirmation		
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				// TODO Auto-generated method stub
				int clickedButton=JOptionPane.showConfirmDialog(MimeTypeGui.this, "Etes-vous sur de vouloir quitter ?","Mime Type File Traitement ",JOptionPane.YES_NO_OPTION);
				if (clickedButton == JOptionPane.YES_OPTION) {
					MimeTypeGui.this.dispose();
				}
			}
		});
		
	}

	public void changecontentTextArea() {
			System.out.println("ss");
			String actueltext=txtcontent.getText();
			System.out.println(actueltext);	
			this.txtcontent.append("ss");
			txtcontent.setText("ddd");
			System.out.println(actueltext);
		}

	public void setTxtcontent(JTextArea txtcontent) {
		this.txtcontent = txtcontent;
	}

	
	public JPanel creatRightPanel() {
		JPanel panel=new JPanel(new GridLayout(5,1));
		panel.setBackground(Color.darkGray);
//bouton01
		button01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button01Listener(e);
			}
		});
		button01.setBackground(Color.GRAY);
		panel.add(button01);
//bouton02		
		button02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button02Listener(e);
			}
		});
		button02.setBackground(Color.GRAY);
		panel.add(button02);
//bouton03		
		button03.addActionListener(this::button03Listener);
		button03.setBackground(Color.GRAY);
		panel.add(button03);
//bouton04
		
//		button04.addActionListener((e)->button04Listener(e));	
		button04.addActionListener(this::button04Listener);
        	/***ou bien etuliser cette methode****/
//		button04.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				button04Listener(e);
//			}
//		});
		button04.setBackground(Color.GRAY);
		panel.add(button04);
		
		button04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				button01.setBackground(Color.gray);
//				button02.setBackground(Color.gray);
//				button03.setBackground(Color.gray);
//				button04.setBackground(Color.blue);
			}
		});
//boutton05
		button05.addActionListener(this::button05Listener);
		button05.setBackground(Color.GRAY);
		panel.add(button05);
		button05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		return panel;
	}

//	//traitement de l'action du boutton 04 quand on a un nombre 
//	//important d'instruction a faire
	public static String J="";
	
	public void serialdebut() {
		if (relectureserialisation()!=null) {
		 	txtcontent.setText(relectureserialisation());
		}
		 
	}	 
	
	public void button01Listener(ActionEvent event) {
//		System.out.println("Boutton 01 cliqué");
//		String textTraitRep=rep.TraitRepertoireMimeExt(System.getProperty("user.dir"));
//		txtcontent.setText(textTraitRep);
	    JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
        if(ret == JFileChooser.APPROVE_OPTION) { // validation
            System.out.println("Selected dir : " + jfc.getSelectedFile());
        String cheminrepertoire=jfc.getSelectedFile().getAbsolutePath();
        rep.TraitRepertoireMimeExt(cheminrepertoire);
        String textTraitRep=rep.TraitRepertoireMimeExt(cheminrepertoire);
        txtcontent.setText(textTraitRep);
        lblStatus1.setText("traité :"+rep.getnbFiletrait());
		lblStatus2.setText("non traité :"+rep.getnbnontrait());
		J=textTraitRep;
 //        setTextArea("ddsssssss");
//        this.txtcontent.getText()=("ddsssssss");
        //		champSelectionBase.setText(selection.getAbsolutePath());
//		System.out.println(s);

		}
		
	}
	
	public void button02Listener(ActionEvent event) {
	
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selection=new File("s");
			selection = chooser.getSelectedFile();
			String s=selection.getAbsolutePath();
			String TraitFile=mthm.validationMimeWithExt(s);
//			setAreaTxtcontent(TraitFile);
			txtcontent.setText(TraitFile);
			J=TraitFile;
			
			}
	}
	public static String getcontenttxtfinal() {
		return J;
	}
	public void button03Listener(ActionEvent event) {
		ecrireSerialisationn();
//		ser.ecrireSerialisationn();
		
	}
	public void button04Listener(ActionEvent event) {
		relectureserialisation();
		txtcontent.setText(relectureserialisation());
		
		
}

	public void button05Listener(ActionEvent event) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
        if(ret == JFileChooser.APPROVE_OPTION) { // validation
        	System.out.println("Selected dir : " + jfc.getSelectedFile());
        	String cheminrep=jfc.getSelectedFile().getAbsolutePath();
        
		
        	File file=new File(cheminrep+"/"+"filesave.txt");
		
        	try
        	{
        		FileWriter fw = new FileWriter(file,true);	
        		fw.write(txtcontent.getText());
        		fw.close();
        	}
        	catch(IOException ioe)
        	{
        		System.err.println(ioe.getMessage());
        	}		
        }
	}
	
	private JToolBar creatToolBar() {
		
		JToolBar toolBar =new JToolBar();
		
		JButton FileButton=new JButton("File");
		FileButton.setPreferredSize(new Dimension(50,30));
		toolBar.add(FileButton);
		
		
		JButton editButton=new JButton("Edit");
		editButton.setPreferredSize(new Dimension(50,30));
		toolBar.add(editButton);
		
		JButton helpButton=new JButton("Help");
		helpButton.setPreferredSize(new Dimension(50,30));
		toolBar.add(helpButton);

		return toolBar;	
	}
	
	private JPanel creatStatusBar() {
		JPanel statusBar=new JPanel(new FlowLayout());
		
		lblStatus1.setPreferredSize(new Dimension(100,30));
		statusBar.add(lblStatus1);
		
		lblStatus2.setPreferredSize(new Dimension(100,30));
		statusBar.add(lblStatus2);
		
		lblStatus3.setPreferredSize(new Dimension(100,30));
		statusBar.add(lblStatus3);
		
		return statusBar;
	}
    
    public void setTextArea(String string)
	{
		JTextArea mypanel = new JTextArea();
		mypanel.setText(string);
	}

    public JTextArea getTxtcontent() {
		return txtcontent;
	}

    
    public void ecrireSerialisationn() {
        ObjectOutputStream oos = null;
        Wrapper out = new Wrapper();
        System.out.println("Before = " + out.dump());

        
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("serialis.ser")));
            oos.writeObject(out);
            
        }catch (Exception e) {
		} finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }
        
        public String relectureserialisation() {
        	//deserialisation 
	        ObjectInputStream ois = null;
	        String result="";
	        try {
	        	Wrapper in = null;
	        	try {
	        		ois = new ObjectInputStream(new FileInputStream(new File("serialis.ser")));
	        		in = (Wrapper) ois.readObject();
	        	} finally {
	        		try {
	        			ois.close();
	        		} catch (Exception e) {
	        		}
	        	}            
//	        	System.out.println("After = " + (in == null ? "null" : in.dump()));            
	       if (in!=null) {
			result=in.dump();
		}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        } finally {
	        }
			return result;
        }

    public static class Wrapper implements Serializable {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextArea textArea;
//        MyEclipse4 mecl=new MyEclipse4();
        
        public Wrapper() {
            textArea = new JTextArea(getcontenttxtfinal());
        }

        public String dump() {
            return textArea.getText();
        }
    }

	public static void main(String[] args) throws UnsupportedLookAndFeelException  {
		//Ally a look'n feel
		
		UIManager.setLookAndFeel( new NimbusLookAndFeel());
			
		// Start my window
		MimeTypeGui myWindow =new MimeTypeGui();
		myWindow.setVisible(true);
		
	}
	
}
