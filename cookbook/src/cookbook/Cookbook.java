/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;



import java.io.IOException;
import java.util.ArrayList;


public class Cookbook {

    public static void main(String[] args) throws IOException {
     
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> word_freqs = new ArrayList<String>();
        
        RemovingStopWords removingStopWords = new RemovingStopWords();
        removingStopWords.setup();
        String text = removingStopWords.replaceRegex();
        System.out.println(text);
        
        
        
        
    
    
    }

    
    }
 
    
