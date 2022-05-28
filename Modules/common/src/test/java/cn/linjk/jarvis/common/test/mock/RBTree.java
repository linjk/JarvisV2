package cn.linjk.jarvis.common.test.mock;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: RBTree
 * @author: linjk
 * @date: 2022/5/1 下午2:33
 * @description: 模拟红黑树
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    /**
     * 获取当前节点的父节点
     * @return parent node of current
     */
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.getParent();
        }

        return null;
    }

    /**
     * 节点是否为红色
     * @return isRed
     */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.isColor() == RED;
        }
        return false;
    }

    /**
     * 设置节点为红色
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.setColor(RED);
        }
    }

    /**
     * 节点是否为黑色
     * @return isBlack
     */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.isColor() == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为黑色
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.setColor(BLACK);
        }
    }

    /**
     * 中序打印
     */
    private void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key: " + node.getKey() + ", value: " + node.getValue());
            inOrderPrint(node.right);
        }
    }

    /**
     * 左旋
     *         P                        P
     *         |                        |
     *         x                        y
     *        / \       ----->         / \
     *      lx   y                    x  ry
     *          / \                  / \
     *         ly ry               lx   ly
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.getRight();
        // 将x的右子节点指向y的左子节点(ly)，将y的左子节点的父节点更新为x
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        // 当x的父节点不为空时，更新y的父节点为x的父节点
        if (x.parent != null) {
            y.parent = x.parent;

            if (x == x.parent.left) {
                x.parent.left = y;
            }
            else {
                x.parent.right = y;
            }
        }
        else {
            // x为根节点，更新根节点为y
            this.root = y;
            this.root.parent = null;
        }

        x.parent = y;
        y.left = x;
    }

    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        if (y.parent != null) {
            x.parent = y.parent;

            if (y == y.parent.left) {
                y.parent.left = x;
            }
            else {
                y.parent.right = x;
            }
        }
        else {
            this.root = x;
            this.root.parent = null;
        }

        y.parent = x;
        x.right = y;
    }

    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        // 新节点一定是红色
        node.setColor(RED);
        insert(node);
    }

    private void insert(RBNode node) {
        // 查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;

        while (x != null) {
            parent = x;
            // cmp > 0 说明node.key大于x.key，需要到x的右子树查找
            // cmp == 0 说明node.key等于x.key，说明需要进行替换操作
            // cmp < 0 说明node.key小于x.key,需要到x的左子树查找
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            }
            else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            }
            else {
                x = x.left;
            }
        }
        node.parent = parent;
        if (parent != null) {
            //判断node与paren的key谁大
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                // 当前node的key比parent的key大，需把node放入parent的右子节点
                parent.right = node;
            }
            else {
                // 当前node的key比parent的key小，需要把node放入parent的左子节点
                parent.left = node;
            }
        }
        else {
            this.root = node;
        }
        // 红黑树平衡性修正
        //
    }

    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
