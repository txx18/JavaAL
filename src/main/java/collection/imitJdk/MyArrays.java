package collection.imitJdk;

import java.lang.reflect.Array;

/**
 * @author Shane Tang
 * @create 2020-01-09 09:39
 */
public class MyArrays {

    public static <T> T[] copyOf(T[] originalElementData, int newCapacity) {
        return (T[])copyOf(originalElementData, newCapacity, originalElementData.getClass());
    }

    private static <T, U> T[] copyOf(T[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public static String toString(Object[] a) {
        if (a == null) {
            return "null";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}
