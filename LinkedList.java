public class LinkedList {

    public ListNode addAtTheEnd(int data,ListNode head){
        ListNode node = new ListNode(data);
        ListNode start = head;
        node.next = null;
        if(head== null){
            return node;
        }else {
           while(start.next != null){
               start= start.next;
           }

           start.next = node ;
           return head;
        }
    }

    public void traverse(ListNode head){
        if(head ==null){
            return;
        }

        System.out.println(head.val);
        traverse(head.next);

    }

    public ListNode reverse(ListNode head){
        if(head == null) {
            return head;
        }
  
        // last node or only one node
        if(head.next == null) {
            return head;
        }
  
        ListNode newHeadNode = reverse(head.next);
  
        // change references for middle chain
        head.next.next = head;
        head.next = null;
  
        // send back new head node in every recursion
        return newHeadNode;


    }
    
    public static void main(String[] args){
        
        LinkedList ll = new LinkedList();
        ListNode head = ll.addAtTheEnd(1, null);
        head = ll.addAtTheEnd(2, head);
        head = ll.addAtTheEnd(3, head);
        head = ll.addAtTheEnd(4, head);
        head = ll.addAtTheEnd(5, head);
        head = ll.addAtTheEnd(6, head);
        head = ll.addAtTheEnd(7, head);
        head = ll.addAtTheEnd(8, head);
        head = ll.reverse(head);
        ll.traverse(head);
        
    }
}
