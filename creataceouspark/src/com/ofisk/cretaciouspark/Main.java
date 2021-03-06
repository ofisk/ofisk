package com.ofisk.cretaciouspark;

import com.ofisk.cretaciouspark.factories.ParkFactory;
import com.ofisk.cretaciouspark.models.dinos.IDinosaur;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.views.BasicFrame;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {
        Park park = ParkFactory.newPark();

        BasicFrame frame = new BasicFrame();
        frame.setPark(park);
        frame.setSize(ParkFactory.getMaxHorizontal() * 10, ParkFactory.getMaxVertical() * 10);
        frame.setVisible(true);

        while(!park.hasWinner()) {
            park.next();
            frame.repaint();
        }

        frame.repaint();

        IDinosaur winner = park.getWinner();

        System.out.println("And the winner is: " + winner.getName());
    }
}
