import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {


        System.out.println("Whould you like to run default tests or run costumed tests to your liking?" +
                "\nEnter d for default, c for costumed tests");
        //int i = scan.nextInt()
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        String s = scan.next();
        if(s.equals("d")){
            System.out.println("edgesAspectExperiment #1 begins:");
            Tests.edgesAspectExperiment(20, 100, 2, 4,7, "edgesAspectExp1.csv" );
            System.out.println("edgesAspectExperiment #2 begins:");
            Tests.edgesAspectExperiment(20, 200, 2, 8,9, "edgesAspectExp2.csv" );
            System.out.println("averageEdgeDifferentialAspectExperiment #1 begins:");
            Tests.averageEdgeDifferentialAspectExperiment(40, 100, 0.5, 20, "avarageEdgeDiff1.csv");
            System.out.println("averageEdgeDifferentialAspectExperiment #2 begins:");
            Tests.averageEdgeDifferentialAspectExperiment(60, 100, 0.75  , 25 , "avarageEdgeDiff2.csv");

            System.out.println("weightsAspectExpirement #1 begins:");
            Tests.weightsAspectExpirement( 25, 100, 0.1, 25,"weightsAspectExpirement1.csv" );
            System.out.println("weightsAspectExpirement #2 begins:");
            Tests.weightsAspectExpirement( 25, 100, 0.5, 25,"weightsAspectExpirement2.csv" );
            System.out.println("weightsAspectExpirement #3 begins:");
            Tests.weightsAspectExpirement( 25, 100, 0.9, 25,"weightsAspectExpirement3.csv" );

        }
        else{

            while(true){
                System.out.println("choose your test");
                System.out.println("enter 1 for edgesAspectExperiment");
                System.out.println("enter 2 for averageDifferentialAspectExperiment ");
                System.out.println("enter 3 for distanceAspectExperiment ");
                int n = scan.nextInt();
                if(n ==1){
                    System.out.println("Enter file name, with ending");
                    String fileName = scan.next();
                    System.out.println("Enter sample size");
                    int sample = scan.nextInt();
                    System.out.println("Enter number of vertics");
                    int num =scan.nextInt();
                    System.out.println("Enter first stretch factor t1");
                    int t1 = scan.nextInt();
                    System.out.println("Enter first stretch factor t2");
                    int t2 = scan.nextInt();
                    System.out.println("Enter first stretch factor t3");
                    int t3 = scan.nextInt();
                    Tests.edgesAspectExperiment(sample, num, t1, t2, t3, fileName);
                }
                else if(n ==2){
                    System.out.println("Enter file name, with ending");
                    String fileName = scan.next();
                    System.out.println("Enter sample size");
                    int sample = scan.nextInt();
                    System.out.println("Enter number of vertics");
                    int num =scan.nextInt();
                    System.out.println("Enter p the probbability for an edge to exist");
                    double p = scan.nextDouble();
                    System.out.println("Enter the maximum stretch factor");
                    int maxSretch = scan.nextInt();
                    Tests.averageEdgeDifferentialAspectExperiment(sample, num, p, maxSretch, fileName);
                }
                else if(n==3){
                    System.out.println("Enter file name, with ending");
                    String fileName = scan.next();
                    System.out.println("Enter sample size");
                    int sample = scan.nextInt();
                    System.out.println("Enter number of vertics");
                    int num =scan.nextInt();
                    System.out.println("Enter p the probbability for an edge to exist");
                    double p = scan.nextDouble();
                    System.out.println("Enter the maximum stretch factor");
                    int maxSretch = scan.nextInt();
                    Tests.weightsAspectExpirement(sample, num, p, maxSretch, fileName);
                }
                else {
                    System.out.println(" You have entered a wrong test index.");
                }




                System.out.println("Whould you like to run another test? (yes / no)");
                s = scan.next();
                if(s.startsWith("n")){
                    break;
                }

            }
        }

    }


}