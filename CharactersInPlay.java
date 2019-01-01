
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 import java.util.*;
 
public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay () {
        charNames =  new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    } 
    
    public void findAllCharacters () {
        charNames.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int index = line.indexOf(".");
            //System.out.println("The Index is  : " + index);
            if (index>0) {
                update(line.substring(0,index));
            }
        }
    }
    
    private void update(String person) {
        int index = charNames.indexOf(person);
        if (index ==-1) { 
            charNames.add(person);
            myFreqs.add(1);
        }
        else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value+1);
        }
    }
    
    public void tester () {
        findAllCharacters ();
        for (int k = 0;k< charNames.size();k++) {
            int value = myFreqs.get(k);
                if (value > 10 && value < 100) {
                    System.out.println(charNames.get(k) +"\t"+myFreqs.get(k));
                } 
        }
    }
}
