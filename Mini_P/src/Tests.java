
import java.util.ArrayList;



//import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class Tests {

    /**
     * graph generator
     * @param numOfVertices
     * @param p
     * @return new Graph(vertices, edges)
     */
    public static Graph graphGenerator (int numOfVertices, double p) {
        ArrayList<Vertex> vertices=new ArrayList<Vertex>();
        for(int i=0; i<numOfVertices; i++){
            vertices.add(new Vertex (i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        // building edges randomly
        for(int i=0; i<numOfVertices;i++){
            Vertex v = vertices.get(i);
            for(int j=i+1; j<numOfVertices;j++){
                Vertex u = vertices.get(j);
                double random=Math.random();
                if(random <= p){
                    int w=(int)Math.ceil(Math.random()*100);
                    edges.add(new Edge(v,u,w));
                }
            }
        }
        return new Graph(vertices, edges);
    }


    /**
     * @param spanner
     * @param stretchFactor
     * @return ture iff numOfEdges < upper bound= n*(n^(1/t))
     * this is from the artical
     */
    public static boolean sizeChecker (Graph spanner, int stretchFactor){
        int numOfVertices=spanner.getVertices().size();
        int numOfEdges=spanner.getEdges().size();
        double power=(double)1/(double)stretchFactor;
        return (numOfEdges < numOfVertices * Math.ceil(Math.pow(numOfVertices, power)));
    }
    /**
     * @param G
     * @param spanner
     * @param stretchFactor
     * @return true iff dist in spanner < dist in G * stretchFactor for every edge in G
     */
    public static boolean lengthChecker (Graph G, Graph spanner, int stretchFactor){
        int numOfVertices=G.getVertices().size();
        for( Vertex s: G.getVertices()){
            //for(int i=0; i<numOfVertices; i++){
            //  Vertex s=G.getVertices().get(i);
            for(Vertex v: G.getVertices()){
                //for(int j=0; j<numOfVertices; j++){
                //  Vertex v=G.getVertices().get(j);
                if(G.Dijkstra(s, v) * stretchFactor < spanner.Dijkstra(s, v)){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * @param G
     * @param spanner
     * @param stretchFactor
     * @return true iff spanner satisfyizes the term for both length and size virtues
     */
    public static boolean spannerChecker (Graph G, Graph spanner, int stretchFactor){
        return (sizeChecker(spanner, stretchFactor) && lengthChecker(G, spanner, stretchFactor));
    }


    public static void printMatrix(int [][] matrix){
        for(int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix.length; j++){
                System.out.print(matrix[i][j]+ ", ");
            }
            System.out.println();
        }
    }

    //Experiments

    /**
     *
     * @param sampleSize - this is the number of graph we will be creating
     * @param numOfVertices
     *  t1, t2, t3 = this are the stretch factors
     * generates sampleSize graphs with numOfVertices vertices.
     * for three different stretch factors the algorithm prints:
     * 	the appropriate upper-bound
     * 	the number of edges in the spanner.
     */
    public static void edgesAspectExperiment (int sampleSize, int numOfVertices, int t1, int t2, int t3, String fileName){
        Graph[] graphs = new Graph[sampleSize] ;
        Graph[] spanners_s1 = new Graph[sampleSize];
        Graph[] spanners_s2 = new Graph[sampleSize];
        Graph[] spanners_s3 = new Graph[sampleSize];

        for(int i=0; i<sampleSize; i++){
            double p=(double)i/(double)sampleSize;
            graphs[i]=graphGenerator(numOfVertices,p);

        }
        double upperBoundt1= numOfVertices * Math.ceil(Math.pow(numOfVertices,(double)1/(double)t1 ));
        double upperBoundt2= numOfVertices * Math.ceil(Math.pow(numOfVertices,(double)1/(double)t2 ));
        double upperBoundt3= numOfVertices * Math.ceil(Math.pow(numOfVertices,(double)1/(double)t3 ));
        System.out.println("The upper bound for stretch factor t1 = "+t1 +" is: "+(int)upperBoundt1);
        System.out.println("The upper bound for stretch factor t2 = "+t2 +" is: "+(int)upperBoundt2);
        System.out.println("The upper bound for stretch factor t3 = "+t3 +" is: "+(int)upperBoundt3);
        System.out.println();

        for(int i=0; i<sampleSize; i++){
            spanners_s1[i]=graphs[i].findSpanner((2*t1)-1);
            spanners_s2[i]=graphs[i].findSpanner((2*t2)-1);
            spanners_s3[i]=graphs[i].findSpanner((2*t3)-1);
        }

        int[] edges_G = new int[sampleSize];
        int[] edges_s1 = new int[sampleSize];
        int[] edges_s2 = new int[sampleSize];
        int[] edges_s3 = new int[sampleSize];
        for(int i =0; i<sampleSize; i++ ){
            edges_G[i] = graphs[i].getEdges().size();
            edges_s1[i] = spanners_s1[i].getEdges().size();
            edges_s2[i] = spanners_s2[i].getEdges().size();
            edges_s3[i] = spanners_s3[i].getEdges().size();
        }

        CsvFileWriter.writeCsvFileEdgesAspect(fileName, sampleSize, edges_G, edges_s1,edges_s2, edges_s3);



    }
    /**
     * tests the average differential number of edges denoted by sigma.
     * @param sampleSize
     * @param numOfVertices
     * @param p the probability for an edge to exist
     * @param maxStretchFactor
     */
    public static void averageEdgeDifferentialAspectExperiment (int sampleSize, int numOfVertices, double p, int maxStretchFactor, String fileName){
        Graph[] graphs = new Graph[sampleSize] ;
        Graph[] spanners = new Graph[sampleSize];
        double[] diff = new double[maxStretchFactor +1];
        double[] ave = new double [maxStretchFactor +1];
        Double avgSize, differential;
        for(int j=1; j<=maxStretchFactor; j++){
            avgSize=0.0;
            double upperBound= numOfVertices * Math.ceil(Math.pow(numOfVertices,(double)1/(double)j));
            for(int i=0; i<sampleSize; i++){
                graphs[i]=graphGenerator(numOfVertices,p);
                spanners[i] = graphs[i].findSpanner((2*j)-1);
                avgSize = avgSize + spanners[i].getEdges().size();
            }

            avgSize = avgSize / sampleSize;
            ave[j] = avgSize;
            diff[j] = upperBound - avgSize;


        }

        CsvFileWriter.writeAverageEdgeDifferentialAspectExperiment(fileName ,ave, diff);
    }

    //;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;test weights;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



    // for prim's algorithm
    public static ArrayList<Edge> crossSection (ArrayList<Edge> edges, ArrayList<Vertex> S){
        ArrayList<Edge> ans = new ArrayList<Edge>();
        Edge e;
        for (int i=0; i<edges.size(); i++){
            e = edges.get(i);
            if( (S.contains(e.getSource()) && !S.contains(e.getDestination())) ||
                    (!S.contains(e.getSource()) && S.contains(e.getDestination())) ){
                ans.add(e);
            }
        }
        bubbleSort(ans);
        return ans;

    }


    public static Graph prim(Graph G){
        ArrayList<Vertex> S = new ArrayList<Vertex>();
        S.add(G.getVertices().get(0));
        ArrayList<Edge> B = new ArrayList<Edge>();

        Graph MST = new Graph(G.getVertices(),B);
        ArrayList<Edge> edges =  G.getEdges();
        int numOfVertices = G.getVertices().size();

        while(B.size() < numOfVertices-1){
            ArrayList<Edge> ans = crossSection(edges, S);
            if( ans.isEmpty() ){
                System.out.println("G is not connected");
                break;
            }
            Edge e= ans.get(0);
            edges.remove(e);
            B.add(e);
            if(S.contains(e.getSource()) && !S.contains(e.getDestination())){
                S.add(e.getDestination());
            }
            else{
                S.add(e.getSource());
            }
        }
        return MST;
    }


    public static int findWeight (Graph G){
        int totalWeight=0;
        ArrayList<Edge> edges = G.getEdges();
        for (int i=0; i<edges.size(); i++){
            Edge e = edges.get(i);
            totalWeight += e.getWeight();
        }
        return totalWeight;
    }


    public static void weightsAspectExpirement(int sampleSize, int numOfVertices, double p, int maxStretchFactor, String fileName){
        Graph[] graphs = new Graph[sampleSize];
        Graph[] MSTs = new Graph[sampleSize];
        Graph[] spanners = new Graph[sampleSize];
        double[] aveWeightInSpanners = new double[maxStretchFactor];
        double[] uperBoundPerStretch = new double [maxStretchFactor];
        System.out.println("average weight in G in the sample:");
        double avgWeight=0;
        for(int i=0; i<sampleSize; i++){
            graphs[i]=graphGenerator(numOfVertices,p);
            avgWeight += findWeight(graphs[i]);
        }
        //average weight of a graph g in the sample
        System.out.println(avgWeight / sampleSize);

        System.out.println("average weight in MST(G) in the sample:");
        avgWeight=0;
        for(int i=0; i<sampleSize; i++){
            MSTs[i]=prim(graphs[i]);
            avgWeight += findWeight(MSTs[i]);
        }
        //average weight of a graph mst g in the sample
        avgWeight = avgWeight / sampleSize;
        System.out.println(avgWeight);


        for (int k=1; k<=maxStretchFactor; k++){
            uperBoundPerStretch[k-1] = avgWeight*(1+(numOfVertices/(2*k)));
         }

        for (int k=1; k<=maxStretchFactor; k++){
            avgWeight = 0;
            for (int i=0; i<sampleSize; i++){
                spanners[i]=graphs[i].findSpanner((2*k)-1);
                avgWeight += findWeight(spanners[i]);
            }
            aveWeightInSpanners[k-1]=avgWeight / sampleSize;
        }
        CsvFileWriter.writeWeightsAspectExpirement(uperBoundPerStretch,aveWeightInSpanners, fileName );
    }

    public static void bubbleSort(ArrayList<Edge> arr){
        boolean isSorted=false;
        Edge tmp;
        for (int bubble=0; !isSorted && bubble<arr.size()-1; bubble++){
            isSorted=true;
            for( int index=0; index<arr.size()-1-bubble; index++){
                if(arr.get(index).getWeight() > arr.get(index+1).getWeight()){
                    tmp=arr.get(index);
                    arr.set(index, arr.get(index+1));
                    arr.set(index+1, tmp);
                    isSorted=false;
                }
            }
        }
    }

}

