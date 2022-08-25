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
    HashMap<Integer, Integer> locationvalues = new HashMap<Integer, Integer>();
    for (int i = 0; i < values.size()-1; i++){
      locationvalues.put(values.get(i),i+1);
    }
    System.out.println(locationvalues);
    int currlocation = values.size();
    int lastVal = values.get(values.size()-1);
    //System.out.println("vals: " + lastVal);
    while (currlocation != 30000000){
      //System.out.println("looking for: " + lastVal);
      if (locationvalues.containsKey(lastVal)){
        //System.out.println("map contains: " + lastVal);
        int previous = locationvalues.get(lastVal);
        locationvalues.put(lastVal, currlocation);  
        //System.out.println("current location: " + currlocation);
        //System.out.println("previous location: " + previous); 
        lastVal = currlocation - previous;
      } else {
        System.out.println(locationvalues.size());
        locationvalues.put(lastVal, currlocation);   
        lastVal = 0;
      }  
      //System.out.println("new value: " + lastVal);
      //locationvalues.put(lastVal, currlocation);     
      //System.out.println(locationvalues);
      currlocation++;
    }
    //System.out.println(locationvalues);
    System.out.println("last value: " + lastVal);
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
