//project Huffman coding
//Mohamad Qorishi


import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class HuffmantTest {
    public static void Huffman(String fileContent,String filePathIncode,String filePathEecodeText,String fileCode){
        HuffmanTree huffmanTree = new HuffmanTree(fileContent,filePathIncode,filePathEecodeText,fileCode);  //Hoffman's tree making class
    }
    public static String ReadFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while((line = br.readLine() )!= null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
    public static void NameFile(){

        String filePathText = "D:\\Developer\\java\\project Huffman\\Huffman\\file\\text.txt";
        String filePathEncodeTable = "D:\\Developer\\java\\project Huffman\\Huffman\\file\\Encode Table.txt";
        String filePathEncodeText = "D:\\Developer\\java\\project Huffman\\Huffman\\file\\Encoded text.txt";
        String fileContent = ReadFile(filePathText);    //Save the text file in a string
        String fileCode = ReadFile(filePathEncodeText); //Save the encoded file in a string
        Huffman(fileContent,filePathEncodeTable,filePathEncodeText,fileCode);
    }
    public static void main(String[]  org){
        NameFile();
    }
}