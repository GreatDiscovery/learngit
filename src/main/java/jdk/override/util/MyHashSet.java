package jdk.override.util;

import org.apache.activemq.store.jdbc.adapter.OracleJDBCAdapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author gavin
 * @date 2019/3/1 12:10
 */
public class MyHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable{

    static final long serialVersionUID = -5024744406713321676L;
    private transient HashMap<E, Object> map;
    private static final Object PRESENT = new Object();
    public MyHashSet() {
        map = new HashMap<>();
    }
    public MyHashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/ .75f) + 1, 16));
        addAll(c);
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }

    public Object clone() {
        try {
            MyHashSet<E> newSet = null;
            newSet = (MyHashSet<E>) super.clone();
            newSet.map = ((HashMap<E, Object> ) map.clone());
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void writeObjects(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
//        s.writeInt(map.capacity());
//        s.writeFloat(map.loadFactor());
        s.writeInt(map.size());

        for (E e : map.keySet())
            s.writeObject(e);
    }
}
