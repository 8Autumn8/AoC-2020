
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    System.out.println(Arrays.toString(x));
    System.out.println(firstStar(x));
  }

  public static int firstStar(String[] x){
    List<List<String>> sea = new ArrayList<List<String>>();

    String[] position = new String[]{"N","E","S","W"};
    int xc = 0;
    int yc = 0;
    String currFace = "E";
    for (int i = 0; i < x.length; i++){
      
      String direction = x[i].substring(0,1);
      int value = Integer.parseInt(x[i].substring(1));
      
      int j = 0;
      while (!position[j].equals(currFace)){
        j++;
      }      
      if (direction.equals("N")){
        yc+=value;
      } else if (direction.equals("S")){
        yc-=value;
      } else if (direction.equals("E")){
        xc+=value;
      } else if (direction.equals("W")){
        xc-=value;
      } else if (direction.equals("L")){
        value/=90;
        for (int k = 0; k < value; k++){
          j--;
          System.out.println(j);
          if ((j) < 0){
            j = 3;
          } else if ((j) > 3){
            j = 0;
          }
        }
        currFace = position[j];
      } else if (direction.equals("R")){
        value/=90;
        for (int k = 0; k < value; k++){
          j++;
          if ((j) < 0){
            j = 3;
          } else if ((j) > 3){
            j = 0;
          }
        }
        currFace = position[j];
      } else if (direction.equals("F")){
        if (currFace.equals("W")){
          xc-=value;
        } else if (currFace.equals("E")){
          xc+=value;
        } else if (currFace.equals("N")){
          yc+=value;
        } else if (currFace.equals("S")){
          yc-=value;
        }
      }
      System.out.println(x[i]);
      System.out.println("current Direction: " + currFace);
      System.out.println("x: " + xc);
      System.out.println("y: " + yc);          
    }
    System.out.println("x: " + xc);
    System.out.println("y: " + yc);    
    return 0;
  }
  public static String[] populateArray(){
    String[] array = new String[5];
    int index = 0;
    try {
      File myObj = new File("test.txt");
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
