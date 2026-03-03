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
}
