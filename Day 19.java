import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static HashMap<Integer,String> rules = new HashMap<Integer,String>();
  public static List<String> array = new ArrayList<String>();
  public static void main(String[] args) {
    populateArray();
    System.out.println(array + "\n");
    System.out.println(rules + "\n");
    int followsZero = 0;
    System.out.println(firstStar(0 , 0, 0));
  }

  public static boolean firstStar(int index, int rule, int indexofChar){
    boolean reachend = false;
    String currString = array.get(index);
    System.out.println("current String: " + currString);
    int i = rule;
    String currRule = rules.get(i).replaceAll("\\s", "");
    System.out.println("current rule: " + currRule);
    String charAt = currString.substring(indexofChar, indexofChar+1);
    System.out.println("char looking at: " + charAt);
    if (currRule.contains("|")){
      String[] rulesToFollow = currRule.split("\\|");
      for (String tempRule: rulesToFollow){
        String[] immediateRule = tempRule.split("");
        boolean found = false;
        for (String x : immediateRule){
          if (!firstStar(index, Integer.parseInt(x), indexofChar)){
            found = false;
            break;
            
          } else {
            found = true;
            indexofChar++;
          }
        
        }
        
      }
    } else if (currRule.contains("\"")){
      currRule = currRule.replaceAll("\"", "");
      if (charAt.equals(currRule)){
        System.out.println("here");
        System.out.println(indexofChar);
        reachend = true;
      }

    } else {
      String[] rulesToFollow = currRule.split("");
      for (String value : rulesToFollow){
        if (firstStar(index, Integer.parseInt(value), indexofChar)){
          reachend = true;
          indexofChar++;
        } else {
          return false;
        }
      }
    }
    return reachend;
    
  
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
      File myObj = new File("test.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String nextLine = myReader.nextLine();
        if (!nextLine.isEmpty()){
          if (isNumeric(nextLine.substring(0,1))){
            String[] split = nextLine.split(":");
            rules.put(Integer.parseInt(split[0]),split[1]);
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
