package BinaryTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void inOrderTravel() {
        BinaryTree<Integer> myTree = new BinaryTree<Integer>();
        myTree.insert(12);
        myTree.insert(13);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(1);
        myTree.insert(3);
        myTree.insert(2);

        myTree.inOrderTravel();
    }

    @Test
    void preOrderTravel() {
        BinaryTree<Integer> myTree = new BinaryTree<Integer>();
        myTree.insert(12);
        myTree.insert(13);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(1);
        myTree.insert(3);
        myTree.insert(2);

        myTree.preOrderTravel();
    }

    @Test
    void postOrderTravel() {
        BinaryTree<Integer> myTree = new BinaryTree<Integer>();
        myTree.insert(12);
        myTree.insert(13);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(1);
        myTree.insert(3);
        myTree.insert(2);

        myTree.postOrderTravel();
    }

    @Test
    void counter_test() {
        BinaryTree<Integer> myTree = new BinaryTree<Integer>();
        myTree.insert(12);
        myTree.insert(13);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(1);
        myTree.insert(3);
        myTree.insert(2);

        Assertions.assertEquals(7, myTree.getSize());
    }
}