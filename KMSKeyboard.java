public class KMSKeyboard{

   public static char[][] keys = {{'W','Z'},
                     {'w','z'},
                     {',','m'},
                     {'?','M'},
                     {';',','},
                     {'.','<'},
                     {':','.'},
                     {'/','>'},
                     {'!','/'},
                     {'§','?'},
                     {'Q','A'},
                     {'q','a'},
                     {'m',';'},
                     {'M',':'},
                     {'ù','\''},
                     {'%','"'},
                     {'*','\\'},
                     {'µ','|'},
                     {'A','Q'},
                     {'a','q'},
                     {'Z','W'},
                     {'z','w'},
                     {'^','['},
                     {'¨','{'},
                     {'$',']'},
                     {'£','}'},
                     {'²','`'},
                     {'&','1'},
                     {'é','2'},
                     {'"','3'},
                     {'\'','4'},
                     {'(','5'},
                     {'-','6'},
                     {'è','7'},
                     {'_','8'},
                     {'ç','9'},
                     {'à','0'},
                     {')','-'},
                     {'1','!'},
                     {'2','@'},
                     {'3','#'},
                     {'4','$'},
                     {'5','%'},
                     {'6','^'},
                     {'7','&'},
                     {'8','*'},
                     {'9','('},
                     {'0',')'},
                     {'º','_'}
                    };
   public static void main(String args[]){
      System.out.println("Enter + to end instance :)");
      read();
   }
   
   public static void read(){
      boolean check = false; int input = ' ';
      while(true){
         try{
            input = System.in.read();
            if((char)input == '+')
               System.out.println("--Ending Instance--");
               break;
         }catch(Exception e){}
         System.out.print(swap((char)input));
      }         
   }
                     
   public static char swap(char n){
      char QWERTY = n;
      for(int i = 0; i < keys.length; i++){
         if(keys[i][0] == n)
            QWERTY = keys[i][1];
      }
      return QWERTY;
   }   
}