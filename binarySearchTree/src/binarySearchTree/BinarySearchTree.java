package binarySearchTree;

/**
 *
 * @author xieziwei99
 * 2019-05-17
 */
public class BinarySearchTree {

    private MyTreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * 查找值为key的节点
     * @param key 节点值
     * @return 若成功则返回节点，失败返回null
     */
    public MyTreeNode search(int key) {
        MyTreeNode current = root;
        while (current != null && key != current.value) {
            if (key < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /**
     * 将参数key作为新节点的值插入二叉搜索树中
     * 若key与树中某个节点值相等，则作为大于的节点值插入
     * @param key 节点值
     */
    public void insert(int key) {
        MyTreeNode newNode = new MyTreeNode(key);
        MyTreeNode current = root;
        MyTreeNode parent;
        if (current == null) {
            root = newNode;
            return;
        }
        while (true) {
            parent = current;
            if (key < current.value) {
                current = current.left;
                if (null == current) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (null == current) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    /**
     * 根据key值删除节点，并保持二叉搜索树的结构
     * @param key 节点值
     * @return 如果树中没有值为key的节点，则返回null，有就删除节点，并返回该节点
     */
    public MyTreeNode delete(int key) {
        MyTreeNode current = root;
        MyTreeNode parent = root;
        boolean isLeftChild = false;
        // 找到要删除节点current，且用isLeftChild判断该节点是否在parent节点的左子树上
        while (key != current.value) {
            parent = current;
            if (key < current.value) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (null == current) {
                return current;
            }
        }

        // 左右节点均为空
        if (null == current.left && null == current.right) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        // 右节点为空
        } else if (null == current.right) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        // 左节点为空
        } else if (null == current.left) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        // 左右节点均不空
        } else {
            MyTreeNode successor = getDeleteSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }

        return current;
    }

    /**
     * 找到要删除节点的后继结点，让该节点成为删除节点右子树的根节点（自然，此节点的左子树为空）
     * @param deleteNode 要删除的节点
     * @return 后继结点
     */
    private MyTreeNode getDeleteSuccessor(MyTreeNode deleteNode) {
        MyTreeNode successor = null;
        MyTreeNode successorParent = null;
        MyTreeNode current = deleteNode.right;
        // 让successor成为deleteNode的右子树的最左节点
        while (null != current) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        // 让后继结点的父节点的左手接管后继结点的右手
        // 然后后继结点的右手就可以接管deleteNode的整个右子树
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }

    /**
     * 以node为根节点按中序遍历的顺序打印树
     * @param node 要打印树的根节点
     */
    public void printTree(MyTreeNode node) {
        if (null != node) {
            printTree(node.left);
            System.out.print(node.value + " -> ");
            printTree(node.right);
        }
    }

    /**
     * 按中序遍历的顺序打印树
     */
    public void printTree() {
        printTree(root);
    }

    /**
     * 获得以node为根节点的子树的最大深度
     * @param node 节点
     * @return int
     */
    public int getMaxDepth(MyTreeNode node) {
        if (null == node) {
            return 0;
        }
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 返回树的最大深度
     * @return int
     */
    public int getMaxDepth() {
        return getMaxDepth(root);
    }

    /**
     * 返回树的最小深度
     * @return int
     */
    public int getMinDepth() {
        return getMinDepth(root);
    }

    /**
     * 获得以node为根节点的子树的最小深度
     * @param node 节点
     * @return int
     */
    public int getMinDepth(MyTreeNode node) {
        if (null == node) {
            return 0;
        }
        return getMin(node);
    }

    private int getMin(MyTreeNode node) {
        if (null == node) {
            return Integer.MAX_VALUE;
        }
        if (null == node.left && null == node.right) {
            return 1;
        }
        return Math.min(getMin(node.left), getMin(node.right)) + 1;
    }

    /**
     * 判断该树是否是平衡树
     * @return boolean
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断以node为根节点的子树是否是平衡树
     * @param node 节点
     * @return boolean
     */
    public boolean isBalanced(MyTreeNode node) {
        return -1 != getMaxDepth2(node);
    }

    /**
     * @param node 节点
     * @return int；若以node为节点的子树是平衡树，则返回树的深度；若不是则返回-1
     */
    private int getMaxDepth2(MyTreeNode node) {
        if (null == node) {
            return 0;
        }
        int left = getMaxDepth2(node.left);
        int right = getMaxDepth2(node.right);
        if (-1 == left || -1 == right || Math.abs(left-right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);tree.insert(8);tree.insert(2);tree.insert(4);tree.insert(6);
        tree.insert(1);tree.insert(10);tree.insert(9);tree.insert(20);tree.insert(25);
        tree.insert(7);
        tree.printTree();
        System.out.println();
        System.out.println("是否是平衡树：" + tree.isBalanced());

        System.out.println("是否存在节点值为10的节点 => " + (tree.search(10) != null ? "存在，值为：" + tree.search(10).value : "不存在"));
        System.out.println("是否存在节点值为11的节点 => " + (tree.search(11) != null ? "存在，值为：" + tree.search(11).value : "不存在"));

        if (tree.delete(100) == null) {
            System.out.println("删除失败，没有该节点值");
        }
        tree.delete(8);
        tree.printTree();
        System.out.println();

        System.out.println("最大深度为：" + tree.getMaxDepth());
        System.out.println("最小深度为：" + tree.getMinDepth());
        System.out.println("是否是平衡树：" + tree.isBalanced());
    }
}

class MyTreeNode {
    /**
     * 节点值
     */
    int value;
    /**
     * 左子结点
     */
    MyTreeNode left;
    /**
     * 右子节点
     */
    MyTreeNode right;

    public MyTreeNode() {
        this(0);
    }

    public MyTreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
