package avl;

import bst.*;
import exceptions.*;

public class AVLTree<E extends Comparable<E>> extends BST<E> {
    class NodeAVL extends Node<E> {
        protected int fb;

        public NodeAVL(E data) {
            super(data);
            this.fb = 0;
        }

        public String toString() {
            return super.toString() + "(" + this.fb + ")";
        }
    }

    private boolean fl;

    public AVLTree() {
        super();
    }

    public void insert(E x) throws ItemDuplicated {
        try {
            this.fl = false;
            this.setRoot(insertRecAVL(x, (NodeAVL) this.getRoot()));
        } catch (ItemNoFound e) {	//avl vacio
            this.fl = true;
            this.setRoot(new NodeAVL(x));
        }
    }

    private Node<E> insertRecAVL(E x, NodeAVL actual) throws ItemDuplicated {
        NodeAVL res = actual;
        if (actual == null) {
            this.fl = true;
            res = new NodeAVL(x);
        } else {
            int resC = actual.getData().compareTo(x);
            if (resC == 0)
                throw new ItemDuplicated("El dato " + x + " ya fue insertado antes...");
            if (resC < 0) {
                res.setRight(insertRecAVL(x, (NodeAVL) actual.getRight()));
                if (fl) {
                    switch (res.fb) {
                        case -1:
                            res.fb = 0;
                            this.fl = false;
                            break;
                        case 0:
                            res.fb = 1;
                            this.fl = true;
                            break;
                        case 1:
                            res = balanceToLeft(res);
                            this.fl = false;
                            break;
                    }
                }
            } else {
                res.setLeft(insertRecAVL(x, (NodeAVL) actual.getLeft()));
                if (fl) {
                    switch (res.fb) {
                        case 1:
                            res.fb = 0;
                            this.fl = false;
                            break;
                        case 0:
                            res.fb = -1;
                            this.fl = true;
                            break;
                        case -1:
                            res = balanceToRight(res);
                            this.fl = false;
                            break;
                    }
                }
            }
        }
        return res;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL son = (NodeAVL) node.getRight();
        switch (son.fb) {
            case 1:
                node.fb = 0;
                son.fb = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL grandSon = (NodeAVL) son.getLeft();
                switch (grandSon.fb) {
                    case -1:
                        node.fb = 0;
                        son.fb = 1;
                        break;
                    case 0:
                        node.fb = 0;
                        son.fb = 0;
                        break;
                    case 1:
                        node.fb = 1;
                        son.fb = 0;
                        break;
                }
                grandSon.fb = 0;
                node.setRight(rotateSR(son));
                node = rotateSL(node);
                break;
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL son = (NodeAVL) node.getLeft();
        switch (son.fb) {
            case -1:
                node.fb = 0;
                son.fb = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL grandSon = (NodeAVL) son.getRight();
                switch (grandSon.fb) {
                    case 1:
                        node.fb = 0;
                        son.fb = -1;
                        break;
                    case 0:
                        node.fb = 0;
                        son.fb = 0;
                        break;
                    case -1:
                        node.fb = -1;
                        son.fb = 0;
                        break;
                }
                grandSon.fb = 0;
                node.setLeft(rotateSL(son));
                node = rotateSR(node);
                break;
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL son = (NodeAVL) node.getRight();
        node.setRight(son.getLeft());
        son.setLeft(node);
        node = son;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL son = (NodeAVL) node.getLeft();
        node.setLeft(son.getRight());
        son.setRight(node);
        node = son;
        return node;
    }

    public void inOrden() {
        try {
            if (this.isEmpty())
                System.out.println("Arbol esta vacio....");
            else
                inOrden((NodeAVL) this.getRoot());
            System.out.println();
        } catch (ItemNoFound e) {
            System.out.println(e.getMessage());
        }
    }

    protected void inOrden(NodeAVL actual) {
        if (actual.getLeft() != null) inOrden((NodeAVL) actual.getLeft());
        System.out.print(actual + ", ");
        if (actual.getRight() != null) inOrden((NodeAVL) actual.getRight());
    }
}
