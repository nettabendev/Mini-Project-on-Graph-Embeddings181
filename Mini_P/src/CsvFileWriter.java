import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.lang.*;
import java.io.FileWriter;

import java.io.IOException;

public class CsvFileWriter {
    /*
     * n is the number of lines, arri[j] is the virtue of spanner j,i we print where i is the stretch factor and j is the index of the graph
     */
    public static void writeCsvFileEdgesAspect(String fileName, int n,int[] arr,  int[] arr1, int[] arr2, int[] arr3){

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName );
            fileWriter.append(" G, s1,s2,s3");
            fileWriter.append("\n");
            for (int i=0; i< n; i++) {
                fileWriter.append(String.valueOf(arr[i]));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(arr1[i]));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(arr2[i]));
                fileWriter.append(",");
                fileWriter.append (String.valueOf(arr3[i]));
                fileWriter.append("\n");

            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter. Make sure no other process uses the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter.");
                e.printStackTrace();
            }
        }

    }
    public static  void writeAverageEdgeDifferentialAspectExperiment(String fileName, double[] aver, double[] diff){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName );
            fileWriter.append("average edge number , average difference");
            fileWriter.append("\n");
            for (int i=0; i<diff.length ; i++) {
                fileWriter.append(String.valueOf(aver[i]));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(diff[i]));
                fileWriter.append("\n");
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter. Make sure no other process uses the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter.");
                e.printStackTrace();
            }
        }

    }
    public static void writeWeightsAspectExpirement(double[] upperBounds, double[] averages, String fileName){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName );
            fileWriter.append("upper bound in spanner , average in spanner");
            fileWriter.append("\n");
            for (int i=0; i<upperBounds.length ; i++) {
                fileWriter.append(String.valueOf(upperBounds[i]));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(averages[i]));
                fileWriter.append("\n");
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter. Make sure no other process uses the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter.");
                e.printStackTrace();
            }
        }
    }

}

