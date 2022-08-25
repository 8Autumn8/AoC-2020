import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {

  private static List<String> value = new ArrayList<String>();
  public static void main(String[] args) {
   populateArray();
   System.out.println(value);
   firstStar();
  }
  
  public static void firstStar(){
    List<List<Integer>> black = new ArrayList<List<Integer>>();
    for (int i = 0; i < value.size(); i++){
      String currValue = value.get(i);
      System.out.println("curr value: " + currValue);
      Integer[] location = new Integer[2];
      location[0] = 0;
      location[1] = 0;
      
      for (int j = 0; j < currValue.length();){        
        String single = currValue.substring(j, j+1);
        String twosubstring = "";
        boolean isthere = true;
        if (j < currValue.length()-1){
          twosubstring = currValue.substring(j, j+2);
          
        } else {
          isthere = false;
        }
        
        if (!twosubstring.equals("")){ 
          
          if (twosubstring.equals("se")){
            location[1]-=2;
            location[0]++;
            //System.out.println(twosubstring);
          } else if (twosubstring.equals("sw")){
            location[1]-=2;
            location[0]--;
            //System.out.println(twosubstring);
          } else if (twosubstring.equals("nw")){
            location[1]+=2;
            location[0]--;
            //System.out.println(twosubstring);
          } else if (twosubstring.equals("ne")){
            location[1]+=2;
            location[0]++;
            //System.out.println(twosubstring);
          } else {
            isthere = false;
          }
        }
        if (!isthere){
          j++;
          if (single.equals("e")){
            location[0]+=2;
          } else if (single.equals("w")){
            location[0]-=2;
          }
          //System.out.println(single);
        } else {
          j+=2;
        }
        //System.out.println(Arrays.toString(location));
      }
      System.out.println(Arrays.toString(location));  
      System.out.println(black.indexOf(Arrays.asList(location)));
      if (black.contains(Arrays.asList(location))){
        System.out.println("contains");
        black.remove(black.indexOf(Arrays.asList(location)));
      } else {
        black.add(Arrays.asList(location));

      }
    }
    for (int i = 0; i < black.size(); i++){
      System.out.println((black.get(i)));
    }
    System.out.println("size: " + black.size());
  }

  public static void populateArray(){
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        
        
        value.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
