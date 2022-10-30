package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author lmt
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    private double loadFactor;
    private int numOfElements;
    private int numOfBuckets;
    // multiplicative factor
    private int multFactor;
    // the keys that i have stored
    public HashSet<K> allMyKeys;
    /** Constructors */
    public MyHashMap() {
        loadFactor = 0.75;
        numOfBuckets = 16;
        numOfElements = 0;
        multFactor = 2;
        allMyKeys = new HashSet<>();
        buckets = createTable(numOfBuckets);
        for(int i =0 ; i < numOfBuckets; i++){
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        loadFactor = 0.75;
        numOfBuckets = initialSize;
        numOfElements = 0;
        multFactor = 2;
        allMyKeys = new HashSet<>();
        buckets = createTable(numOfBuckets);
        for(int i =0 ; i < numOfBuckets; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        loadFactor = maxLoad;
        numOfBuckets = initialSize;
        numOfElements = 0;
        multFactor = 2;
        allMyKeys = new HashSet<>();
        buckets = createTable(numOfBuckets);
        for(int i =0 ; i < numOfBuckets; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        Node temp = new Node(key, value);
        return temp;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {

        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){

        for(int i = 0; i < numOfBuckets; i++){
            buckets[i] = createBucket();
        }
        buckets = createTable(numOfBuckets);
        numOfElements = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        Node findResult = find(key);
        if(findResult != null){
            return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(!containsKey(key)){
            return null;
        }
        Node findNode = find(key);
        return findNode.value;
    }
    /**helper method
     *
     * return the node with key or return null*/
    private Node find(K key){


        int hashcode = key.hashCode();
        int position = Math.floorMod(hashcode,numOfBuckets);

        // if it is null then     temp.iterator() will invoke NullPointerException
        if(buckets[position] == null){
            return null;
        }

        Collection<Node> temp = createBucket();
        temp = buckets[position];

        Iterator<Node> seer = temp.iterator();
        for(Node n : temp){
            if(n.key.equals(key)){
                return n;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return numOfElements;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){
        Node findNode = find(key);
        Node newNode = createNode(key, value);

        if(findNode == null){
            insert(key, value);
            numOfElements++;
            allMyKeys.add(key);
        } else {
            // if the key already exists, then we only need to update the associated value
            findNode.value = value;
        }
        // check whether need to resize the bucket
        double currentLoadFactor = (double) numOfElements / numOfBuckets;
        if(currentLoadFactor > loadFactor){
            resize();
        }
    }
    /**helper method
     *
     * insert the new node to the associated bucket */
    private void insert(K key, V value){
        int hashcode = key.hashCode();
        int position = Math.floorMod(hashcode,numOfBuckets);
        Collection<Node> temp = createBucket();
        temp = buckets[position];

        Node newNode = createNode(key, value);
        temp.add(newNode);
    }

    /**helper method
     *
     * insert the new node to the associated bucket */
    private void resize(){
        int newNumOfBuckets = numOfBuckets * multFactor;
        Collection<Node>[] newBuckets = createTable(newNumOfBuckets);
        for(int i =0 ; i < newNumOfBuckets; i++){
            newBuckets[i] = createBucket();
        }



        for(int i = 0; i < numOfBuckets; i++){
            Collection<Node> temp = buckets[i];
            Iterator<Node> iterate = temp.iterator();

            for(Node n : temp){
                K tempKey = n.key;
                int hashcode = tempKey.hashCode();
                int position = Math.floorMod(hashcode,newNumOfBuckets);
                newBuckets[position].add(n);
            }

        }

        numOfBuckets = newNumOfBuckets;
        buckets = newBuckets;
    }


    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        return allMyKeys;
    }

    private class MyHashMapIterator<K> implements Iterator<K>{
        int size;
        MyHashMapIterator(){
            size = allMyKeys.size();
        }
        @Override
        public boolean hasNext(){
            return size != 0;
        }

        @Override
        public K next(){
            K falseResult = (K) new Object();
            return falseResult;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator<>();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

}
