import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    List<List<List<String>>> threeDimensional = cleanInput(x);
    firstStar(threeDimensional);
  }
  public static List<List<List<String>>> cleanInput(String[] x){
    List<List<List<String>>> array = new ArrayList<List<List<String>>>();
    //String[][][] array = new String[x.length][x.length][3];
    List<List<String>> outer = new ArrayList<List<String>>();
    for (int i = 0; i < x.length; i++){
      String[] temp = x[i].split("");
      outer.add(Arrays.asList(temp));
      
    }
    for (int i = 0; i < x.length; i++){
      array.add(outer);
    }
    System.out.println(array);
    return array;
  }

  public static List<List<List<String>>> firstStar(List<List<List<String>>> list){
    for (int i = 0; i < 1; i++){

      int [][][] data = new int[list.size()][list.get(0).size()][list.get(i).get(i).size()];
      List<List<List<String>>> temp = new ArrayList(list);


      for (int j = 0 ; j < list.size(); j++){
        for (int k = 0; k < list.get(j).size(); k++){
          for (int l = 0; l < list.get(j).get(k).size(); l++){
            List<String> dataArround = getSurroundings(temp, j, k, l);
            int count = 0;
            //System.out.println(dataArround);
            for (String valueIn : dataArround){
              if (valueIn.equals("#")){
                count++;
              }
            }
            data[j][k][l] = count;
          }
        }
      }
      for (int j = 0; j < data.length; j++){
        System.out.println();
        for (int k = 0; k < data[j].length; k++){
          System.out.println(Arrays.toString(data[j][k]));
          for (int l = 0; l < data[j][k].length; l++){
            //System.out.println(list.get(j).get(k).get(l));
            if ((data[j][k][l] == 2 || data[j][k][l] == 3) && list.get(j).get(k).get(l).equals("#")){
              list.get(j).get(k).set(l, "#");
              //System.out.println("here1");
            } else if ((data[j][k][l] == 3) && list.get(j).get(k).get(l).equals(".")){
              list.get(j).get(k).set(l, "#");
              //System.out.println("here2");
            } else {
              list.get(j).get(k).set(l, ".");
            }
          }
        }
      }

      List<List<String>> tempArrTwo = new ArrayList<List<String>>();
      List<String> tempArr = new ArrayList<String>();
      for (int var = 0; var < list.get(0).size()+2; var++){
        tempArr.add(".");
      }
      for (int var = 0; var < list.get(0).size()+2; var++){
        tempArrTwo.add(tempArr);
      }
      List<List<List<String>>> newList = new ArrayList<List<List<String>>>();
      newList.add(tempArrTwo);
      //System.out.println(tempArrTwo);
      //System.out.println();
      for (List<List<String>> outer : list){
        List<List<String>> innerList = new ArrayList<List<String>>();

        //System.out.println(tempArr);
        innerList.add(tempArr);
        for (List<String> inner : outer){
          List<String> temp1;;
          temp1 = new ArrayList(inner);
          temp1.add(0, ".");
          temp1.add(temp1.size(), ".");
          //inner.add(inner.size()-1, ".");
          //System.out.println(temp1);
          innerList.add(temp1);
        }
        innerList.add(tempArr);
        //System.out.println(tempArr);
        newList.add(innerList);
        newList.add(tempArrTwo);
       // System.out.println();
        //System.out.println(tempArrTwo);
        System.out.println();
      }

      list = newList;




    }
    int count = 0;
    for (int j = 0; j < list.size(); j++){
      System.out.println();
      for (int k = 0; k < list.get(j).size(); k++){
        System.out.println(list.get(j).get(k));
        for (int l = 0; l < list.get(j).get(k).size(); l++){
          if (list.get(j).get(k).get(l).equals("#")){
            count++;
          } 
        }
        
      }
    }
    System.out.println(count);

    return list;
  }
  

private static int[][] directions = new int[][]{{-1,-1, 0}, {-1,0,0}, {-1,1,0},  {0,1,0}, {1,1,0},  {1,0,0},  {1,-1,0},  {0,-1,0}, {-1,-1, 1}, {-1,0,1}, {-1,1,1},  {0,1,1}, {1,1,1},  {1,0,1},  {1,-1,1},  {0,-1,1}, {0,0,1}, {-1,-1, -1}, {-1,0,-1}, {-1,1,-1},  {0,1,-1}, {1,1,-1},  {1,0,-1},  {1,-1,-1},  {0,-1,-1}, {0,0,-1}};

static List<String> getSurroundings(List<List<List<String>>> matrix, int z, int x, int y){
    List<String> res = new ArrayList<String>();
    for (int[] direction : directions) {
        int cx = x + direction[0];
        int cy = y + direction[1];
        int cz = z + direction[2];
        if (cz >= 0 && cz < matrix.size())
          if(cx >=0 && cx < matrix.get(cz).size())
              if(cy >= 0 && cy < matrix.get(cz).get(cx).size())
                  if (matrix.get(cz).get(cx).get(cy) == null){
                    res.add(".");
                  } else {
                    res.add(matrix.get(cz).get(cx).get(cy));
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
