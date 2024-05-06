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
@Getter
@RequiredArgsConstructor
public class Node implements Comparable<Node>{
private final int frequency;
@Setter
private Node leftNode;
@Setter
private Node rightNode;

public Node(Node leftNode, Node rightNode){
    this.frequency = leftNode.getFrequency()-rightNode.getFrequency();
    this.leftNode = leftNode;
    this.rightNode=rightNode;

}

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(frequency,node.getFrequency());
    }
}
