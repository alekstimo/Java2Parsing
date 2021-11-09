public class Var {
    public String p;
    public double x;
    public Var(){
        p="";
        x=0;
    }
    public Var(String a,double b){
        p=a;
        x=b;
    }
    public void Add (Var e){
        p=e.p;
        x=e.x;
    }
    public double getData(){
        return x;
    }
    public String getP(){
        return p;
    }
}
