class Solution{
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        return process(head, null);
    }

    public ListNode process(ListNode head, ListNode tail){
        if (head == tail)
            return head;

        // 定位中间节点
        ListNode f = head, s = head;
        while (f.next != tail && f.next.next != tail){
            s = s.next;
            f = f.next.next;
        }

        ListNode mid = s, midNext = mid.next;
        mid.next = null; // 切分链表
        ListNode llist = process(head, mid);
        ListNode rlist = process(midNext, tail);
        return merge(llist, rlist);
    }

    public ListNode merge(ListNode l1, ListNode l2){
        ListNode p1 = l1, p2 = l2;
        ListNode prev_head = new ListNode(), prev = prev_head;

        while (p1 != null && p2 != null){
            if (p1.val <= p2.val){
                prev.next = p1;
                p1 = p1.next;
            }
            else {
                prev.next = p2;
                p2 = p2.next;
            }
            prev = prev.next;
        }

        if (p1 != null){
            prev.next = p1;
        }

        if (p2 != null){
            prev.next = p2;
        }

        return prev_head.next;
    }


}

// get put O(1)
// put 变更数据，不存在会插入还要检查容量
class LRUCache{
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(){}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    private int size;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        Node node = map.get(key);
        if (node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        Node node = map.get(key);
        if (node == null){
            node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            ++size;
            if (size > capacity){
                Node res = removeTail();
                map.remove(res.key);
                --size;
            }
        } else{
            node.value = value;
            moveToHead(node);
        }
    }

    public void addToHead(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }
    public Node removeTail(){
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
