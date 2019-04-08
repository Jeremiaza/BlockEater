package controller;

import java.util.ArrayList;

import model.Food;
import model.Worm;

public interface Controller_IF {
	public ArrayList<Food> createBlocks(int amount, int width, int height);
	public Worm createWorm(int width, int height);
}
