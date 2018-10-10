//A webpage
import java.util.Scanner;
public class PageEntry{
    String pageName;
    PageIndex index;
    public PageEntry(String pageName){
        pageName = pageName;
        index = new PageIndex();
    }
    public PageIndex getPageIndex(){
        return index; //Return the list
    }
}