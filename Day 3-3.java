import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); 
    int verticalChange = 1;
    int horizontalChange = 1;
    int length = 323;
    int width = 31;
   
    //Build Board
    String[][] outer = new String[length][width];
    for (int i = 0; i < length; i++){
      String input = scanner.nextLine();
      
      for (int j = 0; j < input.length(); j++){      
        String temp = input.substring(j,j+1);
        outer[i][j] = temp;

      }
      
    } 
    
  
    //Searching Board
    int currLength = 0;
    while (currLength < length){
      int temp = horizontalChange % width;
      
    }



    int treeCount = 0;
    int horizontal = 0;
    int vertical = 0;
    for (int i = 0; i <= length-1; i++){
      try{
      if (outer[vertical][horizontal].equals("#")){
        treeCount++;
        System.out.println("Location: (" + (horizontal) + ", " + (vertical) + " )");
      }

      horizontal+=horizontalChange;
      vertical+=verticalChange;
      System.out.println(treeCount);
      if (horizontal >= width){
        System.out.println("original: " + horizontal);
        horizontal = horizontal % width;
        System.out.println("Final: " + horizontal);
      }
      } catch(Exception e) {
        System.out.println(treeCount);
      }


    } 
    System.out.println(treeCount); 

    for(int i = 0 ; i < length; i ++){
      System.out.println(Arrays.toString(outer[i]));
    }
    System.out.println(treeCount);
  }
}
