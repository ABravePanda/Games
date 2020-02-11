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
		System.out.print("[INFO] Here are your cards: ");
		for(Card card : player1Cards)
		{
			System.out.print("["  + card + "] ");
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
	
	private void pickupCard()
	{
		if(turn == 1)
		{
			System.out.println("[INFO] Picking up a card from the pile...");
			Card card = deck.drawnCard();
			System.out.println("[INFO] You have picked up a [" + card + "]");
			player1Cards.add(card);
			checkMatches();
			turn++;
			move();
		}
		else if(turn == 2)
		{
			System.out.println("[INFO] Player 2 is picking up a card from the pile...");
			Card card = deck.drawnCard();
			player2Cards.add(card);
			checkMatches();
			turn++;
			move();
		}
		else if(turn == 3)
		{
			System.out.println("[INFO] Player 3 is picking up a card from the pile...");
			Card card = deck.drawnCard();
			player3Cards.add(card);
			checkMatches();
			turn=1;
			move();
		}
	}
	
	private void move()
	{
		//player1 turn
		if(turn == 1)
		{
			System.out.println("[INFO] It's your turn!");
			System.out.println("");
			showCards();
			System.out.println("");
			checkMatches();
			System.out.println("");
			int player = playerSelect();
			int card = chooseCard();
			askCard(player, card);
		}
		
		//player2 turn
		else if(turn == 2)
		{
			System.out.println("[INFO] It's player 2's turn!");
			System.out.println("");
			int player = playerSelect();
			int card = chooseCard();
			askCard(player, card);
		}
		
		//player3 turn
		else if(turn == 3)
		{
			System.out.println("[INFO] It's player 3's turn!");
			System.out.println("");
			int player = playerSelect();
			int card = chooseCard();
			askCard(player, card);
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
		boolean match = false;
		if(turn == 1)
		{
			ArrayList<Card> cardList = player1Cards;
			for(int i = 0; i < player1Cards.size(); i++)
			{
				for(int j = 0; j < cardList.size(); j++)
				{
					if(player1Cards.get(i).getValue() == cardList.get(j).getValue() && player1Cards.get(i) != cardList.get(j))
					{
						match = true;
						System.out.println("[MATCH] Found a match! - " + "[" + player1Cards.get(i) + "] & [" + cardList.get(j) + "]");
						cardList.remove(j);
						cardList.remove(i);
						continue;
					}
				}
			}
		}
		if(turn == 2)
		{
			ArrayList<Card> cardList = player2Cards;
			for(int i = 0; i < player2Cards.size(); i++)
			{
				for(int j = 0; j < cardList.size(); j++)
				{
					if(player2Cards.get(i).getValue() == cardList.get(j).getValue() && player2Cards.get(i) != cardList.get(j))
					{
						match = true;
						cardList.remove(j);
						cardList.remove(i);
						continue;
					}
				}
			}
		}
		if(turn == 3)
		{
			ArrayList<Card> cardList = player3Cards;
			for(int i = 0; i < player3Cards.size(); i++)
			{
				for(int j = 0; j < cardList.size(); j++)
				{
					if(player3Cards.get(i).getValue() == cardList.get(j).getValue() && player3Cards.get(i) != cardList.get(j))
					{
						match = true;
						cardList.remove(j);
						cardList.remove(i);
						continue;
					}
				}
			}
		}
		if(match) showCards();
		
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
				System.out.println("[YOU] Asking for a 'King'.");
				return 13;
			}
			else if(choice.equalsIgnoreCase("Queen")) 
			{
				System.out.println("[YOU] Asking for a 'Queen'.");
				return 12;
			}
			else if(choice.equalsIgnoreCase("Jack"))  
			{
				System.out.println("[YOU] Asking for a 'Jack'.");
				return 11;
			}
			else if(choice.equalsIgnoreCase("Ace")) 
			{
				System.out.println("[YOU] Asking for an 'Ace'.");
				return 1;
			}
			else
			{
				if(Integer.valueOf(choice) != null && (Integer.valueOf(choice) > 10 && Integer.valueOf(choice) < 2))
				{
					System.out.println("[ERROR] There was an error trying to find that.");
					chooseCard();
				}
				else
				{
					System.out.println("[YOU] Asking for a '" + choice + "'.");
					return Integer.valueOf(choice);
				}
			}
		}
		else if(turn == 2)
		{
			Random r = new Random();
			int random = r.nextInt(13);
			random+=1;
			
			switch(random)
			{
			case 1: System.out.println("[Player 2] Asking for an 'Ace'.");
			return 1;
			case 11: System.out.println("[Player 2] Asking for a 'Jack'.");
			return 11;
			case 12: System.out.println("[Player 2] Asking for a 'Queen'.");
			return 12;
			case 13: System.out.println("[Player 2] Asking for a 'King'.");
			return 13;
			default: System.out.println("[Player 2] Asking for a '" + random + "'.");
			return random;
			}
		}
		else if(turn == 3)
		{
			Random r = new Random();
			int random = r.nextInt(13);
			random+=1;
			
			switch(random)
			{
			case 1: System.out.println("[Player 3] Asking for an 'Ace'.");
			return 1;
			case 11: System.out.println("[Player 3] Asking for a 'Jack'.");
			return 11;
			case 12: System.out.println("[Player 3] Asking for a 'Queen'.");
			return 12;
			case 13: System.out.println("[Player 3] Asking for a 'King'.");
			return 13;
			default: System.out.println("[Player 3] Asking for a '" + random + "'.");
			return random;
			}
		}
		return 0;
	}

	private void askCard(int player, int card)
	{
		if(turn == 1)
		{
			int count = 0;
			if(player == 2)
			{
				ArrayList<Card> cards = player2Cards;
				for(Card p2Card : cards)
				{
					if(p2Card.getValue() == card)
					{
						player2Cards.remove(p2Card);
						player1Cards.add(p2Card);
						count++;
					}
				}
				if(count == 0) 
				{
					System.out.println("[Player 2] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[Player 2] I have " + count + " of that card!");
					move();
				}
			}
			else if(player == 3)
			{
				ArrayList<Card> cards = player3Cards;
				for(Card p3Card : cards)
				{
					if(p3Card.getValue() == card)
					{
						player3Cards.remove(p3Card);
						player1Cards.add(p3Card);
						count++;
					}
				}
				if(count == 0)				
				{
					System.out.println("[Player 3] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[Player 3] I have " + count + " of that card!");
					move();
				}
			}
		}
		else if(turn == 2)
		{
			int count = 0;
			if(player == 1)
			{
				ArrayList<Card> cards = player1Cards;
				for(Card p1Card : cards)
				{
					if(p1Card.getValue() == card)
					{
						player1Cards.remove(p1Card);
						player2Cards.add(p1Card);
						count++;
					}
				}
				if(count == 0) 
				{
					System.out.println("[YOU] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[YOU] I have " + count + " of that card!");
					move();
				}
			}
			if(player == 3)
			{
				ArrayList<Card> cards = player3Cards;
				for(Card p1Card : cards)
				{
					if(p1Card.getValue() == card)
					{
						player3Cards.remove(p1Card);
						player2Cards.add(p1Card);
						count++;
					}
				}
				if(count == 0) 
				{
					System.out.println("[PLAYER 3] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[PLAYER 3] I have " + count + " of that card!");
					move();
				}
			}
		}
		else if(turn == 3)
		{
			int count = 0;
			if(player == 1)
			{
				ArrayList<Card> cards = player1Cards;
				for(Card p1Card : cards)
				{
					if(p1Card.getValue() == card)
					{
						player1Cards.remove(p1Card);
						player3Cards.add(p1Card);
						count++;
					}
				}
				if(count == 0) 
				{
					System.out.println("[YOU] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[YOU] I have " + count + " of that card!");
					move();
				}
			}
			if(player == 2)
			{
				ArrayList<Card> cards = player2Cards;
				for(Card p1Card : cards)
				{
					if(p1Card.getValue() == card)
					{
						player2Cards.remove(p1Card);
						player3Cards.add(p1Card);
						count++;
					}
				}
				if(count == 0) 
				{
					System.out.println("[PLAYER 2] Go Fish!");
					pickupCard();
				}
				else 
				{
					System.out.println("[PLAYER 2] I have " + count + " of that card!");
					move();
				}
			}
		}
	}
}
