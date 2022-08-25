import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  private static List<Integer> playerOne = new ArrayList<Integer>();
   private static List<Integer> playerTwo = new ArrayList<Integer>();

  public static void main(String[] args) {
    populateArray();
    System.out.println(playerOne);
    System.out.println(playerTwo);
    firstStar();
  }
  
  public static void firstStar(){
    while (playerOne.size() > 0 && playerTwo.size() > 0){
      int onePlays = playerOne.get(0);
      int twoPlays = playerTwo.get(0);
      playerOne.remove(playerOne.indexOf(onePlays));
      playerTwo.remove(playerTwo.indexOf(twoPlays));
      if (onePlays > twoPlays){
        playerOne.add(onePlays);
        playerOne.add(twoPlays);
      } else if (twoPlays > onePlays) {
        playerTwo.add(twoPlays);
        playerTwo.add(onePlays);
      }
      System.out.println(playerOne);
      System.out.println(playerTwo);
    }
    int score = 0;
    int count = 1;
    if (playerOne.size() > 0){
      for (int i = playerOne.size()-1; i >= 0; i--){
        score+=(playerOne.get(i)*count);
        count++;
      }
    } else if (playerTwo.size() > 0){
      for (int i = playerTwo.size()-1; i >= 0; i--){
        score+=(playerTwo.get(i)*count);
        count++;
      }
    }
    System.out.println(score);
    
  }
  public static void populateArray(){
    try {
      File myObj = new File("puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        if (data.contains("Player 1:")){
          while (myReader.hasNextLine()){
            String next = myReader.nextLine();
            if (next.isEmpty()){
              break;
            } else {
              playerOne.add(Integer.parseInt(next));
            }
            
          }
        } else {
          while (myReader.hasNextLine()){
            String next = myReader.nextLine();
            if (next.isEmpty()){
              
              break;
            } else {
              playerTwo.add(Integer.parseInt(next));
            }
            
          }
        }
        

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
