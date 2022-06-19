import java.util.*;
import java.util.LinkedList;;

public class BinaryTreeAll {

static class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
// used to claculate diameter
static class TreeInfo{
    int diameter;
    int height;

    TreeInfo(int diameter, int height){
        this.diameter = diameter;
        this.height = height;
    }
}

//used in calculatng top view and bottom view of a tree
static class Pair{
    int hd;//horizontal distance
    Node node;

    Pair(int hd, Node node){
        this.hd = hd;
        this.node = node;
    }
}

 static class BinaryTree{
    static int idx =-1;
    public static Node buildTree(int nodes[]){
        idx++;
        if(nodes[idx]== -1){
            return null;
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    //traversals , preOrder , postOrder , inOrder works on DFS ,all having complexcity of O(n)
    public static void preOrderTraverse(Node root){
        if(root== null){
            return ;
        }else {
            System.out.println(root.data);
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }

    }

    public static void inOrderTraversal(Node root){
        if(root== null){
            return ;
        }else {
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }

    }

    public static void postOrderTraversal(Node root){
        if(root== null){
            return ;
        }else {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }

    }

    // it is based on BFS  ans uses Queue for its implementation , complexcity = O(n)
    public static void levelOrderTraverse(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()){
            Node currentNode = queue.remove();
            if(currentNode == null){
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }else {
                    queue.add(null);
                }
            }else{
                System.out.print(currentNode.data+" ");
                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }

                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
            }
        }
    }

    //Difference from normal level order traversal
    // * Instead of printing add the element to the Stack
    // * Traverse Right part first before the left one 
    public static void reverseLevelOrderTraversal(Node root){
        Queue<Node> queue = new LinkedList<Node>();
        Stack<Integer> stack = new Stack<Integer>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()){
            Node currentNode = queue.remove();
            if(currentNode == null){
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }else {
                    queue.add(null);
                }
            }else{
                // System.out.print(currentNode.data+" ");
                stack.push(currentNode.data);
                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }

                
            }
        }

        // Now pop all items from stack one by one and print them
        while (!stack.isEmpty())
        {
            int data = stack.peek();
            System.out.print(data + " ");
            stack.pop();
        }
    }
    //other basic btree problems
    public int countOfNodes(Node root){
        if(root == null){ 
            return 0;
        }
        int leftCount = countOfNodes(root.left);
        int rightCount = countOfNodes(root.right);
        return leftCount + rightCount +1 ;
    }  

    public int sumOfNodes(Node root){
        if(root == null){ 
            return 0;
        }
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }  

    public int heightOfTree(Node root){
        if(root == null){
            return 0;
        }else {
            int leftHeight = heightOfTree(root.left);
            int rightHeight = heightOfTree(root.right);

            int max = leftHeight >= rightHeight ? leftHeight: rightHeight;
            return max+1;

        }
    }

    TreeInfo diameterOfTree(Node root){
        if(root == null){
            return  new TreeInfo(0, 0);
        }
        
        TreeInfo left = diameterOfTree(root.left);
        TreeInfo right = diameterOfTree(root.right);

        int dia1 = left.diameter;
        int dia2 = right.diameter;
        int dia3 = left.height + right.height +1;

        int myheight = Math.max(left.height,right.height)+1;
        int myDia = Math.max(dia1, Math.max(dia2, dia3));

        return new TreeInfo(myDia, myheight);


    }

    public boolean isIdentical(Node mainTreeRoot, Node subTreeRoot){
        //check if both nodes are leaf
        if(mainTreeRoot == null && subTreeRoot == null) {
            return true;
        }

        //check if any one is not leaf
        if(mainTreeRoot == null || subTreeRoot == null){
            return false;
        }
        // now if values are same we will be moving to left part and right part of the tree, else returning false directly
        if(mainTreeRoot.data == subTreeRoot.data){
        return isIdentical(mainTreeRoot.left, subTreeRoot.left) && isIdentical(mainTreeRoot.right, subTreeRoot.right);
        }

        return false;
    }

    public boolean isSubTree(Node mainTreeRoot, Node subTreeRoot){
        if(subTreeRoot == null){
            return true;
        }

        if(mainTreeRoot == null){
            return false;
        }

        if(mainTreeRoot.data == subTreeRoot.data){
                if(isIdentical(mainTreeRoot,subTreeRoot)){
                    return true;
                }
        }

        return isSubTree(mainTreeRoot.left, subTreeRoot) || isSubTree(mainTreeRoot.right, subTreeRoot);
    }

    public Node mirrorImageRecursively(Node root){
        if(root == null){
            return null;
        }

        Node left = mirrorImageRecursively(root.left);
        Node right = mirrorImageRecursively(root.right);
        root.left = right ;
        root.right = left;
        return root;
    }

    public void mirrorImageItetrative(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node currentNode = queue.remove();

            //swap left child with the right one 
            Node temp = currentNode.left;
            currentNode.left = currentNode.right;
            currentNode.right = temp;

            if(currentNode.left != null){
                queue.add(currentNode.left);
            }

            if(currentNode.right != null){
                queue.add(currentNode.right);
            }
        }
        
    }

    //left view of the tree, used level Order traversing based on BFS (Queue)

    public void leftViewOfTree(Node root){
        if (root == null)
            return;
 
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
 
        while (!queue.isEmpty()) {
            // number of nodes at current level
            int n = queue.size();
 
            // Traverse all nodes of current level
            for (int i = 1; i <= n; i++) {
                Node temp = queue.poll();
 
                // Print the left most element at
                // the level
                if (i == 1)
                    System.out.print(temp.data + " ");
 
                // Add left node to queue
                if (temp.left != null)
                    queue.add(temp.left);
 
                // Add right node to queue
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    public void rightViewOfTree(Node root){
        if (root == null)
        return;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        // number of nodes at current level
        int n = queue.size();

        // Traverse all nodes of current level
        for (int i = 1; i <= n; i++) {
            Node temp = queue.poll();

            // Print the left most element at
            // the level
            if (i == n)
                System.out.print(temp.data + " ");

            // Add left node to queue
            if (temp.left != null)
                queue.add(temp.left);

            // Add right node to queue
            if (temp.right != null)
                queue.add(temp.right);
        }
    }
    }

    public void topViewOfTree(Node root){
        Queue<Pair> queue = new ArrayDeque<Pair>();
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();

        queue.add(new Pair(0,root));

        while(!queue.isEmpty()){
            Pair currentPair = queue.remove();
            if(!hashMap.containsKey(currentPair.hd)){
                hashMap.put(currentPair.hd, currentPair.node.data);
            }

            if(currentPair.node.left!= null){
                queue.add(new Pair(currentPair.hd-1, currentPair.node.left));
            }

            if(currentPair.node.right!= null){
                queue.add(new Pair(currentPair.hd+1, currentPair.node.right));
            }
        }

        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getValue());
        }
    }

    public void bottomViewOfTree(Node root){
        Queue<Pair> queue = new ArrayDeque<Pair>();
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();

        queue.add(new Pair(0,root));

        while(!queue.isEmpty()){
            Pair currentPair = queue.remove();
            hashMap.put(currentPair.hd, currentPair.node.data);
    

            if(currentPair.node.left!= null){
                queue.add(new Pair(currentPair.hd-1, currentPair.node.left));
            }

            if(currentPair.node.right!= null){
                queue.add(new Pair(currentPair.hd+1, currentPair.node.right));
            }
        }

        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getValue());
        }
    }
    
    public int countLeafNode(Node root){
        if(root== null){
            return 0;
        }else if(root.left == null  && root.right == null){
            System.out.println("Leaf node "+ root.data);
            return 1;
        }
        
        else{
            int left = countLeafNode(root.left);
            int right = countLeafNode(root.right);
            return left+right;
        }
    }
}
  public static void main(String[] args) {
      int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
      
      //Build a tree
      BinaryTree binaryTree = new BinaryTree();
      Node root = binaryTree.buildTree(nodes);
      System.out.println("Root Node "+ root.data);

    //   //PreOrder traversal
      System.out.println("PreOrder Traversal");
      binaryTree.preOrderTraverse(root);

      //InOrder traversal
      System.out.println("InOrder Traversal");
      binaryTree.inOrderTraversal(root);

      //PostOrder traversal
      System.out.println("PostOrder Traversal");
      binaryTree.postOrderTraversal(root);

      //LevelOrder Traversal
      System.out.println("LevelOrder Traversal");
      binaryTree.levelOrderTraverse(root);

      //Reverse LevelOrder Traversal
      System.out.println("Reverse LevelOrder Traversal");
      binaryTree.reverseLevelOrderTraversal(root);

      //Count of Nodes 
      System.out.println("\nCount of nodes: "+binaryTree.countOfNodes(root));

      //Sum of Nodes 
      System.out.println("Sum of nodes: "+binaryTree.sumOfNodes(root));

      //Height of Tree
      System.out.println("Height of tree: "+binaryTree.heightOfTree(root));
      
      //Diameter of Tree 
      System.out.println("Diamerter of tree: "+binaryTree.diameterOfTree(root));

      //mirror Image of a BTree
    //   Node tmpRoot =binaryTree.mirrorImageRecursively(root);
    // binaryTree.preOrderTraverse(tmpRoot);
    // binaryTree.mirrorImageItetrative(root);
    // binaryTree.preOrderTraverse(root);

    //Left image of a tree
    System.out.println("Left image of a b tree");
    binaryTree.leftViewOfTree(root);

     //Right image of a tree
     System.out.println("Left image of a b tree");
     binaryTree.rightViewOfTree(root);

     //TopView of a tree
     System.out.println("Top view of a b tree");
     binaryTree.topViewOfTree(root);

     //BottomView of a tree
     System.out.println("Bottom view of a b tree");
     binaryTree.bottomViewOfTree(root);

     //leaf node of a tree 
     System.out.println("Leaf node of a tree ");
     binaryTree.countLeafNode(root);



  }  
}
