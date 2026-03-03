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
    // 回文链表
    // 方法一: 链表反转
    public boolean isPalindrome(ListNode head) {
        // Case 1: 0个节点
        if (head == null){
            return false;
        }

        // Case 2: 1个节点
        if (head != null && head.next == null)
            return true;

        //Case 3: 2个及以上的节点
        // 锁定中间位置
        ListNode r = head, l = head;

        while (r != null) {
            r = r.next;
            if (r != null)
                r = r.next;
            l = l.next;
        }

        // 1 2 2 1 -> 1 2 1 2
        // 1 2 3 2 1 -> 1 2 3 1 2 中间位置锁定之后，其到最后一个节点链表反转然后即可判断
        r = l;
        r = reverseList(r);

        l = head;
        boolean res = true;

        while(r != null){
            if (r.val != l.val){
                res = false;
                break;
            }
            r = r.next;
            l = l.next;
        }
        return res;
    }

    public ListNode reverseList(ListNode head){
        ListNode last = null, cur = head;
        while(cur != null){
            ListNode tmp = cur.next;
            cur.next = last;
            last = cur;
            cur = tmp;
        }

        return last;
    }

    // 方法2: 将链表值复制到有序collection中，然后前后双指针
    public boolean isPalindrome1(ListNode head){
        // Case 1: 0个节点
        if (head == null){
            return false;
        }

        // Case 2: 1个节点
        if (head != null && head.next == null)
            return true;

        List<Integer> vals = new ArrayList<>();

        // 链表值复制到数组中
        ListNode curNode = head;
        while (curNode != null){
            vals.add(curNode.val);
            curNode = curNode.next;
        }

        // 双指针
        int l = 0, r = vals.size() - 1;
        while (l < r){
            if (!vals.get(l).equals(vals.get(r))){
                return false;
            }

            ++l;
            --r;
        }
        return true;
    }

}

