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
    
    public Myset<PageEntry>getPagesWhichContainPhrase(String str[]){
        Myset<PageEntry> allPages = getPagesWhichContainWord(str[0]);
        Myset<PageEntry> actualPage = new Myset<>();
        for (int i = 1; i<str.length;i++){
            String currWord = str[i];
            allPages = allPages.intersection(getPagesWhichContainWord(currWord)); //Get the minimum number of pages which contain all the words
        }
        for (int j = 0; j< allPages.list.length();j++){
            PageEntry currPage = allPages.list.getElementByIndex(j); // We will work each page one by one
            /*for (int k = 0; k< currPage.wholeTextArrayWithStopWords.length;k++){
                 String currWord = currPage.wholeTextArrayWithStopWords[k];
                 if (currWord.equals(str[0])){
                     boolean fCheck =true;
                     for (int l =0;l<str.length;l++){
                         int m =l;
                         String[] stopWords = {"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};
                         boolean check = true;
                         String currWord2 = currPage.wholeTextArrayWithStopWords[m];
                         for (int z =0; z<stopWords.length;z++){
                             if (currWord2.equals(stopWords[z])){
                                 check = false;
                                 break;
                             }
                         }
                         if (check =true){ //it is not a stop word

                         } else{

                         }
                     }

                 }
            }*/
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
                    /*for (int m = 0; m<firstPosList.length();m++){
                        Position oldPos = firstPosList.getElementByIndex(m);
                        if(currPos.i-oldPos.i == 1){
                            newPosList.addElement(currPos);
                            break;
                        } else{
                            continue;
                        }
                    }*/
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
    }
    
    static float inverseDocumentFrequency(String word){
        double N = Math.log((double)InvertedPageIndex.numberEntries);
        double nw = Math.log((double)InvertedPageIndex.wordwisePageList.get(word));
        double idf = (N/nw);
        //System.out.println(word+" "+ InvertedPageIndex.wordwisePageList.get(word));
        return (float)idf;
    }
    /*static float idfForPhrase(String str[]){
        double N = Math.log((double)InvertedPageIndex.numberEntries);
        //double nw = Math.log((double)InvertedPageIndex.wordwisePageList.get(word));
        double idf = (N/nw);
        //System.out.println(word+" "+ InvertedPageIndex.wordwisePageList.get(word));
        return (float)idf;
    }*/
    
}