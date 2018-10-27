public class pageEntryAVL<T>{
    public Node<T> head;
    public pageEntryAVL() {
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

    public T inOrderSuccessor(int index){
        int curr = this.length()-1;
        Node<T> pos = head;
        while (curr != index){
            curr -=1;
            pos = pos.next;
        }
        return pos.get();   
    }
}