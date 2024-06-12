package Tost;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GóraPlanszy extends JPanel{

	static GóraPlanszy góraPlanszy= new GóraPlanszy();
	JLabel wyswietlaImie = new JLabel("Tu pojawi sie twoje imie :)");

	Color color1, color2;
	
	public void setText(String text) {
	        wyswietlaImie.setText(text);
	    }
	 
	public GóraPlanszy() {
	
		wyswietlaImie.setPreferredSize(new Dimension(160, 25));

	 	color1= new Color(164,226,245);  
	 
	 	color2=new Color(128,0,0);
	 
	 	
   	 	this.setBackground(color1);
   		
	 
   	 	this.add(wyswietlaImie);
   	 	wyswietlaImie.setForeground(color2);
   	 	wyswietlaImie.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
	}

}
