import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    firstStar(x);
  }

  public static void firstStar(String[] x){
    int sum = 0;
    for (String equation : x){
      //for (int i = 0; i < equation.length(); i++){
        String[] split = equation.replaceAll("\\s","").split("[()]");
        System.out.println(Arrays.toString(split));
        List<String> order = new ArrayList<String>();
        
        int valueOne = 0;
        int valueTwo = 0;
        boolean first = true;
        boolean isAdd = true;
        boolean hasTwo = false;        
        for (String smallerValue : split){
          if (smallerValue != null){
            valueOne = 0;
            valueTwo = 0;
            first = true;
            hasTwo = false; 
            for (int j = 0; j < smallerValue.length(); j++){
              
              
              try{
                if (first){
                  valueOne = Integer.parseInt(smallerValue.substring(j,j+1));
                  first = false;
                } else {
                  valueTwo = Integer.parseInt(smallerValue.substring(j,j+1));
                  hasTwo = true;
                }
                
                //System.out.println("valueOne is: " + valueOne);
                //System.out.println("valueTwo is: " + valueTwo);
              }catch (Exception e){
                String partOfValue = smallerValue.substring(j,j+1);
                if (partOfValue.equals("*")){
                  isAdd = false;
                } else if (partOfValue.equals("+")){
                  isAdd = true;
                }
              }
              if (hasTwo){
                System.out.println("inital value one: " + valueOne);
                System.out.println("inital value two: " + valueTwo);
                if (isAdd){
                  valueOne+= valueTwo;
                  
                } else {
                  valueOne*=valueTwo;
                }
                valueTwo = 0;
                hasTwo = false;
                System.out.println("value one: " +valueOne);
                
              }
              if (j == 0 && (smallerValue.substring(j,j+1).equals("*") || smallerValue.substring(j,j+1).equals("+"))){
                order.add(smallerValue.substring(j,j+1));
              
              } else if (j == smallerValue.length()-1 && !(smallerValue.substring(j,j+1).equals("*") || smallerValue.substring(j,j+1).equals("+"))){
                order.add(Integer.toString(valueOne));
                System.out.println(order);
              } else 
              if (j == smallerValue.length()-1 && (smallerValue.substring(j,j+1).equals("*") || smallerValue.substring(j,j+1).equals("+"))){
                order.add(Integer.toString(valueOne));
                order.add(smallerValue.substring(j,j+1));
                System.out.println(order);
              }
            }
            
          }
        }
        valueOne = 0;
        valueTwo = 0;
        first = true;
        hasTwo = false; 
        for (String value : order){
          try{
            if (first){
              valueOne = Integer.parseInt(value);
              first = false;
            } else {
              valueTwo = Integer.parseInt(value);
              hasTwo = true;
            }
            
          } catch (Exception e){
            if (value.equals("*")){
              isAdd = false;
            } else if (value.equals("+")){
              isAdd = true;
            }
          }
          if (hasTwo){
            if (isAdd){
              valueOne+= valueTwo;
              
            } else {
              valueOne*=valueTwo;
          
            }
          
          }
        }
        System.out.println("all: " + valueOne);
        sum+=valueOne;

    }

    System.out.println(sum);
    
  }

  public static String[] populateArray(){
    String[] array = new String[6];
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
