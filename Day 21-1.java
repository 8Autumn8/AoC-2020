import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  private static List<List<List<String>>> map = new ArrayList<List<List<String>>>(); 
  public static void main(String[] args) {
    populateArray();
    for (List<List<String>> key : map){
      System.out.println(key.get(0) + " " + key.get(1));
    }
    System.out.println();
/**
    for (int i = 0; i < map.size();i++){
      firstStar(i);
    }
    int count = 0;
    int countKey = 0;
    for (int i = 0; i < map.size(); i++){
      List<List<String>> full = new ArrayList<List<String>>(map.get(i));
      List<String> value = new ArrayList<String>(full.get(1));
      List<String> key = new ArrayList<String>(full.get(0));
      count += value.size();
      countKey = key.size();
    }
    System.out.println(count);
    System.out.println(countKey);**/
    
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

  public static List<List<List<String>>> editMap(String key, String value){
    List<List<List<String>>> newMap = new ArrayList<List<List<String>>>();
    System.out.println("\n removing value \n");
    for (List<List<String>> x : map){
      List<List<String>> temp = new ArrayList<List<String>>();
      List<String> keyMain = x.get(0);
      List<String> valuesMain = x.get(1);
      if (keyMain.contains(key) && valuesMain.contains(value)){
        
        List<String> keys = new ArrayList<String>(keyMain);
        while (keys.indexOf(key) > -1){
          keys.remove(keys.indexOf(key));
        }
        List<String> values = new ArrayList<String>(valuesMain);
        while (values.indexOf(value) > -1){
          values.remove(values.indexOf(value));
        }
        
        temp.add(keys);
        temp.add(values);
        newMap.add(temp);
      } else if (valuesMain.contains(value)){
        List<String> values = new ArrayList<String>(valuesMain);
        while (values.indexOf(value) > -1){
          values.remove(values.indexOf(value));
        }
        temp.add(keyMain);
        temp.add(values);
        newMap.add(temp);


      } else {
        temp.add(keyMain);
        temp.add(valuesMain);
        newMap.add(temp);
      }
    }
    map.clear();
    return newMap;
  }

  public static void firstStar(int index){
    List<List<String>> full = new ArrayList<List<String>>(map.get(index));
    List<String> keyMain = full.get(0);
    List<String> valuesMain = full.get(1);
    for (int j = index+1; j < map.size(); j++){
      List<List<String>> comparing = new ArrayList<List<String>>(map.get(j));
      List<String> keyCompare = comparing.get(0);
      List<String> valuesCompare = comparing.get(1);
      if (valuesMain.size() == keyMain.size()){
        map = multipleValues(keyMain,valuesMain);
        System.out.println(map);
      } else if (keyCompare.size() == valuesCompare.size()){
        map = multipleValues(keyCompare,valuesCompare);
        System.out.println(map);
      }
      if (keyCompare != keyMain && !keyMain.isEmpty() && !keyCompare.isEmpty()){
        //checking to see if they have a valid comparison
        System.out.println("key: " + keyMain + " compare: " + keyCompare);
        String x = checkEqual(keyMain,keyCompare);
        System.out.println(x);
        if (!x.equals("")){
          //if they have an equal value
          List<String> originalValue = new ArrayList<String>(valuesMain);
          List<String> compareValue = new ArrayList<String>(valuesCompare);
          String equal = checkEqual(originalValue, compareValue);
          if(!equal.equals("")){
            System.out.println("the equal value is: " + equal);
            map = editMap(x,equal);
            System.out.println(map);
            
          }
          
        }
      }

    }

  }

  public static List<List<List<String>>> multipleValues(List<String> keysToRemove, List<String> valuesToRemove){
    List<List<List<String>>> newMap = new ArrayList<List<List<String>>>();
    System.out.println("\n removing value \n");
    for (List<List<String>> x : map){
      List<List<String>> temp = new ArrayList<List<String>>();
      List<String> keyMain = x.get(0);
      List<String> valuesMain = x.get(1);
      boolean contains = true;
      List<String> valueInsideToRemove = new ArrayList<String>();
      List<String> keysInsideToRemove = new ArrayList<String>();
      for (int i = 0; i < keysToRemove.size(); i++){
        String keyRemove = keysToRemove.get(i);
        String valueRemove = valuesToRemove.get(i);
        if (keyMain.contains(keyRemove)){
          keysInsideToRemove.add(keyRemove);
        } else if (valuesMain.contains(valueRemove)){
          valueInsideToRemove.add(valueRemove);
        }
      }

      List<String> keys = new ArrayList<String>(keyMain);
      List<String> values = new ArrayList<String>(valuesMain);
      for (int k = 0; k < keysToRemove.size(); k++){
        while (keys.indexOf(keysToRemove.get(k)) > -1){
          keys.remove(keys.indexOf(keysToRemove.get(k)));
        }
      }
      for (int k = 0 ; k < valuesToRemove.size(); k++){
        while (values.indexOf(valuesToRemove.get(k)) > -1){
          values.remove(values.indexOf(valuesToRemove.get(k)));
        }
      }
      temp.add(keys);
      temp.add(values);
      newMap.add(temp);
    }
    map.clear();
    return newMap;
  }

  public static void populateArray(){
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data = data.replaceAll("[()]", "");
        String[] split = data.split(" contains ",2);
        String[] secondSplit = split[0].split(" ");
        String[] keySplit = split[1].split(", ");
        List<List<String>> temp = new ArrayList<List<String>>();
        
        temp.add(Arrays.asList(keySplit));
        temp.add(Arrays.asList(secondSplit));
        map.add(temp);

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
