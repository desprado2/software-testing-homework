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
        构造树形状如下，数字为节点编号(不是节点的key值！！！)
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
        // 以下数字均表示节点的key值
        tree = new RBTree<>();
        RBTNode<Integer> node1 = new RBTNode<>(1,true,null,null,null);
        tree.mRoot=node1;
        RBTNode<Integer> node2 = new RBTNode<>(2,false,null,null,null);
        node2.parent=tree.mRoot; tree.mRoot.right=node2;
        RBTNode<Integer> node3 = new RBTNode<>(3,false,null,null,null);
        node3.parent=node2; node2.right=node3;


        /*      1                                     2
                 \                                   / \
                  2              ->                 1   3
                   \
                    3
        */
        //test1  自身红，父亲红,无叔叔，爷爷黑
        tree.insertFixUp(node3);
        assertEquals(tree.mRoot.key,2);
        assertEquals(tree.mRoot.color,true);

        RBTNode<Integer> node4 = new RBTNode<>(4,false,null,null,null);
        node4.parent=node3; node3.right=node4;



        /*       2B                            2B
                /  \                          /  \
               1R   3R         ->           1B   3B
                     \                            \
                      4R                          4R
        */
        //test2  自身红，父亲红，爷爷黑，叔叔红
        tree.insertFixUp(node4);
        assertEquals(node1.color,true);
        assertEquals(node3.color,true);

        RBTNode<Integer> node5 = new RBTNode<>(5,false,null,null,null);
        node5.parent=node4; node4.right=node5;
        tree.insertFixUp(node5);
        RBTNode<Integer> node6 = new RBTNode<>(6,false,null,null,null);
        node6.parent=node5;node5.right=node6;
        tree.insertFixUp(node6);
        RBTNode<Integer> node7 = new RBTNode<>(7,false,null,null,null);
        node7.parent=node6;node6.right=node7;



        /*       2B                                          2B
                /  \                                        /  \
               1R   4R                                     1B   4R
                   /  \                                        /  \
                 3B   5B                                      3B   6B
                        \                                         /  \
                        6R                                       5R   7R
                          \
                          7R
        */
        //test3 自身红，父亲红，爷爷黑，曾爷爷红,无叔叔
        tree.insertFixUp(node7);
        assertEquals(node6.color,true);
        assertEquals(node5.color,false);

        RBTNode<Integer> node8 = new RBTNode<>(8,false,null,null,null);
        node8.parent=node7;node7.right=node8;




        /*       2B                                          4B
                /  \                                       /    \
              1R    4R                                   2R      6R
                   /  \                                 /  \    /   \
                 3B   6B                              1B   3B  5B    7B
                     /  \                                              \
                    5R   7R                                            8R
                          \
                          8R
        */
        //test4  自身红，父亲红，爷爷黑，曾爷爷红, 叔叔红
        tree.insertFixUp(node8);
        assertEquals(node7.color,true);
        assertEquals(node5.color,true);
        assertEquals(node6.color,false);
        assertEquals(tree.mRoot.key,4);


        RBTNode<Integer> node9 = new RBTNode<>(9,false,null,null,null);
        node9.parent=node8;node8.right=node9;
        tree.insertFixUp(node9);
        RBTNode<Integer> node10 = new RBTNode<>(10,false,null,null,null);
        node10.parent=node9;node9.right=node10;

        //test5 需要改变的节点的链较长，连环改变
        /*
         *                      4B                                                                 4B
         *                    /     \                                                            /    \
         *                  2R       6R                                                        2R      6B
         *                 /  \      / \                                                      /  \     /  \
         *                1B  3B   5B  8B                                                    1B   3B  5B  8R
         *                            /  \                                                               /   \
         *                           7R   9R                                                            7B   9B
         *                                 \                                                                   \
         *                                  10R                                                                 10R
         *
         * */

        tree.insertFixUp(node10);
        assertEquals(node6.color,true);
        assertEquals(node2.color,true);
    }

    @Test
    void privateInsert() {
        tree = new RBTree<>();
        RBTNode<Integer> node1=new RBTNode<>(1,true,null,null,null);
        RBTNode<Integer> node2=new RBTNode<>(2,false,null,null,null);
        RBTNode<Integer> node3=new RBTNode<>(3,false,null,null,null);
        RBTNode<Integer> node4=new RBTNode<>(4,false,null,null,null);
        RBTNode<Integer> node5=new RBTNode<>(5,false,null,null,null);
        RBTNode<Integer> node6=new RBTNode<>(6,false,null,null,null);
        RBTNode<Integer> node7=new RBTNode<>(7,false,null,null,null);
        RBTNode<Integer> node8=new RBTNode<>(8,false,null,null,null);
        RBTNode<Integer> node9=new RBTNode<>(9,false,null,null,null);
        RBTNode<Integer> node10=new RBTNode<>(10,false,null,null,null);


        tree.mRoot=node1;
        tree.insert(node2);
        tree.insert(node3);

        assertEquals(tree.mRoot.key,2);
        assertEquals(node2.color,true);
        assertEquals(node3.color,false);

        tree.insert(node5);

        assertEquals(node3.color,true);
        assertEquals(node1.color,true);

        tree.insert(node4);
        assertEquals(node4.color,true);
        assertEquals(node3.color,false);

        tree.insert(node6);



        /*       2B                                          2B
                /  \                                        /  \
               1R   4R                                     1B   4R
                   /  \                                        /  \
                 3B   5B                                      3B   6B
                        \                                         /  \
                        6R                                       5R   7R
                          \
                          7R
        */
        tree.insert(node7);
        assertEquals(node6.color,true);
        assertEquals(node5.color,false);




        /*       2B                                          4B
                /  \                                       /    \
              1R    4R                                   2R      6R
                   /  \                                 /  \    /   \
                 3B   6B                              1B   3B  5B    7B
                     /  \                                              \
                    5R   7R                                            8R
                          \
                          8R
        */
        tree.insert(node8);
        assertEquals(tree.mRoot.key,4);
        assertEquals(node2.color,false);
        assertEquals(node6.color,false);
        assertEquals(node5.color,true);
        assertEquals(node7.color,true);


        /*
         *                      4B                                                                 4B
         *                    /     \                                                            /    \
         *                  2R       6R                                                        2R      6B
         *                 /  \      / \                         ->                           /  \     /  \
         *                1B  3B   5B  8B                                                    1B   3B  5B  8R
         *                            /  \                                                               /   \
         *                           7R   9R                                                            7B   9B
         *                                 \                                                                   \
         *                                  10R                                                                 10R
         *
         * */
        tree.insert(node9);
        tree.insert(node10);
        assertEquals(node6.color,true);
        assertEquals(node7.color,true);
        assertEquals(node9.color,true);

        RBTNode<Integer> node11=new RBTNode<>(-1,false,null,null,null);
        RBTNode<Integer> node12=new RBTNode<>(-2,false,null,null,null);


        /*
         *                      4B                                                                 4B
         *                    /     \                                                            /    \
         *                  2R       6R                                                        2R      6B
         *                 /  \      / \                         ->                           /  \     /  \
         *                1B  3B   5B  8B                                                   -1B   3B  5B  8R
         *               /            /  \                                                  /  \          /   \
         *             -1R           7R   9R                                              -2R   1R       7B   9B
         *             /                    \                                                                   \
         *           -2R                    10R                                                                 10R
         *
         * */
        tree.insert(node11);
        tree.insert(node12);
        assertEquals(node11.color,true);
        assertEquals(node1.color,false);
    }

    @Test
    void publicInsert(){
        tree = new RBTree<>();

        tree.insert(1);
        RBTNode prevRoot = tree.mRoot;
        assertEquals(tree.mRoot.key, 1);
        assertNull(tree.mRoot.left); assertNull(tree.mRoot.right);

        tree.insert(1); //重复插入
        assertEquals(prevRoot, tree.mRoot);
        assertNull(tree.mRoot.left); assertNull(tree.mRoot.right);

        tree.insert(2);
        assertEquals(tree.mRoot.right.key, 2);
    }

    @Test
    void removeFixUp() {
        /*
        构造树形状如下，数字为节点编号
        case1:
             0                  2
           /   \              /   \
          1     2       ->   0     4
               / \          / \
              3   4        1   3
         */
        tree = new RBTree<>();

        RBTNode<Integer> node0 = new RBTNode<>(2, true,null,null,null);
        tree.mRoot = node0;
        RBTNode<Integer> node1 = new RBTNode<>(1, true, null, null, null);
        RBTNode<Integer> node2 = new RBTNode<>(4, false, null, null, null);
        RBTNode<Integer> node3 = new RBTNode<>(3, true, null, null, null);
        RBTNode<Integer> node4 = new RBTNode<>(5, true, null, null, null);

        node0.left = node1; node0.right = node2;
        node1.parent = tree.mRoot;
        node2.parent = tree.mRoot;
        node2.left = node3; node2.right = node4;
        node3.parent = node2;
        node4.parent = node2;

        tree.removeFixUp(node1, node0);

        assertEquals(tree.mRoot, node2);
        assertEquals(tree.mRoot.color, true);
        assertEquals(node2.left, node0);
        assertEquals(node0.color, true);
        assertEquals(node2.right, node4);
        assertEquals(node4.color, true);
        assertEquals(node0.left, node1);
        assertEquals(node1.color, true);
        assertEquals(node0.right, node3);
        assertEquals(node3.color, false);

        /*
             0                  1
           /   \              /   \
          1     2       ->   3     0
         / \                      / \
        3   4                    4   2
         */
        tree = new RBTree<>();

        node0 = new RBTNode<>(4, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(2, false, null, null, null);
        node2 = new RBTNode<>(5, true, null, null, null);
        node3 = new RBTNode<>(1, true, null, null, null);
        node4 = new RBTNode<>(3, true, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node2;
        node1.parent = tree.mRoot; node1.left = node3; node1.right = node4;
        node2.parent = node0;
        node3.parent = node1;
        node4.parent = node1;

        tree.removeFixUp(node2, node0);

        assertEquals(tree.mRoot, node1);
        assertEquals(tree.mRoot.color, true);
        assertEquals(node1.left, node3);
        assertEquals(node3.color, true);
        assertEquals(node1.right, node0);
        assertEquals(node0.color, true);
        assertEquals(node0.left, node4);
        assertEquals(node4.color, false);
        assertEquals(node0.right, node2);
        assertEquals(node2.color, true);

        /*
             0                  3
           /   \              /   \
          1     2       ->   0     2
               /            /
              3            1
         */
        tree = new RBTree<>();

        node0 = new RBTNode<>(2, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(1, true, null, null, null);
        node2 = new RBTNode<>(4, true, null, null, null);
        node3 = new RBTNode<>(3, false, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node2;
        node1.parent = tree.mRoot;
        node2.parent = node0; node2.left = node3;
        node3.parent = node2;

        tree.removeFixUp(node1, node0);

        assertEquals(tree.mRoot, node3);
        assertEquals(tree.mRoot.color, true);
        assertEquals(node3.left, node0);
        assertEquals(node0.color, true);
        assertEquals(node3.right, node2);
        assertEquals(node2.color, true);
        assertEquals(node0.left, node1);
        assertEquals(node1.color, true);

        /*
             0                  3
           /   \              /   \
          1     2       ->   1     0
           \                        \
            3                        2
         */
        tree = new RBTree<>();

        node0 = new RBTNode<>(3, true,null,null,null);
        tree.mRoot = node0;
        node1 = new RBTNode<>(1, true, null, null, null);
        node2 = new RBTNode<>(4, true, null, null, null);
        node3 = new RBTNode<>(2, false, null, null, null);

        tree.mRoot.left = node1; tree.mRoot.right = node2;
        node1.parent = tree.mRoot; node1.right = node3;
        node2.parent = node0;
        node3.parent = node1;

        tree.removeFixUp(node2, node0);

        assertEquals(tree.mRoot, node3);
        assertEquals(tree.mRoot.color, true);
        assertEquals(node3.left, node1);
        assertEquals(node1.color, true);
        assertEquals(node3.right, node0);
        assertEquals(node0.color, true);
        assertEquals(node0.right, node2);
        assertEquals(node2.color, true);
    }

    @Test
    void privteRemove() {
        /*
        构造树形状如下，数字为节点编号
             0
           /   \
          1     4
         / \   / \
        2   3 5   6
         */
        tree = new RBTree<>();

        // 重新构造与上面一样的树
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
             0                     5
           /   \                 /   \
          1     4      ->       1     4
         / \   / \             / \     \
        2   3 5   6           2   3     6

         */
        tree.remove(node0);
        assertEquals(tree.mRoot, node5);
        assertEquals(tree.mRoot.color, true);
        assertEquals(node5.right, node4);
        assertEquals(node4.color, true);
        assertEquals(node4.right, node6);
        assertEquals(node6.color, false);
        /*
             5                     5
           /   \                 /   \
          1     4      ->       3     4
         / \     \             /       \
        2   3     6           2         6


         */
        tree.remove(node1);
        assertEquals(tree.mRoot.left, node3);
        assertEquals(node3.color, true);
        assertEquals(node3.left, node2);
        assertEquals(node2.color, false);
        /*
             5                     4
           /   \                 /   \
          1     4      ->       3     6
         / \     \             /
        2   3     6           2


         */

        tree.remove(node5);
        assertEquals(tree.mRoot, node4);
        assertEquals(node4.color, true);
        assertEquals(node4.right, node6);
        assertEquals(node6.color, true);
        /*
             4                     4
           /   \                 /   \
          3     6      ->       2     6
         /
        2


         */

        tree.remove(node3);
        assertEquals(tree.mRoot.left, node2);
        assertEquals(node2.color, true);
        /*
             4                     4
           /   \                 /
          2     6      ->       2


         */

        tree.remove(node6);
        assertEquals(tree.mRoot.right, null);
        /*
             4                     2
           /
          2            ->


         */

        tree.remove(node4);
        assertEquals(tree.mRoot, node2);
        assertEquals(tree.mRoot.left, null);
        assertEquals(tree.mRoot.right, null);
        assertEquals(node2.color, true);

        /*
        构造树形状如下，数字为节点编号
             0
           /   \
          1     4
         / \   / \
        2   3 5   6
         */
        tree = new RBTree<>();

        // 重新构造与上面一样的树
        RBTNode<Integer> node7 = new RBTNode<>(3, true,null,null,null);
        tree.mRoot = node7;
        RBTNode<Integer> node8 = new RBTNode<>(1, true, null, null, null);
        RBTNode<Integer> node9 = new RBTNode<>(15, false, null, null, null);
        RBTNode<Integer> node10 = new RBTNode<>(5, true, null, null, null);
        RBTNode<Integer> node11 = new RBTNode<>(6, false, null, null, null);
        RBTNode<Integer> node12 = new RBTNode<>(20, false, null, null, null);


        tree.mRoot.left = node8; tree.mRoot.right = node9;
        node8.parent = tree.mRoot; node9.left = node10; node9.right = node12; node10.right = node11;
        node9.parent = tree.mRoot;
        node10.parent = node9;
        node11.parent = node10;
        node12.parent = node9;

        /*
                 7                         10
               /    \                     /   \
             8        9                 8       9
                     /  \                      / \
                  10      12     ->          11   12
                    \
                     11
         */
        tree.remove(node7);
        assertEquals(tree.mRoot, node10);
        assertEquals(node10.color, true);
        assertEquals(tree.mRoot.left, node8);
        assertEquals(node8.color, true);
        assertEquals(tree.mRoot.right, node9);
        assertEquals(node9.color, false);
        assertEquals(node9.left, node11);
        assertEquals(node11.color, true);
        /*
                 10                        10
               /    \                     /   \
             8        9                 8      12
                     / \                      /
                   11   12        ->         11


         */


        tree.remove(node9);
        assertEquals(tree.mRoot.right, node12);
        assertEquals(node12.color, false);
        assertEquals(node12.left, node11);
        assertEquals(node11.color, true);
        assertEquals(node12.right, null);
    }

    @Test
    void publicRemove(){
        tree = new RBTree<>();
        RBTNode<Integer> node = new RBTNode<>(1, true, null, null, null);
        tree.mRoot = node;

        tree.remove(2); // 删除不存在的值
        assertEquals(tree.mRoot, node);
        tree.remove(1);
        assertEquals(tree.mRoot, null);
    }
}







