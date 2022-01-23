import java.io.*; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.*;


class Main {
  public static void main(String[] args) {
    ArrayList<ArrayList<String>> array = populateArray();
    int numofSimilar = 0;
    for (int i = 0; i < array.size(); i ++){
      //System.out.println("array size: " + array.size());
      ArrayList<String> innerList = array.get(i);
      int[] x = new int[26];
      System.out.println(innerList);
      for (int j = 0; j < innerList.size(); j ++){
        //System.out.println(innerList.get(j));
        for (int k = 0; k < innerList.get(j).length(); k++){
          
          char temp = (char) innerList.get(j).charAt(k);
          int a = temp;
          a-=97;
          x[a]++;
        }

      }
      System.out.println(Arrays.toString(x));
      int value = 0;
      for (int m = 0; m < x.length; m++){
         if (x[m] == innerList.size()){
            value++;
          }
        }
      System.out.println("similar value: " + value);
      numofSimilar+=value;
    
    }

    System.out.println(numofSimilar);
 
    //System.out.println(Arrays.toString(temp));
  }
  public static ArrayList<ArrayList<String>> populateArray(){
    ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      
      for (int i = 0; i < 456; i ++){
        String data = null;
        ArrayList<String> inside = new ArrayList<String>();
        while(!(data = myReader.nextLine()).isEmpty()) {
          inside.add(data);
        }
        
        array.add(inside); 
        
      }
      myReader.close();

     
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    System.out.println((array));
    return array;
  }
}
