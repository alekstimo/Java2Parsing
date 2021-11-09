public class Node {
    private Node next;
    private Var data;

    public Node(){ //конструктор по умолчанию для элемента
        this.next=null;

    }
    public Node(Var e){
        this.next=null;
        this.data=new Var(e.p,e.x);
    }

    public Node getNext(){ //получение указателя на следующий
        return next;
    }

    public void setNext(Node next){ //добавление после элемента
        this.next=next;
    }

    public Var getData(){
        return data;
    }
    public void setData(Var data){
        this.data.Add(data);
    }
    public boolean equals(Object obj) {
        return data.equals(obj);
    }
}
