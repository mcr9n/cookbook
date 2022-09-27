/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author 144fps
 */
public class RemovingStopWords {
    private String data;
    
    private List<String> stopwords;

    private String stopwordsRegex;
    
    


    public void setup() throws IOException {
        data = new String(Files.readAllBytes(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\shakespeare-hamlet.txt")));
        data = data.toLowerCase();
        stopwords = Files.readAllLines(Paths.get("C:\\Users\\144fps\\Documents\\NetBeansProjects\\english_stopwords.txt"));
        stopwordsRegex = stopwords.stream().collect(Collectors.joining("|", "\\b(", ")\\b\\s?"));
    }
    public String replaceRegex() {
        return data.replaceAll(stopwordsRegex, "");
    }
    
    
}
