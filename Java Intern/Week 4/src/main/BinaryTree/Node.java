package BinaryTree;

public class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    /**
     * @return Значение в данной ноде.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @param value Значение для данной ноды.
     */
    public void setValue(final int value) {
        this.value = value;
    }

    /**
     * @return Получить левого потомка данной ноды.
     */
    public Node getLeft() {
        return this.leftChild;
    }

    /**
     * @param leftChild Присвоить значение левого ребенка данной ноде.
     */
    public void setLeft(final Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return Получить правого потомка данной ноды.
     */
    public Node getRight() {
        return this.rightChild;
    }

    /**
     * @param rightChild Присвоить правого потомка данной ноды.
     */
    public void setRight(final Node rightChild) {
        this.rightChild = rightChild;
    }

}