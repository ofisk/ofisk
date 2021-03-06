package com.ofisk.cretaciouspark.models.dinos;

import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;

import java.util.List;

public interface IDinosaur extends Food, ParkObject {
    public String getName();

    public void setName(String name);

    public Position move();

    public void eat(Food food);

    public DietType getDietType();

    public void setDietType(DietType dietType);

    public Position getPosition();

    public int getCalorieCount();

    public void setCalorieCount(int calorieCount);

    public int getMovementCost();

    public void setMovementCost(int movementCost);

    public void incCalorieCount(int incCalorieCount);

    public void setPark(Park park);

    public Park getPark();

    public void setCalorieWorth(int calorieWorth);

    public List<ParkObject>[][] look();

    public void die();

    public boolean isDead();

    public Dinosaur reproduce(Dinosaur mate)
            throws InstantiationException, IllegalAccessException;

    public boolean canMate(ParkObject mate);

    public int getAge();

    public int getMatingCooldown();
}
