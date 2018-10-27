//For each word, we have a (Linked)List of Tuples that appear on various webpages. Interesting class.
//It stores all the positions in all the webpages.

public class WordEntry{
    String word;
    MyLinkedList<Position> poslist = new MyLinkedList<>();
    AVL avl = new AVL();
    public WordEntry(String word){
        this.word = word;
        avl.root  = null;
    }
    public boolean positionExists(Position position){
        for (int i = 0; i<poslist.length(); i++){
            Position nowPos = poslist.getElementByIndex(i);
            if (nowPos.page.page.equals(position.page.page)){
                if (nowPos.i == position.i){
                    return true;
                }
            }
        }
        return false;
    }
    public void addPosition(Position position) {
                poslist.addElement(position);
                avl.root = avl.insert(avl.root, position);
    }
    public void addPositions(MyLinkedList<Position> positions) {
        for (int i =0; i< positions.length();i++){
            boolean tVal = true;
            Position currEle = positions.getElementByIndex(i);
            for (int l =0; l< poslist.length();l++){
                if (currEle.page.page.equals(poslist.getElementByIndex(l).page.page) && currEle.i == (poslist.getElementByIndex(l).i)){
                    tVal =false;
                    break;
                }
            }
            if (tVal){
                poslist.addElement(currEle); 
                avl.root = avl.insert(avl.root, currEle);
            }
        }
    }
    public MyLinkedList<Position> getAllPositionsForThisWord(){
        return poslist;
    }
    static float getTotalNumberOfWords(PageEntry p){
        int allWords = 0;
        int totWords = p.index.wordlist.length();
        for (int i = 0; i< totWords; i++){
            WordEntry currWE = p.index.wordlist.getElementByIndex(i);
            for (int k =0; k< currWE.poslist.length();k++){
                Position currPos = currWE.poslist.getElementByIndex(k);
                if (currPos.page.page.equals(p.page)){
                    allWords++;
                }
            }
            //allWords+= currWE.poslist.length();
        }
        //System.out.println("Words in page "+p.page+" is "+ allWords);
        
        return allWords;
    }
    public float getTermFrequency(PageEntry p){
        //float allWords = getTotalNumberOfWords(p);
        float all = 0;
        all = WordEntry.getTotalNumberOfWords(p);
        float occInPage = 0;
        for (int i = 0;i<poslist.length();i++){
            Position currEle = poslist.getElementByIndex(i);
            if (p.page.equals(currEle.page.page)){
                occInPage++;
            }
        }
        float tf = occInPage/all;
        return tf;
    }
    public static void main(String[] args) {
        InvertedPageIndex ipi = new InvertedPageIndex();
        WordEntry a = new WordEntry("lol");
        PageEntry p = new PageEntry("stackoverflow");
        PageEntry q = new PageEntry("stackmagazine");
        WordEntry w = p.index.wordlist.getElementByIndex(0);
        ipi.addPage(p);
        ipi.addPage(q);


        MyLinkedList<WordEntry> check =  ipi.mht.mainArray[ipi.mht.pubHashIndex(w.word)];
        for (int chk = 0; chk< check.length();chk++){
            //System.out.println(check.getElementByIndex(chk).word);
            if ((check.getElementByIndex(chk).word).equals(w.word)){
                WordEntry needed = w;
                System.out.println(needed.word);
                System.out.println( needed.avl.doesPositionExist(q, 1, w.avl.root));
            }
        }
        //System.out.println(check.length());
        //System.out.println(w.poslist.length());
        //System.out.println("Check");
        w.avl.preOrder(w.avl.root);
        //System.out.println("preOrder done");
        //System.out.println(w.word);
        //System.out.println(InvertedPageIndex.wordwisePageList);
    }
}