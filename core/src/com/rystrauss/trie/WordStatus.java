package com.rystrauss.trie;

/**
 * Enum that defines the possible outcomes for a Trie query.
 * <p>
 * If PREFIX is returned, then the string is not a word, but is the prefix of
 * at least one word in the lexicon.
 * <p>
 * If WORD is returned, then the string is a word in the dictionary (it could
 * be a prefix too, e.g., MOM is a word, but is a prefix of MOMENT).
 * <p>
 * If NOT_WORD is returned, the string is not a word, and it's not the prefix
 * of any word in the lexicon.
 *
 * @author Ryan Strauss
 */
public enum WordStatus {
    WORD,
    PREFIX,
    NOT_WORD
}
