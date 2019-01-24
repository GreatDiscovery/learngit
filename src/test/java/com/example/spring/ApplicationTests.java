package com.example.spring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {
    int max = 0;

    public int maxDepth(Node root) {
        dfs(root);
        return max;
    }

    public int dfs(Node root) {
        if (root == null) return 0;
        if (root.children != null) {
            for (Node node : root.children) {
                int temp = dfs(node) + 1;
                if (temp > max) max = temp;
            }
        }
        return max + 1;
    }

    @Test
    public void test() {
        Node node1 = new Node(5, null);
        Node node2 = new Node(6, null);
        List<Node> list1 = new ArrayList<>();
        list1.add(node1);
        list1.add(node2);
        Node node3 = new Node(3, list1);
        Node node4 = new Node(2, null);
        Node node5 = new Node(4, null);
        List<Node> list2 = new ArrayList<>();
        list2.add(node3);
        list2.add(node4);
        list2.add(node5);
        Node root = new Node(1, list2);
        System.out.println(maxDepth(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class TestWait {
    public static void main(String[] args) {
        String str = "hello world";
        synchronized (str) {
            try {
                str.wait();
                System.out.println("完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
