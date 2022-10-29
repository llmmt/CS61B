package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{

    private BSTNode bstmap;
    private int size;
    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        public BSTNode(K k, V v, BSTNode l, BSTNode r){
            key = k;
            value = v;
            left = l;
            right = r;
        }
    }
    public BSTMap(){
        size = 0;
        bstmap = null;
    }
    public BSTMap(K k, V v, BSTNode l, BSTNode r){
        size = 1;
        bstmap = new BSTNode(k, v, l, r);
    }

    public void clear(){

        bstmap = null;
        size = 0;
    }

    public boolean containsKey(K key){
        return find(bstmap, key) != null;
    }


    public V get(K key){
        BSTNode temp = find(bstmap, key);
        if(temp != null){
            return temp.value;
        } else {
            return null;
        }
    }


    public int size(){
        return size;
    }


    public void put(K key, V value){
        if(find(bstmap, key) != null) {
            return ;
        } else {
           bstmap = insert(bstmap, key, value);
           size++;
        }
    }

//**********************   helper methods for BSTNode

    // search method
    private BSTNode find(BSTNode bst, K k){
        if(bst == null){
            return null;
        } else if (k.equals(bst.key)){
            return bst;
        } else if (k.compareTo(bst.key) > 0){
            return find(bst.right, k);
        } else {
            return find(bst.left, k);
        }
    }

    private BSTNode insert(BSTNode bst, K k, V v){
        if(bst == null) {
            return new BSTNode(k, v, null, null);
        } else {
            if(k.compareTo(bst.key) > 0){
                bst.right = insert(bst.right, k, v);
            } else {
                bst.left = insert(bst.left, k, v);
            }
            return bst;
        }
    }

    public Set<K> keySet() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("not implemented\n");
    }

    public V remove(K key) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("not implemented\n");
    }

    public V remove(K key, V value) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("not implemented\n");
    }

    public Iterator iterator() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("not implemented\n");
    }
}

