import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); 
    int verticalChange = 1;
    int horizontalChange = 1;
    int length = 11;
    int width = length * horizontalChange;
   
    //Build Board
    String[][] outer = new String[length][width];
    for (int i = 0; i < length; i++){
      String input = scanner.nextLine();
      
      for (int j = 0; j < input.length(); j++){      
        String temp = input.substring(j,j+1);
        outer[i][j] = temp;

      }
      int temp = 0;
      for (int k = input.length(); k < width; k++){

        outer[i][k]=outer[i][temp];
        
        if (temp >= input.length()){
          temp = 0;
        }
        temp++;
      }
      
    } 
    
    //Searching Board
    int treeCount = 0;
    int horizontal = 1;
    int vertical = 1;
    for (int i = 0; i <= length; i++){
      while (horizontal <= length && vertical <= width){
        if (outer[vertical-1][horizontal-1].equals("#")){
          treeCount++;
        }
        horizontal+=horizontalChange;
        vertical+=verticalChange;
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
