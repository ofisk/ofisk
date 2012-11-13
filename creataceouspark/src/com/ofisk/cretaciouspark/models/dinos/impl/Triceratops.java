package com.ofisk.cretaciouspark.models.dinos.impl;

import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.models.dinos.Dinosaur;
import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;
import com.ofisk.cretaciouspark.models.plants.Plant;

import java.util.List;
import java.util.Random;

public class Triceratops extends Dinosaur {

    private static final String _name = "com.ofisk.cretaciouspark.models.dinos.impl.Triceratops";
    private static final int _initCalorieCount = 18000;
    private static final int _movementCost = 60;
    private static final int _calorieWorth = 2400;
    private static final DietType _dietType = DietType.HERBIVORE;

    public Triceratops(Park park) {
        super(_name, park, _initCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    @Override
    public String getName() {
        return "com.ofisk.cretaciouspark.models.dinos.impl.Triceratops";
    }

    @Override
    protected void makeMove() {
        List<ParkObject>[][] surroundings = look();
        Random random = new Random();
        int direction = random.nextInt(4);
        Position next = null;
        boolean move = true;

        int horizontalTranslation = -1;
        for(int i = 0; i < surroundings.length; i++) {
            int verticalTranslation = -1;
            for(int j = 0; j < surroundings[0].length; j++) {
                if((i != 1 || j != 1) && surroundings[i][j] != null) {
                    for(ParkObject object : surroundings[i][j]) {
                        if(object instanceof Plant) {
                            eat((Food) object);
                            next = getPosition().modify(horizontalTranslation, verticalTranslation);
                            break;
                        }
                    }
                    if(next != null && !getPark().isValidPosition(next, this)) {
                        move = false;
                    }
                }
                verticalTranslation++;
            }
            horizontalTranslation++;
        }

        if(move) {
            switch(direction) {
                case 0:
                    next = getPosition().modify(0, 1);
                    break;
                case 1:
                    next = getPosition().modify(0, -1);
                    break;
                case 2:
                    next = getPosition().modify(1, 0);
                    break;
                case 3:
                    next = getPosition().modify(-1, 0);
                    break;
                default:
                    move = false;
                    break;
            }
        }

        if(move && getPark().isValidPosition(next, this)) {
            setPosition(next);
        }
    }
}
