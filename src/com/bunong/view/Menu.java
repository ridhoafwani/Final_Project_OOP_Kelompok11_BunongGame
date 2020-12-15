/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.view;

//import static com.bunong.view.Window.HEIGHT;
//import static com.bunong.view.Window.WIDTH;
//import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author zikrurridhoafwani
 */
public class Menu extends javax.swing.JPanel implements Runnable {

    static String username;
    boolean musiconoff;
    Music music = new Music();
    

    public Menu() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playbutton = new javax.swing.JButton();
        onoff = new javax.swing.JButton();
        leaderboard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(null);

        playbutton.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        playbutton.setText("Play");
        playbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playbuttonActionPerformed(evt);
            }
        });
        add(playbutton);
        playbutton.setBounds(320, 160, 200, 50);

        onoff.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        onoff.setText("ON/OFF Music");
        onoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onoffActionPerformed(evt);
            }
        });
        add(onoff);
        onoff.setBounds(320, 400, 200, 50);

        leaderboard.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        leaderboard.setText("Leaderboard");
        leaderboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderboardActionPerformed(evt);
            }
        });
        add(leaderboard);
        leaderboard.setBounds(320, 280, 200, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void onoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onoffActionPerformed
        if (musiconoff==true){
            music.clip.stop();
            musiconoff = false;
        }
        else{
            music.clip.start();
            musiconoff = true;
        }
        
    }//GEN-LAST:event_onoffActionPerformed

    private void leaderboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderboardActionPerformed
        System.out.println("button clicked");
        Leaderboard leadpan = new Leaderboard();
        Window.frame.getContentPane().removeAll();
        Window window = new Window(WIDTH, HEIGHT, "Bunong Game", leadpan);
    }//GEN-LAST:event_leaderboardActionPerformed

    private void playbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                      
        System.out.println("button clicked");
        UsernamePanel userpan = new UsernamePanel();
        Window.frame.getContentPane().removeAll();
        Window window = new Window(WIDTH, HEIGHT, "Bunong Game", userpan);
        
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton leaderboard;
    private javax.swing.JButton onoff;
    private javax.swing.JButton playbutton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        music.loadMusic(music.filepath2);
        music.clip.start();
        musiconoff = true;
    }
}