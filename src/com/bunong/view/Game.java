/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.view;

import com.bunong.controller.Controller;
import com.bunong.controller.Controllerv1;
import com.bunong.model.Buaya;
import com.bunong.model.Rintangan;
import com.bunong.model.TubeColumn;
import com.bunong.model.proxy.ProxyImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ridho Afwani
 */
public class Game extends JPanel implements ActionListener {

    int scoretoDB;

    private boolean isRunning = false;
    private ProxyImage proxyImage;
    private Image background;
    private Buaya buaya;
    private TubeColumn tubeColumn;
    private int score;
    private int highScoreFromDB;
    private String nameFromDB;

    public Game() {
        System.out.println("Game loaded");
        proxyImage = new ProxyImage("/assets/background.png");
        background = proxyImage.loadImage().getImage();
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        if (isRunning) {
            ////////////////////////////////
            buaya.tick();
            tubeColumn.tick();
            checkColision();
            score++;
            ///////////////////////////////
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        if (isRunning) {
            ///////////////////////////////
            this.buaya.render(g2, this);
            this.tubeColumn.render(g2, this);
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Score Anda : " + this.tubeColumn.getPoints(), 10, 20);
            ///////////////////////////////
        } else {
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Tekan Enter Untuk Memulai Game", Window.WIDTH / 2 - 150, Window.HEIGHT / 2);
            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 15));
            g2.drawString("Karya dari Bunong Team", Window.WIDTH - 200, Window.HEIGHT - 50);
        }
        g2.setColor(Color.black);
        g.setFont(new Font("Arial", 1, 20));

        ResultSet rs = sql.executeReadQuerry("Select * from bunong order by score desc limit 1");

        try {
            while (rs.next()) {
                nameFromDB = rs.getString("name");
                highScoreFromDB = rs.getInt("score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2.drawString("High Score: " + highScoreFromDB + "(" + nameFromDB + ")", Window.WIDTH - 300, 20);
        g.dispose();
    }

    private void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.buaya = new Buaya(Window.WIDTH / 2, Window.HEIGHT / 2);
            this.tubeColumn = new TubeColumn();
        }
    }

    private void endGame() {
        this.isRunning = false;
        scoretoDB = this.tubeColumn.getPoints();
        if (scoretoDB != 0) {
            sql.executeUpdateQuerry(
                    "insert into bunong (name, score) values ('" + UsernamePanel.username + "','" + scoretoDB + "') ");
            System.out.println("data masuk DB");
        }

        this.tubeColumn.setPoints(0);

    }

    private void checkColision() {
        Rectangle rectBird = this.buaya.getBounds();
        Rectangle rectTube;

        for (int i = 0; i < this.tubeColumn.getTubes().size(); i++) {
            Rintangan tempTube = this.tubeColumn.getTubes().get(i);
            rectTube = tempTube.getBounds();
            if (rectBird.intersects(rectTube)) {
                endGame();
            }
        }
    }

    // Key
    private class GameKeyAdapter extends KeyAdapter {

        private final Controller controller;
        private final Controllerv1 controllerv1;

        public GameKeyAdapter() {
            controller = new Controller();
            controllerv1 = new Controllerv1();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (isRunning) {
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    menu();
                }
                controller.controllerReleased(buaya, e);
            }
        }
    }
    
    private void menu(){
        System.out.println("button clicked");
        Menu menu = new Menu();
        Window.frame.getContentPane().removeAll();
        Window window = new Window(WIDTH, HEIGHT, "Bunong Game", menu);
    }
}
