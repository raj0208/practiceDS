package com.raj.test;

import java.util.HashMap;

public class LRU2 {
    public class Node {
        public final int key;
        public int value;
        public Node next;
        public Node previous;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
            this.next = this.previous = null;
        }
    }

    private final int capacity;
    private HashMap<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRU2(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity cannot be zero or undefined");

        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = this.tail = null;
    }

    public int get(int key) {
        if (this.cache.containsKey(key)) {
            Node currNode = this.cache.get(key);
            moveToFirst(currNode);
            return currNode.value;
        }
        return -1;
    }


    public void put(int key, int value) {
        if (this.cache.containsKey(key)) {
            Node currNode = this.cache.get(key);
            moveToFirst(currNode);
            currNode.value = value;
            return;
        }

        if (this.cache.size() >= this.capacity) {
            Node evictNode = this.tail;
            removeElement(evictNode);
            cache.remove(evictNode.key);
        }

        Node newNode = new Node(key, value);
        addToFirst(newNode);
        this.cache.put(key, newNode);
    }

    private void addToFirst(Node newNode) {
        newNode.next = this.head;
        newNode.previous = null;

        if (this.head != null)
            this.head.previous = newNode;

        this.head = newNode;

        if (this.tail == null)
            this.tail = newNode;
    }

    private void removeElement(Node evictNode) {
        if (evictNode.previous != null)
            evictNode.previous.next = evictNode.next;
        else
            head = evictNode.next;

        if (evictNode.next != null)
            evictNode.next.previous = evictNode.previous;
        else
            tail = evictNode.previous;

        evictNode.next = evictNode.previous = null;
    }

    private void moveToFirst(Node node) {
        if (head == node) return;

        removeElement(node);
        addToFirst(node);
    }
}
