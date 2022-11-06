import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LRUCacheTest {
    @Test
    public void addTest() {
        LRUCache<String, Long> lru = new LRUCache<>(5);
        lru.add("1", 1L);
        lru.add("2", 2L);
        lru.add("3", 3L);
        assertEquals(lru.size(), 3);
        assertEquals(lru.get("1"), 1L);
        assertEquals(lru.get("2"), 2L);
        assertEquals(lru.get("3"), 3L);
    }

    @Test
    public void removeTest() {
        LRUCache<String, Long> lru = new LRUCache<>(5);
        lru.add("1", 1L);
        lru.add("2", 2L);
        lru.add("3", 3L);
        lru.remove("2");
        assertEquals(lru.size(), 2);
        assertEquals(lru.get("1"), 1L);
        assertNull(lru.get("2"));
        assertEquals(lru.get("3"), 3L);
    }

    @Test
    public void limitTest() {
        LRUCache<String, Long> lru = new LRUCache<>(2);
        lru.add("1", 1L);
        lru.add("2", 2L);
        lru.add("3", 3L);
        assertEquals(lru.size(), 2);
        assertNull(lru.get("1"));
        assertEquals(lru.get("2"), 2L);
        assertEquals(lru.get("3"), 3L);
    }

    @Test
    public void complexTest() {
        LRUCache<String, Long> lru = new LRUCache<>(3);
        lru.add("1", 1L);
        lru.add("2", 2L);
        lru.add("3", 3L);
        assertEquals(lru.size(), 3);
        assertEquals(lru.get("1"), 1L);
        assertEquals(lru.get("2"), 2L);
        assertEquals(lru.get("3"), 3L);
        lru.add("1", 4L);
        assertEquals(lru.size(), 3);
        assertEquals(lru.get("1"), 4L);
        lru.add("4", 4L);
        assertEquals(lru.size(), 3);
        assertNull(lru.get("1"));
        assertEquals(lru.get("4"), 4L);
        lru.remove("2");
        assertEquals(lru.size(), 2);
        assertNull(lru.get("2"));
        lru.add("5", 5L);
        assertEquals(lru.size(), 3);
        assertEquals(lru.get("3"), 3L);
        assertEquals(lru.get("4"), 4L);
        assertEquals(lru.get("5"), 5L);
    }
}