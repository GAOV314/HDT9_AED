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
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Esta clase representa una hoja en un árbol de Huffman.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class Leaf extends Node {

    /** Carácter almacenado en esta hoja */
    private final char character;

    /**
     * Constructor de la clase Leaf.
     * @param character El carácter asociado a esta hoja.
     * @param frequency La frecuencia del carácter.
     */
    public Leaf(char character, int frequency) {
        // Llama al constructor de la clase padre (Node) con la frecuencia dada.
        super(frequency);
        this.character = character;
    }

    /**
     * Obtiene el carácter asociado a esta hoja.
     * @return El carácter de la hoja.
     */
    public char getCharacter() {
        return character;
    }

}