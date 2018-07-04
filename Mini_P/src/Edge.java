
public class Edge {

    final private Vertex source;
    final private Vertex destination;
    final private int weight;

    public Edge (Vertex source, Vertex destination, int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }

    //getters


    public Vertex getSource(){
        return this.source;
    }

    public Vertex getDestination(){
        return this.destination;
    }

    public int getWeight(){
        return this.weight;
    }

    public String toString(){
        return new String ("(" + this.source + ", " + this.destination + ", " +this.weight + ")" );
    }

}
