package com.ofisk.cretaciouspark.views;

import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;
import com.ofisk.cretaciouspark.models.dinos.Dinosaur;
import com.ofisk.cretaciouspark.views.tools.GameColors;

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

                            //Choose color
                            if (object instanceof Dinosaur && ((Dinosaur) object).isDead()) {
                                g2D.setColor(GameColors.DEAD_GRAY);
                            } else if(object == _park.getWinner()) {
                                g2D.setColor(GameColors.WINNER_GOLD);
                            } else {
                                if(((Food) object).isPoisonousToEat()) {
                                    g2D.setColor(GameColors.POISONOUS_PURPLE);
                                } else {
                                    g2D.setColor(object.getColor());
                                }
                            }

                            g2D.drawString(object.getToken(), j * 10, k * 10);
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
