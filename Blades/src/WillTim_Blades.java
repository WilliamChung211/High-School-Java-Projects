import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;

/*
 * Name: William Chung and Timothy Yang
 * This program is a card game between two opposing players who alternate turns playing.
 */
public class WillTim_Blades extends JFrame{

	private Player player;
	private Player opponent;

	private boolean playerTurn;			//true if it's the player's turn

	private BufferedImage boltImage;
	private BufferedImage mirrorImage;

	public WillTim_Blades(){

		//makes the frame
		setTitle("Blades");
		setSize(1100,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setLayout(null);

		//reads the image
		try {
			boltImage = ImageIO.read(new File("bolt.jpg"));
			mirrorImage = ImageIO.read(new File("mirror.png"));

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Could not read in the pic");
			System.exit(0);
		}	

		//sets the player and opponent objects
		player = new Player(Color.blue,"You");
		opponent = new Player(Color.red,"Opp.");

		//sets the card panels
		JPanel oppPanel = new JPanel();
		oppPanel.setLayout(new GridLayout(1,10,5,5));
		oppPanel.setBackground(Color.black);
		oppPanel.setBorder(BorderFactory.createLineBorder(Color.black,5));
		oppPanel.setBounds(75, 75, 925, 125);

		for(NumPanel next: opponent.cards){
			oppPanel.add(next);
		}

		add(oppPanel);

		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1,10,5,5));
		playerPanel.setBackground(Color.black);
		playerPanel.setBorder(BorderFactory.createLineBorder(Color.black,5));
		playerPanel.setBounds(75, 550, 925, 125);

		for(NumPanel next: player.cards){
			playerPanel.add(next);
		}

		add(playerPanel);

		//puts the active card for each
		player.activeCard = new NumPanel(Color.blue);
		player.activeCard.setBorder(BorderFactory.createLineBorder(Color.black,5));
		player.activeCard.setBounds(550,375,97,125);
		player.activeCard.removeMouseListener(player.activeCard);
		
		
		add(player.activeCard);
		
		opponent.activeCard = new NumPanel(Color.red);
		opponent.activeCard.setBorder(BorderFactory.createLineBorder(Color.black,5));
		opponent.activeCard.setBounds(425,250,97,125);
		opponent.activeCard.removeMouseListener(opponent.activeCard);
		
		
		//randomly deals different cards from 2 to 7 to each player. smaller card goes first
		redraw();
		add(opponent.activeCard);		

		//adds the labels
		player.label.setBounds(675,400,100,80);
		add(player.label);

		opponent.label.setBounds(355,270,100,80);
		add(opponent.label);

		setVisible(true);
	}

	//randomly gives the player's active card from 2 to 7 to each player. Puts turn as the smaller card
	private void redraw(){
		player.activeCard.setNumber((int)(Math.random()*6)+2);
		
		int numberToSet;
		do{
			numberToSet = ((int)(Math.random()*6)+2);
		} while(numberToSet == player.activeCard.number);
		
		opponent.activeCard.setNumber(numberToSet);
		
		playerTurn = player.activeCard.number < opponent.activeCard.number;
		
		if (playerTurn){
			setLabel(player, opponent);
		}else{
			setLabel(opponent, player);
		}
	}
	
	//Sets the label after redraw
	private void setLabel(Player turnPlayer, Player oppPlayer){
		if(turnPlayer.cardsLeft > 0){
			turnPlayer.label.setForeground(Color.green);
			oppPlayer.label.setForeground(Color.black);
		}
		else{
			oppPlayer.label.setForeground(Color.black);
			turnPlayer.label.setForeground(Color.green);
			playerTurn = !playerTurn;
		}
	}
	
	//prevents user from clicking on a panel
	//should only be called after the game is over
	private void removeListeners(){

		for(NumPanel next: player.cards){
			next.removeMouseListener(next);
		}

		for(NumPanel next: opponent.cards){
			next.removeMouseListener(next);
		}
	}

	

	//Represents a single player in the game.
	//You do not need to add anything to this class
	public class Player{

		private NumPanel[] cards;				//deck of cards
		private NumPanel activeCard;			//card in the middle
		private ArrayList<Integer> playedCards; // in the event of a Bolt
		private int cardsLeft;
		private JLabel label;
		private Color teamColor;				//color on the back red or blue

		public Player(Color theColor, String labelMessage){

			teamColor = theColor;
			label = new JLabel(labelMessage);
			label.setFont(new Font("Lucia Handwriting",Font.PLAIN,22));
			cards = new NumPanel[10];

			for(int i = 0; i < 10; i ++){
				cards[i] = new NumPanel(theColor);
				cards[i].setNumber((int)(Math.random()*8));

			}
			playedCards = new ArrayList<Integer>();
			cardsLeft = 10;
		}


	}

	//The panels for the cards
	class NumPanel extends JPanel implements MouseListener{

		private int number= -1;				//0 is for Bolt, 1 is for Mirror, -1 is empty
		private JLabel text;				//helps draw a number on the card
		private Color backOfCard;			//color when it's flipped over


		public NumPanel(Color t){

			setBackground(Color.white);
			setLayout(null);
			backOfCard = t;
			this.addMouseListener(this);
			
		}		

		//changes the panel to have the given number
		public void setNumber(int number){	
			this.number = number;

			//if it's not bolt or mirror
			if(number != 0 && number != 1){

				text = new JLabel(""+number,SwingConstants.CENTER);
				text.setFont(new Font("Calibri",Font.PLAIN,55));
				text.setBounds(0,35,70,50);
				this.add(text);
				this.setBackground(Color.white);

			}
			repaint();
		}

		//"flips" and card over
		public void removeNumber(){

			//if the card contains a number
			if(number >= 2) {
				this.remove(text);
			}
			number = -1;
			repaint();
		}

		//called by repaint
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			if(number == -1)
				setBackground(backOfCard);
			else if(number == 0){
				g.drawImage(boltImage,10,10,this);
			}
			else if(number == 1){
				g.drawImage(mirrorImage,5,10,this);
			}
		}

		//reacts to a mouse click
		//carries out one play of Blades
		public void mouseClicked(MouseEvent me) {
			
			//If it is the player's turn and the player's color, it will run
			if(playerTurn){
				if(backOfCard.equals(Color.blue)){	
					
					//runs the action of the card
					change(player,opponent);
					
					//if the cards were not redraw sit checks the loss
					if(!checkTie()){
						checkLose(player, opponent, "player", "opponent");
					}
				}
				
				
			}else{
				
				//If it is the popponent's turn and the opponent's color, it will run
				if(backOfCard.equals(Color.red)){
					
					//runs action
					change(opponent,player);
					
					//checks loss if the cards were not redrawn
					if(!checkTie()){
						checkLose(opponent, player, "opponent", "player");						
					}
				}
			}
			
			
		}
		
		//Checks if it is a tie and if it is a tie it redraws cards
		private boolean checkTie(){
			
			if(player.activeCard.number == opponent.activeCard.number){
				
				//if it is a tie, clears the played cards list and redraws new cards from 2-7. The player with lowest card gets turn
				JOptionPane.showMessageDialog(null, "Redraw");
				player.activeCard.removeNumber();
				player.playedCards = new ArrayList<Integer>();
				
				opponent.activeCard.removeNumber();
				opponent.playedCards = new ArrayList<Integer>();
				
				redraw();
				
				return true;
			}
			
			return false;
		}

		//checks if the turn player's cards are lower than the opponent player's cards and prints out end message
		private void checkLose(Player turnPlayer, Player oppPlayer, String playerName, String oppName){
			String toDisplay;
			
			if(turnPlayer.activeCard.number > oppPlayer.activeCard.number){
				if(oppPlayer.cardsLeft > 0){
					oppPlayer.label.setForeground(Color.green);
					turnPlayer.label.setForeground(Color.black);
					
					return;
				}
				
				toDisplay = "The " + oppName + " lost!";
			}

			else{
				toDisplay = "The " + playerName + " lost!";
			}
			
			//prints out the message and removes listeners if the game is over
			JOptionPane.showMessageDialog(null, toDisplay);
			removeListeners();
		}
		
		
		private void change (Player turnPlayer, Player oppPlayer){	
			
			//changes the player's card
			turnPlayer.cardsLeft--;
			
			//if it is a mirror it switches the player card's array list and active card
			if(number==1){
				
				int temp = oppPlayer.activeCard.number;
				setActiveCard(oppPlayer, turnPlayer.activeCard.number);

				ArrayList<Integer> oppCards = oppPlayer.playedCards;
				oppPlayer.playedCards = turnPlayer.playedCards;
				turnPlayer.playedCards = oppCards;
				
				setActiveCard(turnPlayer, temp);
				
				JOptionPane.showMessageDialog(null, "Mirror!");
			} 
			
			//if it is a bolt, it gets rid of the not turn player's active card
			else if(number==0){
				ArrayList<Integer> cards = oppPlayer.playedCards;
				if(cards.size() > 0){
					int numToRemove = cards.remove(cards.size() - 1);
					setActiveCard(oppPlayer, oppPlayer.activeCard.number - numToRemove);
				}
				
				JOptionPane.showMessageDialog(null, "Bolt!");
			}
		
			//if it is another card it adds that card to the active card
			else{
				turnPlayer.playedCards.add(number);
				setActiveCard(turnPlayer, turnPlayer.activeCard.number + number);
			}
			
			//switches turn and removes the played card's number
			playerTurn = !playerTurn;
			removeNumber();
		
		}
		
		//It sets the card to the number
		private void setActiveCard(Player turnPlayer, int numberToSet){
			turnPlayer.activeCard.removeNumber();
			turnPlayer.activeCard.setNumber(numberToSet);
		}
		
		public void mouseEntered(MouseEvent arg0) {
			// do not implement

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// do not implement

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// do not implement
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// do not implement

		}
	}

	//runs the game
	public static void main(String[] args){
		new WillTim_Blades();
	}
}
