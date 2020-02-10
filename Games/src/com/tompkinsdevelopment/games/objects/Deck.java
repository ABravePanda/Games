package com.tompkinsdevelopment.games.objects;

import java.util.Collections;
import java.util.Random;

public class Deck 
{
	private Card[] cards;
	private int index = 51;
	
	public Deck(Card[] cards)
	{
		this.setCards(cards);
	}

	/**
	 * @return the cards
	 */
	public Card[] getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public void shuffle()
	{
		Random rgen = new Random();  // Random number generator			
		Card[] array = getCards();
		
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    Card temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
		
		
	}
	
	public Card drawnCard()
	{
		if(index < 0) return null;
		Card[] cards = getCards();
		Card card = cards[index];
		index--;
		return card;
	}
	
	public int cardsLeft()
	{
		return 53 - (cards.length - index);
	}
	
	public String toString()
	{
		String s = "This deck has " + cardsLeft() + " cards left. The top card is " + cards[index];
		return s;
	}
}
