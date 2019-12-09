import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * Write a description of class Dictionary here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dictionary
{
    // instance variables - replace the example below with your own

    protected Map<Integer,Word> wordList; 
    protected ArrayList<Word> words;

    /**
     * Constructor for objects of class Dictionary
     */
    public Dictionary() throws FileNotFoundException
    {
        words = new ArrayList<>();
        wordList = new HashMap<Integer, Word>();
        buildDictionary();
    }

    public Dictionary(int size) throws FileNotFoundException
    {
        words = new ArrayList<>();
        wordList = new HashMap<Integer, Word>();
        buildDictionary(size);
    }

    public void buildDictionary() throws FileNotFoundException{
        Scanner file = new Scanner(new File("dictionary.txt"));
        while(file.hasNext() == true){
            String word = file.nextLine();
            words.add(new Word(word));
        }
        //printDictionary();
    }

    public void buildDictionary(int size) throws FileNotFoundException{
        Scanner file = new Scanner(new File("dictionary.txt"));
        while(file.hasNext() == true){
            String word = file.nextLine();
            if(word.length() == size){
                words.add(new Word(word));
            }
        }
    }

    public Word getRandomWord(){
        Random rand = new Random();
        int num = rand.nextInt(words.size());

        return words.get(num);
    }

    public void printDictionary(){
        for(int i = 0; i < words.size(); i++){
            System.out.println(words.get(i).getWord());
        }
    }

    public int getSize(){
        return words.size();
    }

    public void removeWordsWithLetter(String letter){
        int size = words.size();
        for(int i = 0; i < size; i++){
            boolean toRemove = false;
            String word = words.get(i).getWord();
            
            for(int j = 0; j < word.length(); j++){
                if(word.charAt(j) == letter.charAt(0)){
                    toRemove = true;
                }
            }
            if(toRemove){
                words.remove(i);
                i--;
                size--;
            }
        }
    }
    
    public void removeWordsExcept(String guess, ArrayList locations){
        
        
    }
}
