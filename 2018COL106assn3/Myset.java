public class Myset<T>{
    MyLinkedList<T> list = new MyLinkedList<>();
    //No constructor added. Will do if necessary.
    public void addElement(T element) {
        list.addElement(element);
    }
    public Myset<T> union(Myset<T> otherSet){  //to check
        int size1 = this.list.length();
        int size2 = otherSet.list.length();
        int tempSize;
        Myset<T> tempBig ;
        Myset<T> tempSmall ;

        if (size1 <= size2){
            tempBig = otherSet;
            tempSize = size1;
            tempSmall = this;
        } else {
            tempBig = this;
            tempSize = size2;
            tempSmall = otherSet;
        }
        for(int i = 0;i<tempSize;i++){
            T curr = tempSmall.list.getElementByIndex(i);
            if (tempBig.list.checkElement(curr) == false){
                tempBig.addElement(curr);
            } else {
                continue;
            }
        }
        return tempBig;
    }
    public Myset<T> intersection(Myset<T> otherSet){
        Myset<T> newSet = new Myset<>();
        int size1 = this.list.length();
        int size2 = otherSet.list.length();
        Myset<T> smallSet;
        Myset<T> bigSet;
        if (size1<=size2){
            smallSet = this;
            bigSet = otherSet;
        } else{
            smallSet = otherSet;
            bigSet = this;
        }
        for(int i = 0; i< smallSet.list.length(); i++){
            T currEle = smallSet.list.getElementByIndex(i);
            if(bigSet.list.checkElement(currEle)){
                newSet.list.addElement(currEle);
            } else {
                continue;
            }
        }
        return newSet;
    }
    public static void main(String[] args) {
        System.out.println("yay");
    }
}