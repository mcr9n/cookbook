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
//we need two different returns in java methods
final class MyResult {
    private final String first;
    private final String second;

    public MyResult(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}


public class Cookbook {
    //procedures
    //replace a char at a specific index
    public static String replaceChar(String str, char ch, int index) {
    StringBuilder myString = new StringBuilder(str);
    myString.setCharAt(index, ch);
    return myString.toString();
    }
    //remove punctuations
    public static String removePunctuations(String source) {
		return source.replaceAll("\\p{Punct}", " ");
	}
    //removing stopwords
    public static MyResult setup() throws IOException {
        
        
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\pride-and-prejudice.txt")));
        data = data.toLowerCase();
        List<String>stopwords = Files.readAllLines(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\english_stopwords.txt"));
        String stopwordsRegex = stopwords.stream().collect(Collectors.joining("|", "\\b(",")\\b\\s?"));
        return new MyResult(data, stopwordsRegex); 
        
    }
    public static String replaceRegex(String data, String stopwordsRegex) {
        return data.replaceAll(stopwordsRegex, "");
    }
    //fill the array of words
    public static ArrayList<String> FillWords(String data){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m1 = p.matcher(data);
        ArrayList<String> words = new ArrayList<String>();
         while (m1.find()) {
            words.add(m1.group());
        }
        return words;
    
    }
    //fill word freqs hashmap
    public static HashMap<String, Integer> FillWordFreqs(ArrayList<String> words){
        HashMap<String, Integer> myWordsCount = new HashMap<String, Integer>();
        for (String s : words){
        if (myWordsCount.containsKey(s)){ 
            myWordsCount.replace(s, myWordsCount.get(s) + 1);
        }
        else{ 
            myWordsCount.put(s, 1);
        }
        }
    
        return myWordsCount;
    }
    //sorting hashmap
    public static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> word_freqs){
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : word_freqs.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder()); 
        for (int num : list) {
            for (Entry<String, Integer> entry : word_freqs.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
    
    
        return sortedMap;
    }

    public static void main(String[] args) throws IOException {
     
        ArrayList<String> words = new ArrayList<String>();
        HashMap<String, Integer> word_freqs = new HashMap<String, Integer>();
        
        
        MyResult result = setup(); //creating the setup so we can start
        String text = replaceRegex(result.getFirst(), result.getSecond()); //getting the text whitout stopwords
        text = removePunctuations(text); //removing punctuations
        words = FillWords(text); //filling the words array
        word_freqs = FillWordFreqs(words); //filling the word freqs hashmap
        HashMap<String, Integer> sorted_map = sortHashMap(word_freqs);//sorting the hashmap of freqs
        
        
//        showing sorted hashmap (frequencies of words ordered
        int counter = 0; //limit the operation of printing the hashmap
        for (String chave: sorted_map.keySet()) {
        counter += 1;
        String key = chave;
        String value = sorted_map.get(chave).toString();
        System.out.println(key + " - " + value);
        if(counter >= 25){
        break;
        }
        }
        
        
        
        
        
    
    
    }

    
    }
 
    
