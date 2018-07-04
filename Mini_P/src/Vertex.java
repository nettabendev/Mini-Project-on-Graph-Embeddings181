/**
 *
 */

public class Vertex {

    final private int v;
    private int dv;

    public Vertex (int v){
        this.v=v;
        //this.dv=Integer.MAX_VALUE;
        this.dv=100000;
    }

    //gettrs

    public int getV(){
        return this.v;
    }

    public int getDv(){
        return this.dv;
    }

    public void setDv(int newDv){
        this.dv=newDv;
    }

    public String toString(){
        return String.valueOf(this.getV());
    }

}
