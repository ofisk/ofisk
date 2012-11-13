package parkresources.dinosaurs;

import parkresources.Dinosaur;
import parkresources.Food;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Joeasaurus extends Dinosaur {
    private static final String _name = "parkresources.dinosaurs.Joeasaurus";
    private static final int _initialCalorieCount = 50000;
    private static final int _movementCost = 70;
    private static final int _calorieWorth = 5000;
    private static final DietType _dietType = DietType.OMNIVORE;
    private Direction _direction = null;
    private int _numTurnsThisDirection = 0;

    public Joeasaurus(Park park) {
        super(_name, park, _initialCalorieCount, _movementCost, _calorieWorth, _dietType);
    }

    @Override
    protected void makeMove() {
        Random r = new Random();
        Position newPosition = null;
        if(_numTurnsThisDirection <= 0) {
            switch (r.nextInt(4)) {
                case 0:
                    _direction = Direction.UP;
                    break;
                case 1:
                    _direction = Direction.RIGHT;
                    break;
                case 2:
                    _direction = Direction.DOWN;
                    break;
                case 3:
                    _direction = Direction.LEFT;
                    break;
                default:
                    _direction = Direction.DOWN;
                    break;
            }
            _numTurnsThisDirection = r.nextInt(10);
        }

        switch (_direction) {
            case Direction.UP:
                newPosition = getPosition().modify(0, 1);
                break;
            case Direction.RIGHT:
                newPosition = getPosition().modify(1, 0);
                break;
            case Direction.DOWN:
                newPosition = getPosition().modify(0, -1);
                break;
            case Direction.LEFT:
                newPosition = getPosition().modify(-1, 0);
                break;
        }
        _numTurnsThisDirection -= 1;

        if(!getPark().isValidPosition(newPosition, this)) {
            _numTurnsThisDirection = 0;
        } else {
            List<ParkObject> objects = new LinkedList<ParkObject>();
            objects.addAll(getPark().getMap()[newPosition.getHorizontal()][newPosition.getVertical()]);
            for(ParkObject object : objects) {
                if(object instanceof Food) {
                    eat((Food) object);
                }
            }
            setPosition(newPosition);
        }
    }
}
