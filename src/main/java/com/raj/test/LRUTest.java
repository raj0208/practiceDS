package com.raj.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LRUTest {
}

class LRUCache
{
    private int capacity;		// LRUCache capacity
    private Map<Integer, CacheItem > cache;
    private CacheItem head;
    private CacheItem tail;

    public LRUCache (int capacity)
    {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public int get (int key)
    {
        // Return the value of the key if the key exists, otherwise return -1.
        if (this.cache.containsKey (key))
        {
            // get CacheItem
            CacheItem item = this.cache.get (key);
            moveItemToFirst (item);	// move on top
            return item.value;
        }

        return -1;
    }

    public void put (int key, int value)
    {
        // if available then update the value
        if (this.cache.containsKey (key))
        {
            CacheItem item = this.cache.get (key);
            moveItemToFirst (item);	// move on top
            item.value = value;
            return;
        }

        // if capacity exceeds then evict item from tail end
        if (this.capacity == this.cache.size ())
        {
            // remove the item from tail
            CacheItem item = this.tail;
            removeItem (item);	// evict from doubly linkedlist
            this.cache.remove (key);	// evict from cache
        }

        // if not available then add to Cache
        CacheItem item = new CacheItem (value);
        addItem (item);
        this.cache.put (key, item);
    }

    private void removeItem (CacheItem item)
    {
        // if only one item
        if (this.head == this.tail && this.head == item)
        {
            this.head = null;
            this.tail = null;
            return;
        }

        // if first item
        if (this.head == item)
        {
            this.head.next.previous = null;
            this.head = this.head.next;
            item.next = null;
            return;
        }

        // if last item
        if (this.tail == item)
        {
            this.tail.previous.next = null;
            this.tail = this.tail.previous;
            item.previous = null;
            return;
        }

        // middle item
        item.next.previous = item.previous;
        item.previous.next = item.next;
        item.next = null;
        item.previous = null;
    }

    private void addItem (CacheItem item)
    {
        // if first item add in the cache
        if (this.head == null)
        {
            this.head = item;
            this.tail = this.head;
            return;
        }

        // Add item on top and point head to top
        this.head.previous = item;
        item.next = this.head;
        this.head = item;
    }

    private void moveItemToFirst (CacheItem item)
    {
        removeItem (item);
        addItem (item);
    }
}


class CacheItem
{
    public CacheItem next;
    public CacheItem previous;
    public int value;

    /**
     * Contructor
     * @param  CacheItem key
     * @param value CacheItem value
     */
    public CacheItem (int value)
    {
        this.value = value;
        this.next = this.previous = null;
    }
}

