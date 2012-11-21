package com.ofisk.cretaciouspark.factories;

import com.ofisk.cretaciouspark.models.dinos.IDinosaur;
import com.ofisk.cretaciouspark.models.dinos.impl.Oviraptor;
import com.ofisk.cretaciouspark.models.Park;
import com.ofisk.cretaciouspark.models.ParkObject;
import com.ofisk.cretaciouspark.models.plants.Plant;
import com.ofisk.cretaciouspark.models.dinos.impl.Triceratops;
import com.ofisk.cretaciouspark.models.dinos.impl.Velociraptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ParkFactory {
    private static final int _maxHorizontal = 140;
    private static final int _maxVertical = 90;
    private static int _numberOfPlants = (int)(_maxHorizontal * _maxVertical * .95);
    private static final int _percentOfPoisonousPlants = 4;
    private static int _maxCalorieWorth = 40;
    private static int _numVelociraptors = 40;
    private static int _numTriceratops = 40;
    private static int _numJoeasaurus = 40;
    private static long _turnDelayMS = 80;

    public static Park newPark() {
        Park newPark = new Park();

        newPark.setMap(newParkMap());
        newPark.addPlants(getInitialPlants(newPark));
        newPark.addDinos(getInitialDinos(newPark));

        return newPark;
    }

    private static List<Plant> getInitialPlants(Park park) {
        List<Plant> result = new LinkedList<Plant>();

        Random r = new Random();

        for(int i = 0; i < _numberOfPlants; i++) {
            result.add(new Plant(r.nextInt(_maxCalorieWorth), (r.nextInt(100) < _percentOfPoisonousPlants)).setParkAndPosition(park));
        }

        return result;
    }

    private static List<IDinosaur> getInitialDinos(Park park) {
        List<IDinosaur> result = new LinkedList<IDinosaur>();

        for(int i = 0; i < _numVelociraptors; i++) {
            result.add(new Velociraptor(park));
        }
        
        for(int i = 0; i < _numTriceratops; i++) {
            result.add(new Triceratops(park));
        }

        for(int i = 0; i < _numJoeasaurus; i++) {
            result.add(new Oviraptor(park));
        }

        return result;
    }

    public static List<ParkObject>[][] newParkMap() {
        LinkedList<ParkObject>[][] result = new LinkedList[getMaxHorizontal()][getMaxVertical()];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = new LinkedList<ParkObject>();
            }
        }
        return result;
    }

    public static int getMaxHorizontal() {
        return _maxHorizontal;
    }

    public static int getMaxVertical() {
        return _maxVertical;
    }

    public static long getTurnDelayMS() {
        return _turnDelayMS;
    }
}
