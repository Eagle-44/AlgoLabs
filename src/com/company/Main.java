package com.company;

public class Main {

    public static void main(String[] args) {
	    BinarySearchTree tree = new BinarySearchTree();
	    tree.insertNode(27);
        tree.insertNode(12);
        tree.insertNode(32);
        tree.insertNode(23);
        tree.insertNode(11);
        tree.insertNode(16);

        tree.print();

        Node findNode = tree.findNode(16);
        findNode.printNode();
    }
}
