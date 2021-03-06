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
                    if (o.equals(tmp.data)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private class MyListIterator implements ListIterator {
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
        public boolean hasPrevious() {
            return count < size;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            previousNode = currentNode;
            currentNode = previousNode.prev;
            count--;
            return previousNode.data;
        }

        @Override
        public int nextIndex() {
            return count;
        }

        @Override
        public int previousIndex() {
            return count - 1;
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

        @Override
        public void set(Object o) {
            previousNode.data = o;
        }

        @Override
        public void add(Object o) {
            if (MyList.this.size() == 0) {
                MyList.this.add(o);
            } else {
                int index = count == 0 ? 0 : count - 1;
                MyList.this.add(index, 0);
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator();
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
            if (o != null) {
                while (iter.hasNext()) {
                    if (o.equals(iter.next())) {
                        iter.remove();
                        return true;
                    }
                }
            } else {
                while (iter.hasNext()) {
                    if (o == iter.next()) {
                        iter.remove();
                        return true;
                    }
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
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (checkOutBound(index) || c.size() == 0) {
            return false;
        }
        Node tmpNode;
        if ((size >> 1) > index) {
            tmpNode = head;
            for (int i = 0; i < index; i++) tmpNode = tmpNode.next;
        } else {
            tmpNode = tail;
            for (int i = size - 1; i > index; i--) tmpNode = tmpNode.prev;
        }

        Node node;
        for (Object o : c) {
            node = new Node(o, tmpNode.prev, tmpNode);
            tmpNode.prev.next = node;
            tmpNode.prev = node;
            size++;
        }
        return true;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
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
        return -1;
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
        list.size = toIndex - fromIndex + 1;
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
            int i=0;
            for (Object item : this) {
                a[i]=item;
                i++;
            }
            return a;
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
        return new MyListIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator operator) {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] a) {
        MyList list = new MyList();
        list.add("qw");
        list.add("qh");
        list.add("qu");
        list.add("qt");
        list.add("qr");
        Iterator iter = list.listIterator();
        iter.next();
        iter.remove();
        for (Object item : list) {
            System.out.println(item);
        }
        System.out.println("********");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }
}