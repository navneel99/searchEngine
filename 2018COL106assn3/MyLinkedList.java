public class MyLinkedList<T>{
    public Node<T> head;
    public MyLinkedList() {
        head = null;
    }
    public void addElement(T element) {   //works
        Node<T> currHead = head;
        if (currHead == null){
            head = new Node<T>(element);
        } else {
            Node<T> newHead = new Node<T>(element);
            newHead.next = currHead;
            head = newHead;
        }
    }
    public int length(){ //works
        Node<T> pos = head;
        int size = 0;
        while (pos != null){
            size+=1;
             pos = pos.next;
        }
        return size;
    }
    public boolean checkElement(T element){ //works
        Node<T> pos = head;
        while (pos != null){
            if (pos.data == element){
                return true;
            } else {
                pos = pos.next;
            }
        }
        return false;
    }
    public T getElementByIndex(int index){ //check for null pointer exception
        int curr = this.length()-1;
        Node<T> pos = head;
        while (curr != index){
            curr -=1;
            pos = pos.next;
        }
        return pos.get();
    }
    public void deleteElement(T element){ //Works
        Node<T> pos = head;
        if (pos.data == element){
            pos.data = null;
            this.head = pos.next;
        } else {
            while (pos.next != null){  //what if 1 element or the head element?
                if (pos.next.data == element){
                    if (pos.next.next == null){
                        pos.next.data = null;
                        pos.next = null;
                    } else {
                        pos.next.data = null; // The data becomes null
                        pos.next = pos.next.next; // The node skips next to directly link the next to next
                    }
                }
            }
        }
    }
    public T inOrderSuccessor(int index){
        int curr = this.length()-1;
        Node<T> pos = head;
        while (curr != index){
            curr -=1;
            pos = pos.next;
        }
        return pos.get();   
    }
    public static void main(String[] args) {
        //System.out.println("Lol");
        MyLinkedList <Integer> list = new MyLinkedList<>();
        //System.out.println(list.head.data);  null

        list.addElement(5);
        System.out.println(list.head.data);
        list.addElement(4);  //New Head
        System.out.println(list.checkElement(7));
        System.out.println(list.checkElement(5));
        System.out.println(list.getElementByIndex(0));
        


    }
}