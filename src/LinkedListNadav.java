import javax.xml.soap.Node;

/**
 * Created by Nadav H on 19/04/2017.
 */

/*
*
*   LinkedListNadav is a bi-directional list with references to the first and last elements of the list.
*   NodeNadav is a helper class for the elements of the list, it is NOT accessible to the user.
*
* */

public class LinkedListNadav<T> {

    private NodeNadav head;
    private NodeNadav tail;

    // constructor for a new empty list
    public LinkedListNadav(){
        setHead(null);
        setTail(null);
    }

    // constructor for a new list with first node
    public LinkedListNadav(T data){
        NodeNadav headNode = new NodeNadav(data);
        head = headNode;
    }

    public T getFirst(){
        try {
            return getFirstNode().getData();
        } catch (Exception e) {
            return null;
        }

    }

    public T getLast(){
        try {
            return getLastNode().getData();
        } catch (Exception e) {
            return null;
        }
    }

    public void insertFirst(T data){
        this.insertNodeFirst(new NodeNadav(data));
    }

    public void insertLast(T data){
        this.insertNodeLast(new NodeNadav(data));
    }

    public int getLength(){
        int length = 0;
        for (NodeNadav i = getFirstNode(); i != null; i.getNext()){
            length++;
        }
        return length;
    }

    public boolean isListEmpty(){
        return  (getLength() == 0);
    }

    public void deleteFirst(){
        int length = getLength();
        if (length > 0){
            if (length == 1){
                setHead(null);
            } else {
                NodeNadav secondNode = this.getLastNode();
                secondNode.setPrev(null);
                setHead(secondNode);
                // if the list was 2 elements long
                if (length == 2){
                    setTail(null);
                }
            }
        }
    }

    public void deleteLast(){
        int length = getLength();
        if (length > 0){
            if (length == 1){
                setHead(null);
            } else {
                NodeNadav secondLastNode = this.getLastNode().getPrev();
                secondLastNode.setNext(null);
                // if the list was 2 elements long
                if (length == 2){
                    setHead(secondLastNode);
                    setTail(null);
                } else {
                    setTail(secondLastNode);
                }
            }
        }
    }

    public boolean isElementInList(T element){
        if (findListElement(element) != null){
            return true;
        }
        return false;
    }

    public void deleteListElement(T element){
        NodeNadav elementNode = findListElement(element);
        if (elementNode != null){
            if (elementNode == getFirstNode()){
                deleteFirst();
            } else if (elementNode == getLastNode()){
                deleteLast();
            } else {
                NodeNadav afterNode = elementNode.getNext();
                NodeNadav beforeNode = elementNode.getPrev();
                beforeNode.setNext(afterNode);
                afterNode.setPrev(beforeNode);
            }
        }
    }

    // private functions

    private void setHead(NodeNadav newNode){
        head = newNode;
    }

    private void setTail(NodeNadav newNode){
        tail = newNode;
    }

    private NodeNadav getLastNode(){
        return tail;
    }

    private NodeNadav getFirstNode(){
        return head;
    }

    // used for public method insertLast
    private void insertNodeLast(NodeNadav newNode){
        NodeNadav oldLastNode = this.getLastNode();
        // if tail equals null
        if (oldLastNode == null){
            // if tail and head both equal null
            if (getFirstNode() == null){
                setHead(newNode);
            } // tail is null, head is not null
            else {
                setTail(newNode);
            }
        } // tail isn't null
        else {
            oldLastNode.setNext(newNode);
            newNode.setPrev(oldLastNode);
            setTail(newNode);
        }
    }

    // used for public method insertFirst
    private void insertNodeFirst(NodeNadav newNode){
        NodeNadav oldFirstNode = this.getFirstNode();
        // if head equals null
        if (oldFirstNode == null) {
            setHead(newNode);
        } else {
            newNode.setNext(oldFirstNode);
            oldFirstNode.setPrev(newNode);
            setHead(newNode);
        }
    }

    private NodeNadav findListElement(T element){
        if (isListEmpty()){
            return null;
        }
        for (NodeNadav i = getFirstNode(); i != null; i.getNext()){
            if (i.getData() == element){
                return i;
            }
        }
        return null;
    }

    private class NodeNadav {

        private T data;
        private NodeNadav next;
        private NodeNadav prev;

        public NodeNadav(T input){
            data = input;
            next = null;
            prev = null;
        }

        public NodeNadav getNext(){
            return next;
        }

        public NodeNadav getPrev(){
            return prev;
        }

        public void setNext(NodeNadav nextNode){
            next = nextNode;
        }

        public void setPrev(NodeNadav nextNode){
            prev = nextNode;
        }

        public T getData(){
            return data;
        }
    }
}
