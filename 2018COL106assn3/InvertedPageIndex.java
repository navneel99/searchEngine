import java.util.*;

public class InvertedPageIndex{
    static float numberEntries = 0;
    static float numberWherePhrase = 0;
    Myset<PageEntry> entries = new Myset<>();
    MyHashTable mht = new MyHashTable();  //hashtable for ipi
    static HashMap<String,Integer> wordwisePageList = new HashMap<String, Integer>();

    public void addPage(PageEntry p) {
        PageIndex PIpage = p.getPageIndex();
        for (int i = 0; i<PIpage.wordlist.length();i++){
            WordEntry currEle = PIpage.wordlist.getElementByIndex(i);
            mht.addPositionsForWord(currEle);
        }
        entries.addElement(p);
        increasePageNumberForWordsInPage(p);
        numberEntries++;
    }
    static void increasePageNumberForWordsInPage(PageEntry p){
        PageIndex pi = p.index;
        for (int i = 0; i< pi.wordlist.length();i++){
            WordEntry we = pi.wordlist.getElementByIndex(i);
            try{
                int pageNum = wordwisePageList.get(we.word);
                wordwisePageList.put(we.word, pageNum+1);
            } catch (Exception e){
                wordwisePageList.put(we.word,1);
            }
        }
    }
    public Myset<PageEntry> getPagesWhichContainWord(String str) {
        Myset<PageEntry> tempPages = new Myset<>();
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
    
    /*public Myset<PageEntry>getPagesWhichContainPhrase(String str[]){
        Myset<PageEntry> allPages = getPagesWhichContainWord(str[0]);
        Myset<PageEntry> actualPage = new Myset<>();
        for (int i = 1; i<str.length;i++){
            String currWord = str[i];
            allPages = allPages.intersection(getPagesWhichContainWord(currWord)); //Get the minimum number of pages which contain all the words
        }
        for (int j = 0; j< allPages.list.length();j++){
            PageEntry currPage = allPages.list.getElementByIndex(j); // We will work each page one by one
            
            MyLinkedList<Position> firstPosList = currPage.getPositionsForWord(str[0]); //First word's list of positions got.
            for (int k = 1; k<str.length;k++){
                String word  = str[k];
                WordEntry WE = this.mht.getWordEntryFromHashTable(word);
                MyLinkedList<Position> currPosList = currPage.getPositionsForWord(word); //  getting list for next word's position
                MyLinkedList<Position> newPosList = new MyLinkedList<>();
                for (int l = 0;l<currPosList.length();l++){
                    Position currPos = currPosList.getElementByIndex(l);
                    for (int m = 0; m<firstPosList.length();m++){
                            Boolean check = WE.avl.doesPositionExist(currPage, firstPosList.getElementByIndex(m).i + 1, WE.avl.root); //This is wrong
                            if (check){
                                newPosList.addElement(currPos);
                            }
                    }
                    
                }
                firstPosList = newPosList;
            }
            if (firstPosList != null){
                actualPage.addElement(currPage);
            } else{
                continue;
            }
        }
        //numberWherePhrase = actualPage.list.length();
        return actualPage;
    }*/
    
    public Myset<PageEntry>getPagesWhichContainPhrase(String str[]){
        Myset<PageEntry> allPages = getPagesWhichContainWord(str[0]);
        Myset<PageEntry> actualPage = new Myset<>();
        for (int i = 1; i<str.length;i++){
            String currWord = str[i];
            allPages = allPages.intersection(getPagesWhichContainWord(currWord)); //Get the minimum number of pages which contain all the words
        }
        // We now have a set of pages which contain all the words.
        for (int i =0; i<allPages.list.length();i++){
            PageEntry nowPage = allPages.list.getElementByIndex(i);
            //Now check;
            for (int j =0; j< nowPage.textAVLTree.length();j++){
                String nowWord = nowPage.textAVLTree.inOrderSuccessor(j);
                //System.out.println(nowWord);
                if (nowWord.equals(str[0])){
                    boolean tVal = true;
                    for (int k =1; k<str.length;k++){
                        WordEntry we;
                        if (str[k].equals(nowPage.textAVLTree.inOrderSuccessor(j+k))){
                            //System.out.println(str.length);
                            continue;
                        } else{
                            tVal = false;
                            break;
                        }
                    }
                    if (tVal){
                        actualPage.addElement(nowPage);
                        break;
                    }
                }
            }
        }
        return actualPage;
    }
    static float inverseDocumentFrequency(String word){
        double N = Math.log((double)InvertedPageIndex.numberEntries);
        double nw = Math.log((double)InvertedPageIndex.wordwisePageList.get(word));
        double idf = (N/nw);
        //System.out.println(word+" "+ InvertedPageIndex.wordwisePageList.get(word));
        return (float)idf;
    }
    
}