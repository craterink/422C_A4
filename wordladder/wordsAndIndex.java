package wordladder;

import java.util.ArrayList;

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