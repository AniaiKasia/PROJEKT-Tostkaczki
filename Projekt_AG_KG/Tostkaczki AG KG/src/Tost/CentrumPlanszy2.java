package Tost;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

public class CentrumPlanszy2 extends JPanel {
    private static final String EXIT_ON_CLOSE = null;

    private int circleKX, circleKY, circleTX, circleTY;
    private final int circleK = 60;
    private final int circleT = 60;
    private final int screenWysokosc = 600;
    private Timer kaczkaCircleTimer;
    private Timer tosterCircleTimer;
    Random random = new Random();

    JLabel pointsLabel;
    Clip clip1, clip, clip3;

    private int x = 0;
    private int y = 430;
    Color color1, color2, color3, color7, color8;
    JButton exitButton;
    private final int panelSzerokosc = 640;
    private final int wannaSize1 = 120;
    private final int wannaSize2 = 80;
    private final int szybkosc = 15;
    private MyKeyListener2 keyListener2;
    public int punkty = 0;
    private int lives = 3;
    String pkt;
    private Image backgroundImage;
    private Image kaczkaImage;
    private Image tosterImage;
    private Image wannaImage;
    private Image SERCEImage1;
    private JFrame parentFrame;

    public CentrumPlanszy2(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setFocusable(true);
        keyListener2 = new MyKeyListener2(this);
        addKeyListener(keyListener2);

        setLayout(null);

        pointsLabel = new JLabel("Punkty: 0");
        pointsLabel.setBounds(275, 5, 100, 30);
        add(pointsLabel);

        pointsLabel.setFont(new Font("Courier New", Font.CENTER_BASELINE, 15));
        pointsLabel.setForeground(Color.white);

        backgroundImage = Toolkit.getDefaultToolkit().createImage("Images/TŁO-NOC-vol2.png");
        kaczkaImage = Toolkit.getDefaultToolkit().createImage("Images/kaczucha_ostateczne_rand3.png");
        tosterImage = Toolkit.getDefaultToolkit().createImage("Images/toster_ostateczny.png");
        wannaImage = Toolkit.getDefaultToolkit().createImage("Images/wanna_ostateczny.png");
        SERCEImage1 = Toolkit.getDefaultToolkit().createImage("Images/SERCE_PINK.png");

        color7 = new Color(0, 102, 204); // kolor okienek
        color8 = new Color(0, 191, 255); // kolor czcionki

        exitButton = new JButton("Exit");
        exitButton.setBounds(590, 10, 40, 20);
        add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        exitButton.setBackground(color7);
        exitButton.setForeground(Color.white);
        exitButton.setBorder(new LineBorder(Color.white, 2));
        exitButton.setPreferredSize(new Dimension(40, 20));
        exitButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 10));

        initGame();
    }

    private void initGame() {
        circleKX = random.nextInt(550);
        circleKY = 0;
        circleTX = random.nextInt(550);
        circleTY = 0;

        if (kaczkaCircleTimer != null) {
            kaczkaCircleTimer.stop();
        }
        kaczkaCircleTimer = new Timer(10, e -> {
            if (circleKY < screenWysokosc - circleK) {
                circleKY += 3;
            } else {
                circleKY = 0;
                circleKX = random.nextInt(550);
            }
            repaint();
        });
        kaczkaCircleTimer.start();

        if (tosterCircleTimer != null) {
            tosterCircleTimer.stop();
        }
        tosterCircleTimer = new Timer(10, e -> {
            if (circleTY < screenWysokosc - circleT) {
                circleTY += 2;
            } else {
                circleTY = 0;
                circleTX = random.nextInt(550);
            }
            repaint();
        });
        tosterCircleTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(kaczkaImage, circleKX, circleKY, circleK, circleK, this);
        g.drawImage(tosterImage, circleTX, circleTY, circleT, circleT, this);
        g.drawImage(wannaImage, x, y, wannaSize1, wannaSize2, this);

        for (int i = 0; i < lives; i++) {
            g.drawImage(SERCEImage1, 0 + i * 40, 0, 40, 40, this);
        }

        if (circleKY + circleK >= y && circleKY <= y + wannaSize2 && circleKX + circleK >= x && circleKX <= x + wannaSize1) {
            circleKX = random.nextInt(550);
            circleKY = 0;
            punkty++;
            pointsLabel.setText("Punkty: " + punkty);
            playMusic1();
        }

        if (circleTY + circleT >= y && circleTY <= y + wannaSize2 && circleTX + circleT >= x && circleTX <= x + wannaSize1) {
            playMusic2();
            endGame();
        }

        if (circleKY >= 520) {
            playMusic3();
            circleKY = 0;
            circleKX = random.nextInt(550);
            lives--;
            if (lives == 0) {
                endGame();
            }
        }
    }

    private void playMusic1() {
        try {
            File soundFile = new File("Music/collect-ring-15982.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip1 = AudioSystem.getClip();
            clip1.open(audioIn);
            FloatControl gainControl = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            clip1.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void playMusic2() {
        try {
            File soundFile = new File("Music/bum-94209.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(6.0f);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void playMusic3() {
        try {
            File soundFile = new File("Music/duck-quack-112941.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip3 = AudioSystem.getClip();
            clip3.open(audioIn);
            FloatControl gainControl = (FloatControl) clip3.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip3.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void endGame() {
        kaczkaCircleTimer.stop();
        tosterCircleTimer.stop();
        circleTX = random.nextInt(550);
        circleTY = 0;
        ImageIcon imageIcon = new ImageIcon("Images/wanna_wybuch_ostateczne.png");
       
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//jest pod soba
        panel.setOpaque(false); 

        
        JLabel imageLabel = new JLabel(imageIcon);//tu dodajemy obrazek
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel("Umarłeś, twoje punkty: " + punkty);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(new Color(255,246,108));
        textLabel.setFont(new Font("Courier New", Font.BOLD, 40));

        panel.add(textLabel);
        panel.add(imageLabel);

      
        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(okButton).dispose();//zameka okienko
            showOptionDialog();// otwieram nowe okienko dialogowe
        });
        okButton.setBackground(Color.white);
        okButton.setForeground(Color.black);
        okButton.setBorder(new LineBorder(new Color(255,246,108), 4));
        //okButton.setPreferredSize(new Dimension(80, 40));
        okButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 40));
        
        
        panel.add(okButton);

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true); // Usuwam dekoracje 
        dialog.setContentPane(panel);
        dialog.setBackground(new Color(0, 0, 0, 0)); //  przezroczyste tło dla okna dialogowego
        dialog.pack();
        dialog.setLocationRelativeTo(null); //centrum

        
        dialog.setVisible(true);
    }

    private void showOptionDialog() {
        Object[] options = {"Jeszcze raz", "Zakończ", "Chcę wrócić"};
        int wynik = JOptionPane.showOptionDialog(null,
                "Czy chcesz zagrać jeszcze raz?",
                "Koniec gry",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        switch (wynik) {
            case 0:
                restartGame();
                break;
            case 1:
                System.exit(0);
                break;
            case 2:
            	parentFrame.setVisible(false);
                Tostkaczki.frame.setVisible(true);
                break;
        }
     
    }
    


    private void restartGame() {
        punkty = 0;
        lives = 3;
        pointsLabel.setText("Punkty: 0");
        initGame();
    }

    public void moveRight() {
        if (x < panelSzerokosc - wannaSize1) {
            x += szybkosc;
            repaint();
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= szybkosc;
            repaint();
        }
    }
}

	class MyKeyListener2 implements KeyListener {
	    private CentrumPlanszy centrumPlanszy;
		private CentrumPlanszy2 centrumPlanszy2;
	
	
	    public MyKeyListener2(CentrumPlanszy2 centrumPlanszy2) {
	        this.centrumPlanszy2 = centrumPlanszy2;
	    }
	
	    @Override
	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        if (key == KeyEvent.VK_LEFT) {
	        	 centrumPlanszy2.moveLeft();
	        }
	        if (key == KeyEvent.VK_RIGHT) {
	        	centrumPlanszy2.moveRight();
	        }
	    }
	
	    @Override
	    public void keyReleased(KeyEvent e) {
	    }
	
	    @Override
	    public void keyTyped(KeyEvent e) {
	    }
}
