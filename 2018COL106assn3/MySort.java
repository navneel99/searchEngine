import java.util.ArrayList;
import java.util.Collections;
public class MySort<Sortable extends Comparable<Sortable>>{
    public ArrayList<Sortable> sortThisList(Myset<Sortable> listOfSortableEntries){
        ArrayList<Sortable> sortedList = new ArrayList<>();
        //Sortable oldEle = listOfSortableEntries.list.getElementByIndex(0);        
        for(int i = 0; i< listOfSortableEntries.list.length();i++){
            Sortable currEle = listOfSortableEntries.list.getElementByIndex(i);
            sortedList.add(currEle);
        }
        Collections.sort(sortedList);
        return sortedList;
    }
}