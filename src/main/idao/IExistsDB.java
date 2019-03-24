package main.idao;

import java.util.ArrayList;

import main.model.Cards;

public interface IExistsDB {

	public void connect();
	public ArrayList<Cards> getCards();
	
}
