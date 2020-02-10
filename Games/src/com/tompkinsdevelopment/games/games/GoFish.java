package com.tompkinsdevelopment.games.games;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.tompkinsdevelopment.games.actions.Base;
import com.tompkinsdevelopment.games.objects.Card;
import com.tompkinsdevelopment.games.objects.Deck;


public class GoFish
{
	
	private Deck deck = Base.buildDeck();
	private ArrayList<Card> player1Cards = new ArrayList<Card>();
	private ArrayList<Card> player2Cards = new ArrayList<Card>();
	private ArrayList<Card> player3Cards = new ArrayList<Card>();
	private int turn = 1;
	
	public void play()
	{
		for(int i = 0; i < 3; i++) deck.shuffle();
		deal();
		move();
	}
	
	private void showCards()
	{
		System.out.print("[INFO] Here are your cards");
		for(Card card : player1Cards)
		{
			System.out.print(", ["  + card + "]");
		}
	}
	
	private void deal()
	{
		System.out.println("[INFO] The dealer is now dealing cards.");
		for(int i = 0; i < 7; i++)
		{
			Card card1 = deck.drawnCard();
			player1Cards.add(card1);
			
			Card card2 = deck.drawnCard();
			player2Cards.add(card2);
			
			Card card3 = deck.drawnCard();
			player3Cards.add(card3);
		}
		
		System.out.println("[INFO] The dealer has dealt the cards.");
	}

	private int cardCheck(ArrayList<Card> cards)
	{
		int count = 0;
		for(int i = cards.size(); i < 7; i++)
		{
			Card card = deck.drawnCard();
			cards.add(card);
			count++;
		}
		return count;
	}
	
	private void move()
	{
		//player1 turn
		if(turn == 1)
		{
			System.out.println("[INFO] It's your turn!");
			if(player1Cards.size() != 7)
			{
				System.out.println("You have picked up " + cardCheck(player1Cards) + " cards.");
			}
			showCards();
			checkMatches();
			showCards();
			int player = playerSelect();
			int card = chooseCard();
		}
		
		//player2 turn
		else if(turn == 2)
		{
			System.out.println("[INFO] It's player 2's turn!");
		}
		
		//player3 turn
		else if(turn == 3)
		{
			System.out.println("[INFO] It's player 3's turn!");
		}
	}
	
	private int playerSelect()
	{
		if(turn == 1)
		{
			System.out.println("");
			System.out.println("[QUESTION] Who you would you like to ask? Player 2 ('2') or Player 3 ('3')");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
	        
	        if(choice == 2) 
	        {	
	        	System.out.println("You have chosen Player 2!");
	        	return 2;
	        }
	        if(choice == 3) 
	        {	
	        	System.out.println("You have chosen Player 3!");
	        	return 3;
	        }
		}
		if(turn == 2)
		{
			Random r = new Random();
			int random = r.nextInt(1);
			if(random == 0)
			{
				System.out.println("Player 2 has chosen you!");
	        	return 1;
			}
			if(random == 1)
			{
				System.out.println("Player 2 has chosen Player 3!");
	        	return 3;
			}
		}
		
		if(turn == 3)
		{
			Random r = new Random();
			int random = r.nextInt(1);
			if(random == 0)
			{
				System.out.println("Player 3 has chosen you!");
	        	return 1;
			}
			if(random == 1)
			{
				System.out.println("Player 3 has chosen Player 2!");
	        	return 2;
			}
		}
		return 0;
	}
	
	private void checkMatches()
	{
		if(turn == 1)
		{
			ArrayList<Card> playerCards = new ArrayList<Card>();
			for(Card card : player1Cards)
			{
				for(Card card2 : player1Cards)
				{
					if(card != card2)
					{
						if(card.getValue() == card2.getValue()) 
						{
							System.out.println("Match found! " + card + " - " + card2);
							continue;
						}
						else
						{
							playerCards.add(card);
						}
					}
					else playerCards.add(card);
				}
			}
			player1Cards = playerCards;
		}
	}

	private int chooseCard()
	{
		if(turn == 1)
		{
			System.out.println("");
			System.out.println("[QUESTION] What card would you like to ask for? Options: 'King', 'Queen', 'Jack', '10', '9', '8', '7', '6', '5', '4', '3', '2', 'Ace'.");
			Scanner in = new Scanner(System.in);
			String choice = in.nextLine();
			if(choice.equalsIgnoreCase("King")) 
			{
				System.out.println("Asking for a 'King'");
				return 13;
			}
			else if(choice.equalsIgnoreCase("Queen")) 
			{
				System.out.println("Asking for a 'Queen'");
				return 12;
			}
			else if(choice.equalsIgnoreCase("Jack"))  
			{
				System.out.println("Asking for a 'Jack'");
				return 11;
			}
			else if(choice.equalsIgnoreCase("Ace")) 
			{
				System.out.println("Asking for an 'Ace'");
				return 11;
			}
			else 
			{
				System.out.println("Asking for a '" + choice + "'.");
				return Integer.valueOf(choice);
			}
		}
		return 0;
	}

	private void askCard(int player, int card)
	{
		if(turn == 1)
		{
			if(player == 2)
			{
				int count = 0;
				for(Card p2Card : player2Cards)
				{
					//if(p2Card)
				}
			}
			else if(player == 3)
			{
				
			}
		}
	}
}
