import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  public static HashMap<Integer, String> dataValue = new LinkedHashMap<Integer, String>();
  public static HashMap<String, String> yourTicket = new LinkedHashMap<String, String>();
  public static HashMap<Integer, String> otherTicket = new LinkedHashMap<Integer, String>();
  public static HashMap<Integer, String> validTicket = new LinkedHashMap<Integer, String>();
  public static void main(String[] args) {
    populateArray();
    firstStar();
    secondStar();
  }

  public static void secondStar(){
    List<List<Integer>> listofPossible = new ArrayList<List<Integer>>();
    int index = 0;
    for (int k = 0; k < dataValue.size(); k++){
      
      //System.out.println("here");
      HashMap<Integer, Integer> bucketList = new HashMap<Integer, Integer>();      
      for (String x : validTicket.values()){
        String[] split = x.split(",");
        
        int value = Integer.parseInt(split[index]);
        bucketList.put(value, 0);
        
      }
      //System.out.println(bucketList);
      
      

      for (Map.Entry<Integer, String> map : dataValue.entrySet()){
        String full = map.getValue();
        int x = map.getKey();
        String values = full.replaceAll("\\s", "");
        String[] twoValues = values.split("or");
        String[] one = twoValues[0].split("-");
        String[] two = twoValues[1].split("-");
        //System.out.println(Arrays.toString(one) + Arrays.toString(two));
        boolean isTheThing = true;
        for (Integer i : bucketList.keySet()){
          if (!(i >= Integer.parseInt(one[0]) && i <= Integer.parseInt(one[1])) && !((i >= Integer.parseInt(two[0]) && i <= Integer.parseInt(two[1])))){
            isTheThing = false;
          } 
        }
        if (isTheThing){
          System.out.println("location in the map: " + x + " :column: " + index );
          List<Integer> array = new ArrayList<Integer>();
          array.add(x);
          array.add(index);
          listofPossible.add(array);
          //temp.remove(x);
          
        } 
      }
      index++;   
    }

    System.out.println(listofPossible);
    List<List<Integer>> actualValues = new ArrayList<List<Integer>>();
    for (List<Integer> outerLoop : listofPossible){
      int mainkey = outerLoop.get(0);
      int maincolumn = outerLoop.get(1);
      boolean key = false;
      boolean column = false;
      for (List<Integer> list : listofPossible){
        int newkey = list.get(0);
        int newcolumn = list.get(1);
        if (mainkey == newkey){
          key = true;
        } else if (maincolumn == newcolumn){
          column = true;
        }
      }
      if (!key){
        actualValues.add(outerLoop);
        for (List<Integer> list : listofPossible){
          int newkey = list.get(0);
          int newcolumn = list.get(1);
          if (maincolumn == mainkey){
            //outerLoop.remove()
          }
        }
      } else if (!column){
        actualValues.add(outerLoop);
      }
    }

    System.out.println(actualValues);
    for (String x : yourTicket.keySet()){
      String[] split = yourTicket.get(x).replaceAll("\\s", "").split(",");
      for (int i = 0; i < split.length; i++){
        System.out.println(i + ": " + split[i]);
      }
    }

 
    
  }


  public static void firstStar(){
    int sum = 0;
    int count = 0;
    for (Integer x : otherTicket.keySet()){
      String[] split = otherTicket.get(x).split(",");
      //System.out.println("ticket is: " + Arrays.toString(split));
      boolean isValid = true;
      for (int i = 0; i < split.length; i++){
        int ticket = Integer.parseInt(split[i]);
        //System.out.println("current value: " + ticket);
        int tempCount = 0;
        for (int j = 0; j < split.length; j++){
            String values = dataValue.get(j).replaceAll("\\s", "");
            String[] twoValues = values.split("or");
            String[] one = twoValues[0].split("-");
            String[] two = twoValues[1].split("-");
            //System.out.println(Arrays.toString(one) + Arrays.toString(two));

            if (!(ticket >= Integer.parseInt(one[0]) && ticket <= Integer.parseInt(one[1])) && !((ticket >= Integer.parseInt(two[0]) && ticket <= Integer.parseInt(two[1])))){
              
              tempCount++;
            } else {
              break;
            }
        }
        if (tempCount > split.length-1){
          //System.out.println("is not valid");
          //System.out.println(tempCount);
          isValid = false;
          break;
        } 

      }
      if (isValid){
        validTicket.put(count, otherTicket.get(x));
        count++;
      }

    }
    System.out.println(validTicket);


  }
  public static void populateArray(){
    //dataValue = new LinkedHashMap<String, String>();
    //yourTicket = new LinkedHashMap<String, String>();
    //otherTicket = new LinkedHashMap<Integer, String>();

    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      int count = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] split = data.split(":");
        if (split.length == 1){
          break;
        }
        dataValue.put(count, split[1]);
        count++;
      }
      while (myReader.hasNextLine()){
        String line1 = myReader.nextLine();
        String line2 = myReader.nextLine();
        yourTicket.put(line1, line2);
        if (myReader.nextLine().isEmpty()){
          break;
        }
      }
      count = 0;
      while (myReader.hasNextLine()){
        String data = myReader.nextLine();
        if (!data.equals("nearby tickets:")){
          otherTicket.put(count, data);
          count++;
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    System.out.println(dataValue);
    System.out.println(yourTicket);
    System.out.println(otherTicket);
  }
}
