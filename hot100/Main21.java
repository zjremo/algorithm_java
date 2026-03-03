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
    // 合并两个链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;

        if (list1 == null && list2 !=null){
            return list2;
        }

        if (list1 != null &&list2 == null){
            return list1;
        }

        // 定义两个指针分别遍历
        ListNode i1 = list1, i2 = list2;

        // 定义一个假的头部
        ListNode prev_head = new ListNode(); 
        ListNode prev = prev_head;

        while (i1 != null && i2 != null) {
            ListNode larger = i1.val > i2.val ? i1 : i2;
            ListNode op = larger == i1 ? i2 : i1;

            i1 = larger;
            i2 = op.next;
            prev.next = op;
            prev = prev.next;
        }

        if (i1 != null){
            prev.next = i1;
        }

        if (i2 != null){
            prev.next = i2;
        }
        return prev_head.next;
    }
}

