import java.util.*;
import java.lang.*;
import java.io.*;

public class KMSScrabble{
   public static char[][] weights =  {{'a','1'},
                                      {'e','1'},
                                      {'i','1'},
                                      {'o','1'},
                                      {'u','1'},
                                      {'l','1'},
                                      {'n','1'},
                                      {'s','1'},
                                      {'t','1'},
                                      {'r','1'},
                                      {'d','2'},
                                      {'g','2'},
                                      {'b','3'},
                                      {'c','3'},
                                      {'m','3'},
                                      {'p','3'},
                                      {'f','4'},
                                      {'h','4'},
                                      {'v','4'},
                                      {'w','4'},
                                      {'y','4'},
                                      {'k','5'},
                                      {'j','8'},
                                      {'x','8'},
                                      {'q',' '},
                                      {'z',' '},
                                      {' ','2'}};
                                      
   public static int maxValue = 0; public static String bestWord = ""; public static String[] dictionary; public static int totalValue;

   public static void main(String args[]) throws IOException{
      loadWords("words.txt");
      System.out.println("Enter the letters given, with spaces representing blanks: ");
      char[] input = readLetters();
      findBestWord(input, hasBlanks(input));
   }
   public static char[] readLetters() throws IOException{ 
      Scanner input = new Scanner(System.in);
      String input2 = input.nextLine();
      char[] result = input2.toCharArray();
      Arrays.sort(result);
      int blanks = hasBlanks(result);
      if(blanks > 0){
         for(int i = blanks; i < result.length; i++){
            result[i-blanks] = result[i];
            result[i] = ' ';
         }
      }
      for (int j = 0; j < result.length; j++) {
         for (int y = 0; y < weights.length; y++){
            if(weights[y][0] == result[j]){
               if(weights[y][1] == ' '){
                  totalValue = totalValue + 10;
               }else{
                  totalValue = totalValue + Character.getNumericValue(weights[y][1]);
               }
            break;
            }
         if(y == weights.length-1)
            System.err.println("---Entered an invalid piece :( \n---Results may be incorrect");
         }
      }
      return result;
   }
   
   public static void findBestWord(char inputs[], int blanks){ //
      char[] word; int value;
      for(int i = 0; i < dictionary.length; i++){
         word = dictionary[i].toCharArray();
         value = promising(inputs,word,blanks);
         if(value > maxValue){
            maxValue = value;
            bestWord = dictionary[i];
               if(value == totalValue)
                  break;
         }
      }
      System.out.println("The best word available is: " + bestWord);
      System.out.println("with a value of: " + maxValue);
   }
   
   public static void loadWords(String file){ 
      int rows = 0;
      try{
         Scanner input = new Scanner(new FileReader(file));
         while(input.hasNextLine())
         {
            ++rows;
            input.nextLine();
         }
         dictionary = new String[rows];
         input.close();
         input = new Scanner(new FileReader(file));
         for(int i = 0; i < rows; i++)
            dictionary[i] = input.nextLine();
      }catch(FileNotFoundException e){
         System.out.println("Error: File Not Found");
      }
   }
   
   public static int hasBlanks(char[] input){
      int result = 0;
      for(int i = 0; i < input.length; i++){
         if(input[i]== ' ')
            result++;
      }
      return result;
   }
   
   public static int promising(char[] input, char[] word, int blank){  //
      int result = 0; int j = 0;
      Arrays.sort(word);
      for(int i = 0; i < word.length; i++){
         if(word[i] != input[j]){
            if(blank == 0){
               result = 0;
               break;
            }
            blank--;
            result = result + 2;
         }else{
            for (int y = 0; y < weights.length; y++){
               if(weights[y][0] == input[j]){
                  if(weights[y][1] == ' '){
                     result = result + 10;
                  }else{
                     result = result + Character.getNumericValue(weights[y][1]);
                  }
               break;
               }
            } 
            j++;
         }
      }
      return result;
   }
}