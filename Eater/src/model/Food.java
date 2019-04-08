package model;
public class Food {
    private int x;
    private int y;
    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    /*
    Calculates the distance between the food and the worm
     */
    public double distance(Worm worm) {
        double distance = 0.5 * Math.sqrt(Math.pow( (worm.getLocationX()-getX()), 2) + Math.pow( (worm.getLocationY()-getY()), 2));
        return distance;
    }
}
