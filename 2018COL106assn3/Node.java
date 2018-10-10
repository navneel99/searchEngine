import java.util.*;
public class Node<T>{
    T data;
    Node next;
    public Node(T info) {
        this.data = info;
        next = null;
    }
    public T get(){
        return this.data;
    }
}