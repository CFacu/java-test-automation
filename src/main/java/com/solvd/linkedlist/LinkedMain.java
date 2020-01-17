package com.solvd.linkedlist;

public class LinkedMain {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();

        list.add("123");
        list.add("456");
        list.add("asd");

        list.add(0,"654");
        list.add(3, "qwe");

        for (Node n : list) {
            System.out.println(n.getData());
        }

        list.remove(0);

        System.out.println("");

        list = list.reverse();
        list.show();
    }
}