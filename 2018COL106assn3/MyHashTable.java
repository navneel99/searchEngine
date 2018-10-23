import java.util.*;
import java.io.*;
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
        boolean tVal =true;
        for (int i = 0; i< chain.length();i++){
            WordEntry currEle = chain.getElementByIndex(i);
            if(currEle.word == w.word){
                currEle.addPositions(w.getAllPositionsForThisWord());
                tVal = false;
                break;
            } else {
                continue;
            }

        }
        if (tVal){
            chain.addElement(w);
        }
    }
}

