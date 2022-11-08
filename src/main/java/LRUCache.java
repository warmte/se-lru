import java.util.HashMap;

public class LRUCache<K, V> {
    private final BufferList<K, V> buffer = new BufferList<>();
    private final HashMap<K, BufferList<K, V>.Node> hashes = new HashMap<>();
    private final int limit;

    LRUCache(int limit) {
        this.limit = limit;
    }

    public int size() {
        assert(hashes.size() == buffer.size());
        return hashes.size();
    }

    public boolean contains(K key) {
        return hashes.containsKey(key);
    }

    public void add(K key, V value) {
        assert(key != null);
        assert(value != null);
        if (contains(key)) {
            remove(key);
        }
        assert(!contains(key));
        if (size() == limit) {
            assert(buffer.getHead() != null);
            remove(buffer.getHead().getKey());
        }
        int bufferSize = buffer.size();
        hashes.put(key, buffer.add(key, value));
        assert(buffer.size() == bufferSize + 1);
        assert(buffer.size() <= limit);
    }

    public void remove(K key) {
        assert(key != null);
        assert(contains(key));
        int bufferSize = buffer.size();
        buffer.remove(hashes.get(key));
        hashes.remove(key);
        assert(buffer.size() == bufferSize - 1);
        assert(buffer.size() <= limit);
        assert(!contains(key));
    }

    public V get(K key) {
        assert(key != null);
        if (contains(key)) {
            V value = hashes.get(key).getValue();
            remove(key);
            add(key, value);
            return value;
        }
        return null;
    }
}
