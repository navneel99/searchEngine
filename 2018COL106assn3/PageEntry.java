//A webpage
import java.util.*;
import java.io.File;
public class PageEntry{
    String page;
    PageIndex index = new PageIndex();
    String wholeText;
    String[] wholeTextArrayWithStopWords;

    public PageEntry(String pageName){
        this.createPageIndex(pageName);
        page = pageName;
    }

    public void createPageIndex(String pageName){
        try{
            File file = new File("webpages/"+ pageName); 
            Scanner sc = new Scanner(file);
            wholeText = "";
             while (sc.hasNextLine()){
                String temp = sc.nextLine();
                String[] pMarks = {"{","}","[","]","(",")","<",">","=",".",",",";","'","\"","\\?","#","!","-",":"};
                for(int i = 0; i< pMarks.length;i++){
                        String curr = pMarks[i];
                        temp = temp.replace(curr," ");
                }
                temp = temp.toLowerCase();
                temp = temp.replace("stacks","stack");
                temp = temp.replace("structures","structure");
                temp = temp.replace("applications","application");        
                wholeText+=temp+" ";
            }
            //wholeText = wholeText.replace("     "," ");
            //wholeText = wholeText.replace("   "," ");
            //wholeText = wholeText.replace("  "," ");
            wholeText = wholeText.replaceAll("^ +| +$|( )+", "$1");
            
            String[] wholeTextArray = wholeText.split(" ");
            wholeTextArrayWithStopWords = wholeTextArray;
            for(int i = 0; i<wholeTextArray.length;i++){
                String[] stopWords = {"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};
                String currEle = wholeTextArray[i];
                boolean check = true;
                for (int k =0;k<stopWords.length;k++){
                    if (currEle.equals(stopWords[k])){
                        check = false;
                    }else{
                        continue;
                    }
                }
                if(check == true){    
                    Position pos = new Position(this,i+1);
                    //System.out.println(pos.page);
                    index.addPositionForWord(currEle,pos);
                    //System.out.println("Check");
                }
                
            }
        } catch(Exception e){
            //e.printStackTrace();
            System.out.println("File "+ pageName+" not found.");
        }
    }
    public PageIndex getPageIndex(){
        return index; //Return the list
    }
    public MyLinkedList<Position> getPositionsForWord(String str){
        MyLinkedList<WordEntry> currPageIndex = this.index.getWordEntries();
        for (int k = 0; k< currPageIndex.length();k++){
            WordEntry currEle = currPageIndex.getElementByIndex(k); 
            if ((currEle.word).equals(str)){
                return currEle.getAllPositionsForThisWord();
            } else {
                continue;
            }
        }
        return null;
    }
    public int getNumberOfTimesPhraseOccurs(String str[]){
        //MyLinkedList<Position> start = this.getPositionsForWord(str[0]);
        String starter = str[0];
        String[] coolKid = wholeText.split(" ");
        int numberOfPhrases = 0;
        for (int i = 0; i< coolKid.length;i++){
            String currWord = coolKid[i];
            boolean check =true;
            if (currWord.equals(starter)){
                for (int j =1; j<str.length;j++){
                    String wordToMatch = str[j];
                    String succWord = coolKid[i+j];
                    if (wordToMatch.equals(succWord)){
                        continue;
                    }else{
                        check = false;
                        break;
                    }
                }
                if (check == true){
                    numberOfPhrases++;
                }
            }
        }
        return numberOfPhrases;
    }

    public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase, InvertedPageIndex ipi){
        float rel = 0;
        /*if(this.page.equals("stack_cprogramming")){
            System.out.println("Final check: "+WordEntry.getTotalNumberOfWords(this));
        }*/
        if (doTheseWordsRepresentAPhrase){
            //Phrase Query
            int m = this.getNumberOfTimesPhraseOccurs(str);
            float wp = WordEntry.getTotalNumberOfWords(this);
            float denominator = wp - (m*(str.length - 1));
            double multiplication = Math.log(ipi.entries.list.length())/Math.log(ipi.getPagesWhichContainPhrase(str).list.length());
            rel = (m/denominator)* (float)multiplication;
            return rel;
        } else {
            for(int i = 0;i<str.length;i++){
                String currWord = str[i];
                //System.out.println("Word: "+ currWord);
                for (int j = 0; j<index.wordlist.length();j++){
                    WordEntry currWE = index.wordlist.getElementByIndex(j);
                    if(currWE.word.equals(currWord)){
                        float tf = currWE.getTermFrequency(this);
                        //float idf = InvertedPageIndex.inverseDocumentFrequency(currWE.word);
                        double idf = Math.log(ipi.entries.list.length())/Math.log(ipi.getPagesWhichContainWord(currWord).list.length());
                        rel = rel + (tf * (float)idf);
                        break;
                    } else{
                        continue;
                    }
                }
            }
        }
        return rel;
    }

}