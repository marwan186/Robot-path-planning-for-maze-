package project; 



    import java.util.Scanner;

    import java.util.Random;

public class Hangman {

	

Random r = new Random();

    String [] words = {"program", "java","csc111"};

   char [] hiddenWord;

   char [] guessedWord;

   int bound;

String name = pickWord();
Scanner n = new Scanner(System.in);

Hangman(){

    hiddenWord = new char [name.length()];

    guessedWord = new char[name.length()];    

    

}

char q;





private int indexOf(char c){

    for (int i = 0; i < hiddenWord.length; i++)

    if( hiddenWord[i]==c )

        return i;

    return-1;

}

  

 private void setCharAt(int i, char c,char[]arr){

     

    

     

     arr[i] = c;

     

 }
 

    private String pickWord( ){

   

    return words[r.nextInt(words.length)];

          

        }

private  char[]  copyStringToArray(String S){

    

       return S.toCharArray();

  }

private void  printWord(){

    

	

    for (int i =0; i<guessedWord.length; i++)

   {

     System.out.print(guessedWord[i]);

    }

    

 }

 
       private boolean isComplete(){

    

    

  for (int i =0; i<guessedWord.length; i++)

    {

        

        if (guessedWord[i]=='*'){

            

            return false;

        }    

    }

    return true;

}


private void playOneRound(){



    int f  = 0;

    name = words[r.nextInt(words.length)];



         hiddenWord =  copyStringToArray(name);

         guessedWord =  copyStringToArray(name);


    for (int i=0; i<guessedWord.length; i++){

        

        guessedWord[i]= '*';

    }

    char q;

    do{

    	int o=0;

    	System.out.print("(Guess) Enter a letter in word ");

    	printWord();

    	System.out.print(" > ");

     q=n.next().charAt(0);

     for (int i=0; i<hiddenWord.length; i++){

     if(guessedWord[i] == q ){

    	 o++;

     System.out.println("	"+q+ " Is alrady is the word");

     

     break;

     }

     }

     if (indexOf(q) != -1 || o == 1){

    	 

     for (int h = 0; h < guessedWord.length;h++){

     if(indexOf(q)!=-1){

     setCharAt(indexOf(q) , q , guessedWord);

     setCharAt(indexOf(q) , '$' , hiddenWord);

     

     }

     }

     }

     

     else if(indexOf(q) == -1){

    	 System.out.println("	"+q+" is not in the word");

     f++;

     

     }

     if (isComplete()==true){

    System.out.print("The word is "); printWord(); System.out.print(".");

     System.out.println(" You missed "+f+" time");

     

     }

     

    }

    while(isComplete()==false);

   
    }

        

    public void play(){

        

        System.out.println("Welcome to Hangman game. Are you ready? OK, let us start:");

        playOneRound();

        

        while (true){

        System.out.print("Do you want to guess another word? Enter y or n> ");

        char u=n.next().charAt(0);

        

        if (u == 'y'){

            playOneRound();

        }

        else if (u == 'n'){

            System.out.print("Goodbye!");

            System.exit(0);

        }

        	}

        

    }

   
    public String[] getWords()

    {

    	return words;

    }



    public char[] getHiddenWord()

    {

    	return hiddenWord;

    }



    }

    

