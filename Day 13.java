import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  private static int time = 1000053;
  public static void main(String[] args) {
    String[] x = populateArray();
    String[] arrOfStr = x[0].split(","); 
    //System.out.println(Arrays.toString(x));
    System.out.println(Arrays.toString(arrOfStr));
    firstStar(arrOfStr);
  }
  public static String[] firstStar(String[] arrOfStr){
    boolean found = false;
    int smallestTime = 999999999;
    int foundTime = 0;
    int bus = 0;
    int id = 0;
    for (int i = 0; i < arrOfStr.length; i++){
      //System.out.println(i);
      
      time = 1000053;
      while (!found){
        System.out.println((arrOfStr[i]));
        if(!arrOfStr[i].equals("x")){
            id = Integer.parseInt(arrOfStr[i]);
            if (time%id==0){   
              found = true;
              foundTime=time; 
              break;    
            } else {
              time++;
            }
        } else {
          break;
        }
      }
      if (foundTime < smallestTime){
        smallestTime = foundTime;
        bus = id;
      }
      found = false;
    }

    System.out.println("bus: " + bus);
    System.out.println("time:" + smallestTime);
    return arrOfStr;
  }
  public static String[] populateArray(){
    String[] array = new String[1];
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
