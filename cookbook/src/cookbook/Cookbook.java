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
import java.util.List;
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
		return source.replaceAll("\\p{Punct}", "");
	}
    //removing stopwords
    public static MyResult setup() throws IOException {
        
        
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\shakespeare-hamlet.txt")));
        data = data.toLowerCase();
        List<String>stopwords = Files.readAllLines(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\english_stopwords.txt"));
        String stopwordsRegex = stopwords.stream().collect(Collectors.joining("|", "\\b(", ")\\b\\s?"));
        return new MyResult(data, stopwordsRegex); 
        
    }
    public static String replaceRegex(String data, String stopwordsRegex) {
        return data.replaceAll(stopwordsRegex, "");
    }
    
    

    public static void main(String[] args) throws IOException {
     
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> word_freqs = new ArrayList<String>();
        
        
        MyResult result = setup();
        String text = replaceRegex(result.getFirst(), result.getSecond());
        text = removePunctuations(text);
        System.out.println(text);
        
        
        
        
        
    
    
    }

    
    }
 
    
