package com.ofisk.cretaciouspark.models.dinos;

import com.ofisk.cretaciouspark.factories.ParkFactory;
import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;

import java.util.LinkedList;
import java.util.List;

public abstract class Dinosaur implements IDinosaur {
    private String _name;
    private Park _park;
    private Position _position;
    private int _calorieCount;
    private int _movementCost;
    private int _calorieWorth;
    private int _turnsSinceDeath = 0;
    private DietType _dietType;
    private boolean _dead = false;
    private int _age = 0;
    private int _matingCooldown = 0;

    public Dinosaur(String name, Park park, int initialCalorieCount, int movementCost, int calorieWorth, DietType dietType) {
        setName(name);
        setCalorieCount(initialCalorieCount);
        setMovementCost(movementCost);
        setCalorieWorth(calorieWorth);
        setDietType(dietType);

        if(park != null) {
            setParkAndPosition(park);
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public Position move(){
        if(!isDead()) {
            _age++;
            if(_matingCooldown > 0) {
                _matingCooldown--;
            }
            makeMove();
            incCalorieCount(getMovementCost() * -1);
            if(getCalorieCount() <= 0) {
                die();
            }
        } else {
            _turnsSinceDeath++;
        }
        return getPosition();
    }

    public void die() {
        _dead = true;
    }

    protected abstract void makeMove();

    @Override
    public void eat(Food food) {
        int calorieWorth = food.isPoisonousToEat() ? food.getCalorieWorth() * -1 : food.getCalorieWorth();
        incCalorieCount(calorieWorth);
        getPark().remove(food);
    }

    @Override
    public Park getPark() {
        return _park;
    }

    @Override
    public void setPark(Park park) {
        _park = park;
    }

    @Override
    public DietType getDietType() {
        return _dietType;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    @Override
    public ParkObject setParkAndPosition(Park park) {
        setPark(park);
        setPosition(park.assignRandomStart(this));
        return this;
    }

    protected void setPosition(Position position) {
        _position = position;
    }

    @Override
    public int getCalorieCount() {
        return _calorieCount;
    }

    @Override
    public int getMovementCost() {
        return _movementCost;
    }

    @Override
    public int getCalorieWorth() {
        return _calorieWorth;
    }

    @Override
    public boolean isPoisonousToEat() {
        return _turnsSinceDeath >= 10;
    }

    @Override
    public void setCalorieCount(int calorieCount) {
        _calorieCount = calorieCount;
    }

    @Override
    public void setMovementCost(int movementCost) {
        _movementCost = movementCost;
    }

    @Override
    public void setCalorieWorth(int calorieWorth) {
        _calorieWorth = calorieWorth;
    }

    @Override
    public void incCalorieCount(int incCalorieCount) {
        setCalorieCount(getCalorieCount() + incCalorieCount);
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public void setDietType(DietType dietType) {
        _dietType = dietType;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public List<ParkObject>[][] look() {
        List<ParkObject>[][] result = new List[3][3];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = new LinkedList<ParkObject>();
            }
        }

        int horizontalTranslation = 0;
        for(int i = getPosition().getHorizontal() - 1; i <= getPosition().getHorizontal() + 1; i++) {
            int verticalTranslation = 0;
            for(int j = getPosition().getVertical() - 1; j <= getPosition().getVertical() + 1; j++) {
                if(i >= 0 && i < getPark().getMap().length && j >= 0 && j < getPark().getMap()[0].length) {
                    result[horizontalTranslation][verticalTranslation] = getPark().getMap()[i][j];
                } else {
                    result[horizontalTranslation][verticalTranslation] = null;
                }
                verticalTranslation++;
            }
            horizontalTranslation++;
        }

        return result;
    }

    public boolean isDead() {
        return _dead;
    }

    public Dinosaur reproduce(Dinosaur mate)
            throws InstantiationException, IllegalAccessException {
        _matingCooldown = ParkFactory.getMatingCooldown();
        return mate.getClass().newInstance();
    }

    @Override
    public String getToken() {
        return getName().charAt(0) + "";
    }

    public boolean canMate(ParkObject mate) {
        return getClass().equals(mate.getClass()) &&
                getAge() >= ParkFactory.getSexualMaturityAge() &&
                getMatingCooldown() == 0 &&
                ((Dinosaur)mate).getMatingCooldown() == 0;
    }

    public int getAge() {
        return _age;
    }

    public int getMatingCooldown() {
        return _matingCooldown;
    }

    public void setParkAndPosition(Park park, Position position) {
        setPark(park);
        setPosition(position);
        park.assignStart(position, this);
    }
}
