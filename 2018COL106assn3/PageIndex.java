//This stores the unique wordEntries for 1 webpage 

public class PageIndex{
    MyLinkedList<WordEntry> wordlist = new MyLinkedList<>(); //This will contain all those WEntries for the current document
    public void addPositionForWord(String str, Position p) {
        for (int i = 0; i< wordlist.length();i++){
            WordEntry currEle = wordlist.getElementByIndex (i);
            if (currEle.word == str){
                currEle.addPosition(p);
                break;
            } else {
                continue;
            }
        }
        WordEntry newWord = new WordEntry(str);
        newWord.addPosition(p); 
        wordlist.addElement(newWord);
    }
    public MyLinkedList<WordEntry> getWordEntries() {
        return wordlist;
    }
}