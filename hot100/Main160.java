/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    // 方法一: 哈希表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        Set<ListNode> visited = new HashSet<>();

        ListNode tmp = headA;
        while (tmp != null){
            visited.add(tmp);
            tmp = tmp.next;
        }
        
        tmp = headB;
        while (tmp != null){
            if (visited.contains(tmp))
                return tmp;
            tmp = tmp.next;
        }
        return null;
        
    }

    // 方法二: 计算两者的长度差，长链表先走长度差步
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tmp = headA;
        int lenA = 0, lenB = 0;
        while (tmp != null){
            ++lenA;
            tmp = tmp.next;
        }
        
        tmp = headB;
        while (tmp != null){
            ++lenB;
            tmp = tmp.next;
        }

        ListNode longer = lenA > lenB? headA : headB;
        tmp = longer == headA ? headB : headA;

        int distance = Math.abs(lenA - lenB);

        // longer先走
        for (int i = 0; i < distance && longer != null; ++i){
            longer = longer.next;
        }

        if (longer == null)
            return null;

        while(longer != null && tmp != null){
            if (longer == tmp)
                return tmp;
            longer = longer.next;
            tmp = tmp.next;
        }
        return null;

    }
}
