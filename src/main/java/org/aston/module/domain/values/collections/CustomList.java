package org.aston.module.domain.values.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomList<T> implements Collection<T> {
    private Node<T> head = null;
    private Node<T> tail;
    public CustomList(){
        this.head = null;
    }
    public CustomList(Collection collection){
        addAll(collection);
    }
    private Node<T> getNodeByIndex(int index){
        int counter = 0;
        Node<T> searcher = head;
        while (searcher != null) {
            if (counter == index) {
                return searcher;
            }
            searcher = searcher.next;
            counter++;
        }
        throw new NoSuchElementException("Нет элемента с таким индексом!");
    }
    public T get(int index){
        return getNodeByIndex(index).data;
    }
    @Override
    public int size() {
        int counter = 0;
        Node<T> searcher = head;
        while (searcher != null) {
            searcher = searcher.next;
            counter++;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new CLIterator(this);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        Node<T> buffer = new Node<T>(t);
        if (head == null) {
            head = tail = buffer;
            tail.next = null;
            head.next = tail;
        } else {
            tail.next = buffer;
            tail = buffer;
            buffer.next = null;
        }
        return true;
    }

    private boolean removeNode(Node<T> element) {
        if (element != null) {
            if (element == head) {
                head = head.next;
                element = null;
                return true;
            } else {
                Node<T> previous = head;
                while (previous.next != element && previous.next != null) {
                    previous = previous.next;
                }
                if (previous.next == element) {
                    previous.next = element.next;
                    element = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> searcher = head;
        while (searcher != null) {
            if (searcher.data.equals(o)) {
                removeNode(searcher);
                return true;
            }
            searcher = searcher.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(c!= null && !c.isEmpty()){
            for(T element : c){
                add(element);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    public void set(int i, T t) {
        getNodeByIndex(i).data = t;
    }

    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T get() {
            return data;
        }
    }

    class CLIterator<T> implements Iterator {
        private final CustomList<T> list;
        private Node<T> node;

        public CLIterator(CustomList<T> list) {
            this.list = list;
            this.node = (Node<T>) list.head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("Нет следующего элемента!");
            T data = node.get();
            node = node.next;
            return data;
        }
    }
}