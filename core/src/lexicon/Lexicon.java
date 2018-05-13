package lexicon;

interface Lexicon {

    /**
     * Adds a word to the lexicon.
     *
     * @param word the word to be added
     * @return true iff the word was successfully added to the lexicon
     */
    boolean add(String word);

    /**
     * Determines the lexicon status of a string.
     *
     * @param s the string to be checked
     * @return the LexStatus of the given string
     */
    LexStatus wordStatus(String s);

    /**
     * Determines whether or not the given word in in the lexicon.
     *
     * @param word the word to be checked
     * @return true iff the word is in the lexicon
     */
    boolean contains(String word);

}
