package com.solvd.treeset;

public class Node {
    private Integer data;
    private Node right;
    private Node left;

    public Node() {
    }

    public Node(Integer data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}
