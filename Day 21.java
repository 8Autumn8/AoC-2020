import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  private static HashMap<List<String>,List<String>> map = new HashMap<List<String>,List<String>>(); 
  public static void main(String[] args) {
    populateArray();
    for (List<String> key : map.keySet()){
      System.out.println("key: " + key + " value: " + map.get(key) );
    }
    System.out.println();
    firstStar();
  }
  public static String checkEqual(List<String> original, List<String> compare){
    System.out.println(" ************** ");
    for (int i = 0; i < original.size(); i++){
      for (int j = 0; j < compare.size();j++){
        System.out.println("comparing: " + original.get(i) + " and: " + compare.get(j));
        if (original.get(i).equals(compare.get(j))){
          
          return original.get(i);
        }
        System.out.println("they are not equal");
      }
    }
    return "";
  }

  public static HashMap<List<String>,List<String>> editMap(String key, String value){
    HashMap<List<String>,List<String>> newMap = new HashMap<List<String>,List<String>>();
    System.out.println("\n removing value \n");
    for (List<String> x : map.keySet()){
      
      if (x.contains(key) && map.get(x).contains(value)){
        System.out.println(x);
        List<String> keys = new ArrayList<String>(x);
        while (keys.indexOf(key) > -1){
          keys.remove(keys.indexOf(key));
        }
        List<String> values = new ArrayList<String>(map.get(x));
        while (values.indexOf(value) > -1){
          values.remove(values.indexOf(value));
        }
        
        newMap.put(keys,values);
      } else if (map.get(x).contains(value)){
        List<String> values = new ArrayList<String>(map.get(x));
        while (values.indexOf(value) > -1){
          values.remove(values.indexOf(value));
        }
        
        newMap.put(x,values);
      } else {
        newMap.put(x,map.get(x));
      }
    }
    map.clear();
    return newMap;
  }

  public static void firstStar(){
    for (List<String> key : map.keySet()){
      outerloop:
      for (List<String> compare : map.keySet()){
        if (compare != key && !key.isEmpty() && !compare.isEmpty()){
          //checking to see if they have a valid comparison
          System.out.println("key: " + key + " compare: " + compare);
          String x = checkEqual(key,compare);
          System.out.println(x);
          if (!x.equals("")){
            //if they have an equal value
            List<String> originalValue = map.get(key);
            List<String> compareValue = map.get(compare);
            String equal = checkEqual(originalValue, compareValue);
            if(!equal.equals("")){
              System.out.println("the equal value is: " + equal);
              map = editMap(x,equal);
              System.out.println(map);
              break outerloop;
            }
            
          }
        }
      }
    }
  }

  public static void populateArray(){
    try {
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data = data.replaceAll("[()]", "");
        String[] split = data.split(" contains ",2);
        String[] secondSplit = split[0].split(" ");
        String[] keySplit = split[1].split(", ");
        map.put(Arrays.asList(keySplit),Arrays.asList(secondSplit));

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
