import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
public class BinaryTreeUse {
	public static int minimum(BinaryTreeNode<Integer> root) {
		if(root==null) {
			return Integer.MAX_VALUE;
		}
		return Math.min(root.data,Math.min(minimum(root.left), minimum(root.right)));
	}
	public static int maximum(BinaryTreeNode<Integer> root) {
		if(root==null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(root.data,Math.max(maximum(root.left), maximum(root.right)));
	}
	public static boolean isBST(BinaryTreeNode<Integer> root) {
		if(root==null) {
			return true;
		}
		int minright = minimum(root.right);
		int maxleft = maximum(root.left);
		if(root.data<=maxleft) {
			return false;
		}
		if(root.data>minright) {
			return false;
		}
		boolean b1 = isBST(root.left);
		boolean b2 = isBST(root.right);
		if(b1&&b2) {
			return true;
		}else {
			return false;
		}
	}
	public static void printLevelWise(BinaryTreeNode<Integer> root) {
		if(root==null) {
			return;
		}
		Queue<BinaryTreeNode<Integer>> pending = new LinkedList<>();
		pending.add(root);
		//pending.add(null);
		while(!pending.isEmpty()) {
			BinaryTreeNode<Integer> temp = pending.remove();
			System.out.print(temp.data+":");
			if(temp.left!=null) {
				System.out.print("L:"+temp.left.data);
				pending.add(temp.left);
			}else {
				System.out.print("L:-1");
			}
			if(temp.right!=null) {
				System.out.print(",R:"+temp.right.data);
				pending.add(temp.right);
			}else {
				System.out.print(",R:-1");
			}
			System.out.println();
		}
	}
	public static int count(BinaryTreeNode<Integer> root) {
		int count = 1;
		if(root==null) {
			return 0;
		}
		count+=count(root.left);
		count+=count(root.right);
		return count;
	}
	public static BinaryTreeNode<Integer> takeInputLevelWise(){
		Queue<BinaryTreeNode<Integer>> pendingNode = new LinkedList<>();
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Enter the root node");
			int rootdata = s.nextInt();
			if(rootdata == -1) {
				return null;
			}
			BinaryTreeNode<Integer> root;
			root = new BinaryTreeNode<>(rootdata);
			pendingNode.add(root);
			while(!pendingNode.isEmpty()) {
				BinaryTreeNode<Integer> temp = pendingNode.remove();
				System.out.println("Enter the left child of "+ temp.data);
				int left = s.nextInt();
				BinaryTreeNode<Integer> leftNode;
				if(left!=-1) {
					leftNode = new BinaryTreeNode<>(left);
					root.left = leftNode;
					pendingNode.add(leftNode);
					
				}
				System.out.println("Enter the right child of "+ temp.data);
				int right = s.nextInt();
				BinaryTreeNode<Integer> rightNode;
				if(right!=-1) {
					rightNode = new BinaryTreeNode<>(right);
					root.right = rightNode;
					pendingNode.add(rightNode);
				}
			}
			return root;
		}
	}
	public static void printTree(BinaryTreeNode<Integer> root) {
		if(root==null) {
			return;
		}
		String s = root.data+" "+":"+" ";
		if(root.left!=null) {
			s = s +"L:"+root.left.data;
		}
		if(root.right!=null) {
			s = s +"R:"+root.right.data;
		}
		System.out.println(s);
		printTree(root.left);
		printTree(root.right);
	}
	public static BinaryTreeNode<Integer> takeInput(Scanner s){
		System.out.println("Enter node data");
		int root = s.nextInt();
		if(root==-1) {
			return null;
		}
		BinaryTreeNode<Integer> rootnode = new BinaryTreeNode<>(root);
		rootnode.left = takeInput(s);
		rootnode.right = takeInput(s);
		return rootnode;
	}
	public static Pair<Boolean,Pair<Integer,Integer>> isBST2(BinaryTreeNode<Integer> root){
		if(root == null) {
			Pair<Boolean,Pair<Integer,Integer>> output = new Pair<Boolean,Pair<Integer,Integer>>();
			output.first = true;
			output.second = new Pair<>();
			output.second.first = Integer.MAX_VALUE;
			output.second.second = Integer.MIN_VALUE;
			return output;
		}
		Pair<Boolean,Pair<Integer,Integer>> leftNode = isBST2(root.left);
		Pair<Boolean,Pair<Integer,Integer>> rightNode = isBST2(root.right);
		int min = Math.min(root.data, Math.min(leftNode.second.first, rightNode.second.first));
		int max = Math.max(root.data, Math.max(leftNode.second.second, rightNode.second.second));
		boolean b = (root.data<=rightNode.second.first)&&(root.data>leftNode.second.second)&&leftNode.first&&rightNode.first;
		Pair<Boolean, Pair<Integer,Integer>> output = new Pair<>();
		output.first = b;
		output.second = new Pair<>();
		output.second.first = min;
		output.second.second = max;
		return output;
	}
	public static boolean isBST3(BinaryTreeNode<Integer> root,int min , int max) {
		if(root == null) {
			return true;
		}
		if(root.data<min||root.data>max) {
			return false;
		}
		boolean right = isBST3(root.right,root.data,max);
		boolean left = isBST3(root.left,min,root.data-1);
		return left&&right;
	}
	public static void main(String[] args) {
		//----------------------------
		//Need to rectify the takeInputLevelWise()
		//----------------------------
		// TODO Auto-generated method stub
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4);
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(6);
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(3);
		BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
		BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(7);
		root.left=node1;
		root.right=node2;
		node1.left=node3;
		node1.right=node4;
		node2.left=node5;
		node2.right=node6;
		
//		Scanner s = new Scanner(System.in);
//		BinaryTreeNode<Integer> root = takeInput(s);
//		printTree(root);
		//BinaryTreeNode<Integer> root = takeInputLevelWise();
		//printLevelWise(root);
		Pair<Boolean, Pair<Integer,Integer>> output = new Pair<>();
		output = isBST2(root);
		System.out.println("Count of nodes "+count(root));
		System.out.println("Is tree BST: "+isBST(root));//complexity will be O(n^2)
		System.out.println("Is tree BST: "+output.first);//complexity will be O(n)
		System.out.println("Is tree BST: "+isBST3(root,Integer.MIN_VALUE,Integer.MAX_VALUE));//complexity will be O(n)
	}

}
