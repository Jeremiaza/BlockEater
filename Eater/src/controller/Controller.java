package controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.stage.Stage;
import model.Food;
import model.Worm;
import view.Launcher;

public class Controller implements Controller_IF {
	public Controller() {
		
	}
	@Override
	public ArrayList<Food> createBlocks(int amount, int width, int height) {
		int i = 0;
		Random r = new Random();
		ArrayList<Food> foods = new ArrayList<Food>();
		while (i<amount) {
			foods.add(new Food(r.nextInt(width), r.nextInt(height)));
			i++;
		}
		return foods;
	}
	@Override
	public Worm createWorm(int width, int height) {
		// TODO Auto-generated method stub
		return new Worm(width, height);
	}
}
