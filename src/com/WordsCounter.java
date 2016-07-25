package com;

/**
 * Created by Small on 7/25/2016.
 */
public class WordsCounter {

    public static class Word implements Comparable<Word>
    {
        String word;

        int counter;

        @Override
        public int hashCode()
        {
            return word.hashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            return word.equals(((Word)obj).word);
        }

        @Override
        public int compareTo(Word b)
        {
            return b.counter - counter;
        }
    }

}
