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
    public int[] getDigit(int num){
        // [mod, res]
        return new int[]{num % 10, num / 10};
    }
    // 两表之和
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode prev_head = new ListNode();
        ListNode prev = prev_head;
        // 记录进位
        int c = 0;

        ListNode i1 = l1, i2 = l2;

        while (i1 != null && i2 != null) {
            int sum = i1.val + i2.val + c;

            int[] res_array = getDigit(sum);
            c = res_array[1];

            prev.next = new ListNode(res_array[0]);
            i1 = i1.next;
            i2 = i2.next;
            prev = prev.next;
        }

        while (i1 != null){
            int sum = i1.val + c;
            
            int[] res_array = getDigit(sum);
            c = res_array[1];

            prev.next = new ListNode(res_array[0]);
            i1 = i1.next;
            prev = prev.next;
        }

        
        while (i2 != null){
            int sum = i2.val + c;
            
            int[] res_array = getDigit(sum);
            c = res_array[1];

            prev.next = new ListNode(res_array[0]);
            i2 = i2.next;
            prev = prev.next;
        }

        // 如果进位的没完
        if (c != 0)
            prev.next = new ListNode(1);
        
        return prev_head.next;
        
    }
}
