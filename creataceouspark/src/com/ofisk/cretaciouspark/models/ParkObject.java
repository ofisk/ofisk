package com.ofisk.cretaciouspark.models;

import com.ofisk.cretaciouspark.models.shared.Position;

import java.awt.Color;

public interface ParkObject {
    public Position getPosition();

    public ParkObject setParkAndPosition(Park park);

    public void setParkAndPosition(Park park, Position position);

    public Color getColor();

    public String getToken();
}
