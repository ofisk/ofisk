package com.ofisk.cretaciouspark.models.plants;

import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;

public class Plant implements Food {
    private int _calorieWorth;
    private boolean _isPoisonous;
    private Position _position;

    public Plant(int calorieWorth, boolean isPoisonous) {
        _calorieWorth = calorieWorth;
        _isPoisonous = isPoisonous;
    }

    @Override
    public int getCalorieWorth() {
        return _calorieWorth;
    }

    @Override
    public boolean isPoisonousToEat() {
        return _isPoisonous;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    @Override
    public Plant setParkAndPosition(Park park) {
        _position = park.assignRandomStart(this);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.ofisk.cretaciouspark.models.plants.Plant:: poisonous: " + _isPoisonous + ", calorie worth: " + _calorieWorth);
        return sb.toString();
    }
}
