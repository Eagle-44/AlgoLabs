package com.company;


import java.util.Stack;

public class BinarySearchTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public BinarySearchTree() {
        root = null;
    }

    public void insertNode(int key) {
        Node newNode = new Node();
        newNode.setKey(key);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (key == current.getKey()) {
                    return;
                } else if (key < current.getKey()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null){
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int key){
        Node current = root;
        while (current.getKey() != key) {
            if (key < current.getKey()){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }


    public boolean deleteNode(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeft = true;
        while (current.getKey() != key) {
            parent = current;
            if (key < current.getKey()) {
                isLeft = true;
                current = current.getLeft();
            } else {
                isLeft = false;
                current = current.getRight();
            }
            if (current == null)
                return false;
        }
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root)
                root = null;
            else if (isLeft)
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        else if (current.getRight() == null){
            if (current == root)
                root = current.getLeft();
            else if (isLeft)
                parent.setLeft(current.getLeft());
            else
                parent.setRight(current.getLeft());
        }
        else if (current.getLeft() == null){
            if (current == root)
                root = current.getRight();
            else if (isLeft)
                parent.setLeft(current.getRight());
            else
                parent.setRight(current.getRight());
        }
        else {
            Node inheritor = inheritorReceiver(current);
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
        Node current = node.getRight();
        while (current != null) {
            parent = inherNode;
            inherNode = current;
            current = current.getLeft();
        }
        if (inherNode != node.getRight()) {
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
