import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static HashMap<String,Integer> rules = new HashMap<String,Integer>();
  public static List<String> array = new ArrayList<String>();
  public static void main(String[] args) {
    populateArray();
    System.out.println(array + "\n");
    System.out.println(rules + "\n");
    int followsZero = 0;
    //firstStar(0);
  }
  public static void firstStar(int index){
    boolean reachend = false;
    String currVal = array.get(index);
    for (int i = 0; i < currVal.length(); i++){
      String charAt = currVal.substring(i, i+1);
    }
    
    
  
  }
  public static boolean isNumeric(String strNum) {
      if (strNum == null) {
          return false;
      }
      try {
          double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
          return false;
      }
      return true;
  }
  public static void populateArray(){
    
    
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String nextLine = myReader.nextLine();
        nextLine = nextLine.replaceAll("\"",""); 
        if (!nextLine.isEmpty()){
          if (isNumeric(nextLine.substring(0,1))){
            String[] split = nextLine.split(":");
            if (split[1].contains("|")){
              String[] splitTwo = split[1].split("\\|");
              for (String value : splitTwo){
                rules.put(value,Integer.parseInt(split[0]));
              }
            
            } else {
              rules.put(split[1],Integer.parseInt(split[0]));
            }

            
          }else{
            array.add(nextLine);
          }
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }  
}
