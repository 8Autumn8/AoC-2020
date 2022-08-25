import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  private static List<Integer> value = new ArrayList<Integer>();
  public static void main(String[] args) {
    populateArray();
    //System.out.println(value);
    firstStar();
  }
  public static void firstStar(){
    int count = 0;
    int min = Collections.min(value);
    int max = Collections.max(value);
    while (count != 100){
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
      
      System.out.println("destingation cup: " + destinationCup);
      
      int preIndex = value.indexOf(destinationCup);
      value.remove(value.indexOf(cupOne));
      value.remove(value.indexOf(cupTwo));
      value.remove(value.indexOf(cupThree));
      System.out.println("index " + value.indexOf(destinationCup));
      int destinationCupIndex = value.indexOf(destinationCup);
      if (destinationCupIndex == value.size()-1){
        value.add(cupOne);
        value.add(cupTwo);
        value.add(cupThree);
      } else if (destinationCupIndex == 0){
        value.remove(0);
        value.add(destinationCup);
        value.add(cupOne);
        value.add(cupTwo);
        value.add(0,cupThree);
      } else if (preIndex <= 3 ){
        int secondCount = 0;
        while (secondCount != preIndex-1){
          int temp = value.get(0);
          value.remove(0);
          System.out.println("removed: " + temp);
          value.add(temp);
          secondCount++;
        }
        int newIndex = value.indexOf(destinationCup);;
        value.add((newIndex+1)%value.size(),cupThree);
        value.add((newIndex+1)%value.size(),cupTwo);
        value.add((newIndex+1)%value.size(),cupOne);        
      } else if ((value.size()-1)-preIndex <= 3){
        while (secondCount != preIndex-1){
          int temp = value.get(0);
          value.remove(0);
          System.out.println("removed: " + temp);
          value.add(temp);
          secondCount++;
        }
      } else {
        value.add((destinationCupIndex+1)%value.size(),cupThree);
        value.add((destinationCupIndex+1)%value.size(),cupTwo);
        value.add((destinationCupIndex+1)%value.size(),cupOne);
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
        

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
