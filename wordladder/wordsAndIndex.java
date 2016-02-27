package wordladder;

import java.util.ArrayList;

/* This class is used to create a HashMap that contains two
 * ArrayLists so that we can save both candidate words and
 * which index is changed in the candidate word.
 */
class wordsAndIndex {
    ArrayList<String> s;
    ArrayList<Integer> j;

    public wordsAndIndex(ArrayList<String> s, ArrayList<Integer> j) {
        this.s = s;
        this.j = j;
    }

    public ArrayList<String> getS() {
        return s;
    }

    public void setS(ArrayList<String> s) {
        this.s = s;
    }

    public ArrayList<Integer> getJ() {
        return j;
    }

    public void setJ(ArrayList<Integer> j) {
        this.j = j;
    }


}