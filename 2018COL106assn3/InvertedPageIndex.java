public class InvertedPageIndex{

    Myset<PageEntry> entries = new Myset<>();
    MyHashTable mht = new MyHashTable();  //hashtable for ipi

    public void addPage(PageEntry p) {
        PageIndex PIpage = p.getPageIndex();
        for (int i = 0; i<PIpage.wordlist.length();i++){
            WordEntry currEle = PIpage.wordlist.getElementByIndex(i);
            //System.out.println("Currently in IPI. The wordEntry has word "+ currEle.word);
            //System.out.println("Page: "+p.page+" word: "+currEle.word);
            mht.addPositionsForWord(currEle);
        }
        entries.addElement(p);
    }
    public Myset<PageEntry> getPagesWhichContainWord(String str) {
        Myset<PageEntry> tempPages = new Myset<>();
        //Making a make-shift Hash Fn//
        char first = str.charAt(0);
        int ascii = (int) first;
        ascii =ascii%26;
        MyLinkedList<WordEntry> chain = mht.mainArray[ascii];
        for (int k = 0; k< chain.length();k++){
            WordEntry currEle = chain.getElementByIndex(k);
            if(currEle.word.equals(str)){
                MyLinkedList<Position> allPos = currEle.getAllPositionsForThisWord();
                for (int i = 0;i<allPos.length();i++){
                    Position currPos = allPos.getElementByIndex(i);
                    if (tempPages.list.checkElement(currPos.page)){
                        continue;
                    } else {
                        tempPages.addElement(currPos.page);
                    }
                }
            }else{
                continue;
            }
        }
        return tempPages;
        //return set of page-entries of webpages which contain the word str
    }
    public Myset<PageEntry>getPagesWhichContainPhrase(String str[]){
        return null;
    }

}