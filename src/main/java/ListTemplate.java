public class ListTemplate {


    private Node head;
    private  int size=0;

    /**
     * default constructor
     */
    public ListTemplate(){
        this.head=null;
    }
    /**
     * constructor with data
     */
    public ListTemplate(Var e){
        this.head=new Node();
        head.setData(e);
        size++;
    }

    /**
     *
     * @return
     * size of list
     */
    public int size(){
        return this.size;
    }

    /**
     * check for emptiness
     * @return
     * 1 if empty
     * 0 if not empty
     */
    public boolean isEmpty(){
        return size()==0;
    }

    /**
     * print list on one line
     */
    public void print(){
        Node node= head;
        while(node!=null){
            System.out.print(node.getData()+" ");
            node=node.getNext();
        }
        System.out.println();
    }

    /**
     * Check lists on equality
     * @param list
     * @return
     * 0 if not equal
     * 1 if equal
     */
    public boolean Equals(ListTemplate list){
        if(isEmpty()&&list.isEmpty())
            return true;
        else {
            Node node= head;
            Node node2=list.head;
            while(node!=null&&node2!=null){
                if(!node.equals(node2.getData()))
                    return false;
                node=node.getNext();
                node2=node2.getNext();
            }
            if(node==null&node2==null)
                return true;
            return false;
        }
    }
    private Node getHead(){
        return head;
    }
    //добавление элемента

    /**
     * concatenates two lists into one
     * @param list
     * list appended to the end;
     */
    public void addList(ListTemplate list){
        if(isEmpty()){
            head=list.getHead();
        }
        else{
            Node tail= head;
            while(tail.getNext()!=null)
            {
                tail=tail.getNext();
            }
            tail.setNext(list.getHead());
        }
        size+=list.size;
    }

    /**
     * add elem to the beginning
     * @param e
     * added element
     */
    public void addToHead(Var e){ //добавление в начало
        Node node= new Node(e);
        node.setNext(head);
        head=node;
        size++;
    }

    /**
     * add elem after another elem
     * @param elem
     * elem after which will be added
     * @param e
     * added element
     */
    public void addAfterElem(Var elem,Var e){ //добавление после определенного элемента
        Node node= new Node(e);
        Node ptr = head;
        if (ptr.equals(elem)) {
            node.setNext(ptr.getNext());
            ptr.setNext(node);
            size++;
        }
        else {
            while (ptr.getNext() != null) {
                if (ptr.equals(elem)) {
                    node.setNext(ptr.getNext());
                    ptr.setNext(node);
                    size++;
                    break;
                }
                ptr = ptr.getNext();
            }
        }
    }

    /**
     * add elem to end
     * @param e
     * added element
     */
    public void addToTail(Var e){ //добавление в конец
        Node tail = head;
        Node node= new Node(e);
        if(isEmpty()){
            head=node;
        }
        else{
            while(tail.getNext()!=null)
            {
                tail=tail.getNext();
            }
            tail.setNext(node);
        }
        size++;
    }

    /**
     * add elem with index
     * @param index
     *
     * @param e
     * added element
     */
    public void addByIndex(int index, Var e){ //добавление по индексу
        index--;
        if(index==0){
            addToHead(e);
        }
        else if(index==size){
            addToTail(e);
        }
        else if(index>0 && index<size){
            Node node= new Node(e);
            Node ptr=getByIndex(index-1);
            node.setNext(ptr.getNext());
            ptr.setNext(node);
            size++;
        }
    }
    //извлечение элемента


    private Node getByIndex(int index) {
        Node node = null;
        if (!isEmpty() && (index >= 0 && index < size)) {
            node = head;
            for(int i=0; i<index; i++){
                node = node.getNext();
            }
        }
        return node;
    }
    /**
     *
     * @param index
     * element index (start from 0)
     * @return elem type E
     */
    public Var get(int index) {  //извлечение по индексу
        index--;
        Var element;
        if (index >= 0 && index < size) {
            element = getByIndex(index).getData();
        } else throw new IndexOutOfBoundsException();
        return element;
    }
    //удаление элемента

    /**
     * delete elem by index
     * @param index
     * index of elem (begin with 0)
     */
    public void deleteByIndex(int index){ //удаление по индексу
        index--;
        if(index==0){
            deleteFromHead();
        }
        if(index>0 && index<size){
            Node node = getByIndex(index-1);
            node.setNext(node.getNext().getNext());
            size--;
        }
    }

    /**
     * delete element from the beginning
     */
    public void deleteFromHead(){ //удаление из начала
        Node node = head;
        head=head.getNext();
        node.setNext(null);
        node=null;
        size--;
    }

    /**
     * delete element from the end
     */
    public void deleteFromTail(){ //удаление последнего
        Node node = head;
        while(node.getNext().getNext()!=null){
            node=node.getNext();
        }
        node.setNext(null);
    }

    /**
     * delete all elem
     */
    public void clear(){
        while(head!=null){
            deleteFromHead();
        }
    }

    /**
     * delete element
     * @param e
     * the elem you want to delete
     */
    public void deleteElem(Var e){ //удаление по элементу
        Node node = head;
        if(node.equals(e)){
            deleteFromHead();
            size--;
        }
        else {
            while (node.getNext() != null) {
                if (node.getNext().equals(e)) {
                    node.setNext(node.getNext().getNext());
                    size--;
                    break;
                }
                node=node.getNext();
            }
        }
    }

    public boolean Search(String s){
        Node node = head;
        while(node!=null){
            if (node.getData().getP().equals(s))
                return true;
            node=node.getNext();
        }
        return false;
    }
    public double get(String s){
        Node node = head;
        double x=0;
        while(node!=null){
            if (node.getData().getP().equals(s))
                x= node.getData().getData();
            node=node.getNext();
        }
        return x;
    }
}
