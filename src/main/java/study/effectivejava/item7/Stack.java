package study.effectivejava.item7;

import java.util.*;

public class Stack {
    public static final int DEFAULT_INITAL_CAPACITY = 10;
    private Object[] element;
    private int size = 0;

    public Stack() {
        element = new Object[DEFAULT_INITAL_CAPACITY];
    }

    public void push(Object item) {
        ensureCapacity(1);
        element[size++] = item;
    }

    private void ensureCapacity(int needSize) {
        if(size + needSize > element.length){
            element = Arrays.copyOf(element, element.length + needSize);
        }
    }

    public Object pop() {
        if(size == 0)
            throw new EmptyStackException();

        Object result = element[--size];
        element[size] = null;
        return result;
    }

    public void pushAll(List<Object> items) {
        ensureCapacity(items.size());
        items.forEach(object -> element[size++] = object);
    }

    public int getCapacity(){
        return element.length;
    }

    public List<Object> popAll() {
        List<Object> result = Arrays.asList(element);
        element = new Object[size];
        size = 0;
        return Arrays.asList(result);
    }
}
