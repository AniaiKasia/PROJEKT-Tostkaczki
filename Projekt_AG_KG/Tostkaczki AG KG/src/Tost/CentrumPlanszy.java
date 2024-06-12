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

public class CentrumPlanszy extends JPanel {

    private int circleKX, circleKY, circleTX, circleTY;
    private final int circleK = 55;
    private final int circleT = 60;
    private final int screenWysokosc = 600;
    private Timer kaczkaCircleTimer;
    private Timer tosterCircleTimer;
    Random random = new Random();

    JLabel pointsLabel;
    Clip clip1, clip2, clip3;

    private int x = 0;
    private int y = 430;
    Color color7, color8;
    private final int panelSzerokosc = 640;
    private final int wannaSize1 = 120;
    private final int wannaSize2 = 80;
    private final int szybkosc = 15;
    private MyKeyListener keyListener;
    private int punkty = 0;
    private int lives = 3;
    private Image backgroundImage;
    private Image kaczkaImage;
    private Image tosterImage;
    private Image wannaImage;
    private Image SERCEImage1;

    JButton exitButton;
    Plansza1 parentFrame;

    public CentrumPlanszy(Plansza1 parentFrame) {
        this.parentFrame = parentFrame;

        setFocusable(true);
        keyListener = new MyKeyListener(this);
        addKeyListener(keyListener);

        setLayout(null);

        pointsLabel = new JLabel("Punkty: 0");
        pointsLabel.setBounds(275, 5, 100, 30);
        add(pointsLabel);

        pointsLabel.setFont(new Font("Courier New", Font.CENTER_BASELINE, 15));
       
        backgroundImage = Toolkit.getDefaultToolkit().createImage("Images/tło.png");
        kaczkaImage = Toolkit.getDefaultToolkit().createImage("Images/kaczucha_ostateczne_rand5.png");
        tosterImage = Toolkit.getDefaultToolkit().createImage("Images/toster_ostateczny.png");
        wannaImage = Toolkit.getDefaultToolkit().createImage("Images/wanna_ostateczny.png");
        SERCEImage1 = Toolkit.getDefaultToolkit().createImage("Images/SERCE_CZER.png");

        color7 = new Color(224, 255, 255); // kolor okienek
        color8 = new Color(0, 191, 255);// kolor czcionki

        exitButton = new JButton("Exit");
        exitButton.setBounds(590, 10, 40, 20);
        
        exitButton.setBackground(color7);
        exitButton.setForeground(Color.black);
        exitButton.setBorder(new LineBorder(Color.black, 2));
        exitButton.setPreferredSize(new Dimension(40, 20));
        exitButton.setFont(new Font("Courier New", Font.CENTER_BASELINE, 10));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        add(exitButton);

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
                circleKY += 2;
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
                circleTY += 3;
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
            clip2 = AudioSystem.getClip();
            clip2.open(audioIn);
            FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(6.0f);
            clip2.start();
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
        pointsLabel.setText("Punkty: " + punkty);
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

    class MyKeyListener implements KeyListener {
        private CentrumPlanszy centrumPlanszy;

        public MyKeyListener(CentrumPlanszy centrumPlanszy) {
            this.centrumPlanszy = centrumPlanszy;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                centrumPlanszy.moveLeft();
            }
            if (key == KeyEvent.VK_RIGHT) {
                centrumPlanszy.moveRight();
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
