// It stores a tuple (p,w) 

public class Position{  
    PageEntry page; //Page position p
    int i; // Word position w
    public Position(PageEntry p, int wordIndex){
        page = p;
        i = wordIndex;
    }
    public PageEntry getPageEntry() {
        return page;
    }
    public int getWordIndex(){
        return i;
    }
}