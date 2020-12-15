/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.view;

/**
 *
 * @author zikrurridhoafwani
 */
//import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
    static Music player = new Music();
    static Clip clip;
    
    String filepath2 = "/Users/zikrurridhoafwani/BunongGame/src/assets/music/bunongMusic.wav";
    public Music(){

    }
    
    public static Music getInstance(){
        return player;
    }
    
    public void loadMusic(String filepath){
        try {
            File musicPath = new File(filepath);
            System.out.println(musicPath.getAbsolutePath());
            
            if(musicPath.exists()) {
                System.out.println("Initialized");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }
        }
        
        catch(Exception e){
            System.out.println("Yes");
        }
    }
}