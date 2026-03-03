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
    // 反转链表
    public ListNode reverseList(ListNode head) {
        // Case 1: no node
        if (head == null){
            return null;
        }

        // Case 2: one node
        if (head != null && head.next == null)
            return head;

        // Case 3: >=2 node
        // 1 -> 2 -> 3 -> 4
        // 1 <- 2 3 -> 4
        ListNode last = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        return last;
    }
}
