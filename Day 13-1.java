import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  private static int time = 0;
  public static void main(String[] args) {
    String[] x = populateArray();
    String[] arrOfStr = x[0].split(","); 
    //System.out.println(Arrays.toString(x));
    System.out.println(Arrays.toString(arrOfStr));
    firstStar(arrOfStr);
  }
  public static String[] firstStar(String[] arrOfStr){
    boolean found = false;
    int[] times = new int[arrOfStr.length];
    for (int i = 0; i < arrOfStr.length; i++){
      if(!arrOfStr[i].equals("x")){
        times[i] = Integer.parseInt(arrOfStr[i]);
      } 
    }    

    int[] departTime = new int[arrOfStr.length];
    for (int i = 0; i < arrOfStr.length; i++){
      departTime[i] = i+1;
    }

    while(!found){
      for (int i = 0; i < arrOfStr.length; i++){
        departTime[i]+=times[i];
      }
    }
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
