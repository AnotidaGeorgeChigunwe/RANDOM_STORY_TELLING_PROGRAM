
/**
 * Write a description of WordsinFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 import java.util.*;
public class WordsinFiles {
    private HashMap<String,ArrayList<String>> fileWordCount;
    
    public WordsinFiles() {
        fileWordCount = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String w : fr.words()) {
            ArrayList<String> wordArray = new ArrayList<String> (); 
            wordArray.add(f.getName());
            if (!fileWordCount.containsKey(w)) {
                fileWordCount.put(w,wordArray);                
            }
            else {
                wordArray = fileWordCount.get(w);
                if (!wordArray.contains(f.getName())) {
                    wordArray.add(f.getName());
                    fileWordCount.put(w,wordArray); 
                }
            }
        }        
    }
    
    public void buildWordFileMap () {
        fileWordCount.clear();
        DirectoryResource dr = new DirectoryResource ();
        for (File F : dr.selectedFiles()) {
            addWordsFromFile(F);
        }
    }
    
    public int maxNumber () {
        int maxNumber= 0;
        for (String s : fileWordCount.keySet()) {
            ArrayList<String> currArray = fileWordCount.get(s);
            int currSize = currArray.size();
            if (currSize > maxNumber) {
                maxNumber = currSize;
            }
        }        
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> fileNames = new ArrayList<String>();
        for (String s : fileWordCount.keySet()) {
            ArrayList<String> currArray = fileWordCount.get(s);
            int currSize = currArray.size();
            if (currSize == number) {
                fileNames.add(s);
            }
        }
        return fileNames;
    }
    
    public void printFilesIn (String word) {
        ArrayList<String> filenames = fileWordCount.get(word);
        System.out.println("The word is : \"" + word + "\" the files are\n");
        for (String s:filenames) {
            System.out.println(s + "\n");
        }
    }
    //TEST
    public void tester () {
        buildWordFileMap ();
        int maxNumber = maxNumber ();
        //System.out.println("The max int :" + maxNumber);
        ArrayList<String> wordWithMax = wordsInNumFiles (maxNumber);
        for (String s: wordWithMax) {
            s = "tree";
            printFilesIn (s);
        }
        System.out.println("The number of files are : "+ (wordWithMax.size()));
    }
}
