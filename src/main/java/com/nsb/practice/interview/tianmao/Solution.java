package com.nsb.practice.interview.tianmao;

/**
 * 评测题目: 给定一个单链表，如何检测链表中存在环并确定环的长度，程序实现
A->B->C->D->E->C
 * @author Dorae
 *
 */
public class Solution {

    public Node existCir(Node head) {
        if (head == null) {
            return null;
        }
        Node p1 = head;
        Node p2 = head.next;
        while (p1 != null && p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        return (p1 == p2 && p1 != null) ? p1 : null;
    }
    
    public int cirLength(Node cirNode) {
        if (cirNode == null) {
            return 0;
        }
        Node tmp = cirNode.next;
        int result = 1;
        while (tmp != cirNode) {
            result++;
            tmp = tmp.next;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node<String> head = init();
        Node cirNode = solution.existCir(head);
        System.out.println(cirNode != null);
        System.err.println(solution.cirLength(cirNode));
    }
    
    /**
     * A->B->C->D->E->C
     * @return
     */
    private static Node<String> init() {
        Node<String> a = new Node("A");
        Node<String> b = new Node("B");
        Node<String> c = new Node("C");
        Node<String> d = new Node("D");
        Node<String> e = new Node("E");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;
        return a;
    }
}

class Node<E> {
    E e;
    Node next;
    
    public Node(E e, Node next) {
        super();
        this.e = e;
        this.next = next;
    }
    
    public Node(E e) {
        super();
        this.e = e;
    }

    public Node() {
        super();
    }
}