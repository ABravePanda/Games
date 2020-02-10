package com.tompkinsdevelopment.games.objects;

import com.tompkinsdevelopment.games.objects.enums.Suit;

public class Card 
{
	private int value;
	private Suit suit;

	
	public Card(int value, Suit suit)
	{
		this.setValue(value);
		this.setSuit(suit);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public String getFace()
	{
		switch(getValue())
		{
		case 1: return "Ace";
		case 11: return "Jack";
		case 12: return "Queen";
		case 13: return "King";
		default: return getValue() + "";
		}
	}
	
	public String toString()
	{
		return getFace() + " of " + getSuit();
	}
}
