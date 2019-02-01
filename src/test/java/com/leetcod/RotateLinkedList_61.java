package com.leetcod;

import org.junit.Test;

/**
 * @author gavin
 * @date 2019/2/1 8:53
 */
public class RotateLinkedList_61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        ListNode p = head, pre = null;
        int headIndex = size - k;
        for (int i = 0; i < headIndex; i++) {
            pre = p;
            p = p.next;
        }
        ListNode newHead = p;
        pre.next = null;
        while (p != null) {
            p = p.next;
        }
        p.next = head;
        return newHead;
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(0);
        n1.next = new ListNode(1);
        n1.next.next = new ListNode(2);
        new RotateLinkedList_61().rotateRight(n1, 1);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
