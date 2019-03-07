//package jdk.override.util;
//
//import org.junit.Test;
//import sun.reflect.generics.tree.Tree;
//
//import javax.swing.event.TreeExpansionEvent;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//import static jdk.override.util.MyHashMap.TreeNode.balanceInsertion;
//import static jdk.override.util.MyHashMap.TreeNode.tieBreakOrder;
//
///**
// * @author gavin
// * @date 2019/2/11 9:38
// */
//public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
//    private static final long serialVersionUID = 362498820763181265L;
//    static final int DEFAULT_INITAL_CAPACITY = 1 << 4;
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//    static final int TREEIFY_THRESHOLD = 8;
//    static final int UNTREEIFY_THRESHOLD = 6;
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    static class Node<K, V> implements Map.Entry<K, V> {
//        final int hash;
//        final K key;
//        V value;
//        Node<K, V> next;
//
//        public Node(int hash, K key, V vallue, Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = vallue;
//            this.next = next;
//        }
//
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public String toString() {
//            return key + "=" + value;
//        }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<K, V> e = (Map.Entry<K, V>) o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    /*-------------static utilities-------------*/
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c;
//            Type[] ts, as;
//            Type t;
//            ParameterizedType p;
//            if ((c = x.getClass()) == String.class)
//                return c;
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (int i = 0; i < ts.length; ++i) {
//                    if (((t = ts[i]) instanceof ParameterizedType) && ((p = (ParameterizedType) t).getRawType() == Comparable.class) &&
//                            (as = p.getActualTypeArguments()) != null &&
//                            as.length == 1 && as[0] == c)
//                        return c;
//                }
//            }
//
//        }
//        return null;
//    }
//
//    static int compareComparables(Class<?> kc, Object k, Object x) {
//        return (x == null || x.getClass() != kc ? 0 : ((Comparable) k).compareTo(x));
//    }
//
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    transient Node<K, V>[] table;
//    transient Set<Map.Entry<K, V>> entrySet;
//    transient int size;
//    transient int modCount;
//    int threshold;
//    final float loadFactor;
//
//    public MyHashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity" + initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    public MyHashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    public MyHashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//    }
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }
//
//    @Override
//    public V getOrDefault(Object key, V defaultValue) {
//        return null;
//    }
//
//    @Override
//    public void forEach(BiConsumer<? super K, ? super V> action) {
//
//    }
//
//    @Override
//    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//
//    }
//
//    @Override
//    public V putIfAbsent(K key, V value) {
//        return null;
//    }
//
//    @Override
//    public boolean remove(Object key, Object value) {
//        return false;
//    }
//
//    @Override
//    public boolean replace(K key, V oldValue, V newValue) {
//        return false;
//    }
//
//    @Override
//    public V replace(K key, V value) {
//        return null;
//    }
//
//    @Override
//    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    @Override
//    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//        return null;
//    }
//
//    public MyHashMap(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//
//    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
//        int s = m.size();
//        if (s > 0) {
//            if (table == null) {
//                float ft = ((float) s / loadFactor) + 1.0F;
//                int t = (ft < (float) MAXIMUM_CAPACITY) ? (int) ft : MAXIMUM_CAPACITY;
//                if (t > threshold)
//                    threshold = tableSizeFor(t);
//            } else if (s > threshold)
//                resize();
//            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//                K key = e.getKey();
//                V value = e.getValue();
//                putVal(hash(key), key, value, false, evict);
//            }
//        }
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public V get(Object key) {
//        Node<K, V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    final Node<K, V> getNode(int hash, Object key) {
//        Node<K, V>[] tab;
//        Node<K, V> first, e;
//        int n;
//        K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash &&
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K, V>) first).getTreeNode(hash, key);
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key) || (key != null && key.equals(k)))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//
//        }
//        return null;
//    }
//
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evice) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        // 如果hash位置为null，直接放入
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            Node<K, V> e;
//            K k;
//            // 如果该位置与要存入的node相等，直接返回该node
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof TreeNode) {
//                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) {
//                            treeifyBin(tab, hash);
//                        }
//                        break;
//                    }
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) {
//                V oldValue = e.value;
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        return null;
//    }
//
//    final Node<K, V>[] resize() {
//        // 先对oldTab进行判断，主要进行容量和阈值的计算
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        // 如果oldCap大于0，先判断有没有超过最大容量
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            // 如果没有超过最大容量，进行移位后再判断超过最大容量
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITAL_CAPACITY)
//                newThr = oldThr << 1;
//        }
//        // 如果容量为0，但是有初始的threshold值，可以不用默认值
//        else if (oldThr > 0)
//            newCap = oldThr;
//        else {
//            newCap = DEFAULT_INITAL_CAPACITY;
//            newThr = (int) (DEFAULT_INITAL_CAPACITY * DEFAULT_INITAL_CAPACITY);
//        }
//        // 重新处理阈值
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        // new一个新的数组，可以开始搬数据了
//        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K, V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        // 如果只有单个node，直接存入新数组
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)
//                        // 如果是treeNode，重新拆解node
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//                    else {
//                        // 为什么需要这么麻烦？为了保证拷贝过去的链表保持原顺序
//                        Node<K, V> loHead = null, loTail = null;
//                        Node<K, V> hiHead = null, hiTail = null;
//                        Node<K, V> next;
//                        do {
//                            next = e.next;
//                            // 原索引
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            }
//                            // 原索引 + oldCap
//                            else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        // 先组成链表，再放入桶内
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//
//        }
//        return newTab;
//    }
//
//    // 将拉链上的node换成TreeNode,并且维护一个双向链表
//    final void treeifyBin(Node<K, V>[] tab, int hash) {
//        int n, index;
//        Node<K, V> e;
//        // 如果tab为空，或者数组的大小小于4*8
//        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
//            resize();
//        else if ((e = tab[index = (n - 1) & hash]) != null) {
//            TreeNode<K, V> hd = null, tl = null;
//            do {
//                TreeNode<K, V> p = replacementTreeNode(e, null);
//                if (tl == null)
//                    hd = p;
//                else {
//                    p.prev = tl;
//                    tl.next = p;
//                }
//                tl = p;
//            } while ((e = e.next) != null);
//            if ((tab[index] = hd) != null)
//                hd.treeify(tab);
//        }
//    }
//
//    final void treeify(Node<K, V>[] tab) {
//        TreeNode<K, V> root = null;
//        for (TreeNode<K, V> x = this, next; x != null; x = next) {
//            next = (TreeNode<K, V>) x.next;
//            x.left = x.right = null;
//            // 先安置好root节点
//            if (root == null) {
//                x.parent = null;
//                x.red = false;
//                root = x;
//            } else {
//                K k = x.key;
//                int h = x.hash;
//                Class<?> kc = null;
//                for (TreeNode<k, V> p = root; ; ) {
//                    int dir, ph;
//                    K pk = p.key;
//                    if ((ph = p.hash) > h)
//                        dir = -1;
//                    else if (ph < h)
//                        dir = 1;
//                    else if ((kc == null && (kc = comparableClassFor(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0)
//                        dir = tieBreakOrder(k, pk);
//                    TreeNode<K, V> xp = p;
//                    if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                        x.parent = xp;
//                        if (dir <= 0)
//                            xp.left = x;
//                        else
//                            xp.right = x;
//                        root = balanceInsertion(root, x);
//                        break;
//                    }
//                }
//            }
//        }
//        moveRootToFront(tab, root);
//    }
//
//    public V remove(Object key) {
//        Node<K,V> e;
//        return (e = remove(hash(key), key, null, false, true)) == null ? null : e.value;
//    }
//
//    public Node<K,V> remove(int hash, Object key, Object value, boolean matchValue, boolean movable) {
//       Node<K,V>[] tab; Node<K,V> p; int n, index;
//       if ((tab = table) != null && (n = tab.length) > 0 &&
//               (p = tab[index = (n - 1) & hash]) != null) {
//           Node<K,V> node = null, e; K k; V v;
//           if (p.hash == hash &&
//                   ((k = p.key) == key || (key != null && key.equals(k))))
//               node = p;
//           else if ((e = p.next) != null) {
//               if (p instanceof TreeNode)
//                   node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
//               else {
//                   do {
//                       if (e.hash == hash &&
//                               ((k = e.key) == key ||
//                                       (key != null && key.equals(k)))) {
//                           node = e;
//                           break;
//                       }
//                       p = e;
//                   } while ((e = e.next) != null);
//               }
//           }
//           if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
//               if (node instanceof TreeNode)
//                   ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
//               else if (node == p)
//                   tab[index] = node.next;
//               else
//                   p.next = node.next;
//               ++modCount;
//               --size;
//               afterNodeRemoval(node);
//               return node;
//           }
//       }
//       return null;
//    }
//
//    public void clear() {
//        Node<K,V>[] tab;
//        modCount++;
//        if ((tab = table) != null && size >0) {
//            size = 0;
//            for (int i = 0; i < tab.length; ++i)
//                tab[i] = null;
//        }
//    }
//
//    public boolean containsValue(Object value) {
//        Node<K,V>[] tab; V v;
//        if ((tab = table) != null && size > 0) {
//            for (int i = 0; i < tab.length; ++i) {
//                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
//                    if ((v = e.value) == value || (value != null && value.equals(v)))
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    public Set<K> keyset() {
//        Set<K> ks = keySet;
//        if (ks == null) {
//            ks = new KeySet();
//            keySet = ks;
//        }
//        return ks;
//    }
//
//
//    // 这里只是从双向链表的角度把root移动到首位，打破了原先的双向链表，并没有破坏红黑树的平衡性
//    static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
//        int n;
//        if (root != null && tab != null && (n = tab.length) > 0) {
//            int index = (n - 1) & root.hash;
//            TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
//            if (root != first) {
//                Node<K, V> rn;
//                tab[index] = root;
//                TreeNode<K, V> rp = root.prev;
//                if ((rn = root.next) != null)
//                    ((TreeNode < K, V > rn)).prev = rp;
//                if (rp != null)
//                    rp.next = rn;
//                if (first != null)
//                    first.prev = root;
//                root.next = first;
//                root.prev = null;
//            }
//            assert checkInvariants(root);
//        }
//    }
//
//    /*-------------LinkedHashMap support---------------*/
//    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
//        return new Node<>(hash, key, value, next);
//    }
//
//    static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {
//        TreeNode<K, V> parent;
//        TreeNode<K, V> left;
//        TreeNode<K, V> right;
//        TreeNode<K, V> prev;
//        boolean red;
//
//        TreeNode(int hash, K key, V val, MyHashMap.Node<K, V> next) {
//            super(hash, key, val, next);
//        }
//
//        final TreeNode<K, V> root() {
//            for (TreeNode<K, V> r = this, p; ; ) {
//                if ((p = r.parent) == null) {
//                    return r;
//                }
//                r = p;
//            }
//        }
//
//        final TreeNode<K, V> putTreeVal(MyHashMap<K, V> map, Node<K, V>[] tab, int h, K k, V v) {
//
//            Class<?> kc = null;
//            boolean searched = false;
//            TreeNode<K, V> root = (parent != null) ? root() : this;
//            for (TreeNode<K, V> p = root; ; ) {
//                int dir, ph;
//                K pk;
//                // 此节点的hash值与h不同，h是调用hash()函数算出来的，而p.hash是节点真正的hash值，在此处比较是为了方便放入左子树还是右子树
//                if ((ph = p.hash) > h)
//                    dir = -1;
//                else if (ph < h)
//                    dir = 1;
//                    // 同一个对象或者值相等都可以
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                    // 判断是否实现了compareble接口，hash值相等，但是不可比较进这里
//                else if ((kc == null && (kc = comparableClassFor(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0))
//                {
//                    // 先搜寻一遍是否已经有相同的node存在了
//                    if (!searched) {
//                        TreeNode<K, V> q, ch;
//                        searched = true;
//                        // 如果找到了直接返回结果
//                        if (((ch = p.left) != null &&
//                                (q = ch.find(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.find(h, k, kc)) != null))
//                            return q;
//                    }
//                    // 没找到的话需要确定往左子树还是右子树存放，因为没有compareable接口，所以只能通过本地的hash方法来比较
//                    dir = tieBreakOrder(k, pk);
//                }
//                // 到这一步已经确定了树里面没有与插入node相同的key
//                TreeNode<K, V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    Node<K, V> xpn = xp.next;
//                    TreeNode<K, V> x = map.newNode(h, k, v, xpn);
//                    if (dir <= 0)
//                        xp.left = x;
//                    else
//                        xp.right = x;
//                    xp.next = x;
//                    x.parent = x.prev = xp;
//                    if (xpn != null)
//                        ((TreeNode<K, V>) xpn).prev = x;
//                    moveRootToFront(tab, balanceInsertion(root, x));
//                    return null;
//                }
//            }
//        }
//
//        /*--------------------------------------------*/
//        // 红黑树的方法
//        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p) {
//            TreeNode<K, V> l, pp, rl;
//            if (p != null && (l = p.left) != null) {
//            }
//            return null;
//        }
//
//        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
//            TreeNode<K, V> l, pp, lr;
//            if (p != null && (l = p.left) != null) {
//                if ((lr = p.left = l.right) != null)
//                    lr.parent = p;
//                if ((pp = l.parent = p.parent) == null)
//                    (root = l).red = false;
//                else if (pp.right == p)
//                    pp.right = l;
//                else
//                    pp.left = l;
//                l.right = p;
//                p.parent = l;
//            }
//        }
//
//        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x) {
//            // 红黑树插入的节点一定是红色，如果是黑色，会破坏红黑树的平衡性
//            x.red = true;
//            for (TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
//                // 如果没有父节点，则x节点就是根节点，要改为黑色
//                if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                // 如果有父节点，父节点是黑色的，并且没有爷爷节点，说明父节点是root节点
//                else if (!xp.red || (xpp = xp.parent) == null)
//                    return root;
//                // 如果有父节点和爷爷节点，并且父节点是红色的，爷爷节点是黑色的（因为原来的红黑树是平衡的），判断父节点是爷爷节点的左子节点
//                if (xp == (xppl = xpp.left)) {
//                    // 爷爷节点有有右节点（称他为叔叔节点），并且叔叔节点也是红色的，将这三个节点换颜色
//                    if ((xppr = xpp.right) != null && xppr.red) {
//                        xppr.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        // 换颜色后要接着往上判断是否颜色平衡
//                        x = xp;
//                    }
//                    // 叔叔节点是黑色的
//                    else {
//                        // 接着判断本节点是父节点的右子节点还是左子节点，该节点和父节点都是红色，所以要旋转两次
//                        if (x == xp.right) {
//                            // 旋转以后父子节点调换了位置
//                            root = rotateLeft(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        // 新增节点是父节点的左子节点
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateRight(root, xpp);
//                            }
//                        }
//                    }
//                } else {
//                    if (xppl != null && xppl.red) {
//                        xppl.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    } else {
//                        if (x == xp.left) {
//                            root = rotateRight(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateLeft(root, xpp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        final void split(MyHashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
//            TreeNode<K, V> b = this;
//            TreeNode<K, V> loHead = null, loTail = null;
//            TreeNode<K, V> hiHead = null, hiTail = null;
//            int lc = 0, hc = 0;
//            for (TreeNode<K, V> e = b, next; e != null; e = next) {
//                next = (TreeNode<K, V>) e.next;
//                e.next = null;
//                // 说明e还在原位置
//                if ((e.hash & bit) == 0) {
//                    if ((e.prev = loTail) == null)
//                        loHead = e;
//                    else
//                        loTail.next = e;
//                    loTail = e;
//                    ++lc;
//                }
//                // e + oldCap
//                else {
//                    if ((e.prev = hiTail) == null)
//                        hiHead = e;
//                    else
//                        hiTail.next = e;
//                    hiTail = e;
//                    ++hc;
//                }
//            }
//
//            if (loHead != null) {
//                if (lc <= UNTREEIFY_THRESHOLD)
//                    tab[index] = loHead.untreeify(map);
//                else {
//                    tab[index] = loHead;
//                    if (hiHead != null)
//                        loHead.treeify(tab);
//                }
//            }
//            if (hiHead != null) {
//                if (hc <= UNTREEIFY_THRESHOLD)
//                    tab[index + bit] = hiHead.untreeify(map);
//                else {
//                    tab[index + bit] = hiHead;
//                    if (loHead != null)
//                        hiHead.treeify(tab);
//                }
//            }
//
//        }
//
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null || (d = a.getClass().getName().compareTo(b.getClass().getName())) == 0)
//                // identityHashCode调用的是默认的用内存地址计算的hash值，而不管是否重写了hash函数
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            return d;
//        }
//
//
//    }
//    // 从双向链表->红黑树之间的连接是否完整->红黑树的颜色是否正确->递归到子类进行检查
//    static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
//        TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
//                tb = t.prev, tn = (TreeNode<K, V>) t.next;
//        if (tb != null && tb.next != t)
//            return false;
//        if (tn != null && tn.prev != t)
//            return false;
//        if (tp != null && t != tp.left && t != tp.right)
//            return false;
//        if (tl != null && (tl.parent != t || tl.hash > t.hash))
//            return false;
//        if (tr != null && (tr.parent != t || tr.hash < t.hash))
//            return false;
//        if (t.red && tl != null && tl.red && tr != null && tr.red)
//            return false;
//        if (tl != null && !checkInvariants(tl))
//            return false;
//        if (tr != null && !checkInvariants(tr))
//            return false;
//        return true;
//    }
//
//}
