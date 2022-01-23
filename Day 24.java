import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {

  private static List<String> value = new ArrayList<String>();
  private static List<List<Integer>> black = new ArrayList<List<Integer>>();
  private static HashMap<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
  public static void main(String[] args) {
   populateArray();
   System.out.println(value);
   firstStar();
   System.out.println(black.size());
   System.out.println(black);
   SecondStar();
   System.out.println("blacks: " + black.size());
  }
  
  public static void firstStar(){
    
    for (int i = 0; i < value.size(); i++){
      String currValue = value.get(i);
      //System.out.println("curr value: " + currValue);
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
      //System.out.println(Arrays.toString(location));  
      //System.out.println(black.indexOf(Arrays.asList(location)));
      if (black.contains(Arrays.asList(location))){
        System.out.println("contains");
        black.remove(black.indexOf(Arrays.asList(location)));
      } else {
        black.add(Arrays.asList(location));

      }
    }
    for (int i = 0; i < black.size(); i++){
      map.put(black.get(i),1);
    }
    //System.out.println("size: " + black.size());
  }
  private static int[][] direction = new int[][]{{1,-2}, {-1,-2}, {-2,0},  {-1,2}, {1, 2}, {2, 0}};
  public static Integer surrounding(int x, int y){
    int count = 0;
    for (int[] coordinate : direction){
      List<Integer> temp = new ArrayList<Integer>();
      int cx = x + coordinate[0];
      int cy = y + coordinate[1];
      temp.add(cx);
      temp.add(cy);
      if (black.contains(temp)){
        count++;
      }
    }
    return count;
  
  }

  public static void SecondStar(){
    for (int m = 0; m < 100; m++){
      List<List<Integer>> newBlack = new ArrayList<List<Integer>>();
      List<List<Integer>> newWhite = new ArrayList<List<Integer>>();
      int maxY = 0;
      int maxX = 0;
      int leastX = 0;
      int leastY = 0;
      for (int i = 0; i < black.size(); i++){
        List<Integer> value = black.get(i);
        if (value.get(0) > maxX){
          maxX = value.get(0);
        } else if (value.get(0) < leastX){
          leastX = value.get(0);
        }
        if (value.get(1) > maxY){
          maxY = value.get(1);
        } else if (value.get(1) < leastY){
          leastY = value.get(1);
        }
      }
      for (int i = leastX -4; i < maxX + 4; i++){
        for (int j = leastY -4; j < maxY + 4; j+=2){
        List<Integer> value = new ArrayList<Integer>();
        value.add(i);
        value.add(j); 
        
        int surround = surrounding(i , j);
          if ((surround == 0 || surround > 2) && black.contains(value)){
            //System.out.println(value + " is now white");
            newWhite.add(value);
          } else if (surround == 2 && !black.contains(value)){
            //System.out.println(value + " is now black");
            newBlack.add(value);
                        
          }
        }
      
      }
      //System.out.println("removing...");
      for (List<Integer> inside : newWhite){
        //System.out.println(inside);
        black.remove(black.indexOf(inside));
      }

      //System.out.println(black);

      //System.out.println("adding...");
      for (List<Integer> inside : newBlack){
        //System.out.println(inside);
        black.add(inside);
      }
      //System.out.println("new black size: " + newBlack.size()); 
      System.out.println("m is: " + m + " black is " + black.size());
      
      }


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
