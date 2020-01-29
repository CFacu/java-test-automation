package com.solvd.treeset;

import org.apache.log4j.Logger;

public class MyTreeSet {
    final static Logger LOGGER = Logger.getLogger(MyTreeSet.class);

    private Node root;

    public MyTreeSet(){
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node addRecursive(Node current, Integer data) {
        if (current == null) {
            return new Node(data);
        }
        if (data < current.getData()) {
            current.setLeft(addRecursive(current.getLeft(), data));
        } else if (data > current.getData()){
            current.setRight(addRecursive(current.getRight(), data));
        } else return current;
        return current;
    }

    public void add(int data) {
        root = addRecursive(root, data);
    }

    public Node deleteRecursive(Node current, int data) {
        if (current == null) return null;
        if (data == current.getData()) {
            if (current.getLeft() == null && current.getRight() == null) return null;
            if (current.getRight() == null) {
                return current.getLeft();
            }
            if (current.getLeft() == null) {
                return current.getRight();
            }
            int smallestData = findSmallestData(current.getRight());
            current.setData(smallestData);
            current.setRight(deleteRecursive(current.getRight(), smallestData));
        }
        if (data < current.getData()) {
            current.setLeft(deleteRecursive(current.getLeft(), data));
            return current;
        }
        current.setRight(deleteRecursive(current.getRight(),data));
        return current;
    }

    public int findSmallestData(Node root) {
        return root.getLeft() == null ? root.getData() : findSmallestData(root.getLeft());
    }

    public void delete (int data) {
        root = deleteRecursive(root, data);
    }

    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.getLeft());
            log(node.getData());
            printInOrder(node.getRight());
        }
    }

    public void printPreOrder(Node node) {
        if (node != null) {
            log(node.getData());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    public void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            log(node.getData());
        }
    }

    private void log(int data) {
        LOGGER.info(" " + data);
    }
}
