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
    // 找出环的起始node
    public ListNode detectCycle(ListNode head) {
        if (head == null || head != null && head.next == null)
            return null;

        if (head != null && head.next == head)
            return head;

        ListNode f = head, s = head;

        // 制造第一次相遇，并判断是否有环
        do{
            if (f.next == null || f.next.next == null)
                return null; // 此时无环
            f = f.next.next;
            s = s.next;
        } while(f != s);

        // 通过计数和第二次通过此点来获取环的长度
        int step = 0;
        ListNode tmp = f;
        do{
            f = f.next;
            ++step;
        } while(f != tmp);

        int cycle_len = step;
        // 快指针先跑环的长度
        s = head;
        f = head;
        for (int i = 0; i < cycle_len; ++i){
            f = f.next;
        }

        while (f != s){
            f = f.next;
            s = s.next;
        }

        return f;
    }
}

