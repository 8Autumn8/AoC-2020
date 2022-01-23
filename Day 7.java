
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.util.*;



class Main {


  public static void main(String[] args) {
    String[] puzzle = populateArray();
    firstStar(puzzle);
    secondStar(puzzle);
  }

  


  public static int firstStar(String[] x){
    HashMap<String,Integer> value = new HashMap<String,Integer>();
    int bags = 0;
    int[] keys = new int[6];
    HashMap<String,List<String>> innerMap = new HashMap<String,List<String>>();
    for (int i = 0; i < x.length; i++){
      String[] arrOfStr = x[i].replaceAll("\\s","").split("bagscontain");
      String[] secondArr = arrOfStr[1].split(",");
      for (int j = 0; j < secondArr.length; j++){
        secondArr[j]=secondArr[j].replaceAll("[^a-zA-Z ]", "");
        secondArr[j]=secondArr[j].replaceAll("bags", "");
        secondArr[j]=secondArr[j].replaceAll("bag", "");

        innerMap.put(arrOfStr[0], Arrays.asList(secondArr));        
      }
    }

    //List<String> keys = new List<String>();
    //System.out.println(innerMap.get("lightsilver"));
    System.out.println(innerMap);
    
    System.out.println(iterateValues(innerMap, "shinygold", value));
    

    

    return 0;
  }

  
  public static int iterateValues(HashMap<String,List<String>> innerMap, String x, HashMap<String, Integer> value){
    int bags = 0;
    List<String> keys = new ArrayList<String>();
    for (String key : innerMap.keySet()){
      //System.out.println("key: " + key);
      //System.out.println("value" + Arrays.toString(innerMap.get(key)));
      if (innerMap.get(key).contains(x)){
        System.out.println("key: " + key);
        bags++;
        System.out.println(bags);
        keys.add(key);
        value.put(key, 0);
      }
    }
    
    //System.out.println(keys);
    if (keys.size()>0){
      for (int i = 0; i < keys.size(); i++){
        
        System.out.println("value seartching for: " + keys.get(i));
        bags+=iterateValues(innerMap, keys.get(i), value);
        System.out.println("number of bags: " + bags);
      }
    }    
    return value.size();
  }

  public static int secondStar(String[] x){
    return 0;
  }


  public static String[] populateArray(){
    String[] array = new String[594];
    int index = 0;
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        array[index] = data;
        
        index++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return array;
  }  
}
