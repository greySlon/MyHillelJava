package com.slon.hashMap;

import java.util.*;

/**
 * Created by Sergii on 18.03.2017.
 */
public class MyHashMap<K, V> implements Map<K, V> {
    private int size = 0;
    private int bucketCount;
    private List<Bucket> buckets;
    private boolean toReorganize = false;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int bucketCount) {
        this.bucketCount = bucketCount;
        buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new Bucket());
        }
    }

    private class Node<K, V> {
        final int hash;
        final K key;
        V value;

        @Override
        public int hashCode() {
            return 31 * hash + (value == null ? 0 : value.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = ((Node) obj);
                return key.equals(node.key) && (value != null ? value.equals(node.value) : value == node.value);
            }
            return false;
        }

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    private class Bucket implements Iterable<Node<K, V>> {
        List<Node<K, V>> list = new ArrayList<>();

        public void add(Node<K, V> node) {
            list.add(node);
        }

        public V add(K key, V value) {
            for (Node<K, V> item : list) {
                if (item.key.equals(key)) {
                    V tmpValue = item.value;
                    item.value = value;
                    return tmpValue;
                }
            }
            list.add(new Node(key, value, key.hashCode()));
            if (list.size() > 10)
                toReorganize = true;
            size++;
            return value;
        }

        public V get(Object key) {
            for (Node<K, V> item : list) {
                if (item.key.equals(key))
                    return item.value;
            }
            return null;
        }

        public boolean containsKey(Object key) {
            for (Node<K, V> item : list) {
                if (item.key.equals(key))
                    return true;
            }
            return false;
        }

        public boolean containsValue(Object value) {
            for (Node<K, V> item : list) {
                if (item.value.equals(value))
                    return true;
            }
            return false;
        }

        public Tuple<V> remove(Object key) {
            Iterator<Node<K, V>> iter = list.iterator();
            while (iter.hasNext()) {
                Node<K, V> node = iter.next();
                if (node.key.equals(key)) {
                    V value = node.value;
                    iter.remove();
                    return new Tuple<>(true, value);
                }
            }
            return new Tuple<>(false, null);
        }

        public void clear() {
            list.clear();
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return list.equals(obj);
        }

        @Override
        public Iterator<Node<K, V>> iterator() {
            return list.iterator();
        }
    }

    private class Tuple<V> {
        boolean removed;
        V result;

        public Tuple(boolean removed, V result) {
            this.removed = removed;
            this.result = result;
        }
    }

    //toReorganize
    private void reorganize() {
        List<Bucket> newBuckets;
        int newBucketCount;
        if (bucketCount < Integer.MAX_VALUE / 2) {
            newBucketCount = bucketCount * 2;
            newBuckets = new ArrayList<>(newBucketCount);
            for (int i = 0; i < newBucketCount; i++) {
                newBuckets.add(new Bucket());
            }
            for (Bucket bucket : buckets) {
                for (Node<K, V> node : bucket) {
                    newBuckets.get(Math.abs(node.hash) % newBucketCount).add(node);
                }
            }
            this.bucketCount = newBucketCount;
            this.buckets = newBuckets;
        }
    }

    // Query Operations
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Bucket bucket : buckets) {
            if (bucket.containsKey(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Bucket bucket : buckets) {
            if (bucket.containsValue(value))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return buckets.get(key.hashCode() % bucketCount).get(key);
    }

    // Modification Operations
    @Override
    public V put(K key, V value) {
        V val = buckets.get(Math.abs(key.hashCode()) % bucketCount).add(key, value);
        if (toReorganize) {
            reorganize();
            toReorganize = false;
        }
        return val;
    }

    @Override
    public V remove(Object key) {
        for (Bucket bucket : buckets) {
            Tuple<V> tuple = bucket.remove(key);
            if (tuple.removed) {
                size--;
                return tuple.result;
            }
        }
        return null;
    }

    // Bulk Operations
    @Override
    public void clear() {
        size = 0;
        for (Bucket bucket : buckets) {
            bucket.clear();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            put(key, value);
        }
    }

    // Views
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>(size);
        for (Bucket bucket : buckets) {
            for (Node<K, V> node : bucket) {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> coll = new ArrayList<V>(size);
        for (Bucket bucket : buckets) {
            for (Node<K, V> node : bucket) {
                coll.add(node.value);
            }
        }
        return coll;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (Bucket bucket : buckets) {
            for (Node<K, V> node : bucket) {
                set.add(new MyEntry<K, V>(node.key, node.value));
            }
        }
        return set;
    }

    public static class MyEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int hashCode() {
            return 31 * key.hashCode() + (value == null ? 0 : value.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof MyEntry) {
                MyEntry myEntry = ((MyEntry) obj);
                return key.equals(myEntry.key) && (value != null ? value.equals(myEntry.value) : value == myEntry.value);
            }
            return false;
        }
    }

    // Comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (o instanceof MyHashMap) {
            MyHashMap another = ((MyHashMap) o);
            return size == another.size && buckets.equals(another.buckets);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * size + buckets.hashCode();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        System.out.println(String.format("size=%d", map.size()));
        System.out.println(map.get("one"));
        System.out.println(map.containsKey("six"));
        System.out.println(map.containsKey("four"));

        System.out.println(map.containsValue("four"));
        System.out.println(map.containsValue(46));
        System.out.println(map.containsValue(4));
        map.remove("two");
        System.out.println(String.format("size=%d", map.size()));
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }

        System.out.println("Copy to another map");
        MyHashMap<String, Integer> map2 = new MyHashMap<>();
        map2.putAll(map);
        for (Entry<String, Integer> entry : map2.entrySet()) {
            System.out.println(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
    }
}
