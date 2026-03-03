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
    // 删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 特殊情况判断
        if (head == null)
            return null;

        if (head.next == null){
            // 此时长度必为1
            return null;
        }

        // 快指针先走n步，慢指针后面走 
        ListNode f = head, s = head;
        int cnt = 0;
        while (f != null && cnt < n) {
            f = f.next;
            ++cnt;
        }

        if (f == null){
            // 此时删除第一个元素
            return s.next;
        }

        // 锁定倒数第n + 1个节点
        while (f.next != null){
            f = f.next;
            s = s.next;
        }

        // begin deletion
        ListNode tail = s.next.next;
        s.next.next = null;
        s.next = tail;
        return head;
    }
}
