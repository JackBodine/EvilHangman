
/**
 * Write a description of class Word here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Word
{
    // instance variables - replace the example below with your own
    private String word;
    private int length;

    /**
     * Constructor for objects of class Word
     */
    public Word(String w)
    {
        // initialise instance variables
        this.word = w.toLowerCase();
        this.length = w.length();
    }
    
    public String getWord(){
        return word;
    }
}
