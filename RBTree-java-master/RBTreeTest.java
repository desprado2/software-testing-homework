package rbtree;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RBTreeTest {
	static RBTree<Integer> tree;
    private static final Integer[] a = {15, 1, 6, 3, 7, 5};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void search() {
        tree = new RBTree<>();
        tree.mRoot = new RBTNode<>(6, true,null,null,null);
        RBTNode<Integer> node1 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(1, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(5, false, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(15, true, null, null, null);
        RBTNode<Integer> node5 = new RBTNode<>(7, false, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5;
        node5.parent = node4;

        RBTNode<Integer> node = tree.search(6);
        assertEquals(node, tree.mRoot);
        node = tree.search(5);
        assertEquals(node, node3);
        node = tree.search(114514);
        assertNull(node);
    }

    @Test
    void minimum() {
        tree = new RBTree<>();

        Integer ret = tree.minimum();
        assertNull(ret);

        tree.mRoot = new RBTNode<>(6, true,null,null,null);
        RBTNode<Integer> node1 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(1, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(5, false, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(15, true, null, null, null);
        RBTNode<Integer> node5 = new RBTNode<>(7, false, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5;
        node5.parent = node4;

        ret = tree.minimum();
        assertEquals(ret, 1);
    }


    @Test
    void maximum() {
        tree = new RBTree<>();

        Integer ret = tree.maximum();
        assertNull(ret);

        tree.mRoot = new RBTNode<>(6, true,null,null,null);
        RBTNode<Integer> node1 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(1, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(5, false, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(15, true, null, null, null);
        RBTNode<Integer> node5 = new RBTNode<>(7, false, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5;
        node5.parent = node4;

        ret = tree.maximum();
        assertEquals(ret, 15);
    }


    @Test
    void leftRotate() {
        /*
        构造树形状如下，数字为节点编号
             0
           /   \
          1     4
         / \   / \
        2   3 5   6
         */
        tree = new RBTree<>();
        RBTNode<Integer> node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        RBTNode<Integer> node1 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(1, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(5, false, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(15, true, null, null, null);
        RBTNode<Integer> node5 = new RBTNode<>(7, false, null, null, null);
        RBTNode<Integer> node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                     4
           /   \                 /   \
          1     4      ->       0     6
         / \   / \             / \
        2   3 5   6           1   5
                             / \
                            2   3
         */
        tree.leftRotate(tree.mRoot);
        assertEquals(tree.mRoot, node4);
        assertEquals(tree.mRoot.right, node6);
        assertEquals(tree.mRoot.left, node0);
        assertEquals(node0.right, node5);
        assertEquals(node0.left, node1);

        // 重新构造与上面一样的树
        node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(3, true, null, null, null);
        node2 = new RBTNode<>(1, false, null, null, null);
        node3 = new RBTNode<>(5, false, null, null, null);
        node4 = new RBTNode<>(15, true, null, null, null);
        node5 = new RBTNode<>(7, false, null, null, null);
        node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                     0
           /   \                 /   \
          1     4      ->       3     4
         / \   / \             /     / \
        2   3 5   6           1     5   6
                             /
                            2
         */
        tree.leftRotate(node1);
        assertEquals(tree.mRoot.left, node3);
        assertEquals(node3.left, node1);
        assertEquals(node1.left, node2);

        node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(3, true, null, null, null);
        node2 = new RBTNode<>(1, false, null, null, null);
        node3 = new RBTNode<>(5, false, null, null, null);
        node4 = new RBTNode<>(15, true, null, null, null);
        node5 = new RBTNode<>(7, false, null, null, null);
        node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                     0
           /   \                 /   \
          1     4      ->       1     6
         / \   / \             / \   /
        2   3 5   6           2   3 4
                                   /
                                  5
         */
        tree.leftRotate(node4);
        assertEquals(tree.mRoot.right, node6);
        assertEquals(node6.left, node4);
        assertEquals(node4.left, node5);
    }

    @Test
    void rightRotate() {
        /*
        构造树形状如下，数字为节点编号
             0
           /   \
          1     4
         / \   / \
        2   3 5   6
         */
        tree = new RBTree<>();
        RBTNode<Integer> node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        RBTNode<Integer> node1 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(1, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(5, false, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(15, true, null, null, null);
        RBTNode<Integer> node5 = new RBTNode<>(7, false, null, null, null);
        RBTNode<Integer> node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                     1
           /   \                 /   \
          1     4      ->       2     0
         / \   / \                   / \
        2   3 5   6                 3   4
                                       / \
                                      5   6
         */
        tree.rightRotate(tree.mRoot);
        assertEquals(tree.mRoot, node1);
        assertEquals(tree.mRoot.right, node0);
        assertEquals(tree.mRoot.left, node2);
        assertEquals(node0.right, node4);
        assertEquals(node0.left, node3);

        // 重新构造与上面一样的树
        node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(3, true, null, null, null);
        node2 = new RBTNode<>(1, false, null, null, null);
        node3 = new RBTNode<>(5, false, null, null, null);
        node4 = new RBTNode<>(15, true, null, null, null);
        node5 = new RBTNode<>(7, false, null, null, null);
        node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                      0
           /   \                 /     \
          1     4      ->       2       4
         / \   / \               \     / \
        2   3 5   6               1   5   6
                                   \
                                    3
         */
        tree.rightRotate(node1);
        assertEquals(tree.mRoot.left, node2);
        assertEquals(node2.right, node1);
        assertEquals(node1.right, node3);

        node0 = new RBTNode<>(6, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(3, true, null, null, null);
        node2 = new RBTNode<>(1, false, null, null, null);
        node3 = new RBTNode<>(5, false, null, null, null);
        node4 = new RBTNode<>(15, true, null, null, null);
        node5 = new RBTNode<>(7, false, null, null, null);
        node6 = new RBTNode<>(17, false, null, null, null);
        tree.mRoot.left = node1; tree.mRoot.right = node4;
        node1.parent = tree.mRoot; node1.left = node2; node1.right = node3;
        node2.parent = node1;
        node3.parent = node1;
        node4.parent = tree.mRoot; node4.left = node5; node4.right = node6;
        node5.parent = node4;
        node6.parent = node4;

        /*
             0                      0
           /   \                 /     \
          1     4      ->       1       5
         / \   / \             / \       \
        2   3 5   6           2   3       4
                                           \
                                            6
         */
        tree.rightRotate(node4);
        assertEquals(tree.mRoot.right, node5);
        assertEquals(node5.right, node4);
        assertEquals(node4.right, node6);
    }

    @Test
    void insertFixUp() {
    }

    @Test
    void insert() {
    }

    @Test
    void testInsert() {
    }

    @Test
    void removeFixUp() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void destroy() {
    }

    @Test
    void clear() {
    }

    @Test
    void print() {
    }

    @Test
    void testPrint() {
    }
}







