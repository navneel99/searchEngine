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
            //System.out.println(entries.list.length());
            if(entries.list.length()!= 0){
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
            }
        }else if(message[0].equals("queryFindPositionsOfWordInAPage")){
            String word = message[1];
            String page = message[2];
            boolean pCheck = true;
            Myset<PageEntry> entries = ipi.entries;
            for (int i = 0; i< entries.list.length();i++){
                PageEntry currPage = entries.list.getElementByIndex(i);
                if (page.equals(currPage.page)){
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
            if (pCheck == true){
                System.out.println("Webpage "+page+" does not contain word "+word);
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