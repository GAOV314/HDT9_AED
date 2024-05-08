import java.io.*;
import java.util.*;

/**
 * Clase que representa un descompresor de Huffman.
 */
public class HuffmanDecompressor {

    /**
     * Descomprime el archivo especificado en el archivo de salida especificado.
     *
     * @param inputFile  Nombre del archivo de entrada.
     * @param outputFile Nombre del archivo de salida.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static void decompress(String inputFile, String outputFile) throws IOException {
        // Lee el árbol de Huffman del archivo de entrada.
        Node root = readTree(inputFile);

        // Lee los datos comprimidos del archivo de entrada.
        BitInputStream bis = new BitInputStream(new FileInputStream(inputFile));

        // Descomprime los datos utilizando el árbol de Huffman.
        StringBuilder decompressedData = new StringBuilder();
        Node currentNode = root;
        while (bis.available() > 0) {
            int bit = bis.readBit();
            if (bit == 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode.isLeaf()) {
                decompressedData.append(currentNode.getCharacter());
                currentNode = root;
            }
        }

        // Escribe los datos descomprimidos al archivo de salida.
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        bw.write(decompressedData.toString());
        bw.close();
    }

    /**
     * Lee el árbol de Huffman del archivo especificado.
     *
     * @param inputFile Nombre del archivo de entrada.
     * @return El árbol de Huffman.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private static Node readTree(String inputFile) throws IOException {
        // Lee el árbol de Huffman del archivo de entrada.
        DataInputStream dis = new DataInputStream(new FileInputStream(inputFile));
        Node root = readNode(dis);
        dis.close();
        return root;
    }

    /**
     * Lee un nodo del archivo especificado.
     *
     * @param dis Objeto DataInputStream para leer el nodo.
     * @return El nodo leído.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private static Node readNode(DataInputStream dis) throws IOException {
        // Lee el tipo de nodo (hoja o interno).
        int type = dis.readInt();
        if (type == 0) {
            // Lee el carácter y la frecuencia de un nodo hoja.
            char ch = dis.readChar();
            int freq = dis.readInt();
            return new LeafNode(ch, freq);
        } else {
            // Lee los nodos izquierdo y derecho de un nodo interno.
            Node left = readNode(dis);
            Node right = readNode(dis);
            return new InternalNode(left, right);
        }
    }
}