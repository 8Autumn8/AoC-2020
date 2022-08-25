import java.util.*;  

// A simple Java program for traversal of a linked list 
class LinkedList  { 
    Node head; // head of list 
  
    /* Linked list Node.  This inner class is made static so that 
       main() can access it */
    static class Node { 
        int data; 
        Node next; 
        Node(int d) 
        { 
            data = d; 
            next = null; 
        } // Constructor 
    } 
  
    /* This function prints contents of linked list starting from head */
    public void printList() 
    { 
        Node n = head; 
        while (n != null) { 
            System.out.print(n.data + " "); 
            n = n.next; 
        } 
    } 
  
    /* method to create a simple linked list with 3 nodes*/
    public static void main(String[] args) 
    { 
        /* Start with the empty list. */
        LinkedList llist = new LinkedList(); 
  
        llist.head = new Node(2); 
        Node last = llist.head;
        String value = "19347865";
        for (int i = 0; i < value.length(); i++){
            Node newNode = new Node(Integer.parseInt(value.substring(i,i+1)));
            last.next= newNode;
            last = newNode;
        }
            
       last = llist.head;
        for (int i = 10; i <= 1000000;i++){
            Node newNode = new Node(i);
            last.next= newNode;
            last = newNode;
        }          
        System.out.println("done");
  
//        llist.head.next = second; // Link first node with the second node 
//        second.next = third; // Link second node with the third node 
  
        Node n = llist.head; 
        
        int min = 1;
        int max = 9;
        for (int i = 0; i < 10000000; i++) { 
            n = llist.head; 
            Node first = n.next;
            Node second = first.next;
            Node third = second.next;
            Node fourth = third.next;
  
            int currCup = n.data;
            int cupOne = first.data;
            int cupTwo = second.data;
            int cupThree = third.data;
            int destinationCup = currCup-1;
              while (destinationCup == cupOne || destinationCup == cupTwo || destinationCup == cupThree || destinationCup == currCup || destinationCup < min){
                destinationCup--;
                if (destinationCup < min){
                  destinationCup = max;
                  while (destinationCup == cupOne || destinationCup == cupTwo || destinationCup == cupThree || destinationCup == currCup){
                    destinationCup--;
                  }
                  break;
                }
              }
              
            Node temp = llist.head;
            while (temp.data != destinationCup){
                temp = temp.next;
            }               
            third.next = temp.next;
            temp.next =first;
            
            System.out.print(n.data + " "); 
            if (last == temp)
                third.next = n;
            else
                last.next = n;
            last = n;
            last.next = null;
            llist.head = fourth; 
            //llist.printList();
            //System.out.println();
        } 
        n = llist.head; 
            
            while (n.next != null && n.next.data != 1){
                 n = n.next;
        
            }
            System.out.println(n.data);
            System.out.println(n.next.next.data);
    } 
}
