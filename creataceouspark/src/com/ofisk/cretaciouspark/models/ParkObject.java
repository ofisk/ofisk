package com.ofisk.cretaciouspark.models;

import com.ofisk.cretaciouspark.models.shared.Position;

import java.awt.Color;

public interface ParkObject {
    public Position getPosition();

    public ParkObject setParkAndPosition(Park park);

    Color getColor();

    String getToken();
}
