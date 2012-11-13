package com.ofisk.cretaciouspark.models;

import com.ofisk.cretaciouspark.models.shared.Position;

public interface ParkObject {
    public Position getPosition();

    public ParkObject setParkAndPosition(Park park);
}
