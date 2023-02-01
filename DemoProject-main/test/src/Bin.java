import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bin {

    ArrayList<Double> objects;
    double maxWeight;
    double totalWeight;

    public static void main(String[]  args){
        //Bin with max weight of 10
        Bin bin = new Bin(10);

        ArrayList<Double> weights = new ArrayList<Double>();

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of objects: ");
        double numObjects = scan.nextInt();

        System.out.print("Enter weights of the objects: ");
        for(int i = 0; i <  numObjects; i++){
            weights.add((scan.nextDouble()));
        }

        //Packs the objects from the bin into containers of maxWeight
        bin.binPacking(weights, 10);
    }

    Bin(){
        objects = new ArrayList<Double>();
        totalWeight = getTotalWeight();
    }

    Bin(double newMaxWeight){
        objects = new ArrayList<Double>();
        maxWeight = newMaxWeight;
        totalWeight = getTotalWeight();
    }

    boolean addItem(double weight){
        if(weight + totalWeight <= maxWeight){
            objects.add(weight);
            totalWeight += weight;
            return true;
        }
        return false;
    }

    int getNumberOfObjects(){
        return objects.size();
    }

    double getTotalWeight() {
        double sum = 0;
        for(Double d : objects){
            sum += d;
        }
        return sum;
    }

    @Override
    public String toString(){
        String bin = "";
        for(double d : objects){
            bin += (d + " ");
        }
        return bin;
    }

    void binPacking(ArrayList<Double> weights, double maxWeight){

        int container = 0;
        Collections.sort(weights, Collections.reverseOrder());
        ArrayList<Bin> bins = new ArrayList<Bin>();
        bins.add(new Bin(maxWeight));
        boolean added;

        for(Double d : weights){
            added = false;
            for(int i = 0; i < container + 1; i++){
                if(d+bins.get(i).totalWeight <= maxWeight) {
                    bins.get(i).addItem(d);
                    added = true;
                    break;
                }
            }
            if (added == false){
                bins.add(new Bin(maxWeight));
                container++;
                bins.get(container).addItem(d);
            }
        }
        int index = 1;
        for(Bin b : bins){
            System.out.println();
            System.out.print("Container " + index + " contains objects with weight ");
            System.out.print(b.toString());
            index++;
        }
    }
}
