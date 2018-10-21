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
    public float getTermFrequency(PageEntry p){
        int totWords = p.getPageIndex().getWordEntries().length();
        float occInPage = 0;
        for (int i = 0;i<poslist.length();i++){
            Position currEle = poslist.getElementByIndex(i);
            if ( (p.page).equals(currEle.page.page)){
                occInPage++
            }
        }
        float tf = occInPage/totWords;
        return tf;
    }
    public static void main(String[] args) {
        WordEntry a = new WordEntry("lol");
        PageEntry p = new PageEntry("stackoverflow");
        WordEntry w = p.index.wordlist.getElementByIndex(0);
        System.out.println(w.getTermFrequency(p));
    }
}