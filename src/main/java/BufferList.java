public class BufferList<K, V> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    BufferList() {}

    Node add(K key, V value) {
        size++;
        Node next = new Node(key, value, null, null);
        if (head == null) {
            head = next;
        }
        if (tail != null) {
            tail.addNext(next);
        }
        tail = next;
        return next;
    }

    void remove(Node node) {
        assert (size > 0);
        assert (node != null);
        node.remove();
        size--;
    }

    Node getHead() {
        return head;
    }

    Node getTail() {
        return tail;
    }

    int size() {
        return size;
    }

    class Node {
        private final K key;
        private final V value;
        private Node next;
        private Node prev;

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void addNext(Node next) {
            assert(this.next == null);
            next.prev = this;
            this.next = next;
        }

        void remove() {
            if (this.prev != null) {
                this.prev.next = this.next;
            }
            if (this.next != null) {
                this.next.prev = this.prev;
            }
        }

        Node(K key, V value, Node next, Node prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
