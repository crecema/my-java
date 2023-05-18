package com.crecema.my.java.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;

    private final Node head, tail;

    private final Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            moveToTail(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        // 查map
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToTail(node);
        } else {
            if (map.size() == capacity) {
                Node oldNode = delHead();
                map.remove(oldNode.key);
            }
            node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        }
    }

    private static class Node {

        private final int key;

        private int value;

        private Node prev;

        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    // 添加节点
    private void addNode(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    // 删除节点
    private void delNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 移动到尾部
    private void moveToTail(Node node) {
        delNode(node);
        addNode(node);
    }

    // 移除头部
    private Node delHead() {
        Node node = head.next;
        delNode(node);
        return node;
    }

}
