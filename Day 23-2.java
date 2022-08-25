import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  private static List<Integer> value = new ArrayList<Integer>();
  public static void main(String[] args) {
    populateArray();
    System.out.println(value);
    //firstStar();
    String ans = "";
    int index = 0;
    boolean found = false;
    while (!found){
      if (value.get(index%(value.size())) == 1){
        System.out.println(value.get(index-1));
        System.out.println(value.get(index+1));
        break;
      }

    }
  }
  public static void firstStar(){
    int count = 0;
    int min = Collections.min(value);
    int max = Collections.max(value);
    while (count != 10){
      System.out.println(value);
      int currCup = value.get(count%(value.size()));
      int cupOne = value.get((count+1)%(value.size()));
      int cupTwo = value.get((count+2)%(value.size()));
      int cupThree = value.get((count+3)%(value.size()));
      System.out.println("curr cup: " + currCup);
      System.out.println("cup 1: " + cupOne);
      System.out.println("cup 2: " + cupTwo);
      System.out.println("cup 3: " + cupThree);
      int destinationCup = (currCup-1);
      while (destinationCup == cupOne || destinationCup == cupTwo || destinationCup == cupThree || destinationCup == currCup || destinationCup < min){
        destinationCup--;
        if (!value.contains(destinationCup) && destinationCup < min){
          destinationCup = max;
          while (destinationCup == cupOne || destinationCup == cupTwo || destinationCup == cupThree || destinationCup == currCup){
            destinationCup--;
            System.out.println(destinationCup);
          }
          break;
        }
      }
      
      //System.out.println("destingation cup: " + destinationCup);
      
      int preIndex = value.indexOf(currCup);
      value.remove(value.indexOf(cupOne));
      value.remove(value.indexOf(cupTwo));
      value.remove(value.indexOf(cupThree));
     //System.out.println("index " + value.indexOf(destinationCup));
      int destinationCupIndex = value.indexOf(destinationCup);
      if (destinationCupIndex == value.size()-1){
        value.add(cupOne);
        value.add(cupTwo);
        value.add(cupThree);
      } else {
        //System.out.println("here");
        value.add((destinationCupIndex+1),cupThree);
        value.add((destinationCupIndex+1),cupTwo);
        value.add((destinationCupIndex+1),cupOne);
      }
      //System.out.println("new value: " + value);
      int currIndex = value.indexOf(currCup);
      if (currIndex < preIndex){
        while (currIndex != preIndex){
          int temp = value.get(value.size()-1);
          value.remove(value.size()-1);
          value.add(0,temp);
          currIndex = value.indexOf(currCup);
        }
      } else if (currIndex > preIndex){
        while (currIndex != preIndex){
          int temp = value.get(0);
          value.remove(0);
          value.add(temp);
          currIndex = value.indexOf(currCup);
        }
      }

      
      count++;
    }
  }

  public static void populateArray(){
    try {
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] split = data.split("");
        for (int i =0; i < split.length; i++){
          value.add(Integer.parseInt(split[i]));
        }
        int max = Collections.max(value);
        for (int i = max+1; i < 10; i++){
          value.add(i);
        }
        

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
