import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Compress {
    Huffman huffmanTree;
    String huffmanCode;

    public Compress(Huffman huffmanTree){
        this.huffmanTree = huffmanTree;
        this.huffmanCode = huffmanTree.encode();
    }

    public void generateHuffmanFile(){
        int elementQuantity = huffmanCode.length();
        int residue = elementQuantity % 8;
        if(residue != 0){
            int leftCeros = 8 - residue;

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < leftCeros; i++){
                sb.append(0);
            }

            sb.append(huffmanCode);
            huffmanCode = sb.toString();
            elementQuantity += leftCeros;
        }
        ArrayList<Byte> outputBytes = new ArrayList<>();
        while (huffmanCode.length() > 0){
            String eightBites = huffmanCode.substring(0,8);
            huffmanCode = huffmanCode.substring(8,huffmanCode.length());
            outputBytes.add((byte)(binaryToDecimal(Integer.parseInt(eightBites)) - 128));
        }

        try{
            FileOutputStream fos = new FileOutputStream("D:\\Documentos UVG\\compressedFile.bin");

            // Datos binarios que deseas guardar (en este caso, simplemente un array de bytes de ejemplo)
            Byte[] data = new Byte[outputBytes.size()];
            data = outputBytes.toArray(data);

            byte[] datosOutput = new byte[outputBytes.size()];
            int i = 0;
            for(Byte dato : data){
                datosOutput[i] = dato;
                i++;
            }

            // Escribir los datos en el archivo
            fos.write(datosOutput);

            // Cerrar el flujo de salida
            fos.close();

            System.out.println("Archivo binario guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo binario: " + e.getMessage());
        }
        }

    public static int binaryToDecimal(int binaryNumber){
        int decimalNumber = 0;
        int base = 1;

        while(binaryNumber > 0){
            int digit = binaryNumber % 10;
            binaryNumber = binaryNumber / 10;
            decimalNumber += digit * base;
            base *= 2;
        }
        return decimalNumber;
    }
}
