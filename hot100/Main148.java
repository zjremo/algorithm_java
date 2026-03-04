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
    // 排序链表 
    // 方法一: 归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null )
            return head;

        if (head.next == tail){
            head.next = null;
            return head;
        }

        // 确定中间位置
        ListNode s = head, f = head;
        while (f != tail) {
            s = s.next;
            f = f.next;
            if (f != tail)
                f = f.next;
        }

        ListNode mid = s;
        // 归并排序逻辑
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode mergeList = merge(list1, list2);
        return mergeList;
    }

    public ListNode merge(ListNode list1, ListNode list2){
        ListNode p1 = list1, p2 = list2, p_head = new ListNode();
        ListNode p = p_head;

        while (p1 != null && p2 != null){
            if (p1.val <= p2.val){
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        // 清除未完成的动作
        if (p1 != null){
            p.next = p1;
        }

        if (p2 != null){
            p.next = p2;
        }

        return p_head.next;
    }
}
