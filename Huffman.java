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


import java.util.*;

import static java.util.Objects.requireNonNull;

public class Huffman {

    private Node root;
    private final String text;
    private Map<Character, Integer> charFrequencies;
    private final Map<Character, String> huffmanCodes;


    public Huffman(String text) {
        this.text = text;
        fillCharFrequenciesMap();
        huffmanCodes = new HashMap<>();
    }

    private void fillCharFrequenciesMap() {
        charFrequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            charFrequencies.put(character, charFrequencies.getOrDefault(character, 0) + 1);
        }
    }


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

    private void generateHuffmanCodes(Node node, String code) {
        if (node instanceof Leaf leaf) {
            huffmanCodes.put(leaf.getCharacter(), code);
            return;
        }
        generateHuffmanCodes(node.getLeftNode(), code.concat("0"));
        generateHuffmanCodes(node.getRightNode(), code.concat("1"));
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for (char character : text.toCharArray()) {
            sb.append(huffmanCodes.get(character));
        }
        return sb.toString();
    }


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


    public void printCodes() {
        huffmanCodes.forEach((character, code) ->
                System.out.println(character + ": " + code)
        );
    }

}