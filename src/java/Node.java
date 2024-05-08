import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * MIT License
 *
 * Copyright (c) 2023 Geekific (https://www.youtube.com/c/Geekific)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *The above copyright notice, Geekific's channel link and this permission notice
 *shall be included in all copies or substantial portions of the Software.
 */



/**
 * Esta clase representa un nodo utilizado en un árbol de Huffman.
 */
@Getter
@RequiredArgsConstructor
public class Node implements Comparable<Node>{
    /** Frecuencia del nodo */
    private final int frequency;

    /** Nodo izquierdo */
    @Setter private Node leftNode;

    /** Nodo derecho */
    @Setter private Node rightNode;

    /**
     * Constructor de la clase Node.
     * @param leftNode El nodo izquierdo.
     * @param rightNode El nodo derecho.
     */
    public Node(Node leftNode, Node rightNode) {
        // La frecuencia de este nodo es la diferencia entre las frecuencias de los nodos izquierdo y derecho.
        this.frequency = leftNode.getFrequency() - rightNode.getFrequency();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Obtiene la frecuencia del nodo.
     * @return La frecuencia del nodo.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Obtiene el nodo izquierdo.
     * @return El nodo izquierdo.
     */
    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * Obtiene el nodo derecho.
     * @return El nodo derecho.
     */
    public Node getRightNode() {
        return rightNode;
    }

    /**
     * Compara este nodo con otro nodo basado en su frecuencia.
     * @param node El nodo con el que se compara.
     * @return Un valor negativo, cero o un valor positivo si la frecuencia de este nodo es menor, igual o mayor que la del nodo dado.
     */
    @Override
    public int compareTo(Node node) {
        return Integer.compare(frequency, node.getFrequency());
    }
}
