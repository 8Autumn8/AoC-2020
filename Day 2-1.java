import java.util.*;

class Main {
  public static void main(String[] args) {
    int numValidPass = 0;
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 1000; i++){
      String input = sc.nextLine();
      
      String[] arrOfStr = input.split("[-: ]", 4); 
      
      String special_char = arrOfStr[2];
      int min = Integer.parseInt(arrOfStr[0]) ;
      int max = Integer.parseInt(arrOfStr[1]);
      int occurance = 0;
      String pass = arrOfStr[3]; 
      System.out.println(Arrays.toString(arrOfStr));
      for (int j = 0; j < pass.length(); j++){
        if (pass.substring(j,j+1).equals(special_char)){
          occurance++;  
        }
      }
      if (occurance >= min && occurance <= max){
        System.out.println(pass);
        numValidPass++;
      }
    }
    System.out.println(numValidPass);
  }
}
