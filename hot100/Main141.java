/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    // 有无环
    public boolean hasCycle(ListNode head) {
        if (head == null || head != null && head.next == null)
            return false;


        ListNode f = head, s = head;
        boolean res = false;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
            if (f != s){
                res = true;
                break;
            }
        }

        return res;
    }
}
