package com.ofisk.cretaciouspark.models.dinos.impl;

import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Direction;
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

public class Oviraptor extends Dinosaur {
    private static final String _name = "Oviraptor";
    private static final int _initialCalorieCount = 50000;
    private static final int _movementCost = 700;
    private static final int _calorieWorth = 5000;
    private static final DietType _dietType = DietType.OMNIVORE;
    private Direction _direction = null;
    private int _numTurnsThisDirection = 0;

    public Oviraptor() {
        super(_name, null, _initialCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    public Oviraptor(Park park) {
        super(_name, park, _initialCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    @Override
    protected void makeMove() {
        Random r = new Random();
        Position newPosition = null;
        if(_numTurnsThisDirection <= 0) {
            switch (r.nextInt(4)) {
                case 0:
                    _direction = Direction.UP;
                    break;
                case 1:
                    _direction = Direction.RIGHT;
                    break;
                case 2:
                    _direction = Direction.DOWN;
                    break;
                case 3:
                    _direction = Direction.LEFT;
                    break;
                default:
                    _direction = Direction.DOWN;
                    break;
            }
            _numTurnsThisDirection = r.nextInt(10);
        }

        switch (_direction) {
            case UP:
                newPosition = getPosition().modify(0, 1);
                break;
            case RIGHT:
                newPosition = getPosition().modify(1, 0);
                break;
            case DOWN:
                newPosition = getPosition().modify(0, -1);
                break;
            case LEFT:
                newPosition = getPosition().modify(-1, 0);
                break;
        }
        _numTurnsThisDirection -= 1;

        if(!getPark().isValidPosition(newPosition, this)) {
            _numTurnsThisDirection = 0;
        } else {
            List<ParkObject> objects = new LinkedList<ParkObject>();
            objects.addAll(getPark().getMap()[newPosition.getHorizontal()][newPosition.getVertical()]);
            for(ParkObject object : objects) {
                if(canMate(object)) {
                    try {
                        Dinosaur offspring = reproduce((Dinosaur) object);
                        offspring.setParkAndPosition(getPark(), getPosition());
                        getPark().addDino(offspring);
                    }
                    catch (InstantiationException e) {e.printStackTrace();}
                    catch (IllegalAccessException e) {e.printStackTrace();}
                }
                else if(object instanceof Food) {
                    eat((Food) object);
                }
            }
            setPosition(newPosition);
        }
    }

    @Override
    public Color getColor() {
        return GameColors.OVIRAPTOR_ORANGE;
    }
}
