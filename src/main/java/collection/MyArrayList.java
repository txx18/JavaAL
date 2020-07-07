package collection;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Shane Tang
 * @create 2020-07-07 16:01
 */
public class MyArrayList<T> implements Iterable<T> {

    private int size;

    private T[] items;

    public MyArrayList() {
        this.items = ((T[]) new Objects[10]);
    }

    // get
    public T get(int index) {
        if (index >= size || index < 0 ) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index];
    }

    // set
    public void set(int index, T newItem) {
        if (index >= size || index < 0 ) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        items[index] = newItem;
    }

    // ensureCapacity
    private void ensureCapacity(int newCap) {
//        if (size <= newCap) { /* <= 原来有2个，准备*/
//            T[] newArr = (T[]) new Objects[newCap * 2];
//            for (int i = 0; i < size; i++) {
//                newArr[i] = items[i];
//            }
//            items = newArr;
//        }
    }

    // add
    public void add(int index, T x) {
//        ensureCapacity(size + 1);
//        for (int i = size; i > index; i--) { /* i = size */
//            items[i] = items[i - 1];
//        }
//        items[index] = x;
//        size++;
    }



    // remove
    public T remove(int index) {
        T itemToRemove = items[index];
        for (int i = index; i < size; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return itemToRemove;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
