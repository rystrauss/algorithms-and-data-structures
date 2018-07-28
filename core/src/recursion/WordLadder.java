package recursion;

import java.util.List;
import java.util.ListIterator;

public class WordLadder {

    private List<String> dict;

    public WordLadder(List<String> dict) {
        this.dict = dict;
    }

    /**
     * Returns true if the words are one letter different.
     */
    private boolean oneAway(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                count++;
        }
        return count == 1;
    }

    /**
     * Determines if there is a word ladder from from to to going through
     * at least one of the rung words. You may only move to the next word if
     * there is only one letter different.
     *
     * @param from - the initial starting from word
     * @param to   - the initial ending word in the ladder
     * @return - true if a ladder exists, or false if there is no ladder
     */
    public boolean ladderExists(String from, String to) {
        if (dict.isEmpty())
            return false;
        ListIterator<String> listIterator = dict.listIterator();
        while (listIterator.hasNext()) {
            String word = listIterator.next();
            if (oneAway(word, from)) {
                listIterator.remove();
                if (oneAway(word, to) || ladderExists(word, to))
                    return true;
                listIterator.add(word);
            }
        }
        return false;
    }

}
