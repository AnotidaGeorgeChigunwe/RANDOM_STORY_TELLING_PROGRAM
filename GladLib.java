import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> usedWords;
    private ArrayList<String> considerList;
    private HashMap<String,ArrayList<String>> listMap;
    private int wordCount;    
    private Random myRandom;    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        listMap = new HashMap<String,ArrayList<String>>();
        String[] labels ={"country","noun","animal","adjective",
                          "name","color",
                            "timeframe","fruit","verb"};
        for (String s: labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            listMap.put(s, list);
        }
        usedWords = new ArrayList<String>();
        considerList = new ArrayList<String> ();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        wordCount++;
        //keeps track of used labels 
        if (!considerList.contains(label)) {
            considerList.add(label);
        }
        
        if (label.equals("number")) {
            return ""+ myRandom.nextInt(50)+5;
        }
        return randomFrom(listMap.get(label));
    }
    

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index = usedWords.indexOf(sub);
        while (index !=-1) {
            sub = getSubstitute(w.substring(first+1,last));
            index = usedWords.indexOf(sub);
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        
        System.out.print("\n \n" +"words replaced is : "+ wordCount);
    }
    
    private String fromTemplate(String source){
        String story = "";
        usedWords.clear();
        considerList.clear();
        wordCount =0;
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    
    public int totalWordsInMap () {
        int count = 0;
        for (String s : listMap.keySet()) {
            ArrayList<String> currList = listMap.get(s);
            for (String w : currList) {
                count++;
            }
        }
        return count;
    }
    
    public int totalWordsConsidered () {
        int count = 0;
        for (String s : considerList) {
            count += listMap.get(s).size();
        }
        return count;        
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n" + totalWordsInMap());
        
    }

}