// It stores a tuple (p,w) 

public class Position{  
    PageEntry p; //Page position p
    int i; // Word position w
    public Position(PageEntry p, int wordIndex){
        p = p;
        i = wordIndex;
    }
    public PageEntry getPageEntry() {
        return p;
    }
    public int getWordIndex(){
        return i;
    }
}