package model;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Worm {
    private HashMap<Integer, HashMap<Integer, Double>> coordinates;
    int locationX;
    int locationY;
    private int width;
    private int height;
    public Worm(int width, int height) {
        this.coordinates = new HashMap<>();
        int x = 0;
        while (x < width) {
            this.coordinates.putIfAbsent(x, new HashMap<>());

            int y = 0;
            while (y < height) {
                this.coordinates.get(x).put(y, 0.0);
                y++;
            }

            x++;
        }
        this.width = width;
        this.height = height;
        /*
        Worm's location is at the center
         */
        this.locationX = width / 2;
        this.locationY = height / 2;

        this.coordinates.get(this.locationX).put(this.locationY, 1.0);
    }

    public void walk(ArrayList<Food> foodlist) {
        double shortestdistance = 0;
        ArrayList<Double> distances = new ArrayList<>();
        int index = 0;
        int x = 0;
        while (x < this.coordinates.size()) {
            int y = 0;

            while (y < this.coordinates.get(x).size()) {

                double trail = this.coordinates.get(x).get(y);
                if (trail > 0) {
                    trail -= 0.01;
                }

                this.coordinates.get(x).put(y, trail);
                y++;
            }

            x++;
        }
        /*
        Gets the closest food source from the worm
        Index and distance of the closest food source is saved
        Index is needed to check the distance to the food source while moving
         */
        shortestdistance = foodlist.get(0).distance(this);
        for (int i = 0; i<foodlist.size(); i++) {
            if (foodlist.get(i).distance(this) < shortestdistance) {
                shortestdistance = foodlist.get(i).distance(this);
                index = i;
            }
            distances.add(foodlist.get(i).distance(this));
        }

        Random r = new Random();
        int rndm = r.nextInt(4);
        /*
        Moves the worm using a random number
         */
        if (rndm == 0 && this.locationX > 0) {
            this.locationX -= 1;
            double distanceatnextpoint = foodlist.get(index).distance(this);
            if (distanceatnextpoint > shortestdistance) {
                this.locationX += 1;
            }
        }
        if (rndm == 1 && this.locationX < width) {
            this.locationX += 1;
            double distanceatnextpoint = foodlist.get(index).distance(this);
            if (distanceatnextpoint > shortestdistance) {
                this.locationX -= 1;
            }
        }
        if (rndm == 2 && this.locationY < height) {
            this.locationY += 1;
            double distanceatnextpoint = foodlist.get(index).distance(this);
            if (distanceatnextpoint > shortestdistance) {
                this.locationY -= 1;
            }
        }
        if (rndm == 3 && this.locationY > 0) {
            this.locationY -= 1;
            double distanceatnextpoint = foodlist.get(index).distance(this);
            if (distanceatnextpoint > shortestdistance) {
                this.locationY += 1;
            }
        }
        this.coordinates.get(this.locationX).put(this.locationY, 1.0);
    }
    
    public void manualWalk() {
    	System.out.println("you called");
    	KeyListener listener = new KeyListener() { 
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("keytyped");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==87) { // W
					System.out.println("w painettu");
					setLocationY(getLocationY()+1);
					getCoordinates().get(getLocationX()).put(getLocationY()+1, 1.0);
				}
				if (e.getKeyCode()==65) { // A
					setLocationX(getLocationX()-1);
					getCoordinates().get(getLocationX()-1).put(getLocationY(), 1.0);
				}
				if (e.getKeyCode()==83) { // S
					setLocationY(getLocationY()-1);
					getCoordinates().get(getLocationX()).put(getLocationY()-1, 1.0);
					
				}
				if (e.getKeyCode()==68) { // D
					setLocationX(getLocationX()+1);
					getCoordinates().get(getLocationX()+1).put(getLocationY(), 1.0);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("keyreleased");
				// TODO Auto-generated method stub
				
			}
    		
    	};
    	
    }

    public HashMap<Integer, HashMap<Integer, Double>> getCoordinates() {
        return coordinates;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }
    public void setLocationX(int x) {
    	this.locationX=x;
    }
    public void setLocationY(int y) {
    	this.locationY=y;
    }
    
}
