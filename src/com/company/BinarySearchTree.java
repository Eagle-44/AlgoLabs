package com.company;


import java.util.Stack;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insertNode(int key) {
        Node newNode = new Node();
        newNode.setKey(key);
        if (root == null) { // if root not exist --> newNode is new node
            root = newNode;
        } else { // if root exist ... start from current
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (key == current.getKey()) {
                    return;
                } else if (key < current.getKey()) { //move left
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode); //insert in left  node
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null){
                        parent.setRight(newNode);// insert in right node
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int key){
        Node current = root;
        while (current.getKey() != key) {
            if (key < current.getKey()){ //move left
                current = current.getLeft();
            } else { //move right
                current = current.getRight();
            }
            if (current == null) { //if child not exist --> return null
                return null;
            }
        }
        return current; // return find elem
    }

    public boolean deleteNode(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeft = true;
        while (current.getKey() != key) { // start find key
            parent = current;
            if (key < current.getKey()) { // move to left node
                isLeft = true;
                current = current.getLeft();
            } else { // move to right node
                isLeft = false;
                current = current.getRight();
            }
            if (current == null)
                return false; // not find
        }
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) // if node = root --> tree will be cleaned
                root = null;
            else if (isLeft) // if node != --> node detach from parent
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        else if (current.getRight() == null){ // if right not exist --> node left
            if (current == root)
                root = current.getLeft();
            else if (isLeft)
                parent.setLeft(current.getLeft());
            else
                parent.setRight(current.getLeft());
        }
        else if (current.getLeft() == null){ // if legt not exist --> node right
            if (current == root)
                root = current.getRight();
            else if (isLeft)
                parent.setLeft(current.getRight());
            else
                parent.setRight(current.getRight());
        }
        else { // if exist left and right --> node send to inheritor
            Node inheritor = inheritorReceiver(current); // looking for new inherit node for deleted node
            if (current == root)
                root = inheritor;
            else if (isLeft)
                parent.setLeft(inheritor);
            else
                parent.setRight(inheritor);
        }
        return true;
    }

    private Node inheritorReceiver(Node node) {
        Node parent = node;
        Node inherNode = node;
        Node current = node.getRight(); // move to right inheritor
        while (current != null) { // while left 'left' inheritors
            parent = inherNode;
            inherNode = current;
            current = current.getLeft(); // move to left inheritor
        }
        if (inherNode != node.getRight()) { // if inheritor not right
            parent.setLeft(inherNode.getRight());
            inherNode.setRight(node.getRight());
        }
        return inherNode;
    }

    public void print() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false){
                Node temp = (Node) globalStack.pop();
                if (temp != null){
                    System.out.print(temp.getKey());
                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    if (temp.getLeft() != null ||
                            temp.getRight() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("Null!");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());

        }
        System.out.println(separator);
    }


}
