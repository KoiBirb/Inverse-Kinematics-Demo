package Main;

import InverseKinematics.Angles;
import InverseKinematics.Leg;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

        public final int screenWidth = 680; // game screen width
        public final int screenHeight = 680; // game screen height

        int FPS = 60; // frames per second

        Thread gameThread;
        KeyListener keyInput = new KeyListener(this);
        Mouselistener mouseInput = new Mouselistener();
        public Leg leg = new Leg();

        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.WHITE);
            this.addKeyListener(keyInput);
            this.addMouseListener(mouseInput);
            this.addMouseMotionListener(mouseInput);
            this.setFocusable(true);
        }

        public void startGameThread() {
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public void run() {

            // Delta method FPS clock
            double drawInterval = 1000000000.0/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (gameThread != null) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if(delta >= 1) {
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                }
                if(timer>= 1000000000) {
//                    System.out.println("FPS:" + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

        public void update() {
            leg.update();
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            leg.draw(g2);

            g2.dispose();
    }
}
