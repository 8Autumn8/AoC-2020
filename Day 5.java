
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {

  public static void main(String[] args) {
    String[] temp = populateArray();
    List<Integer> allSeats = new ArrayList<Integer>();
    int highestSeatNum = 0;
    for (int i = 0; i < 868; i++){
      int seat = 128;
      int width = 8;
      int upper = 128;
      int lowerWide = 1;
      int upperWide = 8;
      int row = 0;
      int column = 0;
      int lower = 1;
      for (int j = 0; j < 10; j ++){
        
        
       //FBFBBFFRLR
        if (temp[i].substring(j,j+1).equals("B")){
          seat/=2;
          lower = upper - seat + 1;

        } else if (temp[i].substring(j,j+1).equals("F")){
          seat/=2;
          
          upper = lower + seat-1;
        }
        //System.out.println("seat: " + seat);
        //System.out.println("upper: " + upper);
        //System.out.println("lower: " + lower);
        
        //System.out.println("row: " + row);
        if (temp[i].substring(j,j+1).equals("R")){
          width/=2;
          lowerWide = upperWide - width + 1;

        } else if (temp[i].substring(j,j+1).equals("L")){
          width/=2;
          
          upperWide = lowerWide + width-1;
        }
        //System.out.println("seaWidtht: " + width);
        //System.out.println("upperWidth: " + upperWide);
        //System.out.println("lowerWidth: " + lowerWide);
        row = lower;
        row--;
        column = upperWide;
        column--;
        //System.out.println("column: " + column);
        //System.out.println("row: " + row);
        
      }
        int temphigh = ((row * 8) + column);
        allSeats.add(temphigh);
        if (highestSeatNum < temphigh){
          highestSeatNum = temphigh;
        }

      
    }

    System.out.println("highest seat num: " + highestSeatNum);
    Collections.sort(allSeats);
    System.out.println(allSeats);
    for (int i = 70; i < 938; i++){
      if (!allSeats.contains(i)){
        System.out.println("Missing num: " + i);
      }
    }
  }


  
  public static String[] populateArray(){
    String[] array = new String[868];
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
