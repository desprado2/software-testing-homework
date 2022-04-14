package rbtree;


public class RBTreeTest {
	static RBTree<Integer> tree;
    private static final int a[] = {10, 20, 30, 40, 50, 60, 70, 80, 90,100,110,120};
    private static final boolean mDebugInsert = false;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = false;    // "删除"动作的检测开关(false，关闭；true，打开)

    public static void main(String[] args) {
        int i, ilen = a.length;
        tree=new RBTree<Integer>();

        System.out.printf("== 原始数据: ");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // 设置mDebugInsert=true,测试"添加函数"
            if (mDebugInsert) {
                System.out.printf("== 添加节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

        System.out.printf("\n");

        System.out.printf("== min: %s\n", tree.minimum());
        System.out.printf("== max: %s\n", tree.maximum());
        System.out.printf("== details: \n");
        tree.print();
        System.out.printf("\n");

        // 设置mDebugDelete=true,test remove func
        if (mDebugDelete) {
            for(i=0; i<ilen; i++)
            {
                tree.remove(a[i]);

                System.out.printf("== 删除节点: %d\n", a[i]);
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
            }
        }

		System.out.println(tree.search(100).key+"");

    }

}







