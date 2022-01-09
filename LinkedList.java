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
        ll.traverse(head);
        
    }
}
