import java.util.Scanner;  // Import the Scanner class
import java.io.File;  // Import the File class
import java.util.Scanner;  // Import the Scanner class
import java.io.FileNotFoundException;
import java.util.Arrays;
/**
* Write a description of class Nodes here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class Main
{
    static int arrSize = 91 ;
    static int maxVal = 0;
    
    public static int[] populateArray(){
        int[] array = new int[arrSize];
        int index = 0;
        try {
            File myObj = new File("puzzle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                array[index] = Integer.parseInt(data);
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return array;
    } 
    
    /**
    *  main method for testing outside BlueJ 
    */
    public static void main(String[] args) {
        
        int[] array = populateArray();
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));    
        
        long tot1 = 1;
        long tot2 = 0;
        long tot3 = 0;
        long currTot = 0;
        int pt = arrSize - 2;
        for (int i = pt; i>=0; i--)
        {
            //System.out.println(array[i]);
            currTot=0;
            if (i+1 < arrSize && array[i+1] - array[i] <= 3)
                currTot += tot1;
                
            if (i+2 < arrSize && array[i+2] - array[i] <=3)
                currTot += tot2;
                
            if (i+3 < arrSize && array[i+3] - array[i] <=3)
                currTot += tot3;
                
            tot3 = tot2;
            tot2 = tot1;
            tot1 = currTot;
        }
        
        System.out.println(currTot);
        
        maxVal = array[arrSize-1];
    }

}
