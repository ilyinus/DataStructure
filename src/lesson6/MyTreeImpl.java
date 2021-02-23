package lesson6;

import java.util.Stack;

public class MyTreeImpl<E extends Comparable<? super E>> implements MyTree<E> {

    private class NodeAndParent {
        MyNode<E> current;
        MyNode<E> parent;

        public NodeAndParent(MyNode<E> current, MyNode<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    private int size;
    private MyNode<E> root;


    @Override
    public void add(E value) {
        MyNode<E> newMyNode = new MyNode<>(value);

        if (isEmpty()) {
            root = newMyNode;
            size++;
            return;
        }

        NodeAndParent nodeAndParent = doFind(value);
        if (nodeAndParent.current != null) {
            // nodeAndParent.current.setValue(value)
            return;
        }

        MyNode<E> previous = nodeAndParent.parent;

//        Consumer<Node<E>> setter = previous.isLeftChild(value) ? previous::setLeftChild : previous::setRightChild;
//        setter.accept(newNode);

        if (previous.isLeftChild(value)) {
            previous.setLeftChild(newMyNode);
        } else {
            previous.setRightChild(newMyNode);
        }

        size++;
    }

    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        return nodeAndParent.current != null;
    }

    private NodeAndParent doFind(E value) {
        MyNode<E> current = root;
        MyNode<E> previous = null;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, previous);
            }
            previous = current;
            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }

        return new NodeAndParent(null, previous);
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        MyNode<E> removedMyNode = nodeAndParent.current;
        MyNode<E> parentMyNode = nodeAndParent.parent;

        if (removedMyNode == null) {
            return false;
        }

        if (removedMyNode.isLeaf()) {
            if (removedMyNode == root) {
                root = null;
            } else if (parentMyNode.isLeftChild(value)) {
                parentMyNode.setLeftChild(null);
            } else {
                parentMyNode.setRightChild(null);
            }
        }
        else if (removedMyNode.hasOnlyOneChild()) {
            MyNode<E> childMyNode = removedMyNode.getLeftChild() != null
                    ? removedMyNode.getLeftChild()
                    : removedMyNode.getRightChild();

            if (removedMyNode == root) {
                root = childMyNode;
            } else if (parentMyNode.isLeftChild(value)) {
                parentMyNode.setLeftChild(childMyNode);
            } else {
                parentMyNode.setRightChild(childMyNode);
            }
        }
        else {
            MyNode<E> successor = getSuccessor(removedMyNode);
            if (removedMyNode == root) {
                root = successor;
            } else if (parentMyNode.isLeftChild(value)) {
                parentMyNode.setLeftChild(successor);
            } else {
                parentMyNode.setRightChild(successor);
            }

            successor.setLeftChild(removedMyNode.getLeftChild());
        }

        size--;
        return true;
    }

    private MyNode<E> getSuccessor(MyNode<E> removedMyNode) {
        MyNode<E> successor = removedMyNode;
        MyNode<E> successorParent = null;
        MyNode<E> current = removedMyNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedMyNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedMyNode.getRightChild());
        }

        return successor;
    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown traverse mode: " + mode);
        }
    }

    private void inOrder(MyNode<E> current) {
        if (current == null) {
            return;
        }

        inOrder(current.getLeftChild());
        System.out.println(current.getValue());
        inOrder(current.getRightChild());
    }

    private void preOrder(MyNode<E> current) {
        if (current == null) {
            return;
        }

        System.out.println(current.getValue());
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void postOrder(MyNode<E> current) {
        if (current == null) {
            return;
        }

        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current.getValue());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<MyNode<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<MyNode<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                MyNode<E> tempMyNode = globalStack.pop();
                if (tempMyNode != null) {
                    System.out.print(tempMyNode.getValue());
                    localStack.push(tempMyNode.getLeftChild());
                    localStack.push(tempMyNode.getRightChild());
                    if (tempMyNode.getLeftChild() != null || tempMyNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }
}
