package com.ofisk.cretaciouspark.models.dinos.impl;

import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.models.dinos.Dinosaur;
import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;
import com.ofisk.cretaciouspark.views.tools.GameColors;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Velociraptor extends Dinosaur {

    private static final String _name = "Velociraptor";
    private static final int _initCalorieCount = 10000;
    private static final int _movementCost = 120;
    private static final int _calorieWorth = 5000;
    private static final DietType _dietType = DietType.CARNIVORE;

    public Velociraptor() {
        super(_name, null, _initCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    public Velociraptor(Park park) {
        super(_name, park, _initCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    @Override
    public void makeMove() {
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
                    List<ParkObject> surroundingObjects = new LinkedList<ParkObject>();
                    surroundingObjects.addAll(surroundings[i][j]);
                    for(ParkObject object : surroundingObjects) {
                        if(canMate(object)) {
                            try {
                                Dinosaur offspring = reproduce((Dinosaur) object);
                                offspring.setParkAndPosition(getPark(), getPosition());
                                getPark().addDino(offspring);
                            }
                            catch (InstantiationException e) {e.printStackTrace();}
                            catch (IllegalAccessException e) {e.printStackTrace();}
                        }
                        else if(object instanceof Dinosaur && !(object instanceof Velociraptor)) {
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

    @Override
    public Color getColor() {
        return GameColors.VELOCIRAPTOR_RED;
    }
}
