
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.util.*;


class Main {

  private static int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
  public static int width = 90; //92
  public static int height = 92; //90

  static List<String> getSurroundings(String[][] matrix, int x, int y){
      List<String> res = new ArrayList<String>();
      //int i = 0;
      for (int[] direction : directions) {
          //i++;
          int cx = x + direction[0];
          int cy = y + direction[1];     
          if(cy >=0 && cy < matrix.length){
              if(cx >= 0 && cx < matrix[cy].length){
                  
                  while (matrix[cy][cx].equals(".")){
                      cx+=direction[0];
                      cy+=direction[1];
                      //System.out.println("cx :" + cx);
                      //System.out.println("cy: " + cy);
                      try{
                        matrix[cy][cx].equals(".");
                      } catch (Exception e){
                        break;
                      }
                  }
                  try{
                    res.add(matrix[cy][cx]); 
                  } catch(Exception e) {

                  }
                      
              }
          }

      
      }
      return res;
  }

  public static void main(String[] args) {
    String[][] x = populateArray();
    for (int i = 0; i < x.length; i++){
      for (int j = 0; j < x[i].length;j++){
        if(x[i][j].equals("L")){
          x[i][j] = "#";
        }
      }
    }

    //System.out.println(getSurroundings(x,3, 0));
    for(int j = 0 ; j < x.length; j ++){
      System.out.println(Arrays.toString(x[j]));
    }


    
    for (int i = 0; i < 250; i++){
      x = secondStar(x);
      /**System.out.println("\n***********************\n");
      for(int j = 0 ; j < x.length; j ++){
        System.out.println(Arrays.toString(x[j]));
      }**/
    }

    int numOfOccupied = 0;
    for (int i = 0; i < x.length; i++){
      for (int j = 0; j < x[i].length;j++){
        if(x[i][j].equals("#")){
          numOfOccupied++;
        }
      }
    }
    System.out.println("number of occupied: " + numOfOccupied);
    
  }


  public static String[][] secondStar(String[][] x){
    int[][] seatsSurrounding = new int[width][height];
    List<String> res = new ArrayList<String>();
    int occupiedSeat = 0;
    
    for (int i = 0; i < x.length; i++){ 
      for (int j = 0; j < x[i].length; j++){
        occupiedSeat = 0;
        res = getSurroundings(x, j, i);
        for (String chair : res){
          if (chair.equals("#")){
            occupiedSeat++;
          }
        }
        seatsSurrounding[i][j] = occupiedSeat;
      }
    }

    for (int i = 0; i < x.length; i++){
      for (int j = 0; j < x[i].length;j++){
        if (x[i][j].equals("#") && seatsSurrounding[i][j] >= 5){
          x[i][j] = "L";
        } else if (x[i][j].equals("L") && seatsSurrounding[i][j] == 0){
          x[i][j] = "#";
        }
      }
    }
    //System.out.println("\n"); 
    /**for(int i = 0 ; i < seatsSurrounding.length; i ++){
      //System.out.println(Arrays.toString(seatsSurrounding[i]));
    }**/   
    
    
    return x;
  }

  public static void secondStar(){

  }

  public static String[][] populateArray(){
    String[][] array = new String[width][height];
    int index = 0;
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for (int i = 0; i < data.length(); i++){
          array[index][i] = data.substring(i,i+1);
        }  
        
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
