import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.util.*;

class Main {
  public static void main(String[] args) {
    long[] x = populateArray();
    int startIndex = 25;
    int value = 0;
    /**while(firstStar(x, startIndex, value)){
      firstStar(x, startIndex, value);
      startIndex++;
      value++;
    }**/
    startIndex = 0;
    long searchValue = 15353384;
    //searchValue = 127;
    while(!secondStar(x, startIndex, searchValue)){
      secondStar(x, startIndex, searchValue);   
      startIndex++;
    }
    
  }

  public static boolean firstStar(long[] x, int startIndex, int i){
      
      long[] preamble = new long[25];
      for (int j = 0; j < 25; j ++){
        preamble[j] = x[j+i];
      } 
      
      long currum = x[startIndex];
      for (int j = startIndex-25; j < startIndex; j ++){
        for (int k = j; k < j+25; k ++){
          if (x[j] + x[k] == currum){
            return true;
          }
        }
      }
    System.out.println(currum);
    return false;
  }
  public static boolean secondStar(long[] x, int startIndex, long searchValue){
    
    for(int i = startIndex; i < x.length; i ++){
      long sum = 0;
      List<Long> valuesThatEqual = new ArrayList<Long>();
      sum = x[i];
      valuesThatEqual.add(x[i]);
      for (int j = i+1; j < x.length; j++){
        valuesThatEqual.add(x[j]);
        sum+=x[j];
        //System.out.println("sum: " + sum);
        //System.out.println(valuesThatEqual);
        if(sum == searchValue){
          Collections.sort(valuesThatEqual);
          System.out.println(sum);
          System.out.println(valuesThatEqual);
          System.out.println(searchValue);
          
          long ans = valuesThatEqual.get(0) + valuesThatEqual.get(valuesThatEqual.size()-1);
          System.out.println("sum: " + ans);
          return true;
        } else if (sum > searchValue){
          return false;
        }
      }
    }
    System.out.println("not found");
    return false;
  }

  public static long[] populateArray(){
    long[] array = new long[1000];
    int index = 0;
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        long data = Long.parseLong(myReader.nextLine());
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
