package com.ofisk.cretaciouspark.views;

import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;
import com.ofisk.cretaciouspark.models.dinos.Dinosaur;
import com.ofisk.cretaciouspark.models.dinos.impl.Joeasaurus;
import com.ofisk.cretaciouspark.models.dinos.impl.Triceratops;
import com.ofisk.cretaciouspark.models.dinos.impl.Velociraptor;
import com.ofisk.cretaciouspark.models.plants.Plant;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class BasicFrame extends JFrame {
    Park _park;

    public BasicFrame(){
        super();
    }

    public void setPark(Park park) {
        _park = park;
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        List<ParkObject>[][] map = _park.getMap();
        List<ParkObject> objects;
        for(int j = 0; j < map.length; j++) {
            for(int k = 0; k < map[0].length; k++) {
                g2D.clearRect(j * 10, k * 10, 10, 10);
                objects = map[j][k];
                if(!objects.isEmpty()) {
                    for(ParkObject object : objects) {
                        if(object instanceof Food) {

                            if (object instanceof Dinosaur && ((Dinosaur) object).isDead()) {
                                g2D.setColor(Color.YELLOW);
                            }

                            if(((Food) object).isPoisonousToEat()) {
                                g2D.setColor(Color.MAGENTA);
                            } else {
                                if(object instanceof Plant) {
                                    g2D.setColor(Color.GREEN);
                                } else if (object instanceof Velociraptor) {
                                    g2D.setColor(Color.RED);
                                } else if (object instanceof Triceratops) {
                                    g2D.setColor(Color.BLUE);
                                } else if (object instanceof Joeasaurus) {
                                    g2D.setColor(Color.RED);
                                }
                            }

                            if(object == _park.getWinner()) {
                                g2D.setColor(Color.BLACK);
                            }

                            if(object instanceof Plant) {
                                g2D.drawString("P", j * 10, k * 10);
                            } else if (object instanceof Velociraptor) {
                                g2D.drawString("V", j * 10, k * 10);
                            } else if (object instanceof Triceratops) {
                                g2D.drawString("T", j * 10, k * 10);
                            } else if (object instanceof Joeasaurus) {
                                g2D.drawString("J", j * 10, k * 10);
                            }
                        }
                    }
                } else {
                    g2D.setColor(Color.BLACK);
                    g2D.drawString(".", j * 10, k * 10);
                }
            }
        }
    }
}
