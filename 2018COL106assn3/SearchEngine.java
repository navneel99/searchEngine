import java.util.ArrayList;
import java.util.Arrays;

public class SearchEngine{
    InvertedPageIndex ipi;
    public SearchEngine(){
        ipi = new InvertedPageIndex();
    }

    public void performAction(String actionMessage){
        String[] message = actionMessage.split(" ");
        if(message[0].equals("addPage")){
            String page = message[1];
            PageEntry PEpage = new PageEntry(page); //PageIndex made.
            ipi.addPage(PEpage);
        } else if(message[0].equals("queryFindPagesWhichContainWord")){
            String word = message[1];
            String word2 = word.toLowerCase();
            word2 = word2.replace("stacks","stack");
            word2 = word2.replace("structures","structure");
            word2 = word2.replace("applications","application");
            Myset<PageEntry> entries = ipi.getPagesWhichContainWord(word2);
            if (entries.list.length() != 0){
                String[] word3 = {word2};
                Myset<SearchResult> srs = new Myset<>();
                for (int j = 0;j<entries.list.length();j++){
                    PageEntry currPage = entries.list.getElementByIndex(j);
                    float currRel = currPage.getRelevanceOfPage(word3, false, ipi);
                    SearchResult currSR = new SearchResult(currPage, currRel);
                    srs.addElement(currSR);
                }
                //System.out.println("before: "+srs.list.getElementByIndex(0).page.page);
                MySort<SearchResult> newMySort = new MySort<>();
                ArrayList<SearchResult> arraySearchResults =  newMySort.sortThisList(srs);
                //System.out.println("after: "+arraySearchResults.get(0).page.page);
                for (int k = 0;k < arraySearchResults.size();k++){
                    SearchResult currSR = arraySearchResults.get(k);
                    //System.out.println("Page Name: "+currSR.page.page+" Relevance: "+currSR.relevance);
                    System.out.print(currSR.page.page);                
                    if (k != arraySearchResults.size()-1){
                        System.out.print(", ");
                    }else{
                        System.out.println("");
                    }
                }
            } else{
                System.out.println("No webpage contains word "+word);
            }
            
            //System.out.println(entries.list.length());
            /*if(entries.list.length()!= 0){
                for (int i = 0 ; i< entries.list.length();i++){
                    System.out.print(entries.list.getElementByIndex(i).page);
                    if (i != entries.list.length()-1){
                        System.out.print(", ");
                    }
                    else{
                        System.out.print("\n");
                    }
                }
            }else{
                System.out.println("No webpage contains word "+word);
            }*/
        }else if(message[0].equals("queryFindPositionsOfWordInAPage")){
            String word = message[1];
            String page = message[2];
            boolean pCheck = true;
            boolean pCheck2 = false;
            Myset<PageEntry> entries = ipi.entries;
            for (int i = 0; i< entries.list.length();i++){
                PageEntry currPage = entries.list.getElementByIndex(i);
                if (page.equals(currPage.page)){
                    pCheck2 = true;
                    PageIndex currIndex = currPage.getPageIndex();
                    MyLinkedList<WordEntry> words = currIndex.getWordEntries();
                    for (int j =0;j<words.length();j++){
                        WordEntry currWord = words.getElementByIndex(j);
                        if(word.equals(currWord.word)){
                            pCheck = false;
                            MyLinkedList<Position> allPos = currWord.getAllPositionsForThisWord();
                            for (int l =0;l<allPos.length();l++){
                                Position currPos =  allPos.getElementByIndex(l);
                                System.out.print(currPos.i);
                                if (l!= allPos.length()-1){
                                    System.out.print(", ");
                                } else {
                                    System.out.print("\n");
                                }
                            }
                        } else{
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            }
            if (pCheck2 == false){
                System.out.println("Webpage "+ page+" doesn't exist.");
            }
            else if (pCheck == true){
                System.out.println("Webpage "+page+" does not contain word "+word);
            }
        } else if(message[0].equals("queryFindPagesWhichContainAllWords")){
            System.out.print(message[0]+": ");
            String[] words = Arrays.copyOfRange(message,1,message.length);
            Myset<PageEntry> pages = ipi.getPagesWhichContainWord(words[0]);
            Myset<SearchResult> srs = new Myset<>();
            for (int i = 1; i< words.length;i++){
                //System.out.println(ipi.getPagesWhichContainWord(words[i]).list.length());
                pages = pages.intersection(ipi.getPagesWhichContainWord(words[i]));
            }
            for (int j = 0;j<pages.list.length();j++){
                PageEntry currPage = pages.list.getElementByIndex(j);
                float currRel = currPage.getRelevanceOfPage(words, false, ipi);
                //System.out.println("lolol"+ currRel);
                SearchResult currSR = new SearchResult(currPage, currRel);
                srs.addElement(currSR);
            }
            //System.out.println("before: "+srs.list.getElementByIndex(0).page.page);
            MySort<SearchResult> newMySort = new MySort<>();
            ArrayList<SearchResult> arraySearchResults =  newMySort.sortThisList(srs);
            //System.out.println("after: "+arraySearchResults.get(0).page.page);
            for (int k = 0;k < arraySearchResults.size();k++){
                SearchResult currSR = arraySearchResults.get(k);
                //System.out.println("Page Name: "+currSR.page.page+" Relevance: "+currSR.relevance);
                System.out.print(currSR.page.page);                
                if (k != arraySearchResults.size()-1){
                    System.out.print(", ");
                }else{
                    System.out.println("");
                }
            }
            //System.out.println(InvertedPageIndex.wordwisePageList);

        } else if(message[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
            System.out.print(message[0]+": ");            
            String[] words = Arrays.copyOfRange(message,1,message.length);
            Myset<SearchResult> srs = new Myset<>();
            Myset<PageEntry> pages = ipi.getPagesWhichContainWord(words[0]);
            for (int i = 1; i< words.length;i++){
                pages = pages.union(ipi.getPagesWhichContainWord(words[i]));
            }
            //System.out.println(pages.list.length());
            for (int j = 0;j<pages.list.length();j++){
                PageEntry currPage = pages.list.getElementByIndex(j);
                float currRel = currPage.getRelevanceOfPage(words, false, ipi);
                //System.out.println("lolol"+ currRel);
                SearchResult currSR = new SearchResult(currPage, currRel);
                srs.addElement(currSR);
            }
            //System.out.println("before: "+srs.list.getElementByIndex(0).page.page);
            MySort<SearchResult> newMySort = new MySort<>();
            ArrayList<SearchResult> arraySearchResults =  newMySort.sortThisList(srs);
            //System.out.println("after: "+arraySearchResults.get(0).page.page);
            for (int k = 0;k < arraySearchResults.size();k++){
                SearchResult currSR = arraySearchResults.get(k);
                //System.out.println("Page Name: "+currSR.page.page+" Relevance: "+currSR.relevance);
                System.out.print(currSR.page.page);                
                if (k != arraySearchResults.size()-1){
                    System.out.print(", ");
                } else{
                    System.out.println("");
                }
            }
        
        } else if(message[0].equals("queryFindPagesWhichContainPhrase")){
            System.out.print(message[0]+": ");            
            String[] words = Arrays.copyOfRange(message,1,message.length);
            Myset<SearchResult> srs = new Myset<>();
            Myset<PageEntry> pages = ipi.getPagesWhichContainPhrase(words);
            for (int j = 0;j<pages.list.length();j++){
                PageEntry currPage = pages.list.getElementByIndex(j);
                float currRel = currPage.getRelevanceOfPage(words, true, ipi);
                //System.out.println("lolol"+ currRel);
                SearchResult currSR = new SearchResult(currPage, currRel);
                srs.addElement(currSR);
            }
            MySort<SearchResult> newMySort = new MySort<>();
            ArrayList<SearchResult> arraySearchResults =  newMySort.sortThisList(srs);
            for (int k = 0;k < arraySearchResults.size();k++){
                SearchResult currSR = arraySearchResults.get(k);
                //System.out.println("Page Name: "+currSR.page.page+" Relevance: "+currSR.relevance);
                System.out.print(currSR.page.page);
                if (k != arraySearchResults.size()-1){
                    System.out.print(", ");
                } else{
                    System.out.println("");
                }
            }
        } else {
            System.out.println("Wrong Query Message");
        }
    }
    public static void main(String[] args) {
        SearchEngine s =new SearchEngine();
        s.performAction("addPage stack_datastructure_wiki");
        s.performAction("queryFindPagesWhichContainWord delhi");
        s.performAction("queryFindPagesWhichContainWord stack");
        s.performAction("queryFindPagesWhichContainWord wikipedia");
        s.performAction("queryFindPositionsOfWordInAPage magazines stack_datastructure_wiki");
        s.performAction("queryFindPagesWhichContainWord allain");
        s.performAction("addPage stack_cprogramming");
        s.performAction("queryFindPagesWhichContainWord allain");
        s.performAction("queryFindPagesWhichContainWord C");
        s.performAction("queryFindPagesWhichContainWord C++");
        s.performAction("addPage stack_oracle");
        s.performAction("queryFindPagesWhichContainWord jdk");
        s.performAction("addPage stackoverflow");
        s.performAction("queryFindPagesWhichContainWord function");
        s.performAction("addPage stacklighting");
        s.performAction("addPage stackmagazine");
        s.performAction("queryFindPagesWhichContainWord magazines");
        s.performAction("queryFindPagesWhichContainAllWords stack function only" );
        //System.out.println("Done!");
    }
}
/*
addPage stack_datastructure_wiki
queryFindPagesWhichContainWord delhi
queryFindPagesWhichContainWord stack
queryFindPagesWhichContainWord wikipedia
queryFindPositionsOfWordInAPage magazines stack_datastructure_wiki
queryFindPagesWhichContainWord allain
addPage stack_cprogramming
queryFindPagesWhichContainWord allain
queryFindPagesWhichContainWord C
queryFindPagesWhichContainWord C++
addPage stack_oracle
queryFindPagesWhichContainWord jdk
addPage stackoverflow
queryFindPagesWhichContainWord function
addPage stacklighting
addPage stackmagazine
queryFindPagesWhichContainWord magazines*/