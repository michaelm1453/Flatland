import java.util.Random;

public class WeightedRNG.java
{
    public static void main (String[] args){
        Random rand = new Random();
        int n = rand.nextInt(100)+1;
        int threshold = 84;
        if(n<threshold)
            System.out.println("Theseus wins!");
        else
            System.out.println("Aeneas wins!");
    }

}
