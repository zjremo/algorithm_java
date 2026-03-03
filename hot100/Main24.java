/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 两两交换链表中的节点
    // 方法1
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;

        // 长度为1
        if (head.next == null)
            return head;

        // 长度≥2
        ListNode prev_head = new ListNode();
        prev_head.next = head;
        ListNode prev = prev_head;
        while (prev.next != null && prev.next.next != null){
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;
            prev.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            prev = node1;
        }
        return prev_head.next;
    }

    // 方法2
    public ListNode swapPairs2(ListNode head) {
        if (head == null)
            return null;

        // 长度为1
        if (head.next == null)
            return head;

        // 长度≥2
        ListNode f = head.next, s = head, prev = null, prev_head = null;

        while (f != null) {
            ListNode tmp = f.next;
            f.next = s;
            s.next = null;

            if (prev_head == null) {
                prev_head = f;
            } else{
                prev.next = f;
            }

            // update pointer
            prev = s;
            f = tmp == null ? null : tmp.next;
            s = tmp;
        }

        if (s != null)
            prev.next = s;
        return prev_head;
    }


}
