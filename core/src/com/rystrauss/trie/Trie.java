package com.rystrauss.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * A Trie data structure (a.k.a digital tree or prefix tree).
 * <p>
 * This implementation is keyed by strings and is intended to represent a dictionary of words that can be
 * queried to determine whether or not a string is a word in the dictionary, a prefix of a word in the
 * dictionary, or not in the dictionary.
 * <p>
 * This Trie is case-insensitive. All strings are converted to lower-case upon insertion.
 *
 * @author Ryan Strauss
 */
public class Trie {

    private Node root;
    private int size;

    /**
     * Constructs an empty Trie dictionary.
     */
    public Trie() {
        this.root = new Node("");
        this.size = 0;
    }

    /**
     * Inserts a word into the dictionary, if it doesn't already exist.
     * <p>
     * The word will be converted to lower-case before insertion.
     *
     * @param word the word to be inserted, if necessary
     * @return true iff the word was inserted, i.e. iff the word was not already in the dictionary
     */
    public boolean insert(String word) {
        word = word.toLowerCase();
        Node cur = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node child = cur.children.get(c);
            if (child == null) {
                child = new Node(cur.sequence + c);
                cur.children.put(c, child);
            }
            cur = child;
        }

        if (!cur.isWord) {
            cur.isWord = true;
            this.size++;
            return true;
        }

        return false;
    }

    /**
     * Queries the dictionary for a word. See the {@code WordStatus} documentation
     * for the meanings of the three possible results.
     * <p>
     * The queried word is converted to lower-case before being processed.
     *
     * @param word the queried word
     * @return the {@code WordStatus} of {@code word}
     */
    public WordStatus query(String word) {
        word = word.toLowerCase();
        Node cur = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.children.get(c);
            if (cur == null)
                return WordStatus.NOT_WORD;
        }

        return cur.isWord ? WordStatus.WORD : WordStatus.PREFIX;
    }

    /**
     * Gets the size of the trie.
     *
     * @return the number of words in the dictionary
     */
    public int size() {
        return this.size;
    }

    private static class Node {

        String sequence;
        boolean isWord;
        Map<Character, Node> children;

        Node(String sequence) {
            this.sequence = sequence;
            this.isWord = false;
            this.children = new HashMap<>();
        }

    }

}
