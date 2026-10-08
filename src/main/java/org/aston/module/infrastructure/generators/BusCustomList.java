package org.aston.module.controller.generators;

import java.util.*;

public class BusCustomList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    public BusCustomList()
    {
        head = null;
    }

    public BusCustomList(Collection<T> buscCollection) {
        addAll(buscCollection);
    }

    private Node<T> getNodeByIndex(int index) {
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
        return null;
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
                add((T) c);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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

    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    @Override
    public T set(int index, T element) {
        T previousData = getNodeByIndex(index).data;
        getNodeByIndex(index).data = element;
        return previousData;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new CLIterator<T>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
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

    class CLIterator<T> implements ListIterator {
        private final BusCustomList<T> list;
        private Node<T> node;

        public CLIterator(BusCustomList<T> list) {
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

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
