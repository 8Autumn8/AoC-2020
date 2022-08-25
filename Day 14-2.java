import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  
import java.util.*;

class Main {
  public static void main(String[] args) {
    Map<String,Map<Integer,Long>> map = populateArray();
    //System.out.println(firstStar(map));
    Map<String,Long> sums = firstStar(map);
		long sum = 0L;
		for (String key : sums.keySet()) {
      System.out.println(Long.parseLong(key,2));
			//sum += val;
		}
    System.out.println(sum);
    //System.out.println(sums.size());


  }

  public static long getDecimal(String binary){  
    long ans = 0;
    int position = 0;
    for (int i = binary.length(); i > 0; i--){
      if (binary.substring(i-1, i).equals("1")){
        ans += Math.pow(2,position);
      }
      position++;
    }
    //System.out.println(ans);
    
    return ans;
  }  
  public static String getBinary(long decimal){
    String ans = "";
     
    for (int i = 35; i >= 0; i--){
      if (decimal - Math.pow(2,i) > 0){
        ans+= "1";
        decimal-= Math.pow(2,i);
      } else if (decimal == Math.pow(2,i) && decimal != 0){
        ans+= "1";
        decimal-= Math.pow(2,i);
      } else {
        ans += "0";
      }

    }

    return ans;
  }
	private static ArrayList<String> decodeAddresses(long address, String mask) {
		// ret will contain all combinations of possible binary addresses
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("");

		// convert address to binary and pad with leading zeros
		String binAdd = Long.toBinaryString(address);
		while (binAdd.length() < 36) {
			binAdd = "0" + binAdd;
		}

		// look at every char in mask
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == 'X') {
				// an X could be a 1 or a 0
				// for every element in ret, replace it with 2 more strings
				// one that ends in a 1, one that ends in a 0
				int s = ret.size(); // must lock in the current size of ret before looping or we will accidentally
									// loop too far
				for (int a = 0; a < s; a++) {
					String add = ret.remove(0);
					ret.add(add + "0");
					ret.add(add + "1");
				}
			} else if (mask.charAt(i) == '1') {
				// a 1 means the address must have a 1 at this location
				for (int a = 0; a < ret.size(); a++) {
					ret.set(a, ret.get(a) + "1");
				}
			} else if (mask.charAt(i) == '0') {
				// a 0 means the new address must match the original address at this location
				for (int a = 0; a < ret.size(); a++) {
					ret.set(a, ret.get(a) + binAdd.substring(i, i + 1));
				}
			}
		}

		return ret;
	}		

  public static Map<String,Long> firstStar(Map<String,Map<Integer,Long>> outerMap){
    Map<String,Long> sumAns = new LinkedHashMap<String,Long>();
    int addedValueTime = 0;
    long sumWithout = 0;
    for (String x : outerMap.keySet()){
      Map<Integer,Long> innerMap = outerMap.get(x);
      for (Integer key : innerMap.keySet()){
        long value = (innerMap.get(key));
        /**if (sumAns.containsKey(key)){
          value = sumAns.get(key);
        }**/
        String ans = "";
        //System.out.println("value: " + value);
        String bin = getBinary(value);
        //System.out.println(bin);
        //System.out.println(x);
        for (int i = 0; i < bin.length(); i++){
          if (x.substring(i, i+1).equals("X")){
            ans+=x.substring(i, i+1);
          } else if (x.substring(i, i+1).equals("1")){
            ans+=x.substring(i, i+1);
          } else {
            ans+=bin.substring(i, i+1);

          }
        }
        System.out.println("value: " + value + "to binary: " + ans);
        ArrayList<String> addresses = decodeAddresses(value, x);
        
				for (String add : addresses) {
					if (!sumAns.containsKey(add)) {
						sumAns.put(add, value);
					} else {
						sumAns.replace(add, value);
					}
				}
      }
      outerMap.put(x,innerMap);
    }
    System.out.println(sumAns);
    return sumAns;
  }

  public static Map<String,Map<Integer,Long>> populateArray(){
   Map<String,Map<Integer,Long>> outerMap = new LinkedHashMap<String,Map<Integer,Long>>();

    try{
      
      File myObj = new File("ex.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        while (myReader.hasNextLine()) {
          if (data.contains("mask")){
            String temp ="";
            Map<Integer,Long> innerMap = new LinkedHashMap<Integer,Long>();
            while (myReader.hasNextLine()) {
              String innerData = myReader.nextLine();
              if (innerData.contains("mask")){
                temp = innerData;
                break;
              } else {
                String[] splitArray = innerData.replaceAll("\\s","").split("[\\[\\]=]");
                //System.out.println(Arrays.toString(splitArray));
                innerMap.put(Integer.parseInt(splitArray[1]),Long.parseLong(splitArray[3])); 
                 
              }
            }
            String[] splitArray = data.replaceAll("\\s","").split("=");
            outerMap.put(splitArray[1], innerMap);
            data = temp;
          }
        }
      }
      myReader.close();
    } catch (Exception e){
      System.out.println("error");
    }
    //System.out.println(outerMap);
    //System.out.println("size" + outerMap.size());
    return outerMap;
  }

}
