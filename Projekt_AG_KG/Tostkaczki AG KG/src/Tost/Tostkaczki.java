package Tost;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Tostkaczki extends JFrame {

	Color color1, color2, color3, color4, color5, color6, color7, color8;
	private Clip clip;
	private boolean musicPlaying = true;

	JPanel panelup, panelcenter, paneldown;

	
	JButton day, night, song;
	
	JLabel title, podajImie;
	
	private JTextField twojeImietext;
	
	
	static Tostkaczki frame = new Tostkaczki();
	
	static GóraPlanszy góraPlanszy= new GóraPlanszy();
	static String twojeImiestring;
	
	public Tostkaczki () throws HeadlessException{
	
		System.out.println("Goławska, Gruszewska, Fotonika 2023/24");
		System.out.println("Muzyka i efekty dzwiękowe: https://pixabay.com/pl/");
		System.out.println("Grafika: inspiracja - Pinterest");
		this.setSize(600,620);
   	 	setDefaultCloseOperation(EXIT_ON_CLOSE);
   	 	setUndecorated(true);
   	 
   	 	color1= new Color(255,218,185);  
   	 	color2= new Color(255,240,245);
   	 	color3= new Color(245,255,250);
   	 	color4= new Color(50,205,50);
   	 	color5= new Color (0,0,56);
   	 	color6= new Color (0,56,0);
   	 	color7=new Color(255,250,240); //kolor okienek
   	 	color8=new Color(128,0,0);//kolor czcionki
   	 	
   	 	
   	   panelup= new JPanel();
   	   add(BorderLayout.NORTH, panelup);
   	   panelup.setBackground(color1);
   	   panelup.setPreferredSize(new Dimension(640,80));
   	   
   	   JButton exitButton = new JButton("Exit"); 
   	   
   	   
	   	exitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	System.exit(0);
	        }
	    });
	
		
		exitButton.setBackground(color7);
		exitButton.setForeground(color8);
		exitButton.setBorder(new LineBorder(color8, 2));
		exitButton.setPreferredSize(new Dimension(80, 40));
		exitButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 25));
   	   
   	   title=new JLabel("Tostkaczki");
   	   panelup.add(title);
   	   title.setFont(new Font("Courier New", Font.BOLD, 50));
   	   title.setForeground(color8);
   	 	
   	 	
   	   panelcenter= new JPanel();
   	   add(BorderLayout.CENTER, panelcenter);
   	   panelcenter.setBackground(color2);
		 
   	   day= new JButton("DZIEŃ");
   	   day.setBackground(color7);
   	   day.setForeground(color8);
   	   day.setBorder(new LineBorder(color8, 2));
   	   day.setPreferredSize(new Dimension(150, 50));
   	   day.setFont(new Font("Courier New", Font.CENTER_BASELINE, 40));
		 
   	   day.addActionListener(new ActionListener() {
				
   		   @Override
   		   public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
   			JOptionPane.showMessageDialog(null, "Poruszaj się strzałkami w lewo i w prawo, \n"
			 		+ "tak, żeby złapać jak najwięcej KACZEK.\n" 
					+"Możesz przepuścić najwyżej "
					+ "3 KACZKI!!!"
			 		+ "\nUnikaj TOSTERÓW!!!");
   			
   			
   			   Object[] options = {"Łatwe",
	                    "Trudne",
	                   "Chce wrócić" };
				 
   			   int wynik = JOptionPane.showOptionDialog(frame,
					    "Łatwe czy trudne?",
					    "Wybór poziomu trudnosci",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[2]);
				 
   			   switch(wynik)
   			   {
   			   case 0:
   				   Plansza1 plansza1 = new Plansza1();
   				   plansza1.setVisible(true);
   				   frame.setVisible(false);
   				   String text = twojeImietext.getText();
			       plansza1.góraPlanszy.wyswietlaImie.setText(text);
			       break;
   			   case 1:
   				   Plansza3 plansza3 = new Plansza3();
   				   plansza3.setVisible(true);
   				   frame.setVisible(false);
   				   String text1 = twojeImietext.getText();
			       plansza3.góraPlanszy.wyswietlaImie.setText(text1);
			       break;
   			   case 2:
					 frame.setVisible(true);
				   break;
   			   }			
   		   }
   	   });

	 	
   	   night= new JButton(" NOC ");
   	   night.setFont(new Font("Courier New", Font.CENTER_BASELINE, 40));
   	   night.setBackground(color7);
   	   night.setForeground(color8);
   	   night.setBorder(new LineBorder(color8, 2));
   	   night.setPreferredSize(new Dimension(150, 50));
	 	
   	   night.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Poruszaj się strzałkami w lewo i w prawo, \n"
				 		+ "tak, żeby złapać jak najwięcej KACZEK.\n" 
						+"Możesz przepuścić najwyżej "
						+ "3 KACZKI!!!"
				 		+ "\nUnikaj TOSTERÓW!!!");
				
				Object[] options = {"Łatwe",
				                    "Trudne",
				                   "Chce wrócić" };
						
				int wynik = JOptionPane.showOptionDialog(frame,
						    "Łatwe czy trudne?",
						    "Wybór poziomu trudnosci",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[2]);
				 
				 switch(wynik)
				 {
				 	case 0:
						 Plansza2 plansza2 = new Plansza2();
						 plansza2.setVisible(true);
						 frame.setVisible(false);
						 String text = twojeImietext.getText();
				         plansza2.góraPlanszy.wyswietlaImie.setText(text);
				         break;
				 	case 1:
				 		Plansza4 plansza4 = new Plansza4();
				 		plansza4.setVisible(true);
				 		frame.setVisible(false);
				 		String text1 = twojeImietext.getText();
				 		plansza4.góraPlanszy.wyswietlaImie.setText(text1);
				 		break;
				 	case 2:
				 		frame.setVisible(true);
				 		break;
				 }
			}
		});
	 	
	 	
	 	paneldown= new JPanel();
	 	add(BorderLayout.SOUTH, paneldown);
	 	paneldown.setBackground(color1);
	 	paneldown.setPreferredSize(new Dimension(640,80));
	 	
	 	podajImie= new JLabel("Podaj swoje imie:");
	 	podajImie.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
	 	podajImie.setForeground(color8);
	 	paneldown.add(podajImie, BorderLayout.WEST);
	  	
	 	twojeImietext= new JTextField(20);
	 	paneldown.add(twojeImietext,BorderLayout.EAST);
	 	twojeImietext.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
	 	twojeImietext.setBorder(new LineBorder(color8, 2));
		
	 	song = new JButton("Song");
	 
	 	class ImagePanelTest {

	 		public ImagePanelTest(String fname) {
		  		ImagePanel p = new ImagePanel(fname);
		  		p.setLayout(null);
		  		p.add(day);
		  		p.add(night, BorderLayout.CENTER);
		  		p.add(song);
		  		p.add(exitButton);
		  		day.setBounds(149, 180, 200, 130);
		  		night.setBounds(350, 180, 200, 130);
		  		exitButton.setBounds(590, 555, 100, 30);
		  		song.setBounds(590, 520, 100, 30);
		  		
		  		add(p);
		  		pack();
		  		setLocationRelativeTo(null);
		  		setVisible(true);
	 		}
	 	}
	 	
       new ImagePanelTest("Images/okno-tło_OSTATECZNE.png");
	 	
       song.setBackground(color7);
       song.setForeground(color8);
       song.setBorder(new LineBorder(color8, 2));
       song.setPreferredSize(new Dimension(80, 40));
       song.setFont(new Font("Courier New", Font.CENTER_BASELINE, 25));
	 	
       song.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				toggleMusic();
			}
		});
	 	
	 	playMusic();
		
	}

  
	private void toggleMusic() {
		if (musicPlaying) {
			stopMusic();
	        musicPlaying = false;
	    } else {
	    	playMusic();
	    	musicPlaying = true;
	    }
	}
	
	
	 private void playMusic() {
		    try {
		        File soundFile = new File("Music/for-elevator-jazz-music-124005.wav");
		        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		        clip = AudioSystem.getClip();
		        clip.open(audioIn);
		        clip.setLoopPoints(0, -1);
		        clip.loop(Clip.LOOP_CONTINUOUSLY); 
		        FloatControl gainControl = 
		        	       (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        	        		gainControl.setValue(-15.0f); 
		        clip.start();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
	}

	void stopMusic() {
		if (clip != null && clip.isRunning()) {
         clip.stop();
		}
	}

	public static void main(String[] args) {
			frame.setVisible(true);
	}

}



