package com.raj.practice.LeetCode;

import javax.management.modelmbean.RequiredModelMBean;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public class CacheItem {
        public CacheItem next;
        public CacheItem prev;
        public int value;
        public int key;

        public CacheItem(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    CacheItem head;
    CacheItem tail;
    Map<Integer, CacheItem> cache = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void setItem(int key, int value) {
        // if available, update and move first
        if (cache.containsKey(key)) {
            CacheItem temp = cache.get(key);
            moveFirst(temp);
            temp.value = value;
            return;
        }

        // if capacity is full, remove
        if (capacity == cache.size()) {
            int itemid = tail.key;
            remove(tail);
            cache.remove(itemid);
        }

        //add cache item
        CacheItem item = new CacheItem(key, value);
        add(item);
        cache.put(key, item);
    }

    public int get(int key) {
        // if available return and move to first
        if (cache.containsKey(key)) {
            CacheItem item = cache.get(key);
            moveFirst(item);
            return item.value;
        }

        // if not available, return -1
        return 0;
    }

    public void add(CacheItem item) {
        // if head == null, add and return
        if (head == null) {
            head = item;
            tail = item;
            return;
        }

        item.next = head;
        head.prev = item;
        head = item;
    }

    public void remove(CacheItem item) {
        // only item
        if (head == tail && head == item) {
            head = tail = null;
            return;
        }

        //head item
        if (head == item) {
            head = head.next;
            head.prev = null;
            item.next = null;
            return;
        }
        // tail item
        if (tail == item) {
            tail = tail.prev;
            tail.next = null;
            item.prev = null;
            return;
        }

        // middle item
        item.next.prev = item.prev;
        item.prev.next = item.next;
        item.next = null;
        item.prev = null;
    }

    public void moveFirst(CacheItem item) {
        remove(item);
        add(item);
    }
}


