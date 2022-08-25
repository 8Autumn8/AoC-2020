import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    List<Integer> starting = new ArrayList<Integer>();
    starting = populateArray();


    secondStar(starting);
  }

  public static int secondStar(List<Integer> values){
    int[] indexContainer = new int[2147483646];
    int currlocation = 0;
    while (currlocation != 30000000){

    }
    //System.out.println(locationvalues);
    //System.out.println("last value: " + lastVal);
    return 0;

  }
  public static List<Integer> populateArray(){
      List<Integer> ints = new ArrayList<Integer>();
    try{
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      String data = myReader.nextLine();
      int index = 0;
      String[] array = data.split(",");


      for (int i = 0; i < array.length; i++){
        ints.add(Integer.parseInt(array[i]));
      } 
    }catch (Exception e){

    }
    return ints;
  }
}
