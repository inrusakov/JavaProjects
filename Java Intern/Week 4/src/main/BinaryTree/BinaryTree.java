package BinaryTree;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTree<T extends Comparable<T>>{
    private Node root;
    private Integer counter = 0;

    /**
     * @param value - Значение, которое мы вставляем.
     */
    public void insert(Integer value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null) {
            root = newNode;
        }
        else {
            Node currentNode = root;
            Node parentNode;
            while (true)
            {
                parentNode = currentNode;
                if(value == currentNode.getValue()) {
                    return;
                }
                else  if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null){
                        parentNode.setLeft(newNode);
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTravel(){
        inOrder(this.root);
    }

    /**
     * Центрированный обход, от детей к родителям.
     * @param node Данный узел.
     */
    public void inOrder(Node node) throws NullPointerException{
        if(node == null) return;
        inOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        inOrder(node.getRight());
    }

    public void preOrderTravel(){
        preOrder(this.root);
    }

    /**
     * Прямой обход, проверяющий каждый лист дерева, начиная с левого.
     * @param node Данный узел.
     */
    private void preOrder(Node node){
        if(node == null) return;
        System.out.print(node.getValue()+" ");
        inOrder(node.getLeft());
        inOrder(node.getRight());
    }

    public void postOrderTravel(){
        postOrder(this.root);
    }

    /**
     * Обратный обход, при котором дерево рассматривается снизу вверх.
     * @param node Данный узел.
     */
    private void postOrder(Node node){
        if(node == null) return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getValue()+" ");
    }

    /**
     * Подсчет количества узлов дерева.
     * @return Количество значений в дереве
     */
    public int getNumber() {
        counter = 0;
        counter(root);
        return counter;
    }

    private void counter(Node node){
        if(node == null) return;
        counter(node.getLeft());
        counter++;
        counter(node.getRight());
    }
}
