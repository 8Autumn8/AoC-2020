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

  public static boolean testCompare(int subNumber, int loop){
    int secondVal = value.get(1);
    long temp = 1;
    int count = 0;
    long prev = 0;
    long actual = secondVal * (long) 20201227;
    System.out.println("curr value: " + secondVal);
    while (temp < actual){
      count++;
      temp*= subNumber;
      temp = temp % 20201227;
      System.out.println("temp : " + temp);
      

      if (temp == prev || temp == secondVal || count > 100){
        System.out.println(secondVal);
        break;
      }
      prev = temp;

      
    }
    if (temp > secondVal){
      return false;
    } else if (temp == secondVal){
      System.out.println("FOUND BOTH");
      System.out.println("loop size: " + count);
      System.out.println("subNUm " + subNumber);
      
      return true;
    }   
    return false;
  }

  public static void firstStar(){
    int loopSize = 0;
    boolean innerfound = false;
    boolean outerFound = false;
    int currVal = value.get(0);
    boolean compare = false;
    int subNumber = 2;
    while (!outerFound){
      
      int loop = 1; 
      long newVal = 1;
      while (!innerfound){
        
      

        newVal*=subNumber;
        newVal%=20201227;
        

        System.out.println("new value: " + newVal);
        if (loop == 10000){
          innerfound = true;
          
        } else if (newVal == currVal){
          System.out.println("found :)");
          System.out.println(subNumber + " " + loop);
          if (testCompare(subNumber, loop)){
            outerFound = true;
            loopSize = loop;
            break;
          } else {
            break;
          }
          
          
        }
        loop++;
      }
      subNumber++;
      if (subNumber == 10000){
        System.out.println("not found");
        break;
      } else {
        innerfound = false;
      }
    }



    double count = value.get(1);
    long temp = 1;
    for (int i =0; i < loopSize; i++){
      temp*=count;
       temp %= 20201227;
       System.out.println("temp : " + temp);
    }

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
