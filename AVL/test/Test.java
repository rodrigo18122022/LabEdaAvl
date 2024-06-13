package test;
import avl.*;
import bst.*;

public class Test {

	 public static void main(String[] args) {
		 
		 BST<Integer> d = new BST<Integer>();
		 try {
			System.out.println("save1");
			 d.insert(20);
			 d.inOrden();
			 d.insert(30);
			 d.inOrden();
			 d.insert(40);
			 d.inOrden();
			 d.insert(50);
			 d.insert(55);
			 d.inOrden();
			 d.insert(60);
			 d.insert(68);
			 d.insert(70);
			 d.inOrden();
			 
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 } 
		 
		 AVLTree<Integer> a = new AVLTree<Integer>();
		 try {
			System.out.println("save1");
			 a.insert(20);
			 a.inOrden();
			 a.insert(30);
			 a.inOrden();
			 a.insert(40);
			 a.inOrden();
			 a.insert(50);
			 a.insert(55);
			 a.inOrden();
			 a.insert(60);
			 a.insert(68);
			 a.insert(70);
			 a.insert(12);
			 a.insert(26);
			 a.insert(44);
			 a.inOrden();
			 
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 } 
	 }

}