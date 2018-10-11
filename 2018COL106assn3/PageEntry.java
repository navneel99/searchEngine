//A webpage
import java.util.*;
import java.io.File;
public class PageEntry{
    String page;
    PageIndex index = new PageIndex();

    public PageEntry(String pageName){
        this.createPageIndex(pageName);
        page = pageName;
    }

    public void createPageIndex(String pageName){
        try{
            File file = new File("webpages/"+ pageName); 
            Scanner sc = new Scanner(file);
            String wholeText = "";
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
            wholeText = wholeText.replace("     "," ");
            wholeText = wholeText.replace("   "," ");
            wholeText = wholeText.replace("  "," ");

            String[] wholeTextArray = wholeText.split(" ");
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
                }
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        /*System.out.println("Checking PageIndex for a page for duplicates: ");
        for (int i = 0; i <index.wordlist.length();i++){
            WordEntry curr = index.wordlist.getElementByIndex(i);
            System.out.println(curr.word);
        }*/
    }
    public PageIndex getPageIndex(){
        return index; //Return the list
    }
}