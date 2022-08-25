import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;


class Main {
  public static void main(String[] args) {
    String[] x = populateArray();
    firstStar(x);
  }
  public static boolean isNumeric(String strNum) {
      if (strNum == null) {
          return false;
      }
      try {
          double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
          return false;
      }
      return true;
  }

  public static void firstStar(String[] x){
    HashMap<String,Integer> priority = new HashMap<String,Integer>();
    priority.put("*", 1);
    priority.put("+", 2);
    List<String> allValues = new ArrayList<String>();
    Stack<String> stack = new Stack<String>(); 
    
    for (String equation : x){
      equation = equation.replaceAll("\\s", "");
      System.out.println("equation: " + equation);
      String post = "";
      for (int i = 0; i < equation.length(); i++){
        String temp = equation.substring(i,i+1);
        System.out.println("token: " + temp);
        if(isNumeric(temp)){
          post+=temp;
        } else {
          //System.out.println("stack size: " + stack.size());
          if (stack.size() != 0){
            String previous = stack.peek();
            if ((priority.containsKey(previous) && (priority.containsKey(temp)))){
              if (priority.get(previous) <= (priority.get(temp))){
                post+=stack.pop();
                stack.push(temp);
              }
            } else {
              stack.push(temp);
            }
            //System.out.println("test " + stack);
            if(temp.equals(")")){
              
              while (!previous.equals("(") && stack.size() != 0){
                previous = stack.peek();
                if (!previous.equals("(") && !previous.equals(")")){
                  post+=previous;
                  stack.pop();
                } else {
                  stack.pop();
                }
              }
            }

          } else {
            stack.push(temp);
          }

        }
        if (i == equation.length()-1){
          for (int k = 0; k < stack.size(); k++){
            post+=stack.pop();
          }
        }
        System.out.println(stack);
        System.out.println(post);
        
        
      }
      System.out.println(post);
      allValues.add(post);
      stack.clear();

    }
    long allValuesAdded = 0;
    System.out.println(allValues);
    System.out.println("*************************************************************");
    Stack<Long> stackTwo = new Stack<Long>(); 
    for (String post : allValues){
      System.out.println(post);
      boolean doMath = false;
      String sign = "";
      for (int i = 0; i < post.length();i++){
        String temp = post.substring(i, i+1);
        if (isNumeric(temp)){
          stackTwo.push(Long.parseLong(temp));
        } else {
          long num1 = stackTwo.pop();
          long num2 = stackTwo.pop();
          long sum = 0;
          if (temp.equals("+")){
            sum = num1+num2;
          } else if (temp.equals("*")){
            sum = num1*num2;
          }
          stackTwo.push(sum);
        

        }
        //System.out.println(stackTwo);
      }
      System.out.println(stackTwo);
      allValuesAdded+=stackTwo.peek();
      stackTwo.clear();
    }
    System.out.println("sum: " + allValuesAdded);
    
  }

  public static String[] populateArray(){
    String[] array = new String[7];
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
