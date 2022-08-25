 
import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.*;
import java.io.*;


class Main {
  private static HashMap<String,List<String>> map = new HashMap<String,List<String>>();

  // Key = > rotated () => flipped (reverse array)
  private static HashMap<String,HashMap<Boolean, Boolean>> movement = new HashMap<String,HashMap<Boolean, Boolean>> ();
  public static void main(String[] args) {
    populateArray();
    System.out.println(map);
    firstStar();
  }
  public static String checkEqual(List<String> original, List<String> compared){
    for (String x : original){
      for (String y : compared){
        //System.out.println("comparing: " + x + " to " + y);
        if (y.equals(x)){
          return y;
        } else {
          //System.out.println("check to see if reversed");
          String reversed = "";
          for (int i = y.length(); i > 0 ; i--){
            reversed+= y.substring(i-1,i);
          }
          //ystem.out.println("reversed string " + reversed);
          if (reversed.equals(x)){
            //ystem.out.println("reversed");
            //System.out.println("********************************************************************************");
            return y;
          }

        }
        //System.out.println("They are not equal");
      }
    }
    return "";
  }
  public static List<String> getAll(String key){
    List<String> fourSides = new ArrayList<String>();
    List<String> tile = map.get(key);
    int index = 0;
    for (int j = 0; j < 2; j++){
      String ans = "";
      for (int i = 0; i < tile.size(); i++){
        ans+=tile.get(i).substring(index, index+1);
      }
      fourSides.add(ans);
      index = tile.get(0).length()-1;
    }
    fourSides.add(tile.get(0));
    fourSides.add(tile.get(tile.size()-1));    
    return fourSides;

  }

  public static void addOn(List<String> currentPicture){

  }
  public static void printArray(String[][] x){
    for (String[] tmep : x){
      System.out.println(Arrays.toString(tmep));
    }
  }

  public static void buildPicture(List<String> corners){
    String[][] picture = new String[26][26];
    int[][] indexNextTo = new int[][]{{0, 1}, {1, 0}, {2, 3}, {3, 2}};
    
    
    for (String key : corners){
      List<String> fourSidesMain =  getAll(key);
      //System.out.println("********************************************************************************");
      //System.out.println("at: " + key);
      //System.out.println("horizontalMain " + fourSidesMain);
      //System.out.println("********************************************************************************");
      for (String compare : map.keySet()){
        
        if (!compare.equals(key)){
          //System.out.println("********** Comparing " + key + " to " + compare + "*********");
         //System.out.println("at: " + compare);
          List<String> fourSidesCompare =  getAll(compare);
          //System.out.println("fourSidesCompare " + fourSidesCompare);
          String equalString = checkEqual(fourSidesMain, fourSidesCompare);
          //ystem.out.println(equalString);
          if (!equalString.equals("")){
            //System.out.println(key + " is next too " + compare + "\n");
            int indexOne = fourSidesMain.indexOf(equalString);
            int indexTwo = fourSidesCompare.indexOf(equalString);
            if (indexOne != -1 && indexTwo != -1){

            }
            count++;
          }

        }
      }
      if (count == 2){
        courner.add(key);
      }
    }
  }

  public static void firstStar(){
    List<String> courner = new ArrayList<String>();
    
    for (String key : map.keySet()){
      List<String> fourSidesMain =  getAll(key);
      //System.out.println("********************************************************************************");
      //System.out.println("at: " + key);
      //System.out.println("horizontalMain " + fourSidesMain);
      //System.out.println("********************************************************************************");
      int count = 0;
      for (String compare : map.keySet()){
        
        if (!compare.equals(key)){
          //System.out.println("********** Comparing " + key + " to " + compare + "*********");
         //System.out.println("at: " + compare);
          List<String> fourSidesCompare =  getAll(compare);
          //System.out.println("fourSidesCompare " + fourSidesCompare);
          String equalString = checkEqual(fourSidesMain, fourSidesCompare);
          //ystem.out.println(equalString);
          if (!equalString.equals("")){
            //System.out.println(key + " is next too " + compare + "\n");

            count++;
          }

        }
      }
      if (count == 2){
        courner.add(key);
      }
    }
    System.out.println(courner);
    buildPicture(courner);
  }

  public static void populateArray(){
    
    int index = 0;
    try {
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        List<String> array = new ArrayList<String>();
        String data = myReader.nextLine();
        
        while (data.contains("Tile") && myReader.hasNextLine()){
          //System.out.println(data);
          String inside = myReader.nextLine();
          if (!inside.isEmpty()){
            array.add(inside);
          } else {
            break;
          }
          
        }
        map.put(data, array);

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }  
}
