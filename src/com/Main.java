package com;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException
    {
        long time = System.currentTimeMillis();

        File file = new File(args[1]);

        file.setWritable(true);

        if(file.exists()){
            file.delete();
        }

        if(!file.exists()){
            file.createNewFile();
        }

        getMostFrequentlyUsedWords(4, args[0], args[1]);

        time = System.currentTimeMillis() - time;

        System.out.println("Done in " + time + " ms");
    }

    public static void writeReversedFile(String outputFilePath, String data){
        Writer writer = null;

        try{

            File file = new File(outputFilePath);

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);

            bufferWritter.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void getMostFrequentlyUsedWords(int limit, String inputFilePath, String outputFilePath) throws IOException{


        Map<String, WordsCounter.Word> counterMap = new HashMap<String, WordsCounter.Word>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath)));
        String line;
        while ((line = reader.readLine()) != null) {

            //Reverse written line and add it to the output-file:
            StringBuffer buffer = new StringBuffer(line);
            buffer.reverse();
            if(!line.isEmpty()) {
                line = buffer.substring(0, buffer.length() - 1);
            }

            if(!line.trim().equals("")){
                writeReversedFile(outputFilePath, "\n");
            }
            writeReversedFile(outputFilePath, line);


            //Calculate five most used words:
            String[] words = line.split("[ ,!?.]+" );

            for (String word : words) {

                if (word.trim().equals("")){
                    continue;
                }

                WordsCounter.Word wordObj = counterMap.get(word);
                if (wordObj == null) {
                    wordObj = new WordsCounter.Word();
                    wordObj.word = word;
                    wordObj.counter = 0;
                    counterMap.put(word, wordObj);
                }

                wordObj.counter++;
            }
        }

        reader.close();

        SortedSet<WordsCounter.Word> sortedWords = new TreeSet<WordsCounter.Word>(counterMap.values());
        int i = 0;
        for (WordsCounter.Word word : sortedWords) {
            if (i > limit) {
                break;
            }

            System.out.println(word.counter + "\t" + word.word);

            i++;
        }
    }
}

