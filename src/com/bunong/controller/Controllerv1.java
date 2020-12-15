/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.controller;

import com.bunong.model.Buaya;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ridho Afwani
 */
public class Controllerv1 implements IStrategy {

    @Override
    public void controller(Buaya buaya, KeyEvent kevent) {
    }

    @Override
    public void controllerReleased(Buaya buaya, KeyEvent kevent) {
        if (kevent.getKeyCode() == KeyEvent.VK_ENTER) {
            buaya.jump();
        }
    }

}
