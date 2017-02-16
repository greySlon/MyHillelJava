package com.slon.lesson5;

import java.util.*;

/**
 * Created by Sergii on 16.02.2017.
 */
public class IntList {
    private int[] arr;
    private int defaultSize = 10;
    private int size = 0;

    public IntList(int capacity) {
        this.arr = new int[capacity];
    }

    public IntList() {
        this.arr = new int[defaultSize];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == x) return true;
        }
        return false;
    }

    public int[] toArray() {
        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }


    public boolean add(int x) {
        if (arr.length == size) {
            grow();
        }
        arr[size++] = x;
        return true;
    }

    public boolean containsAll(int[] coll) {
        for (int i = 0; i < coll.length; i++) {
            if (!this.contains(coll[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(int[] coll) {
        if (coll.length > arr.length - size) {
            grow();
        }
        for (int i = 0; i < coll.length; i++) {
            this.add(coll[i]);
        }
        return true;
    }

    public void clear() {
        arr = new int[defaultSize];
    }

    public int get(int index) {
        return arr[index];
    }

    public boolean set(int index, int element) {
        if (index < size && index > 0) {
            arr[index] = element;
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(int index) {
        if (isOutOfBoundIndex(index)) {
            return false;
        }
        int[] tmpArray = new int[arr.length];
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (j == index) {
                j++;
            }
            tmpArray[i] = arr[j];
        }
        arr = tmpArray;
        return true;
    }

    public int indexOf(int x) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int x) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int[] subList(int fromIndex, int toIndex) {
        if (isOutOfBoundIndex(fromIndex) || isOutOfBoundIndex(toIndex)) {
            return null;
        }
        int[] subArray = new int[toIndex - fromIndex + 1];
        for (int i = fromIndex, j = 0; i <= toIndex; i++, j++) {
            subArray[j] = arr[i];
        }
        return subArray;
    }

    public void qsort() {
        qsort(0, size - 1);
    }

    private boolean isOutOfBoundIndex(int index) {
        if (index >= 0 || index < size) {
            return false;
        } else {
            return true;
        }
    }


    public boolean equalsTo(int[] coll) {
        if (coll.length != size) return false;
        for (int i = 0; i < size; i++) {
            if(arr[i]!=coll[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    private void grow() {
        int[] grownArray = new int[arr.length * 3 / 2];
        for (int i = 0; i < size; i++) {
            grownArray[i] = arr[i];
        }
        arr = grownArray;
    }

    void qsort(int b, int e) {
        int l = b, r = e;
        int piv = arr[(l + r) / 2]; // Опорным элементом для примера возьмём средний
        do {
            while (arr[l] < piv) {
                l++;
            }
            while (arr[r] > piv) {
                r--;
            }
            if (l <= r)
                swap(l++, r--);
        } while (l <= r);
        if (b < r)
            qsort(b, r);
        if (e > l)
            qsort(l, e);
    }

    private void swap(int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;

    }


    public static void main(String[] a) {
        IntList list = new IntList(3);
        list.add(4);
        list.add(45);
        list.add(1);
        list.add(-4);
        list.add(54);
        list.add(16);
        list.addAll(new int[]{5, 8, -6});
        list.qsort();
        System.out.println(list);
    }
}
