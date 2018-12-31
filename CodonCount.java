
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 import java.util.*;
 
public class CodonCount {
    private HashMap<String,Integer> dnaCodonCount;
    
    public CodonCount () {
        dnaCodonCount = new HashMap<String,Integer> ();
    }
    
    public void buildCodonMap (int start, String dna) {
        dnaCodonCount.clear();
        int index = 0;
        int currStart = start;
        for (int k = start; k<dna.length()-3; k=k+3) {
            String sub = dna.substring(k, k+3);
            if (!dnaCodonCount.containsKey(sub)) {
                dnaCodonCount.put(sub,1);
            }
            else {
                int value = dnaCodonCount.get(sub);
                dnaCodonCount.put(sub,value + 1);
            }
        }
    }
    
    public String getMostCommonCodon (){
        int currHightest =0;
        String mostComm = "NO STRING FOUND";
        for(String k : dnaCodonCount.keySet()) {
            int value = dnaCodonCount.get(k);
            if (value>currHightest) {
                currHightest=value;
                mostComm = k;
            }            
        }
        return mostComm;
    }
    
    public void printCodonCounts () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap (2,dna);
        for (String k : dnaCodonCount.keySet()){
            System.out.println( k +"\t:\t"+(dnaCodonCount.get(k)));
        }
    }
}
