import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    String[] arrOfStr = x[0].split(","); 
    //System.out.println(Arrays.toString(x));
    System.out.println(Arrays.toString(arrOfStr));
    firstStar(arrOfStr);
  }

  static int inv(int a, int m) 
  { 
      int m0 = m, t, q; 
      int x0 = 0, x1 = 1; 
    
      if (m == 1) 
      return 0; 
    
      // Apply extended Euclid Algorithm 
      while (a > 1) 
      { 
          // q is quotient 
          q = a / m; 
    
          t = m; 
    
          // m is remainder now, process 
          // same as euclid's algo 
          m = a % m;a = t; 
    
          t = x0; 
    
          x0 = x1 - q * x0; 
    
          x1 = t; 
      } 
    
      // Make x1 positive 
      if (x1 < 0) 
      x1 += m0; 
    
      return x1; 
  } 
  public static String[] firstStar(String[] arrOfStr){
    List<Integer> nums = new ArrayList<Integer>();
    List<Integer> rem = new ArrayList<Integer>();
    for (int i = 0; i < arrOfStr.length; i++){
      if (!arrOfStr[i].equals("x")){
        nums.add(Integer.parseInt(arrOfStr[i]));
        rem.add(i);
      }
    }
    int[] primeNum = new int[nums.size()];
    int[] remainder = new int[rem.size()];
    for (int i = 0; i < primeNum.length; i++){
      primeNum[i] = nums.get(i);
      remainder[i] = rem.get(i);
    }
    int k = primeNum.length;
    
    System.out.println(findMinX(primeNum, remainder, k));
    return arrOfStr;
  }

  static int findMinX(int[] num, int[] rem, int k) 
  { 
      // Compute product of all numbers 
      int prod = 1; 
      for (int i = 0; i < k; i++) 
          prod *= num[i]; 
    
      // Initialize result 
      int result = 0; 
    
      // Apply above formula 
      for (int i = 0; i < k; i++) 
      { 
          int pp = prod / num[i]; 
          result += rem[i] * inv(pp, num[i]) * pp; 
      } 
    
      return result % prod; 
  } 

  public static String[] populateArray(){
    String[] array = new String[1];
    int index = 0;
    try {
      File myObj = new File("puzzle.txt");
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
