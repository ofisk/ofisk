public class Position {
    private int _horizontal;
    private int _vertical;

    public int getHorizontal() {
        return _horizontal;
    }

    public int getVertical() {
        return _vertical;
    }

    public Position(int horizontal, int vertical) {
        _vertical = vertical;
        _horizontal = horizontal;
    }

    public Position modify(int horizontalChange, int verticalChange) {
        return new Position(_horizontal + horizontalChange, _vertical + verticalChange);
    }
}
