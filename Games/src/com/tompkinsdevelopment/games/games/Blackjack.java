package com.tompkinsdevelopment.games.games;

import java.util.ArrayList;
import java.util.Scanner;

import actions.Base;
import objects.Card;
import objects.Deck;
import objects.Player;

public class Blackjack 
{
	static Deck deck = Base.buildDeck();
	static ArrayList<Card> playerCards = new ArrayList<Card>();
	static ArrayList<Card> dealerCards = new ArrayList<Card>();
	static ArrayList<Card> playerAces = new ArrayList<Card>();
	
	public static void play()
	{
		deck.shuffle();
		dealCards();
				
		move();
	}
	
	
	private static void dealCards()
	{
		//add the players and dealers card
		
		Card player1 = deck.drawnCard();
		System.out.println("[YOU] Card 1: " + player1);
		if(player1.getValue() == 1)
		{
			System.out.println("[INFO] Ace detected. Would you like your ace as a 'HIGH' (11 points) or a 'LOW' (1 point).");
			player1 = aceCounter(player1);
		}
		playerCards.add(player1);
		
		Card dealer1 = deck.drawnCard();
		System.out.println("[DEALER] Card 1: ?");
		dealerCards.add(dealer1);
		
		Card player2 = deck.drawnCard();
		System.out.println("[YOU] Card 2: " + player2);
		if(player2.getValue() == 1)
		{
			System.out.println("[INFO] Ace detected. Would you like your ace as a 'HIGH' (11 points) or a 'LOW' (1 point).");
			player2 = aceCounter(player2);
		}
		playerCards.add(player2);
		
		Card dealer2 = deck.drawnCard();
		System.out.println("[DEALER] Card 2: " + dealer2);
		if(dealer2.getValue() == 1)
		{
			if(calcPoints(dealerCards) + 11 > 21)
			{
				System.out.println("[INFO] Ace detected. Dealer has selected low (1 point).");
				dealer2.setValue(-1);
			}
			else
			{
				System.out.println("[INFO] Ace detected. Dealer has selected high (11 points).");
				dealer2.setValue(11);
			}
		}
		dealerCards.add(dealer2);
	}
	
	public static Card aceCounter(Card card)
	{
		Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if(s.equalsIgnoreCase("high") || s.equalsIgnoreCase("h"))
        {
        	System.out.println("[INFO] The ace has been selected as a high card for 11 points.");
        	card.setValue(15);
        	return card;
        }
        else if(s.equalsIgnoreCase("low") || s.equalsIgnoreCase("l"))
        {
        	System.out.println("[INFO] The ace has been selected as a low card for 1 point.");
        	card.setValue(-1);
        	return card;
        }
        else
        {
        	System.out.println("[INFO] " + s + " not detected. Would you like your ace as a 'HIGH' (11 points) or a 'LOW' (1 point).");
        	aceCounter(card);
        }
        return card;
	}
	

	
	private static void move()
	{
		//ask if they want to hit or stay
		System.out.println("Do you want to 'HIT' or 'STAY'? (Total Points: " + calcPoints(playerCards) + ").");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        
        if(s.equalsIgnoreCase("hit") || s.equalsIgnoreCase("h"))
        {
        	//if they hit, draw a card and display it
        	Card card = deck.drawnCard();
        	System.out.println("[YOU] Card: " + card);
    		if(card.getValue() == 1)
    		{
    			System.out.println("[INFO] Ace detected. Would you like your ace as a 'HIGH' (11 points) or a 'LOW' (1 point).");
    			card = aceCounter(card);
    		}
        	playerCards.add(card);
        	//if that drawn card totals all points above 21, its a bust
        	if(calcPoints(playerCards) > 21) 
        	{
        		System.out.println(calcPoints(playerCards) + ". BUST! Dealer wins!");
        		return;
        	}
        	//if player has 21, give em the win
        	if(calcPoints(playerCards) == 21) 
        	{
        		getWinner();
        		return;
        	}
        	//ask them to move again
        	move();
        }
        else if(s.equalsIgnoreCase("stay") || s.equalsIgnoreCase("s"))
        {
        	//if they stay, reveal dealer card
        	System.out.println("[DEALER] Hidden card was: " + dealerCards.get(0));
        	if(dealerCards.get(0).getValue() == 1)
    		{
        		Card card = dealerCards.get(0);
    			System.out.println("[INFO] Ace detected. Would you like your ace as a 'HIGH' (11 points) or a 'LOW' (1 point).");
				if(calcPoints(dealerCards) + 11 > 21)
				{
					System.out.println("[INFO] Ace detected. Dealer has selected low (1 point).");
					card.setValue(-1);
				}
				else
				{
					System.out.println("[INFO] Ace detected. Dealer has selected high (11 points).");
					card.setValue(11);
				}
				dealerCards.set(0, card);
    		}
        	//have dealer make moves
        	dealerMove();
        	//display each players cards
        	System.out.println("[YOU] Your points: " + calculatePlayerPoints());
        	System.out.println("[DEALER] Dealer points: " + calcPoints(dealerCards));
        	//display winner
        	getWinner();
        }
        else
        {
        	System.out.println(s + " not recognized. Do you want to 'HIT' or 'STAY'? (Total Points: " + calcPoints(playerCards) + ").");
        	move();
        }
	}
	
	private static void dealerMove()
	{
		//if dealer has more than 21 points, bust
		if(calcPoints(dealerCards) > 21)
		{
			System.out.println("[DEALER] BUSTED! ");
			return;
		}
		//if the card is less than or equal to 16, deal another card
		if(calcPoints(dealerCards) <= 16)
		{
			Card card = deck.drawnCard();
			System.out.println("[DEALER] Card: " + card);
			if(card.getValue() == 1)
			{
				if(calcPoints(dealerCards) + 11 > 21)
				{
					System.out.println("[INFO] Ace detected. Dealer has selected low (1 point).");
					card.setValue(-1);
				}
				else
				{
					System.out.println("[INFO] Ace detected. Dealer has selected high (11 points).");
					card.setValue(11);
				}
			}
			
			dealerCards.add(card);
		}
		//if its above 17, stay
		else
		{
			return;
		}
		//restart a dealer move
		dealerMove();
	}
	
	private static String calculatePlayerPoints()
	{
		int points = calcPoints(playerCards);
		if(points >= 21) return points + ". BUST. Dealer wins!";
		else return points + "";
	}
	
	private static int calcPoints(ArrayList<Card> cards)
	{
		int points = 0;
		for(Card card : cards)
		{
			if(card.getValue() == 15) points+=11;
			else if(card.getValue() == -1) points+=1;
			else if(card.getValue() > 10 && card.getValue() < 14) points+=10;
			else points+=card.getValue();
		}
		return points;
	}
	
	private static void getWinner()
	{
		if(calcPoints(dealerCards) > calcPoints(playerCards))
		{
			if(calcPoints(dealerCards) <= 21)
			{
				System.out.println("Dealer wins with " + calcPoints(dealerCards) + " points!");
			}
			else
			{
				System.out.println("Player wins with " + calcPoints(playerCards) + " points!");
			}
		}
		
		else if(calcPoints(dealerCards) < calcPoints(playerCards))
		{
			if(calcPoints(playerCards) <= 21)
			{
				System.out.println("Player wins with " + calcPoints(playerCards) + " points!");
			}
			else
			{
				System.out.println("Dealer wins with " + calcPoints(dealerCards) + " points!");
			}
		}
		else
		{
			System.out.println("Push! Dealer wins!");
		}
	}
}
