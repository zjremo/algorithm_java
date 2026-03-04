/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {

    // 建立哈希表
    public Map<Node, Node> map = new HashMap<>();

    // 随机链表的复制
    // 方法一: 递归, 每个节点只用管next和random，没有就创建
    // 有的话就不用管了
    public Node copyRandomList(Node head) {
        // 哈希表存储，每次遍历都加到hash表中
        if (head == null)
            return null;


        if (!map.containsKey(head)){
            Node n_head = new Node(head.val);
            map.put(head, n_head);
            n_head.next = copyRandomList(head.next);
            n_head.random = copyRandomList(head.random);
        }
        return map.get(head);
    }

    public Node copyRandomList1(Node head) {
        // 哈希表存储，每次遍历都加到hash表中
        if (head == null)
            return null;

        Map<Node, Node> mp = new HashMap<>();
        Node p = head;

        while (p != null) {
            mp.put(p, new Node(p.val));
            p = p.next;
        }

        p = head;
        while (p != null){
            Node tmp = mp.get(p);
            tmp.next = mp.get(p.next);
            tmp.random = mp.get(p.random);
            p = p.next;
        }
        
        return mp.get(head);
    }
}
