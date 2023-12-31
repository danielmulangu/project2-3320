// AvlTree class
//Daniel Mulangu Kaseya
//72826452
//dkaseya@unomaha.edu

import java.util.*;

public class AvlTree<AnyType extends Comparable<? super AnyType>>
{
    private AvlNode<AnyType> root;
    public AvlTree() {
        root = null;
    }

    public void insert(AnyType key) {
        //Write your code here
        this.root=insert(key, this.root);
    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        //Write your code here
        //You need to adjust return value
        if(t==null){
            return new AvlNode<AnyType>(x,null,null);

        }
        if(x.compareTo(t.element) < 0){
            t.left= insert(x, t.left);
            if(height(t.left) - height(t.right)== 2 ){
                if(x.compareTo(t.left.element) < 0){
                    t= rotateWithLeftChild(t);
                }
                else{
                    t= doubleWithLeftChild(t);
                }
            }
        }
        else if(x.compareTo(t.element) > 0 ){
            t.right = insert(x, t.right);
            if(height(t.right) - height(t.left)== 2 ){
                if(x.compareTo(t.right.element) > 0){
                    t = rotateWithRightChild(t);
                }
                else{
                    t= doubleWithRightChild(t);
                }

            }
        }
        else{}
        t.height = Math.max(height(t.left), height(t.right)) + 1;

        return t;
    }

    public boolean contains(AnyType key) {
        //Write your code here
        //You need to adjust return value
        return contains(key, root);
    }
    private boolean contains(AnyType x, AvlNode<AnyType> t){
        if(t==null){
            System.out.println("Not found!");
            return false;
        }
        else{ 
            if(x.compareTo(t.element)<0){
                return contains(x, t.left);
            }
            else if(x.compareTo(t.element)>0){
                return contains(x, t.right);
            }
            else {
                System.out.println("Element "+x+" Found in the tree");
                return true;
            }
        }
    }

    //Return the height of node t, or -1, if not found.
    private int height(AvlNode<AnyType> left) {
        //Write your code here
        //You need to adjust return value
       return left == null ? -1: left.height;
    }
    private int getheight(AnyType key){
          AvlNode<AnyType> current= this.root;
        return getheight(key, current);
    }
    private int getheight (AnyType x, AvlNode<AnyType> t){
                if(t ==null){
            System.out.println("Element not found in the tree");
            return -1;
        }
        else{
           
            int comparedvalue = x.compareTo(t.element);
            if(comparedvalue<0){
            return getheight(x, t.left);
            }
            else if(comparedvalue>0){
            return getheight(x, t.right);    
            }
            else{
            int lheight = height(t.left); //left sub tree height
            int rheight = height(t.right); // right sub tree height
            int temp = Math.max(lheight, rheight) +1;
            System.out.println(x+"'s height in the tree is "+temp);
            return temp;
            }

        }
        
    }

    //Return the depth of node t, or -1, if not found.
    private int depth(AnyType key) {
        //Write your code here
        //You need to adjust return value
        return depth(key,this.root, 0);
    }
    private int depth(AnyType x, AvlNode<AnyType> t, int current){
       if(t==null){return -1;}
        
       else{

        int comparedvalue = x.compareTo(t.element);
        if(comparedvalue<0){
            return depth(x, t.left, current +1);
        }
        else if(comparedvalue >0){
            return depth(x, t.right, current +1);
        }
        else{

            System.out.println(x +"'s depth in the tree is "+current);
            return current;
         }
       }
    }

    public AnyType findMin() {
        //Write your code here
        //You need to adjust return value
        AvlNode<AnyType> current= this.root;
        if(this.root== null ){System.out.println("Cannot find Minimum value, Tree is empty"); return null;}
        else{
             while(current.left != null){current= current.left;}
        }
       
        return current.element;
    }
    public AnyType findMin(AvlNode<AnyType> t) {
        //Write your code here
        //You need to adjust return value
        AvlNode<AnyType> current= t;
        if(this.root== null ){System.out.println("Cannot find Minimum value, Tree is empty"); return null;}
        else{
             while(current.left != null){current= current.left;}
        }
       
        return current.element;
    }


    public AnyType findMax() {
        //Write your code here
        //You need to adjust return value
         AvlNode<AnyType> current= this.root;
        if(this.root== null ){System.out.println("Cannot find Maximum value, Tree is empty"); return null;}
        else{
             while(current.right != null){current= current.right;}
        }
       
        return current.element ;
    }

    //Implmeneted
    public void printTree( ) {
        if(isEmpty())
            System.out.println( "Empty tree" );
        else
			BTreePrinter.printNode(root);
    }

    //Implmeneted
    public boolean isEmpty() {
        return root == null;
    }
    
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        //Write your code here
        //You need to adjust return value
        AvlNode<AnyType> k1 =k2.left;
        AvlNode<AnyType> k3 = k1.right;
        k1.right= k2;
        k2.left = k3;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) +1;
        return k1;
    }

    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1) {
        //Write your code here
        //You need to adjust return value
        AvlNode<AnyType> k2 = k1.right;
        AvlNode<AnyType> k3 = k2.left;
        k2.left=k1;
        k1.right =k3;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) +1;
        return k2;
    }

    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3) {
        //Write your code here
        //You need to adjust return value
        k3.left = rotateWithRightChild(k3.left);

        return rotateWithLeftChild(k3);
    }

    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1) {
        //Write your code here
        //You need to adjust return value
        k1.right = rotateWithLeftChild(k1.right);

        return rotateWithRightChild(k1);
    }
    private int display_menu(){
        System.out.println("------------ AVL Tree ------------");
        System.out.println("\n0.Show Menu\n1.Insert a new key\n2.Check if a key exists");
        System.out.println("3.Find the node's height\n4.Find the node's depth\n5.Find the min value");
        System.out.println("6.Find the max value\n7.Print tree\n8.Delete a key\n9.Exit");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        return input;
    }
    private void delete(AnyType key){
      this.root= delete(key, this.root);
    }

     private AvlNode<AnyType> delete(AnyType x, AvlNode<AnyType> t) {
        if(t==null){
            return t;
        }
        else if(x.compareTo(t.element) < 0){
            t.left= delete(x, t.left);           
        }
        else if(x.compareTo(t.element) > 0){
            t.right= delete(x, t.right);
        }
        else {
            //deletion with one child or no child case scenario
            if(t.left==null || t.right==null){
                AvlNode<AnyType> z= (t.left!=null) ? t.left: t.right;
               if(z==null){
                z=t;
                t=null;
               }
               else{ t=z;} 
               
            }
            //Deletion when the node has two children
            else{
                AnyType newparent = findMin(t.right);
                AvlNode<AnyType> temp = new AvlNode(newparent);
                t.element= temp.element;
                t.right = delete(temp.element,t.right);
            }
        }
        if(t==null){
            return t;
        }
            //Update height
            t.height = Math.max(height(t.left), height(t.right)) + 1;
            //Perform the rotation
            if((height(t.left) - height(t.right))>1 && ( height(t.left.left) - height(t.left.right))>=0){
                t= rotateWithLeftChild(t);
            }
            else if((height(t.left) - height(t.right))>1 && ( height(t.left.left) - height(t.left.right)) <0){
                t= doubleWithLeftChild(t);
            }
            else if((height(t.right) - height(t.left))>1 && ( height(t.right.left) - height(t.right.right)) <=0){
                t= rotateWithRightChild(t);
            }
            else if((height(t.right) - height(t.left))>1 && ( height(t.right.right) - height(t.right.left)) <0){
                t= doubleWithRightChild(t);
            }
            return t;
        
     }


    // Test program
    public static void main(String [] args) {
        AvlTree<Integer> tree = new AvlTree<>( );
        
        //Show your menu here and call AvlTree function with parameters
       int input = tree.display_menu();
        while(true){
                if(input ==0){ input= tree.display_menu();}
                else if(input==1) {
                    System.out.println("Input a key: ");
                    Scanner  scan= new Scanner(System.in);
                    int key;
                    key=scan.nextInt();
                    tree.insert(key);
                    System.out.println(key+" is added to the tree."); 
                    System.out.println("Press 0 to display menu or 9 to exit");
                    Scanner scan2= new Scanner(System.in);
                    input= scan2.nextInt();
                }
                else if (input ==2){
                    System.out.println("Input a key: ");
                    Scanner  scan= new Scanner(System.in);
                    int key;
                    key=scan.nextInt();
                    tree.contains(key);
                    System.out.println("Press 0 to display menu or 9 to exit");
                    Scanner scan2= new Scanner(System.in);
                    input= scan2.nextInt();
                }
                else if (input == 3){ 
                  System.out.println("Input a key: ");
                  Scanner  scan= new Scanner(System.in);
                  int key;
                  key=scan.nextInt();
                  tree.getheight(key);
                  System.out.println("Press 0 to display menu or 9 to exit");
                  Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt();
                }
                else if(input==4){
                 System.out.println("Input a key: ");
                 Scanner  scan= new Scanner(System.in);
                  int key;
                  key=scan.nextInt();   
                  tree.depth(key);
                  System.out.println("Press 0 to display menu or 9 to exit");
                  Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt();
                }
                else if(input==5){
                    System.out.println("Tree's minimum element is "+tree.findMin());
                    System.out.println("Press 0 to display menu or 9 to exit");
                    Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt();
                }
                else if(input==6){
                    System.out.println("Tree's maximum element is "+tree.findMax());
                    System.out.println("Press 0 to display menu or 9 to exit");
                    Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt();
                }
                else if(input==7){
                    tree.printTree();
                  System.out.println("Press 0 to display menu or 9 to exit");
                  Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt();
                }
                else if(input==8){
                    System.out.println("Input a key: ");
                    Scanner  scan= new Scanner(System.in);
                    int key;
                    key=scan.nextInt();
                    tree.delete(key);
                    System.out.println(key+" is successfully removed from the tree."); 
                    System.out.println("Press 0 to display menu or 9 to exit");
                    Scanner scan2= new Scanner(System.in);
                    input= scan2.nextInt();
                    
                }
                else if(input==9){ 
                     System.out.println("Exit done");
                    System.exit(0);
                }
                else{ 
                  System.out.println("Wrong input .Please, press 0 to show the full menu or  9 to exit");
                  Scanner scan2= new Scanner(System.in);
                  input= scan2.nextInt(); 
                }
            }
        
    }
}
