import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.util.*;

class Main {
  private static List<Integer> intList;
  private static int[] x;
  public static void main(String[] args) {
    x = populateArray();
    Arrays.sort(x);
    System.out.println(Arrays.toString(x));
    //System.out.println(firstStar(x));
    int index = 0;
    intList = new ArrayList<Integer>(x.length);
    for (int i : x)
    {
        intList.add(i);
    }
    System.out.println("possible soultions: " + secondStar(index));
    
  }

  public static boolean firstStar(int[] x){
    int oneDiff = 0;
    int twoDiff = 0;
    int threeDiff = 0;

    for (int i = 0; i < x.length-1; i ++){
      int diff = x[i+1] - x[i];
      if (diff == 1){
        oneDiff++;
      } else if (diff == 2){
        twoDiff++;
      } else if (diff == 3){
        threeDiff++;
      } else if (diff > 3){
        System.out.println(oneDiff);
        System.out.println("reached max value");
        return false;
      }
    } 
    oneDiff++;
    threeDiff++;
    System.out.println("one: " + oneDiff);
    System.out.println("two: " + twoDiff);
    System.out.println("three: " + threeDiff);
    return true;

  }

  public static int secondStar(int index){
    int numOfPossible = 0;
    //for (int i = index; i < x.length-2; i ++){
      int k = index;
      System.out.println(intList);
      int currVal = x[index];
      //System.out.println(currVal);
      for (int j = 0; j < 3; j++){
        intList = new ArrayList<Integer>();
        for (int i = index+1; i < index+4; i++){
          try{
            intList.add(x[i]);
          } catch(Exception e){

          }
          
        }
        index = k;
        int difference = 0;
        try{
          difference = intList.get(j) - currVal;
          System.out.println("differece: " + difference);
        } catch(Exception e){

        }
        
        
        if (difference <= 3 && difference != 0){
          //System.out.println("here");
          //System.out.println("test value: " + testVal);
          index++;
          System.out.println("temp index: " + index);
          //System.out.println("index: " + tempIndex + " length: " + (x.length-2));
          
          if(index == x.length-1){
            System.out.println("got here");
            numOfPossible++;
            //return numOfPossible;
            return numOfPossible;
          } else if(index<x.length-1){
            //System.out.println("index: " + index);
            System.out.println("making branch");
            numOfPossible+=secondStar(index);
            

          } 
          
        } else {
          return numOfPossible;
        }
      }
    //}

    return numOfPossible;
  }


  public static int[] populateArray(){
    int[] array = new int[13];
    int index = 0;
    try {
      File myObj = new File("ex1.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        int data = Integer.parseInt(myReader.nextLine());
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
