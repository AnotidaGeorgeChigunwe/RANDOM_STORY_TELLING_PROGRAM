
/**
 * Random stroy telling program
 * 
 * @author Anotida George Chigunwe 
 * @version (01 12/28/2018)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 import java.util.*;
 
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    int unique;
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        unique =0;
        FileResource fr = new FileResource();
        for (String word: fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index ==-1) {
                unique++;
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
              int value = myFreqs.get(index);
              myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax () {
        int value = 0;
        int indexOfMax = 0;
        for (int k =0; k< myFreqs.size(); k++) {
            int currValue = myFreqs.get(k);
            if (currValue > value) {
                indexOfMax = k;
                value = currValue;
            }
        }
        return indexOfMax;
    }    
        
    public void tester(){
        findUnique();
        System.out.println("The unquie words are :\t" + unique);
        System.out.println( "The word that apprears the most :\t" + myWords.get(findIndexOfMax())+"\t"+myFreqs.get(findIndexOfMax()));
        for (int k=0; k< myWords.size(); k++) {
            System.out.println( myFreqs.get(k)+ "\t " + myWords.get(k)) ;
        }
    }
}
