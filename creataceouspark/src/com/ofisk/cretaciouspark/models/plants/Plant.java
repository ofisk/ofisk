package com.ofisk.cretaciouspark.models.plants;

import com.ofisk.cretaciouspark.models.Food;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.shared.Position;
import com.ofisk.cretaciouspark.views.tools.GameColors;

import java.awt.Color;

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
    public void setParkAndPosition(Park park, Position position) {
        park.assignStart(position, this);
    }

    @Override
    public Color getColor() {
        return GameColors.GRASS_GREEN;
    }

    @Override
    public String getToken() {
        return "P";
    }
}
