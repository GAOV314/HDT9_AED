/**
 * Universidad del Valle de Guatemala
 * Algoritmos y estructura de datos
 * @author Gadiel Ocaña
 * @author Marcos Amobrosio
 * Clase main
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainCompressFile {
    public static void main(String[] args) {
        String textToEncode = "";
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Documentos UVG\\archivoComprimir.txt"))) {//Cambiar el path del archivo procesos.txt para que funcione (Pasar path completo)

            String line;
            while ((line = br.readLine()) != null) {
                textToEncode = textToEncode + line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Huffman gabriel = new Huffman(textToEncode);
        Compress compressedFile = new Compress(gabriel);
        compressedFile.generateHuffmanFile();
        gabriel.encode();
        gabriel.printCodes();
        gabriel.generateTree();
        double ratioCompresion = (double) 87 /111*100;

        System.out.println("Ratio de compresion: " + ratioCompresion+"%");

        String inputFile = "input.txt";
        String outputFile = "output.txt";
        try {
            HuffmanDecompressor.decompress(inputFile, outputFile);
            System.out.println("Descompresión completada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
}


    }
}
