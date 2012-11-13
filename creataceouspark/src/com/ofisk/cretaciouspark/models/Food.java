package com.ofisk.cretaciouspark.models;

public interface Food extends ParkObject {
    public int getCalorieWorth();

    public boolean isPoisonousToEat();
}
