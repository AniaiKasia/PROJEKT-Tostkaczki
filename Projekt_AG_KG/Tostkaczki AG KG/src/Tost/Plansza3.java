package Tost;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plansza3 extends JFrame {

    Color color1;
    private CentrumPlanszy3 panelCentrum3;
    private JPanel panelDolny;
    GóraPlanszy góraPlanszy = new GóraPlanszy();
    
    public Plansza3() {
        this.setSize(640, 660);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
       
        panelCentrum3 = new CentrumPlanszy3(this); 

        this.add(góraPlanszy, BorderLayout.NORTH);
        this.add(panelCentrum3, BorderLayout.CENTER);

        color1 = new Color(154, 205, 50);

        panelDolny = new JPanel();
        panelDolny.setBackground(color1);
        panelDolny.setLayout(null);
        this.add(panelDolny, BorderLayout.SOUTH);
        panelDolny.setPreferredSize(new Dimension(640, 30));
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}
