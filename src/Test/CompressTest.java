import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class CompressTest {
    @Test
    public void testCompress(){
        Huffman treeHuffman = new Huffman("Hola me llamo gadiel");
        Compress compressFile = new Compress(treeHuffman);
        Assertions.assertEquals("Archivo binario guardado correctamente.", compressFile.generateHuffmanFile());

    }

}