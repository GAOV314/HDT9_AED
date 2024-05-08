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
 *
 * The above copyright notice, Geekific's channel link and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 */


import lombok.Getter;

import java.util.*;

import static java.util.Objects.requireNonNull;

/**
 * Clase para la codificación y decodificación de texto utilizando el algoritmo Huffman.
 */
public class Huffman {

    private Node root; // Raíz del árbol Huffman
    private final String text; // Texto a codificar
    @Getter
    private Map<Character, Integer> charFrequencies; // Mapa de frecuencias de caracteres
    private final Map<Character, String> huffmanCodes; // Mapa de códigos Huffman

    /**
     * Constructor de la clase Huffman.
     *
     * @param text Texto a codificar.
     */
    public Huffman(String text) {
        this.text = text;
        fillCharFrequenciesMap();
        huffmanCodes = new HashMap<>();
    }

    /**
     * Llena el mapa de frecuencias de caracteres.
     */
    private void fillCharFrequenciesMap() {
        charFrequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            charFrequencies.put(character, charFrequencies.getOrDefault(character, 0) + 1);
        }
    }

    /**
     * Codifica el texto utilizando el algoritmo de Huffman.
     *
     * @return Texto codificado.
     */
    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();
        charFrequencies.forEach((character, frequency) ->
                queue.add(new Leaf(character, frequency))
        );
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), requireNonNull(queue.poll())));
        }
        generateHuffmanCodes(root = queue.poll(), "");
        return getEncodedText();
    }

    /**
     * Genera los códigos Huffman para cada carácter.
     *
     * @param node Nodo actual.
     * @param code Código actual.
     */
    private void generateHuffmanCodes(Node node, String code) {
        if (node instanceof Leaf leaf) {
            huffmanCodes.put(leaf.getCharacter(), code);
            return;
        }
        generateHuffmanCodes(node.getLeftNode(), code.concat("0"));
        generateHuffmanCodes(node.getRightNode(), code.concat("1"));
    }

    /**
     * Obtiene el texto codificado.
     *
     * @return Texto codificado.
     */
    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for (char character : text.toCharArray()) {
            sb.append(huffmanCodes.get(character));
        }
        return sb.toString();
    }

    /**
     * Decodifica el texto codificado utilizando el árbol Huffman.
     *
     * @param encodedText Texto codificado.
     * @return Texto decodificado.
     */
    public String decode(String encodedText) {
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for (char character : encodedText.toCharArray()) {
            current = character == '0' ? current.getLeftNode() : current.getRightNode();
            if (current instanceof Leaf leaf) {
                sb.append(leaf.getCharacter());
                current = root;
            }
        }
        return sb.toString();
    }

    /**
     * Imprime los códigos Huffman para cada carácter.
     */
    public void printCodes() {
        huffmanCodes.forEach((character, code) ->
                System.out.println(character + ": " + code)
        );
    }

    /**
     * Genera el árbol Huffman.
     */
    public void generateTree() {
        // Este método podría implementarse para generar visualizaciones del árbol Huffman si es necesario.
    }
}
