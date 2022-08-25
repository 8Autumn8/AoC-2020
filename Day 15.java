import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    List<Integer> starting = new ArrayList<Integer>();
    starting = populateArray();
    firstStar(starting);
  }

  public static int firstStar(List<Integer> values){
    while (values.size() != 30){
      //System.out.println(values);
      int lastVal = values.get(values.size()-1);
      //System.out.println("last value: " + lastVal);
      int index = -1;
      for (int i = values.size()-2; i >= 0; i--){
        if (lastVal == values.get(i)){
          index = i+1;
          break;
        }
      }
      //System.out.println("index " + index);
      if (index < 0){
        values.add(0);
      } else {
        values.add(values.size()-index);
      }
    }
    System.out.println(values);
    System.out.println("last value: " + values.get(values.size()-1));
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
