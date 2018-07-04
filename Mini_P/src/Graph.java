
import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Graph(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<Edge>();
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void print(){
        System.out.println("vertices:");
        System.out.print("{ ");
        for (Vertex v:vertices){
            System.out.print(v + ", ");
        }
        System.out.println("}");

        System.out.println("edges:");
        System.out.print("{ ");
        for (Edge e:edges){
            System.out.print(e + ", ");
        }
        System.out.println("}");
        System.out.println();
    }

    public void initializeSingleSource(Vertex s){
        for (Vertex v:vertices){
            v.setDv(1000000);
        }
        s.setDv(0);
    }

    public void relax(Vertex source, Vertex destination, int weight){
        if(destination.getDv() > source.getDv() + weight && source.getDv()>=0 ){
            destination.setDv(source.getDv() + weight);
        }
    }

    public Vertex extractMin(ArrayList<Vertex> Q){
        int minDv=1000000;
        Vertex ans=null;
        for(Vertex v : Q){
            if (v.getDv() <= minDv){
                ans=v;
                minDv=v.getDv();
            }
        }
        Q.remove(ans);
        return ans;
    }

    public int Dijkstra(Vertex s, Vertex v){//lightest path from s to v
        initializeSingleSource(s);
        ArrayList<Vertex> Q = new ArrayList<Vertex>(this.getVertices());
        ArrayList<Vertex> S= new ArrayList<Vertex>();
        while (!Q.isEmpty()){
            Vertex u=extractMin(Q);
            S.add(u);
            for (Edge e:this.edges){
                if(e.getSource().equals(u) && !S.contains(e.getDestination())){
                    relax(u, e.getDestination(), e.getWeight());
                }
                else if(e.getDestination().equals(u) && !S.contains(e.getSource())){
                    relax(u, e.getSource(), e.getWeight());
                }
            }
        }
        return v.getDv();
    }

    public void bubbleSort(ArrayList<Edge> edges){
        //implementation of the well known algorithm for edge sorting
        boolean isSorted=false;
        Edge tmp;
        for (int bubble=0; !isSorted && bubble<edges.size()-1; bubble++){
            isSorted=true;
            for( int index=0; index<edges.size()-1-bubble; index++){
                if(edges.get(index).getWeight() > edges.get(index+1).getWeight()){
                    tmp=edges.get(index);
                    edges.set(index, edges.get(index+1));
                    edges.set(index+1, tmp);
                    isSorted=false;
                }
            }
        }
    }

    public Graph findSpanner(int r){
        bubbleSort(this.getEdges());
        Graph spanner=new Graph(this.getVertices());
        for(Edge e:this.getEdges()){
            int Puv=spanner.Dijkstra(e.getSource(),e.getDestination());
            if(r*e.getWeight() < Puv){
                spanner.getEdges().add(e);
            }
        }
        return spanner;
    }

}
