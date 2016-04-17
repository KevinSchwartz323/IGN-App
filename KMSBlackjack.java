import java.util.*;

public class KMSBlackjack{

   public static char[] deck; public static int decks; public static int count = 0; public static char[][] hands; public static int AI;
   
   public static void main(String args[]){
      Scanner input = new Scanner(System.in);
      System.out.println("How many AI players, excluding the dealer do you want to join you?: ");
      AI = input.nextInt();
      System.out.println("How many decks do you want to play with? (Standard 1-8): ");
      decks = input.nextInt();
      play();
   }
   public static void play(){
      int i = 1;
      createHands();
      createDeck(1);
      while(round(i)){
         i++;
      }
      System.out.println("Ending Game");
   }
   public static boolean round(int i){
      System.out.println("Starting Round: " + i);
      dealOut();
      boolean player, ai, dealer;
      player = ai = dealer = false;
      if(AI == 0)
         ai = true;
      while(!player || !ai || !dealer){
         if(!player)
            player = PlayerTurn();
         if(!ai)
            ai = AIturn();
         if(!dealer)
            dealer = DealerTurn();
      }
      whoWins();
      Scanner input = new Scanner(System.in);
      while(true){
         System.out.println("\nPlay again?(yes/no): ");
         String answer = input.nextLine();
         if(answer.equals("yes"))
            return true;
         else if(answer.equals("no"))
            return false;
         else
            System.out.println("Not a Valid input");
      }
   }
   public static void createHands(){
      hands = new char[2+AI][11];
   }
   public static void dealOut(){
      for(int i = 0; i < 11; i++){
         for(int j = 0; j < 2+AI; j++){
            hands[j][i] = '\u0000';
         }
      }
      for(int i = 0; i < 2; i++){
         for(int j = 0; j < 2+AI; j++){
            addCard(hit(),j);
         }
      }
      System.out.print("The dealer has " + value(hands[1]) + " with ");
      showHand(hands[1]);
      for(int i = 0; i < AI; i++){
         System.out.print("\nAI player " + (i+1) + " has " + value(hands[i+2]) + " with ");
         showHand(hands[i+2]);
      }
   }
   public static boolean PlayerTurn(){
      char temp;
      Scanner input = new Scanner(System.in);
      while(true){
         System.out.print("\nDo you want to hit or stand? You have " + value(hands[0]) + " and your cards are : ");
         showHand(hands[0]);
         String answer = input.nextLine();
         if(answer.equals("hit")){
            temp = hit();
            addCard(temp, 0);
            if(value(hands[0]) < 22){
               System.out.print("--You drew a " + value(temp) + " and now have " + value(hands[0]) + " your hand is now ");
               showHand(hands[0]);
               return false;
            }else{
               System.out.print("--You drew a " + value(temp) + " and bust at " + value(hands[0]));
               return true;
            }
         }else if(answer.equals("stand")){
            System.out.print("--You stand with a value of: " + value(hands[0]));
            return true;
         }else
            System.out.print("Not a valid command");
      }        
   }
   public static boolean AIturn(){
      char temp = '0'; boolean done = true; int hand;
      for(int i = 2; i < AI+2; i++){
         hand = value(hands[i]);
         if((hand - trueCount()) < 13 || (hand < value(hands[1]) && value(hands[1]) > 17 && value(hands[1]) < 22)){
            temp = hit();
            addCard(temp, i);
            hand = value(hands[i]);
            if(value(hands[i]) < 22){
               System.out.print("\nAI player "  + (i-1) + " draws " + value(temp) + "and now has " + hand + " with ");
               showHand(hands[i]);
               done = false;
            }else{
               System.out.print("\nAI player " + (i-1) + " drew a " + value(temp) + "and bust at " + value(hands[i]));            }
         }else{
            System.out.print("\nAI player " + (i-1) + " stands on " + hand + " with ");
            showHand(hands[i]);
         }      
      }
      return done;          
   }  
   public static boolean DealerTurn(){
      int hand = value(hands[1]); char temp;
      if(hand < 17){
         temp = hit();
         addCard(temp, 1);
         hand = value(hands[1]);
         if(value(hands[1]) < 22){
            System.out.print("\nDealer draws " + value(temp) + "and now has " + hand + " with: ");
            showHand(hands[1]);            
            return false;
         }else{
            System.out.print("\nDealer draws " + value(temp) + "and busts at " + hand + " with: ");
            showHand(hands[1]);            
            return true;
         }
      }else{
         System.out.print("\nDealer stands on " + hand + " with: ");
         showHand(hands[1]);
         return true;
      }
   }
   public static void whoWins(){
      int max = 0; int winner = 0;
      for(int i = 0; i < AI+2; i++){
         if(value(hands[i]) > max && value(hands[i]) < 22){
            winner = i;
            max = value(hands[i]);
         }
      }
      if(winner == 1)
         System.out.print("\nThe House wins on " + value(hands[1]));
      else{
         if(value(hands[0]) < 22 && value(hands[0]) > value(hands[1]))
            System.out.print("\n--You win on " + value(hands[0]));
         for(int i = 0; i < AI; i++){
            if(value(hands[i+2]) < 22 && value(hands[i+2]) > value(hands[1]))
               System.out.print("\nAI Player " + (i+1) + " wins on " + value(hands[i+2]));
         }
      }
   }
   public static void cardCount(int n){
      if(n<7)
         count++;
      else if(n<10){}
      else
         count--;
   }
   public static int trueCount(){
      int n = 0;
      for(int i = 0; i < deck.length; i++){
         if(deck[i] != '\u0000')
            n++;
      }
      return count/((n/52)+1);
   }
   public static void createDeck(int n){
      deck = new char[n * 52]; int y = 0;
      for(int i = 1; i < 10; i++){
         for(int j = 0; j < 4*n; j++){
            deck[y] = (char)i;
            y++;
         }
      }
      for(int i = 0; i < 4*n; i++){
         deck[y] = 'J';
         y++;
      }
      for(int i = 0; i < 4*n; i++){
         deck[y] = 'Q';
         y++;
      }
      for(int i = 0; i < 4*n; i++){
         deck[y] = 'K';
         y++;
      }
      for(int i = 0; i < 4*n; i++){
         deck[y] = 'A';
         y++;
      }
   }
   public static void addCard(char card, int hand){
      int i = 0;
      while(hands[hand][i] != '\u0000'){
            i++;
      }
      hands[hand][i] = card;
   }
   public static char hit(){
      char result = '\u0000'; int temp;
      while(result == '\u0000'){
         temp = (int)(Math.random()*(deck.length));
         result = deck[temp];
         deck[temp] = '\u0000';
      }
      cardCount(result);
      return result;
   }
   public static int value(char[] hand){
      int result = 0; int Ace = 0; char card = hand[0]; int i = 0;
      while(card != '\u0000'){
         if((int)card == 1 || card == 'J' || card == 'Q' || card == 'K')
            result = result + 10;
         else if(card == 'A'){
            result++;
            Ace++;
         }else{
            result = result + (int)card;
         }
         i++;
         card = hand[i];
      }
      for(int j = 0; j < Ace; j++){
         if(result < 12)
            result = result + 10;
      }
      return result;
   }
   public static String value(char card){
      if((int)card == 1)
         return (10 + " ");
      else if((int)card < 10)
         return ((int)card + " ");
      else
         return (card + " ");
   }
   public static void showHand(char[] hand){
      int i = 0;
      while(hand[i] != '\u0000'){
         System.out.print(value(hand[i]));
         i++;
      }
   }
}