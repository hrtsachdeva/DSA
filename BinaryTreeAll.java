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

  }  
}
