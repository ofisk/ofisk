package com.ofisk.cretaciouspark.models;

import com.ofisk.cretaciouspark.factories.ParkFactory;
import com.ofisk.cretaciouspark.models.dinos.Dinosaur;
import com.ofisk.cretaciouspark.models.dinos.IDinosaur;
import com.ofisk.cretaciouspark.models.plants.Plant;
import com.ofisk.cretaciouspark.models.shared.DietType;
import com.ofisk.cretaciouspark.models.shared.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Park {
    private IDinosaur _winner = null;
    private List<IDinosaur> _dinosaurs = new ArrayList<IDinosaur>();
    private List<Plant> _plants = new ArrayList<Plant>();
    private int _turn = 0;
    private List<ParkObject>[][] _map;

    public boolean hasWinner() {
        return _winner != null;
    }

    public void next()
            throws InterruptedException {
        Position oldPosition;
        Position newPosition;
        List<IDinosaur> dinosaurs = new LinkedList<IDinosaur>();
        dinosaurs.addAll(_dinosaurs);
        for(IDinosaur dinosaur : dinosaurs) {
            oldPosition = dinosaur.getPosition();

            newPosition = dinosaur.move();

            _map[oldPosition.getHorizontal()][oldPosition.getVertical()].remove(dinosaur);
            _map[newPosition.getHorizontal()][newPosition.getVertical()].add(dinosaur);
        }

        Thread.sleep(ParkFactory.getTurnDelayMS());

        calculateWinner();

        _turn++;
    }

    private void calculateWinner() {
        if(_dinosaurs.size() == 1) {
            _winner = _dinosaurs.get(0);
        } else {
            Dinosaur winner = null;
            int numSurvivors = 0;
            for(IDinosaur dinosaur : _dinosaurs) {
                if(!dinosaur.isDead()) {
                    numSurvivors++;
                    winner = (Dinosaur) dinosaur;
                }
            }
            if(numSurvivors == 1) {
                _winner = winner;
            }
        }
    }

    public IDinosaur getWinner() {
        return _winner;
    }

    public Park addDinos(List<IDinosaur> dinos) {
        _dinosaurs.clear();
        _dinosaurs.addAll(dinos);
        return this;
    }

    public Park addPlants(List<Plant> plants) {
        _plants.clear();
        _plants.addAll(plants);
        return this;
    }

    public boolean isValidPosition(Position position, ParkObject object) {
        if(position.getHorizontal() >= _map.length || position.getVertical() >= _map[0].length || position.getHorizontal() < 0 || position.getVertical() < 0) {
            return false;
        }

        List<ParkObject> target = _map[position.getHorizontal()][position.getVertical()];

        if(target.isEmpty()) {
            return true;
        }

        if(object instanceof IDinosaur) {
            switch (((IDinosaur) object).getDietType()) {
                case HERBIVORE:
                    for(ParkObject targetObject : target) {
                        if(targetObject instanceof Plant || (targetObject instanceof Dinosaur && !((Dinosaur)targetObject).getDietType().equals(DietType.HERBIVORE))) {
                            return false;
                        }
                    }
                    return true;
                case OMNIVORE:
                    for(ParkObject targetObject : target) {
                        if((targetObject instanceof Dinosaur && !((Dinosaur)targetObject).getDietType().equals(DietType.HERBIVORE))) {
                            return false;
                        }
                    }
                    return true;
                case CARNIVORE:
                    for(ParkObject targetObject : target) {
                        if((targetObject instanceof Dinosaur && !((Dinosaur)targetObject).getDietType().equals(DietType.HERBIVORE))) {
                            return false;
                        }
                    }
                    return true;
                default:
                    return target.isEmpty();
            }
        } else {
            return target.isEmpty();
        }
    }

    public Position assignRandomStart(ParkObject object) {
        Random r = new Random();
        Integer horizontal = r.nextInt(ParkFactory.getMaxHorizontal());
        Integer vertical = r.nextInt(ParkFactory.getMaxVertical());

        while(!isValidPosition(new Position(horizontal, vertical), object)) {
            horizontal = r.nextInt(ParkFactory.getMaxHorizontal());
            vertical = r.nextInt(ParkFactory.getMaxVertical());
        }

        _map[horizontal][vertical].add(object);
        return new Position(horizontal, vertical);
    }

    public void setMap(List<ParkObject>[][] map) {
        _map = map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.ofisk.cretaciouspark.models.Park Report: \n");
        for(int j = 0; j < _map.length; j++) {
            for(int k = 0; k < _map[0].length; k++) {
                if(_map[j][k] == null) {
                    sb.append("   map(" + j + ", " + k + ") is empty.\n");
                } else {
                    sb.append("   map(" + j + ", " + k + ") has: " + _map[j][k].toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

    public void remove(ParkObject object) {
        if(object instanceof Dinosaur) {
            _dinosaurs.remove(object);
        }
        _map[object.getPosition().getHorizontal()][object.getPosition().getVertical()].remove(object);
    }

    public List<ParkObject>[][] getMap() {
        return _map;
    }

    public void addDino(Dinosaur dinosaur) {
        _dinosaurs.add(dinosaur);
        assignRandomStart(dinosaur);
    }

    public void assignStart(Position position, ParkObject object) {
        _map[position.getHorizontal()][position.getVertical()].add(object);
    }
}
