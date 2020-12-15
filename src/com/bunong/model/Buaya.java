/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.model;

import com.bunong.model.proxy.ProxyImage;
import com.bunong.view.Window;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import com.bunong.model.BunongObject;

/**
 *
 * @author Ridho Afwani
 */
public class Buaya extends BunongObject {

    private ProxyImage proxyImage;
    private Rintangan[] rintangan;

    public Buaya(int x, int y) {
        super(x, y);
        if (proxyImage == null) {
            proxyImage = new ProxyImage("/assets/bunong.gif");
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.x -= width;
        this.y -= height;
        rintangan = new Rintangan[1];
        rintangan[0] = new Rintangan(900, Window.HEIGHT - 60);
        this.dy = 4;
    }

    @Override
    public void tick() {
        if (dy < 5) {
            dy += 2;
        }
        this.y += dy;
        rintangan[0].tick();
        checkWindowBorder();
        
    }

    public void jump() {
        if (dy > 0) {
            dy = 0;
        }
        dy -= 15;
        System.out.println("jump");
    }

    private void checkWindowBorder() {
        if (this.x > Window.WIDTH) {
            this.x = Window.WIDTH;
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y > Window.HEIGHT - 50) {
            this.y = Window.HEIGHT - 50;
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
        rintangan[0].render(g, obs);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
