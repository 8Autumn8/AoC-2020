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
    boolean x = firstStar(playerOne, playerTwo);
    if (x){
      System.out.println("one won");
    } else {
      System.out.println("two won");
    }
    int score = 0;
    int count = 1;
    if (playerOne.size() > 0){
      System.out.println("getting score for 1");
      for (int i = playerOne.size()-1; i >= 0; i--){
        score+=(playerOne.get(i)*count);
        count++;
      }
    }
    System.out.println(score);
    score = 0;
    if (playerTwo.size() > 0){
      System.out.println("getting score for two");
      for (int i = playerTwo.size()-1; i >= 0; i--){
        score+=(playerTwo.get(i)*count);
        count++;
      }
    }
    System.out.println(score);
  }

  public static String determineWinner(List<String> one, List<String> two){
    
    return "";
  }
  
  public static boolean firstStar(List<Integer> playerOneNew, List<Integer> playerTwoNew){
    List<List<List<Integer>>> history = new ArrayList<List<List<Integer>>>();
    //System.out.println("new game");
    while (playerOneNew.size() > 0 && playerTwoNew.size() > 0){
      //System.out.println(playerOneNew);
      //System.out.println(playerTwoNew);      
      int onePlays = playerOneNew.get(0);
      
      int twoPlays = playerTwoNew.get(0);
      //System.out.println(onePlays);
      //System.out.println(twoPlays);

      List<List<Integer>> checkHistoryMain = new ArrayList<List<Integer>>();
      List<Integer> checkHistoryOne = new ArrayList<Integer>(playerOneNew);
      List<Integer> checkHistoryTwo = new ArrayList<Integer>(playerTwoNew);
      checkHistoryMain.add(checkHistoryOne);
      checkHistoryMain.add(checkHistoryTwo);
      if (history.contains(checkHistoryMain)){
        playerOne = playerOneNew;
        playerTwo = playerTwoNew;
        //System.out.println("repeat");
        return true;
      } else {
        history.add(checkHistoryMain);
      }
      playerOneNew.remove(playerOneNew.indexOf(onePlays));
      playerTwoNew.remove(playerTwoNew.indexOf(twoPlays));
      if (playerOneNew.size() >= onePlays && playerTwoNew.size() >= twoPlays){
        List<Integer> oneTemp = new ArrayList<Integer>();
        List<Integer> twoTemp = new ArrayList<Integer>();
        for (int i = 0; i < onePlays; i++){
          oneTemp.add(playerOneNew.get(i));
        }
        for (int i = 0; i < twoPlays; i++){
          twoTemp.add(playerTwoNew.get(i));
        }        
        if (firstStar(oneTemp, twoTemp)){
          playerOneNew.add(onePlays);
          playerOneNew.add(twoPlays);
        } else {
          playerTwoNew.add(twoPlays);
          playerTwoNew.add(onePlays);          
        }
      }else if (onePlays > twoPlays){
        playerOneNew.add(onePlays);
        playerOneNew.add(twoPlays);
      } else if (twoPlays > onePlays) {
        playerTwoNew.add(twoPlays);
        playerTwoNew.add(onePlays);
      }
    }
    //System.out.println("end");
    if (playerOneNew.size() > 0){
      playerOne = playerOneNew;
      playerTwo = playerTwoNew;
      return true;
    } else if (playerTwoNew.size() > 0){
      playerOne = playerOneNew;
      playerTwo = playerTwoNew;
      return false;
    }
    return false;
    
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
