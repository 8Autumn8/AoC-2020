import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  public static HashMap<Integer, String> dataValue = new LinkedHashMap<Integer, String>();
  public static HashMap<String, String> yourTicket = new LinkedHashMap<String, String>();
  public static HashMap<Integer, String> otherTicket = new LinkedHashMap<Integer, String>();

  public static void main(String[] args) {
    populateArray();
    firstStar();
  }
  public static void firstStar(){
    int sum = 0;
    for (Integer x : otherTicket.keySet()){
      String[] split = otherTicket.get(x).split(",");
      System.out.println("ticket is: " + Arrays.toString(split));
      for (int i = 0; i < split.length; i++){
        int ticket = Integer.parseInt(split[i]);
        System.out.println("current value: " + ticket);
        String values = dataValue.get(i).replaceAll("\\s", "");
        String[] twoValues = values.split("or");
        String[] one = twoValues[0].split("-");
        String[] two = twoValues[1].split("-");
        if (!(ticket >= Integer.parseInt(one[0]) && ticket <= Integer.parseInt(one[1])) && !((ticket >= Integer.parseInt(two[0]) && ticket <= Integer.parseInt(two[1])))){
          System.out.println("not valid");
          sum+=ticket;
        }
      }
    }
    System.out.println(sum);


    /**
    String validSeat = dataValue.get("seat").replaceAll("\\s", "");
    String[] twoSeat = validSeat.split("or");
    String[] seatOne = twoSeat[0].split("-");
    String[] seatTwo = twoSeat[1].split("-");
    System.out.println("range 1: " + Arrays.toString(seatOne));
    System.out.println("range 2: " + Arrays.toString(seatTwo));
    String nearTicket = dataValue.get("nearby tickets:");
    String[] split = nearTicket.split(",");
    int sum = 0;
    for (String x : split){
      int ticket = Integer.parseInt(x);
      System.out.println("ticket is: " + ticket);
      if (ticket >= Integer.parseInt(seatOne[0]) && (ticket <= Integer.parseInt(seatOne[1]))){

      } else if (ticket >= Integer.parseInt(seatTwo[0]) && (ticket <= Integer.parseInt(seatTwo[1]))){

      } else {
        System.out.println("is not valid");
        sum+=ticket;
      }
    }
    System.out.println(sum);**/

  }
  public static void populateArray(){
    //dataValue = new LinkedHashMap<String, String>();
    //yourTicket = new LinkedHashMap<String, String>();
    //otherTicket = new LinkedHashMap<Integer, String>();

    try {
      File myObj = new File("test.txt");
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
