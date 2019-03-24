package main.model;

import java.util.ArrayList;

public class Baraja {

	private String DeckName;
	private int DeckValue;
	private ArrayList<Cards> deck;
	
	public Baraja(String deckName, int deckValue, ArrayList<Cards> deck) {
		super();
		DeckName = deckName;
		DeckValue = deckValue;
		this.deck = deck;
	}

	public String getDeckName() {
		return DeckName;
	}

	public void setDeckName(String deckName) {
		DeckName = deckName;
	}

	public int getDeckValue() {
		return DeckValue;
	}

	public void setDeckValue(int deckValue) {
		DeckValue = deckValue;
	}

	public ArrayList<Cards> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Cards> deck) {
		this.deck = deck;
	}
	
	
	
}
