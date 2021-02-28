package lesson6.homework06;

import lesson6.MyTreeImpl;

public class Main {

    public static void main(String[] args) {
        final int treesCount = 20;
        final int maxDeep = 4;
        int notBalanced = 0;

        for (int i = 0; i < treesCount; i++) {
            MyTreeImpl<Integer> tree = new MyTreeImpl<>(maxDeep);
            while (tree.height(tree.root()) - 1 != maxDeep) {
                tree.add((int) (Math.random() * 200) - 100);
            }
            notBalanced += tree.isBalanced(tree.root()) ? 0 : 1;
        }

        System.out.println("Несбалансированных деревьев: " + ((double) notBalanced / treesCount * 100) + "%");

    }

}
