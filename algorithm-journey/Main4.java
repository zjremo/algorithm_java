class Solution {
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

    // Method6: 将单向链表按照某值划分为左边小、中间相等和右边大的形式
    // 1. 使用数组来做，数组的partition是会的
    public static Node listPartition(Node head, int pivot){
        if (head == null)
            return null;
        List<Node> list = new ArrayList<>();
        Node p = head;
        while (p != null){
            list.add(p);
            p = p.next;
        }
        // 开始对数组（列表）做partition
        partition(list, pivot);
        // 重新串连起来
        Node prev_head = new Node(), prev = prev_head;
        for (Node node : list){
            prev.next = node;
            prev = prev.next;
            node.next = null;
        }
        return prev_head.next;
    }

    // 数组的partition操作，参考荷兰国旗问题
    public static void partition(List<Node> list, int pivot){
        int less = -1, more = list.size();
        int l = 0;
        while (l < more){
            int val = list.get(l).val;
            if (val < pivot){
                Collections.swap(list, ++less, l++);
            } else if (val > pivot){
                Collections.swap(list, --more, l);
            } else {
                ++l;
            }
        }
    }


    // Method6: 将单向链表按照某值划分为左边小、中间相等和右边大的形式
    // 2. 用有限的变量来做，共使用6个变量
    public static Node listPartition2(Node head, int pivot){
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        // 共分段组织三个链表，然后三个链表连起来
        Node p = head, next = null;
        while (p != null){
            if (p.val < pivot){
                if (sH == null){
                    sH = p;
                    sT = p;
                } else {
                    sT.next = p;
                    sT = p;
                }
            } else if (p.val > pivot){
                if (mH == null){
                    mH = p;
                    mT = p;
                } else {
                    mT.next = p;
                    mT = p;
                }
            } else {
                if (eH == null){
                    eH = p;
                    eT = p;
                } else {
                    eT.next = p;
                    eT = p;
                }
            }
            next = p.next;
            p.next = null;
            p = next;
        }
        // 开始串联
        if (sH !=null){ // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 确定谁去连接大于区域的头部
        }

        if (mH != null){ // 小于区域和等于区域不是都没有
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }


    // Method7: 复制随机链表
    // 1. 借助哈希表，快速实现复制
    public static Node copyListWithRand(Node head){
        if (head == null)
            return null;
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null){
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null){
            // 开始复制
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    // 2. 不借助哈希表
    public static Node copyListWithRand2(Node head){
        if (head == null)
            return null;
        Node p = head, next = null;
        // 先在每一个节点后面跟一个新创的copy节点
        while(p != null){
            next = p.next;
            Node tmp = new Node(p.val);
            p.next = tmp;
            tmp.next = next;
            p = next;
        }

        // 开始串联, 完成random指针的指向
        p = head;
        Node curCopy = null;
        while (p != null){
            next = p.next.next;
            curCopy = p.next;
            curCopy.random = p.random != null? p.random.next : null;
            p = next;
        }

        // split two list
        Node res = head.next;
        p = head;
        while (p != null){
            next = p.next.next;
            curCopy = p.next;
            p.next = next;
            curCopy.next = next != null ? next.next : null;
            p = next;
        }
        return res;
    }

    //Method8: 删除链表的倒数第N个节点
    public Node removeNthFromEnd(Node head, int n){
        if (head == null){
            return null;
        }

        if (head.next == null && n == 1){
            return null;
        }

        Node prev_head = new Node();
        Node f = head, s = prev_head;

        // f先走n步
        for (int i = 0; i < n; ++i){
            f = f.next;
        }

        while(f != null){
            s = s.next;
            f = f.next;
        }

        Node next = s.next.next;
        s.next.next = null;
        s.next = next;
        return prev_head.next;
    }

    //Method9: 找到链表的第一个入环节点，如果无环，返回null
    public Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null)
            return null;

        Node n1 = head.next;
        Node n2 = head.next.next;

        while(n1 != n2){
            if (n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while(n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
