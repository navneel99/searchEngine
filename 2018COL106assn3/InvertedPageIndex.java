public class InvertedPageIndex{
    Myset<PageEntry> entries = new Myset<>();
    public void addPage(PageEntry p) {
        entries.addElement(p);
    }
    public Myset<PageEntry> getPagesWhichContainWord(String str) {
        //return set of page-entries of webpages which contain the word str
    }

}