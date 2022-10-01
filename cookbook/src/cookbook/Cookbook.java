/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;





public class Cookbook {
    //global variables
    public static String stopwordsRegex;
    public static String data;
    public static ArrayList<String> words = new ArrayList<String>();
    public static HashMap<String, Integer> myWordsCount = new HashMap<String, Integer>();
    public static LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    //procedures
    //replace a char at a specific index - aux procedure
    public static String replaceChar(String str, char ch, int index) {
    StringBuilder myString = new StringBuilder(str);
    myString.setCharAt(index, ch);
    return myString.toString();
    }
    //remove punctuations
    public static void removePunctuations() {
              Cookbook.data = Cookbook.data.replaceAll("\\p{Punct}", " ");
	}
    //removing stopwords
    public static void setup() throws IOException {
        
        
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\pride-and-prejudice.txt")));
        Cookbook.data = data.toLowerCase();
        List<String>stopwords = Files.readAllLines(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\english_stopwords.txt"));
        Cookbook.stopwordsRegex = stopwords.stream().collect(Collectors.joining("|", "\\b(",")\\b\\s?"));
        
        
    }
    public static void replaceRegex() {
        Cookbook.data = Cookbook.data.replaceAll(stopwordsRegex, "");
    }
    //fill the array of words
    public static void FillWords(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m1 = p.matcher(Cookbook.data);
         while (m1.find()) {
            Cookbook.words.add(m1.group());
        }
        
    
    }
    //fill mywordscount hashmap
    public static void FillWordFreqs(){
        
        for (String s : Cookbook.words){
        if (myWordsCount.containsKey(s)){ 
            myWordsCount.replace(s, myWordsCount.get(s) + 1);
        }
        else{ 
            myWordsCount.put(s, 1);
        }
        }
    
       
    }
    //sorting mywordscount hashmap
    public static void sortHashMap(){
        ArrayList<Integer> list = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : myWordsCount.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder()); 
        for (int num : list) {
            for (Entry<String, Integer> entry : myWordsCount.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
    
    
        
    }

    public static void main(String[] args) throws IOException {
        
        setup(); //creating the setup so we can start
        replaceRegex(); //getting the text whitout stopwords
        removePunctuations(); //removing punctuations
        FillWords(); //filling the words array
        FillWordFreqs(); //filling mywordscount hashmap
        sortHashMap();//sorting the hashmap of freqs
        
        
//        showing sorted hashmap (frequencies of words ordered
        int counter = 0; //limit the operation of printing the hashmap
        for (String chave: sortedMap.keySet()) {
        counter += 1;
        String key = chave;
        String value = sortedMap.get(chave).toString();
        System.out.println(key + " - " + value);
        if(counter >= 25){
        break;
        }
        }
        
        
        
        
        
    
    
    }

    
    }
 
    
