import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    String[][][] threeDimensional = cleanInput(x);
    firstStar(threeDimensional);
  }
  public static String[][][] cleanInput(String[] x){

    HashMap<Integer, HashMap<Integer, HashMap <Integer, String>>> mainMap = new LinkedHashMap<Integer, HashMap<Integer, HashMap <Integer, String>>>();
    HashMap<Integer, HashMap <Integer, String>> outerMap = new LinkedHashMap<Integer, HashMap<Integer, String>>();
    String[][][] array = new String[x.length][x.length][3];
    for (int i = 0; i < x.length; i++){
      String[] temp = x[i].split("");
      HashMap<Integer, String> innerMap = new LinkedHashMap<Integer, String>();
      for (int j = 0; j < temp.length; j++){
        
        innerMap.put(0, temp[j]);
        
      }

      
    }
    System.out.println(mainMap);
    return array;
  }

  public static String[][][] firstStar(String[][][] x){
    
    for (int i = 0; i < 6; i++){
      int [][][] data = new int[x.length+2][x[0].length+2][x[0][0].length+2];
      for (int j = 0 ; j < x.length; j++){
        for (int k = 0; k < x[j].length; k++){
          for (int l = 0; l < x[j][k].length; l++){
            String[][][] temp = x.clone();
            List<String> dataArround = getSurroundings(temp, j, k, l);
            int count = 0;
            System.out.println(dataArround);
            for (String valueIn : dataArround){
              if (valueIn.equals("#")){
                count++;
              }
            }
            data[j][k][l] = count;
          }
        }
      }
    }
    return x;
  }
  

private static int[][] directions = new int[][]{{-1,-1, 0}, {-1,0,0}, {-1,1,0},  {0,1,0}, {1,1,0},  {1,0,0},  {1,-1,0},  {0,-1,0}, {-1,-1, 1}, {-1,0,1}, {-1,1,1},  {0,1,1}, {1,1,1},  {1,0,1},  {1,-1,1},  {0,-1,1}, {-1,-1, -1}, {-1,0,-1}, {-1,1,-1},  {0,1,-1}, {1,1,-1},  {1,0,-1},  {1,-1,-1},  {0,-1,-1}};

static List<String> getSurroundings(String[][][] matrix, int x, int y, int z){
    List<String> res = new ArrayList<String>();
    for (int[] direction : directions) {
        int cx = x + direction[0];
        int cy = y + direction[1];
        int cz = z + direction[2];
        if (cx >= 0 && cx < matrix.length)
          if(cy >=0 && cy < matrix[cx].length)
              if(cz >= 0 && cz < matrix[cx][cy].length)
                  if (matrix[cx][cy][cz] == null){
                    res.add(".");
                  } else {
                    res.add(matrix[cx][cy][cz]);
                  }
                  
    }
    return res;
}
  public static String[] populateArray(){
    String[] array = new String[3];
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
