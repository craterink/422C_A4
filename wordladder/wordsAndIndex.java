/**
 * EE422C Assignment 4 - Word Ladder
 *
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import java.util.ArrayList;

/** This class is used to create a HashMap that contains two
 * ArrayLists so that we can save both candidate words and
 * which index is changed in the candidate word.
 * 
 * Used for path finding in the word map graph.
 */
class WordsAndIndex {
	
	/**
	 * Contains all words that are potentially the next word-ladder word
	 */
    ArrayList<String> candidateWords;
    
    /**
     * Contains past changed indices so that we don't go backwards to the last word
     */
    ArrayList<Integer> changedIndices;

    /**
     * Initializes a new WordsAndIndex object with the specified candidate words and changed indices
     * @param candidates Candidate words list
     * @param indices Changed indices list
     */
    public WordsAndIndex(ArrayList<String> candidates, ArrayList<Integer> indices) {
        this.candidateWords = candidates;
        this.changedIndices = indices;
    }

    /**
     * Gets the candidate words list
     * @return Candidate words list
     */
    public ArrayList<String> getCandidateWords() {
        return candidateWords;
    }

    /**
     * Sets this object's condidate words list
     * @param candidates Candidate words list
     */
    public void setCandidateWords(ArrayList<String> candidates) {
        this.candidateWords = candidates;
    }

    /**
     * Gets this objefct's changed indices list.
     * @return List of changed indices/
     */
    public ArrayList<Integer> getChangedIndices() {
        return changedIndices;
    }

    /**
     * Sets this object's changed indices list
     * @param changedIndices List of changed indices.
     */
    public void setChangedIndices(ArrayList<Integer> changedIndices) {
        this.changedIndices = changedIndices;
    }


}