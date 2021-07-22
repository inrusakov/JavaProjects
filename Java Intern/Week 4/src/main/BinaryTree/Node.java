package BinaryTree;

public class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public Node getLeft() {
        return this.leftChild;
    }

    public void setLeft(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRight() {
        return this.rightChild;
    }

    public void setRight(final Node rightChild) {
        this.rightChild = rightChild;
    }

}