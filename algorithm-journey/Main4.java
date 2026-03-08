public class Solution {
    // Method1: 相交链表
    public static Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Node p = headA, q = headB;
        while (p != q) {
            p = (p == null) ? headB : p.next;
            q = (q == null) ? headA : q.next;
        }
        return p;
    }

    // Method2: 1. 不借助额外空间 反转链表
    public static Node reverseList(Node head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        // 此时还剩下两个元素或者以上
        // 存储前一个节点，当前节点
        Node prev = null, cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // Method2: 2. 借助额外空间 反转链表
    public static Node reverseList2(Node head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;

        Deque<Node> s = new ArrayDeque<>();
        Node p = head;
        while (p != null) {
            s.push(p);
            p = p.next;
        }

        // pop出来然后连接在一起
        Node prev_head = new Node();
        p = prev_head;
        while (!s.isEmpty()) {
            p.next = s.pop();
            p = p.next;
        }
        // 最后一个节点的next一定要置为null
        p.next = null;
        return prev_head.next;
    }

    // Method3: 打印两个有序链表的公共序列部分
    public static void printCommonPart(Node headA, Node headB) {
        Node p = headA, q = headB;
        while (p != null && q != null) {
            if (p.val < q.val) {
                p = p.next;
            } else if (p.val > q.val) {
                q = q.next;
            } else {
                System.out.print(p.val + " ");
                p = p.next;
                q = q.next;
            }
        }
        System.out.println();
    }

    // Method5: 1. 借助栈 判断一个链表是否是回文结构
    public static boolean isPalindrome(Node head){
        if (head == null)
            return false;
        if (head.next == null)
            return true;
        // 两个及以上节点
        // 先找中间节点 1 2 1     1 2 3 3 2 1
        Node f = head, s = head;
        while (f.next != null && f.next.next != null){
            f = f.next.next;
            s = s.next;
        }

        Node tmp = s.next;
        Deque<Node> stack = new ArrayDeque<>();
        while (tmp != null){
            stack.push(tmp);
            tmp = tmp.next;
        }

        s = head;
        while (!stack.isEmpty()){
            if (stack.pop().val != s.val){
                return false;
            }
            s = s.next;
        }
        return true;
    }

    // Method5: 2. 不使用栈 判断一个链表是否是回文结构
    public static boolean isPalindrome2(Node head){
        if (head == null)
            return false;
        if (head.next == null)
            return true;

        // 寻找中间节点  1 2 1     1 2 3 3 2 1
        Node f = head, s = head;
        while (f.next != null && f.next.next != null){
            f = f.next.next;
            s = s.next;
        }

        Node prev = reverseList(s.next);
        // 开始遍历 prev 往前动，head往后动
        f = head;
        boolean res = true;
        Node prev_head = prev;
        while (prev != f && prev != null){
            if (prev.val != f.val){
                res = false;
                break;
            }
            prev = prev.next;
            f = f.next;
        }

        // 链表回调
        s.next = reverseList(prev_head);
        return res;
    }

}
