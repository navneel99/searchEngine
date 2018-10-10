public class MyHashTable{
    MyLinkedList<WordEntry>[] mainArray = new MyLinkedList[26];
    public MyHashTable(){
        for (int i =0; i< mainArray.length;i++){
            MyLinkedList<WordEntry> obj = new MyLinkedList<>();
            mainArray[i] = obj;
        } 
    }
    
    //MyLinkedList<MyLinkedList<Integer>> mainList = new MyLinkedList<>();
    private int getHashIndex(String str) {
        char first = str.charAt(0);
        int ascii = (int) first;
        ascii =ascii%26;
        return ascii;
    }
    public void addPositionsForWord(WordEntry w) {
        String word = w.word;
        int index = this.getHashIndex(word);
        MyLinkedList<WordEntry> chain = mainArray[index];
        for (int i = 0; i< chain.length();i++){
            WordEntry currEle = chain.getElementByIndex(i);
            if(currEle.word == w.word){
                currEle.addPositions(w.getAllPositionsForThisWord());
                break;
            } else {
                continue;
            }

        }
        chain.addElement(w);
    }
    public static void main(String[] args) {
        System.out.println("yo");
        MyHashTable lol =new MyHashTable();
        WordEntry w1 = new WordEntry("Navneel");
        System.out.println(w1.word);
        lol.addPositionsForWord(w1);
        System.out.println(lol.mainArray.length);

    }
}

