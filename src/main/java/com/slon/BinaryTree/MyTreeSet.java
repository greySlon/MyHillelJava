package com.slon.BinaryTree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Sergii on 18.03.2017.
 */
public class MyTreeSet<T> implements Iterable<T> {
    private int size;
    private Node<T> root = null;
    private Comparator<T> comparator = null;

    public MyTreeSet() {
    }

    public MyTreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private class Node<T> {
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        T data;

        public Node(T data, Node<T> parent) {
            this(data, parent, null, null);
        }

        public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
            MyTreeSet.this.size++;
        }
    }

    private class TreeIterator implements Iterator<T> {
        Stack<Node> stack = new Stack<>();
        int count = size;
        Node<T> top = root;

        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public T next() {
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
            top = stack.pop();

            T d = top.data;

            if (top.right != null) top = top.right;
            else top = null;
            count--;
            return d;
        }
    }

    private class ReverseTreeIterator implements Iterator<T> {
        Stack<Node> stack = new Stack<>();
        int count = size;
        Node<T> top = root;

        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public T next() {
            while (top != null) {
                stack.push(top);
                top = top.right;
            }
            top = stack.pop();

            T d = top.data;

            if (top.left != null) top = top.left;
            else top = null;
            count--;
            return d;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    public Iterator<T> reverseIterator() {
        return new ReverseTreeIterator();
    }

    public boolean add(T d) {
        if (d == null) return false;
        if (root == null) {
            root = new Node<T>(d, null);
            return true;
        } else {
            return addChild(d, root);
        }
    }

    public boolean contains(T d) {
        return findNode(d) != null;
    }

    public boolean remove(T d) {
        Node<T> node = findNode(d);
        if (node == null) {
            return false;
        } else {
            reconnect(node);
            size--;
            return true;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean addChild(T d, Node<T> node) {
        while (true) {
            int cmp = compare(node, d);
            if (cmp == 0)
                return false;
            if (cmp > 0) {
                if (node.left == null) {
                    node.left = new Node<T>(d, node);
                    return true;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    node.right = new Node<T>(d, node);
                    return true;
                } else {
                    node = node.right;
                }
            }
        }
    }

    private int compare(Node<T> node, T d) {
        if (comparator != null) {
            return comparator.compare(node.data, d);
        } else {
            return ((Comparable<T>) node.data).compareTo(d);
        }
    }

    private void reconnect(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> left = node.left;
        Node<T> right = node.right;
        if (parent != null) {
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            attachNode(left);
            attachNode(right);
        } else {
            root = null;
            attachNode(left);
            attachNode(right);
        }
    }

    private void attachNode(Node<T> nodeToAttach) {
        if (nodeToAttach != null) {
            nodeToAttach.parent = null;
            Node<T> node = root;
            if (root == null) {
                root = nodeToAttach;
                return;
            }
            while (true) {
                int cmp = compare(node, nodeToAttach.data);
                if (cmp < 0) {
                    if (node.right == null) {
                        node.right = nodeToAttach;
                        nodeToAttach.parent = node;
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    if (node.left == null) {
                        node.left = nodeToAttach;
                        nodeToAttach.parent = node;
                        break;
                    } else {
                        node = node.left;
                    }
                }
            }
        }
    }

    private Node<T> findNode(T d) {
        Node<T> node = root;
        while (node != null) {
            int cmp = compare(node, d);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyTreeSet<Integer> set = new MyTreeSet<>();
        System.out.println("Iterator");
        set.add(4);
        set.add(5);
        set.add(4);
        set.add(3);
        set.add(8);
        for (Integer o : set) {
            System.out.println(o);
        }
        System.out.println("ReverseIterator");
        Iterator<Integer> iter = set.reverseIterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("Contains");
        System.out.println("5->" + set.contains(5));
        System.out.println("55->" + set.contains(55));
        System.out.println("3->" + set.contains(3));

        System.out.println("Removing 4,8,3");
        set.remove(4);
        set.remove(4);
        set.remove(8);
        set.remove(3);
        for (Integer o : set) {
            System.out.println(o);
        }
    }
}
