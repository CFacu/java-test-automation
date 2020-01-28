package com.solvd.treeset;

public class TreeSetMain {
    public static void main(String[] args) {
        MyTreeSet tree = new MyTreeSet();

        tree.add(15);
        tree.add(123);
        tree.add(546);
        tree.add(1);
        tree.add(78);
        tree.add(853);

        System.out.println("InOrder TreeSet:");
        tree.printInOrder(tree.getRoot());
        System.out.println("PostOrder TreeSet:");
        tree.printPostOrder(tree.getRoot());

        System.out.println("Deleting info:");
        tree.delete(123);
        tree.delete(546);
        tree.delete(1);
        tree.printInOrder(tree.getRoot());
    }
}