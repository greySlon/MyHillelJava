package com.slon;

//import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Sergii on 28.02.2017.
 */
class MyListTest {

    MyList list;

    @BeforeEach
    void setUp() {
        list = new MyList();
    }

    @Test
    void iterator() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        Iterator iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals("qw", iter.next());
        assertTrue(iter.hasNext());
        assertEquals(null, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(4, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(8, iter.next());
        assertFalse(iter.hasNext());

    }

    @Test
    void add() {
        assertTrue(list.add("qw"));
        assertTrue(list.add(5));
        assertTrue(list.add(null));
    }

    @Test
    void size() {
        assertEquals(0, list.size());
        list.add(null);
        list.add(7);
        assertEquals(2, list.size());
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(7);
        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {
        assertThrows(NoSuchElementException.class, () -> list.contains(6));

        list.add("qw");
        list.add(null);
        list.add(5);

        assertTrue(list.contains("qw"));
        assertTrue(list.contains(5));
        assertTrue(list.contains(null));
        assertFalse(list.contains(7));
    }

    @Test
    void remove() {
        list.add("qw");
        list.add(null);
        list.add(5);
        assertEquals(null, list.remove(1));
        assertEquals(5, list.remove(1));
        assertEquals(1, list.size());
    }

    @Test
    void addAll() {
        assertEquals(0, list.size());
        assertTrue(list.addAll(Arrays.asList("qw", null, 4, 8)));
        assertEquals(null, list.get(1));
        assertEquals(4, list.size());
    }

    @Test
    void addAll1() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertTrue(list.addAll(1, Arrays.asList("tu", null, 9, 89)));
        assertEquals("tu", list.get(1));

        int index = list.size() - 1;
        assertTrue(list.addAll(index, Arrays.asList("tu", null, 18, 77)));

        assertEquals(8, list.get(list.size() - 1));
        assertEquals(77, list.get(list.size() - 2));
    }

    @Test
    void clear() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void get() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertEquals("qw", list.get(0));
        assertEquals(null, list.get(1));
        assertEquals(4, list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void set() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertEquals(null, list.set(1, String.class));
        assertEquals(4, list.set(2, "test"));
        assertEquals(String.class, list.get(1));
        assertEquals("test", list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-4, null));
    }

    @Test
    void add1() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        list.add(1, "insert");
        assertEquals("insert", list.get(1));
    }

    @Test
    void remove1() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertTrue(list.remove("qw"));
        assertFalse(list.remove("uu"));
        assertTrue(list.remove(null));
        assertEquals(2, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-4));
    }

    @Test
    void indexOf() {
        list.addAll(Arrays.asList("qw", null, 4, 8));
        assertEquals(2, list.indexOf(4));
        assertEquals(-1, list.indexOf(5));
    }

    @Test
    void lastIndexOf() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        assertEquals(3, list.lastIndexOf(8));
        assertEquals(-1, list.lastIndexOf(5));
    }

    @Test
    void subList() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        List subList = list.subList(1, 3);
        assertEquals(3, subList.size());
        assertEquals(8, subList.get(2));
    }

    @Test
    void retainAll() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        assertTrue(list.retainAll(Arrays.asList("qw", 8, null)));
        assertEquals(4, list.size());

        assertTrue(list.retainAll(Arrays.asList("qw", 8)));
        assertFalse(list.retainAll(Arrays.asList("qw", 8, null)));
        assertEquals(3, list.size());
    }

    @Test
    void removeAll() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        assertTrue(list.removeAll(Arrays.asList("qw", null)));
        assertEquals(3, list.size());

        assertTrue(list.removeAll(Arrays.asList("qw", 8)));
        assertFalse(list.removeAll(Arrays.asList("qw", 8, null)));
        assertEquals(1, list.size());
    }

    @Test
    void containsAll() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        assertTrue(list.containsAll(Arrays.asList(8, null, "qw")));
        assertFalse(list.containsAll(Arrays.asList(8, null, "qw", 9)));
    }

    @Test
    void toArray() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        Object[] array = list.toArray();
        assertEquals(5, array.length);
        assertEquals(8, array[1]);
    }

    @Test
    void toArray1() {
        list.addAll(Arrays.asList("qw", 8, null, 8, 88));
        Object[] arraySmall = new Object[3];
        Object[] arrayBig = new Object[9];
        arraySmall = list.toArray(arraySmall);
        arrayBig = list.toArray(arrayBig);
        assertEquals(5, arraySmall.length);
        assertEquals(9, arrayBig.length);
    }
}