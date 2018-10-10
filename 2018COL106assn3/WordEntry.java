//For each word, we have a (Linked)List of Tuples that appear on various webpages. Interesting class.
//It stores all the positions in all the webpages.

public class WordEntry{
    String word;
    MyLinkedList<Position> poslist = new MyLinkedList<>();
    public WordEntry(String word){
        this.word = word;
    }
    public void addPosition(Position position) {
        poslist.addElement(position);
    }
    public void addPositions(MyLinkedList<Position> positions) {
        for (int i =0; i< positions.length();i++){
            Position currEle = positions.getElementByIndex(i);
            poslist.addElement(currEle); 
        }
    }
    public MyLinkedList<Position> getAllPositionsForThisWord(){
        return poslist;
    }
    public float getTermFrequency(String word){
        //return the term frequency of the word in a webpage
        return 0;
    }
}