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
    }

    @Test
    void testMinimum() {
    }

    @Test
    void maximum() {
    }

    @Test
    void testMaximum() {
    }

    @Test
    void leftRotate() {
    }

    @Test
    void rightRotate() {
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







