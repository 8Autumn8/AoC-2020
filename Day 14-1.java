import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    Map<String,Map<Integer,Long>> map = populateArray();
    //System.out.println(firstStar(map));
    Map<Integer,Long> sums = firstStar(map);
    long ans = 0;
    int loopThrough = 0;
    for (Integer key : sums.keySet()){
      ans += sums.get(key);
      loopThrough++;
    }
    System.out.println(ans);
    //System.out.println(map.size());
    System.out.println(sums.size());
    //System.out.println(sums);
    //System.out.println(loopThrough);
    //findDuplicates(values);

  }
  public static long getDecimal(String binary){  
    long ans = 0;
    int position = 0;
    for (int i = binary.length(); i > 0; i--){
      if (binary.substring(i-1, i).equals("1")){
        ans += Math.pow(2,position);
      }
      position++;
    }
    //System.out.println(ans);
    
    return ans;
  }  
  public static String getBinary(long decimal){
    String ans = "";
     
    for (int i = 35; i >= 0; i--){
      if (decimal - Math.pow(2,i) > 0){
        ans+= "1";
        decimal-= Math.pow(2,i);
      } else if (decimal == Math.pow(2,i) && decimal != 0){
        ans+= "1";
        decimal-= Math.pow(2,i);
      } else {
        ans += "0";
      }

    }

    return ans;
  }

  public static Map<Integer,Long> firstStar(Map<String,Map<Integer,Long>> outerMap){
    Map<Integer,Long> sumAns = new LinkedHashMap<Integer,Long>();
    int addedValueTime = 0;
    long sumWithout = 0;
    for (String x : outerMap.keySet()){
      Map<Integer,Long> innerMap = outerMap.get(x);
      for (Integer key : innerMap.keySet()){
        long value = (innerMap.get(key));
        /**if (sumAns.containsKey(key)){
          value = sumAns.get(key);
        }**/
        String ans = "";
        //System.out.println("value: " + value);
        String bin = getBinary(value);
        //System.out.println(bin);
        //System.out.println(x);
        for (int i = 0; i < bin.length(); i++){
          if (x.substring(i, i+1).equals("X")){
            ans += bin.substring(i, i+1);
          } else {
            ans+=x.substring(i, i+1);

          }
        }
        //System.out.println(ans);
        long temp = getDecimal(ans);
        //System.out.println("temp: " + temp);
        innerMap.put(key, temp);
        sumAns.put(key, temp);
        //values.add(key);  
        //keys.add(key);
        addedValueTime++;
        sumWithout+=temp;
        //System.out.println(x + "," + key + "," + bin + "," + ans + "," + temp);
      }
      outerMap.put(x,innerMap);
    }
    //System.out.println(outerMap);
   // System.out.println("sums added together without takign into accoutn duplicates: " + sumWithout);
    //System.out.println("added to the other map: " + addedValueTime);
    return sumAns;
  }

  public static Map<String,Map<Integer,Long>> populateArray(){
   Map<String,Map<Integer,Long>> outerMap = new LinkedHashMap<String,Map<Integer,Long>>();

    try{
      
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        while (myReader.hasNextLine()) {
          if (data.contains("mask")){
            String temp ="";
            Map<Integer,Long> innerMap = new LinkedHashMap<Integer,Long>();
            while (myReader.hasNextLine()) {
              String innerData = myReader.nextLine();
              if (innerData.contains("mask")){
                temp = innerData;
                break;
              } else {
                String[] splitArray = innerData.replaceAll("\\s","").split("[\\[\\]=]");
                //System.out.println(Arrays.toString(splitArray));
                innerMap.put(Integer.parseInt(splitArray[1]),Long.parseLong(splitArray[3])); 
                 
              }
            }
            String[] splitArray = data.replaceAll("\\s","").split("=");
            outerMap.put(splitArray[1], innerMap);
            data = temp;
          }
        }
      }
      myReader.close();
    } catch (Exception e){
      System.out.println("error");
    }
    System.out.println(outerMap);
    //System.out.println("size" + outerMap.size());
    return outerMap;
  }

}
