package com.raj.test;

import java.util.HashMap;
import java.util.LinkedList;

public class LFU {
    public static void main(String[] args) {
        LFU cache = new LFU(2);

        cache.put(1, 1);
        cache.get(1);
        cache.put(2,2);
        cache.put(3,3);
        cache.get(3);
        cache.get(3);
        cache.get(1);
        cache.put(2,2);
    }

    public static class CachedItem {
        public int key;
        public int value;
        public int frequency;

        public CachedItem(int k, int v) {
            key = k;
            value = v;
            frequency = 1;
        }
    }


    private HashMap<Integer, CachedItem> cache;
    private HashMap<Integer, LinkedList<CachedItem>> frequencyList;
    private final int capacity;
    private int minFrequency;

    public LFU(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        frequencyList = new HashMap<>();
        minFrequency = 0;
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) {
            return -1;
        }

        CachedItem cachedItem = this.cache.get(key);
        updateFrequency(cachedItem);
        return cachedItem.value;
    }

    public void put(int key, int value) {
        if (this.cache.containsKey(key)) {
            CachedItem cachedItem = this.cache.get(key);
            cachedItem.value = value;
            updateFrequency(cachedItem);
            return;
        }

        if (this.cache.size() >= this.capacity) {
            removeItem(minFrequency);
        }

        CachedItem newItem = new CachedItem(key, value);
        cache.put(key, newItem);

        if (!frequencyList.containsKey(1)) {
            frequencyList.put(1, new LinkedList<>());
        }
        frequencyList.get(1).addFirst(newItem);
        minFrequency = 1;
    }

    public void updateFrequency(CachedItem item) {
        int freq = item.frequency;
        LinkedList<CachedItem> freqList = frequencyList.get(freq);
        freqList.remove(item);

        if (freqList.isEmpty()) {
            frequencyList.remove(freq);
            if (freq == minFrequency) {
                minFrequency++;
            }
        }

        item.frequency++;
        if (!frequencyList.containsKey(item.frequency)) {
            frequencyList.put(item.frequency, new LinkedList<>());
        }
        frequencyList.get(item.frequency).addFirst(item);
    }

    public void removeItem(int frequency) {
        LinkedList<CachedItem> itemLinkedList = frequencyList.get(frequency);
        CachedItem evict = itemLinkedList.getLast();

        this.cache.remove(evict.key);
        itemLinkedList.remove(evict);

        if (itemLinkedList.isEmpty()) {
            frequencyList.remove(frequency);
        }
    }
}
