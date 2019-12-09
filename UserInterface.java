import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Write a description of class UserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserInterface
{

    public void main() throws FileNotFoundException
    {
        // put your code here
        System.out.println("Welcome to Evil Hangman!\n");

        boolean validWordLength = false;
        int gameWordLength = -1;

        Scanner scan = new Scanner(System.in);
        while (!validWordLength){
            System.out.println("How long is the word?");
            try{
                gameWordLength = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                System.out.println("Invalid word length.");
            }

            if(gameWordLength > 0){
                validWordLength = true;
            }

        }

        boolean validNumGuesses = false;
        int numGuesses = -1;

        while (!validNumGuesses){
            System.out.println("How many guesses do you want?");
            try{
                numGuesses = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                System.out.println("Invalid number of guesses");
            }

            if(numGuesses > 0){
                validNumGuesses = !validNumGuesses;
            }
        }

        boolean validWordsRemaining = false;
        boolean showWordsRemainingBoolean = false;
        String showWordsRemaining = "";

        while (!validWordsRemaining){
            System.out.println("Do you want the game to show how many words are remaining? (y/n)");
            showWordsRemaining = scan.nextLine();

            if(showWordsRemaining.equals("y")){
                showWordsRemainingBoolean = true;
                validWordsRemaining = true;
            }else if(showWordsRemaining.equals("n")){
                showWordsRemainingBoolean = false;
                validWordsRemaining = true;
            } else{
                System.out.println("Invalid Answer.");
            }

        }

        boolean playing = true;
        int remainingGuesses = numGuesses;
        ArrayList<String> usedLetters = new ArrayList();
        String guessedWord = "";

        Dictionary remainingWords = new Dictionary(gameWordLength);

        for(int i = 0; i < gameWordLength; i++){
            guessedWord = guessedWord + "_";
        }

        while(playing){

            System.out.println(" ");

            System.out.println("Currently: "+ guessedWord);

            String printUsedLetters = "Used Letters: [";
            for (int i = 0; i < usedLetters.size(); i++){
                printUsedLetters = printUsedLetters + usedLetters.get(i) + ", ";
            }
            //printUsedLetters = printUsedLetters.substring(0, printUsedLetters.length() - 2);
            printUsedLetters = printUsedLetters + "]";
            System.out.println(printUsedLetters);

            if(remainingGuesses == 1){
                System.out.println("You have 1 guess remaining.");
            } else {
                System.out.println("You have "+ remainingGuesses + " guesses remaining.");
            }

            if(showWordsRemainingBoolean){
                System.out.println("There are " + remainingWords.getSize() + " words remaining.");
            }

            boolean validGuess = false;
            String guess = "";

            while(!validGuess){
                System.out.println("Input your guess: ");
                guess = scan.nextLine().toLowerCase();

                if(checkForValidGuess(guess, usedLetters)){
                    validGuess = true;
                }else{
                    System.out.println("Invalid guess.\n");
                }
            }
            remainingGuesses--;
            usedLetters.add(guess);

            int previousSize = remainingWords.getSize();
            remainingWords.removeWordsWithLetter(guess);

            String theWord = remainingWords.getRandomWord().getWord();
            
            if(previousSize == remainingWords.getSize()){
                System.out.println("!!!!");
                ArrayList<Integer> locations = new ArrayList<>();
                guessedWord = "";
                for(int i = 0; i < gameWordLength; i++){
                    System.out.println("For Loop Ran");
                    System.out.println("The word is "+ theWord);
                    if(theWord.charAt(i) == guess.charAt(0)){
                        System.out.println("If Statement Ran");
                        guessedWord = guessedWord + guess;
                        locations.add(i);
                    }else{
                        guessedWord = guessedWord + "_";
                    }
                }
                remainingWords.removeWordsExcept(guess, locations);
            }

            if((remainingWords.getSize() == 1)&&(guessedWord.equals(remainingWords.getRandomWord()))){
                playing = true;
                System.out.println("\nYou Win.");
                System.out.println("The word was '"+ theWord +"'");
            }
            else if(remainingGuesses == 0){
                playing = false;
                System.out.println("\nGame Over.");
                System.out.println("The word was '"+ theWord +"'");
            }
        }
    }

    private boolean checkForValidGuess(String guess, ArrayList list){
        boolean validGuess = true;
        int character = -1;
        if(guess.length() != 1){
            validGuess = false;
        }else{
            character = guess.charAt(0);
        }

        if(!(97 <= character && character <= 122)){
            validGuess = false;
        }

        boolean alreadyGuessed = false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(guess)){
                alreadyGuessed = true;
            }
        }

        if(alreadyGuessed == true){
            validGuess = false;
        }

        return validGuess;
    }
}
