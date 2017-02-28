package com.slon;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by Sergii on 28.02.2017.
 */
public class MyList implements List {
    private static class Node {
        Object data;
        Node prev;
        Node next;

        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    private boolean checkOutBound(int index) {
        if (index < 0 || index >= size) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (o == null) {
                for (Node tmp = head; tmp != null; tmp = tmp.next) {
                    if (tmp.data == o) {
                        return true;
                    }
                }
            } else {
                for (Node tmp = head; tmp != null; tmp = tmp.next) {
                    if (tmp.data.equals(o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node currentNode = head;
            Node previousNode = null;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                previousNode = currentNode;
                currentNode = previousNode.next;
                count++;
                return previousNode.data;
            }

            @Override
            public void remove() {
                Node prev = previousNode.prev;
                Node next = previousNode.next;
                if (prev == null) {
                    head = next;
                } else {
                    prev.next = next;
                }
                if (next == null) {
                    tail = prev;
                } else {
                    next.prev = prev;
                }
                previousNode.next = previousNode.prev = null;
                previousNode.data = null;
                count--;
                size--;
            }
        };
    }

    @Override
    public boolean add(Object o) {
        if (size == 0) {
            Node node = new Node(o, head, tail);
            head = tail = node;
            size++;
            return true;
        } else {
            Node node = new Node(o, tail, null);
            tail.next = node;
            tail = node;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        } else {
            Iterator<Object> iter = this.iterator();
            while (iter.hasNext()) {
                if (iter.next().equals(o)) {
                    iter.remove();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }
        for (Object o : c) {
            this.add(o);
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (checkOutBound(index) || c.size() == 0) {
            return false;
        }
        if ((size << 1) > index) {
            Node tmpNode = head;
            for (int i = 0; i <= index; i++) tmpNode = tmpNode.next;

            Node node;
            for (Object o : c) {
                node = new Node(o, tmpNode.prev, tmpNode);
                tmpNode.prev.next = node;
                tmpNode.prev = node;
                size++;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        head = tail = null;
    }

    @Override
    public Object get(int index) {
        if (checkOutBound(index)) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    @Override
    public Object set(int index, Object element) {
        if (checkOutBound(index)) {
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        Object oldData = node.data;
        node.data = element;
        return oldData;
    }

    private Node getNode(int index) {
        if (index < (size >> 1)) {
            Node node = head;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node;
        } else {
            Node node = tail;
            for (int i = size - 1; i > index; i--)
                node = node.prev;
            return node;
        }
    }

    @Override
    public void add(int index, Object element) {
        Node node = getNode(index);
        Node prev = node.prev;
        Node newNode = new Node(element, prev, node);
        node.prev = newNode;
        prev.next = newNode;
    }

    @Override
    public Object remove(int index) {
        if (checkOutBound(index)) {
            throw new IndexOutOfBoundsException();
        }
        Iterator<Object> iter = this.iterator();
        int i = 0;
        Object data = null;
        while (i++ <= index) {
            data = iter.next();
        }
        iter.remove();
        return data;
    }

    @Override
    public int indexOf(Object o) {
        int i = -1;
        if (o == null) {
            for (Object item : this) {
                i++;
                if (item == null) {
                    return i;
                }
            }
        } else {
            for (Object item : this) {
                i++;
                if (o.equals(item)) {
                    return i;
                }
            }
        }
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = size - 1;
        if (o != null) {
            for (Node node = tail; node != null; node = node.prev) {
                if (o.equals(node.data)) {
                    return i;
                }
                i--;
            }
        } else {
            for (Node node = tail; node != null; node = node.prev) {
                if (o == node.data) {
                    return i;
                }
                i--;
            }
        }
        return -1;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        MyList list = new MyList();
        list.head = getNode(fromIndex);
        list.tail = getNode(toIndex);
        return list;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean changed = false;
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!c.contains(iter.next())) {
                iter.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean changed = false;
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            if (c.contains(iter.next())) {
                iter.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (indexOf(o) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) {
            return this.toArray();
        } else {
            int i = 0;
            Object[] array = new Object[size];
            for (Node node = head; node != null; node = node.next) {
                array[i++] = node.data;
            }
            return array;
        }
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        } else {
            Object[] array = new Object[size];
            int i = 0;
            for (Node node = head; node != null; node = node.next) {
                array[i++] = node.data;
            }
            return array;
        }
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator operator) {
        throw new UnsupportedOperationException();
    }

}