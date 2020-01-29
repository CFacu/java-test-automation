package com.solvd.repeatedwords;

import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class RepeatedWords {
    public static void Counter() throws IOException {
        File file = new File("textIn.txt");
        String text = readFileToString(file, "utf-8").toLowerCase();
        String[] delimiters = {",", ".", ";", "\t", "\n"};
        Map<String, Integer> repeated = new HashMap<>();

        for (String delimiter : delimiters) {
            while (text.indexOf(delimiter) != -1) {
                text = text.replace(delimiter, " ");
            }
        }

        String[] words = split(text," ");

        for (String word : words) {
            try {
                word = parseDate(word, "mm-dd-yyyy", "mm/dd/yyyy").toString();
            } catch (ParseException e) {
                ;
            }
            if (repeated.containsKey(word))
                repeated.replace(word, repeated.get(word), repeated.get(word) + 1);
            else repeated.put(word, 1);
        }

        writeStringToFile(new File("textOut.txt"), repeated.toString(), "utf-8");

    }

    public static void main(String[] args) throws IOException{
        Counter();
    }

}
