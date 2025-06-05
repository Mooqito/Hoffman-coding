import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.io.FileWriter;
import java.io.IOException;

public class HuffmanTree {

    private String filePathEncodeText;  //Encoded file address
    private static String filePathEncodeTable;
    private HuffmanNode Root;
    private Queue Queue;
    private String fileContent; //text file address
    private int[] counArr = new int[257];   //Counter of the number of each character in the text
    public String[] savaCharTable = new String[257];    //Counter of the number of each character in the text
    private String savaCharText ="";    //Encoded string
    public int SizeTree;
    public String fileCode;
    public HuffmanTree(String fileContent,String filePathEncodeTable,String filePathEecodeText,String fileCode){
        this.fileCode=fileCode;
        this.fileContent = fileContent;
        this.filePathEncodeTable = filePathEncodeTable;
        this.filePathEncodeText=filePathEecodeText;
        countChar(fileContent); //function Counter of the number of each character in the text
        QueueList();
        buildTree();
    }
    public void QueueList(){
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(257,new MyComparator());
        this.Queue=queue;
        for(int i=0;i<257;i++){
            if(counArr[i]!=0){
                HuffmanNode hn = new HuffmanNode();
                hn.character=(char) i;
                hn.data=counArr[i];
                hn.Left=null;
                hn.Right=null;
                queue.add(hn);
            }
        }
        HuffmanNode root=null;
        this.Root=root;
    }
    public void buildTree(){
        while (Queue.size()>1){
            HuffmanNode extractM1 = (HuffmanNode) Queue.peek(); //extract min
            Queue.poll();
            HuffmanNode extractM2 = (HuffmanNode) Queue.peek(); //extract min
            Queue.poll();
            HuffmanNode newNode = new HuffmanNode();
            newNode.data = extractM1.data + extractM2.data;
            newNode.character='+';
            newNode.Left = extractM1;
            newNode.Right = extractM2;
            Root = newNode;
            Queue.add(newNode);
        }
        CreateEncryptionCode(Root,"");
        SaveCodingTable();
        EncodedText();
        decoded();
    }
    public void CreateEncryptionCode(HuffmanNode root, String ByneriCode){
        if(root.Left == null && root.Right == null ){
            savaCharText+=ByneriCode;   //Store the password for each character together in the variable savaCharText
            savaCharTable[(int) root.character] = ByneriCode;   //Store each character's password in its own ascii home
            return;
        }
        CreateEncryptionCode(root.Left,ByneriCode + "0");
        CreateEncryptionCode(root.Right,ByneriCode + "1");
    }
    public void SaveCodingTable(){
            try{
                FileWriter fw=new FileWriter(filePathEncodeTable);
                for(int i=0;i<257;i++){
                    if(savaCharTable[i] != null){
                        fw.write((char) i +" : "+savaCharTable[i]+"\n");
                    }
            }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public void EncodedText(){
        try{
            FileWriter fw=new FileWriter(filePathEncodeText);
            for(int i=0;i<fileContent.length();i++){
                fw.write(savaCharTable[(int) fileContent.charAt(i)]);
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    class HuffmanNode{  //Nodes related to Huffman tree
        int data;
        char character;
        HuffmanNode Left;
        HuffmanNode Right;
    }
    class MyComparator implements Comparator<HuffmanNode> { //Comparison of nodes
        public int compare(HuffmanNode x, HuffmanNode y)
        {

            return x.data - y.data;
        }
    }
    public void countChar(String fileContent){  //The counter of each node
        int ascii=0;
        for(int i=0;i<fileContent.length();i++){
            ascii = (int) fileContent.charAt(i);    //Store in the ascii index of each node
            counArr[ascii]++;
        }
    }
    void decoded(){
        String DecodeText = "";
        String charact = "";
        for(int i=0;i<fileCode.length();i++){
            charact+=fileCode.charAt(i);
            for(int j=0;j<savaCharTable.length;j++){
                if(charact.equals(savaCharTable[j])){
                    charact="";
                    DecodeText+=((char) j);
                }
            }
        }
        WriteDecodeText(DecodeText);
    }
    public void WriteDecodeText(String DecodeText){
        try{
            FileWriter fw=new FileWriter("D:\\Developer\\java\\project Huffman\\Huffman\\file\\Decoded text.txt");
            fw.write(DecodeText);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}