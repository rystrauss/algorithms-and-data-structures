package lexicon;

import java.util.Map;
import java.util.TreeMap;

/**
 * A Trie implementation of a lexicon.
 *
 * <p>
 * Lexicon contains words, is case insensitive, and can be queried to see if a given
 * String is a word in the lexicon, a prefix to a word, or not in the lexicon.</p>
 *
 * @author Ryan Strauss
 */
public class TrieLexicon implements Lexicon {

    private class Node {

        Node parent;
        Map<Character, Node> children;
        boolean isWord;
        char c;

        Node() {
            children = new TreeMap<>();
            isWord = false;
            parent = null;
            c = '\0';
        }

        Node(char c, Node parent) {
            children = new TreeMap<>();
            isWord = false;
            this.parent = parent;
            this.c = c;
        }

    }

    private Node root;
    private int size;

    public TrieLexicon() {
        root = new Node();
        size = 0;
    }

    /**
     * Adds a word to the lexicon.
     *
     * @param word the word to be added
     * @return true iff the word was successfully added to the lexicon
     */
    @Override
    public boolean add(String word) {
        word = word.toLowerCase();
        Node pos = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node child = pos.children.get(c);
            if (child == null) {
                child = new Node(c, pos);
                pos.children.put(c, child);
            }
            pos = child;
        }
        if (!pos.isWord) {
            pos.isWord = true; // walked down path, mark this as a word
            size++;
            return true;
        }
        return false;
    }

    /**
     * Determines the lexicon status of a string.
     *
     * @param s the string to be checked
     * @return the LexStatus of the given string
     */
    @Override
    public LexStatus wordStatus(String s) {
        s = s.toLowerCase();
        Node pos = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pos = pos.children.get(c);
            if (pos == null) {
                return LexStatus.NOT_WORD;
            }
        }
        return pos.isWord ? LexStatus.WORD : LexStatus.PREFIX;
    }

    /**
     * Determines whether or not the given word is in the lexicon.
     *
     * @param word the word to be checked
     * @return true iff the word is in the lexicon
     */
    @Override
    public boolean contains(String word) {
        word = word.toLowerCase();
        return wordStatus(word) == LexStatus.WORD;
    }

    /**
     * Returns the size of the lexicon.
     *
     * @return the number of words in the lexicon
     */
    public int size() {
        return size;
    }

}
