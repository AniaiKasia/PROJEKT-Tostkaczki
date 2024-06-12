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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CentrumPlanszy3 extends JPanel {
    private static final String EXIT_ON_CLOSE = null;
    private final int circleK = 60;
    private final int circleT = 60;
    private final int screenWysokosc = 600;
    private final int panelSzerokosc = 640;
    private final int wannaSize1 = 120;
    private final int wannaSize2 = 80;
    private final int szybkosc = 15;
    Color color1, color7, color8;
    JButton exitButton;
    JLabel pointsLabel;
    Clip clip1, clip, clip3;
    private int lives = 3;
    private List<Integer> kaczkaCircleX;
    private List<Integer> kaczkaCircleY;
    private int circleTX;
    private int circleTY;
    private Timer kaczkaCircleTimer;
    private Timer tosterCircleTimer;
    private Random random = new Random();
    private MyKeyListener3 keyListener3;
    public int punkty = 0;
    private int x = 0;
    private int y = 430;
    private Image backgroundImage;
    private Image kaczkaImage;
    private Image tosterImage;
    private Image wannaImage;
    private Image SERCEImage1;
    private Plansza3 parentFrame;

    public CentrumPlanszy3(Plansza3 parentFrame) {
        this.parentFrame = parentFrame;
        setFocusable(true);
        keyListener3 = new MyKeyListener3(this);
        addKeyListener(keyListener3);
      
        setLayout(null);
        
        pointsLabel = new JLabel("Punkty: 0");
        pointsLabel.setBounds(275, 5, 100, 30);
        add(pointsLabel);

        pointsLabel.setFont(new Font("Courier New", Font.CENTER_BASELINE, 15));

        backgroundImage = Toolkit.getDefaultToolkit().createImage("Images/tło.png");
        kaczkaImage = Toolkit.getDefaultToolkit().createImage("Images/kaczucha_ostateczne_rand2.png");
        tosterImage = Toolkit.getDefaultToolkit().createImage("Images/toster_ostateczny.png");
        wannaImage = Toolkit.getDefaultToolkit().createImage("Images/wanna_ostateczny.png");
        SERCEImage1 = Toolkit.getDefaultToolkit().createImage("Images/SERCE_CZER.png");

        color7 = new Color(224, 255, 255);
        color8 = new Color(0, 191, 255);
        
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
        exitButton.setForeground(Color.black);
        exitButton.setBorder(new LineBorder(Color.black, 2));
        exitButton.setPreferredSize(new Dimension(40, 20));
        exitButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 10));
        
        kaczkaCircleX = new ArrayList<>();
        kaczkaCircleY = new ArrayList<>();
        addNewRedCircle();

        circleTX = random.nextInt(550); 
        circleTY = 0; 

        kaczkaCircleTimer = new Timer(10, e -> updateRedCircles());
        kaczkaCircleTimer.start();

        tosterCircleTimer = new Timer(5, e -> updateGreenCircle());
        tosterCircleTimer.start();
    }

    private void addNewRedCircle() {
        kaczkaCircleX.add(random.nextInt(550)); 
        kaczkaCircleY.add(0); 
    }

    private void updateRedCircles() {
        for (int i = 0; i < kaczkaCircleY.size(); i++) {
            if (kaczkaCircleY.get(i) < screenWysokosc - circleK) {
                kaczkaCircleY.set(i, kaczkaCircleY.get(i) + 2);
                if (kaczkaCircleY.get(i) >= screenWysokosc / 2 && kaczkaCircleY.size() == 1) {
                    addNewRedCircle();
                }
            } else {
                kaczkaCircleY.set(i, 0);
                kaczkaCircleX.set(i, random.nextInt(550));
            }
        }
        repaint();
    }

    private void updateGreenCircle() {
        if (circleTY < screenWysokosc - circleT) {
            circleTY += 4;
        } else {
            circleTY = 0;
            circleTX = random.nextInt(550);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        for (int i = 0; i < kaczkaCircleX.size(); i++) {
            g.drawImage(kaczkaImage, kaczkaCircleX.get(i), kaczkaCircleY.get(i), circleK, circleK, this);
        }
        
        for (int i = 0; i < lives; i++) {
            g.drawImage(SERCEImage1, 0 + i * 40, 0, 40, 40, this);
        }
        
        g.drawImage(tosterImage, circleTX, circleTY, circleT, circleT, this);
        g.drawImage(wannaImage, x, y, wannaSize1, wannaSize2, this);
      
        for (int i = 0; i < kaczkaCircleX.size(); i++) {
            if (kaczkaCircleY.get(i) + circleK >= y && kaczkaCircleY.get(i) <= y + wannaSize2 && kaczkaCircleX.get(i) + circleK >= x && kaczkaCircleX.get(i) <= x + wannaSize1) {
                kaczkaCircleX.set(i, random.nextInt(550));
                kaczkaCircleY.set(i, 0);
                punkty++;
                pointsLabel.setText("Punkty: " + punkty);
                playMusic1();
            }
            
            if (kaczkaCircleY.get(i) >= 520) {
                playMusic3();
                kaczkaCircleY.set(i, 0);
                kaczkaCircleX.set(i, random.nextInt(550)); 
                lives--;
                if (lives == 0) {
                    endGame();
                }
            }
        }

        if (circleTY + circleT >= y && circleTY <= y + wannaSize2 && circleTX + circleT >= x && circleTX <= x + wannaSize1) {
            playMusic2();
            endGame();
        }
    }

    private void endGame() {
        kaczkaCircleTimer.stop();
        tosterCircleTimer.stop();
        circleTX = random.nextInt(550);
        circleTY = 0;
        ImageIcon imageIcon = new ImageIcon("Images/wanna_wybuch_ostateczne.png");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel("Umarłeś, twoje punkty: " + punkty);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(new Color(193, 4, 4));
        textLabel.setFont(new Font("Courier New", Font.BOLD, 40));

        panel.add(textLabel);
        panel.add(imageLabel);

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(okButton).dispose();
            showOptionDialog();
        });

        okButton.setBackground(Color.white);
        okButton.setForeground(Color.black);
        okButton.setBorder(new LineBorder(new Color(193, 4, 4), 4));
        okButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 40));

        panel.add(okButton);

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setContentPane(panel);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
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
        x = 0;
        kaczkaCircleX.clear();
        kaczkaCircleY.clear();
        circleTX = random.nextInt(550);
        circleTY = 0;
        addNewRedCircle();
        initGame();
    }

    private void initGame() {
        kaczkaCircleTimer.start();
        tosterCircleTimer.start();
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

    class MyKeyListener3 implements KeyListener {
        private CentrumPlanszy3 centrumPlanszy3;

        public MyKeyListener3(CentrumPlanszy3 centrumPlanszy3) {
            this.centrumPlanszy3 = centrumPlanszy3;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                centrumPlanszy3.moveLeft();
            }
            if (key == KeyEvent.VK_RIGHT) {
                centrumPlanszy3.moveRight();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}
