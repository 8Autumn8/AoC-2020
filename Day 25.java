import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;
import java.lang.Math; 

class Main {
  private static List<Integer> value = new ArrayList<Integer>();
  public static void main(String[] args) {
    populateArray();
    System.out.println(value);
    firstStar();

  }

  public static void firstStar(){
    
    boolean found = false;
    double subNum = 0;
    double loopSize = 3.0;
    double loopSizeTwo = 0;
    int currVal = value.get(0);
    boolean compare = false;
    while (!found){
      currVal = value.get(0);
       double nth = Math.pow(currVal, 1.0 / loopSize);
       System.out.println("loop size: " + loopSize);
       System.out.println("value: " + currVal);
       System.out.println(nth);
       System.out.println(Math.round(nth) - nth);
      if (Math.round(nth) - nth < 0.0001 && Math.round(nth) - nth >= 0){
        System.out.println("HHHHHERRRRRRRRRRRRRRRRRREEEEEEEE");
        System.out.println(loopSize);
        System.out.println(nth);
        subNum = nth;
        compare = true;
       
      }
      if (compare){
        currVal = value.get(1);
        long temp = 1;
        int count = 0;
        long prev = 0;
        long actual = currVal * (long) 20201227;
        System.out.println("curr value: " + currVal);
        while (temp < actual){
          count++;
          temp*= subNum;
          temp = temp % 20201227;
          System.out.println("temp : " + temp);
          

          if (temp == prev || temp == currVal || count > 10){
            System.out.println(currVal);
            break;
          }
          prev = temp;

          
        }
        if (temp > currVal){
          found = false;
          compare = false;
        } else if (temp == currVal){
          System.out.println("FOUND BOTH");
          loopSizeTwo = count;
          System.out.println("loop size: " + count);
          System.out.println("subNUm " + subNum);
          
          found = true;
          break;
        }
      }
      
      loopSize++;
      if (loopSize > 100){
        break;
      }
    }
    double count = value.get(0);
    long temp = 1;
    for (int i =0; i < loopSizeTwo; i++){
      temp*=count;
       temp %= 20201227;
       System.out.println("temp : " + temp);
    }
    System.out.println("temp : " + temp);
    System.out.println("*********************");
    count = value.get(1);
    temp = 1;
    for (int i =0; i < loopSize; i++){
      temp*=count;
       temp %= 20201227;
       System.out.println("temp : " + temp);
    }
    System.out.println("temp : " + temp);
  }
  public static void populateArray(){
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        
        
        value.add(Integer.parseInt(data));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
