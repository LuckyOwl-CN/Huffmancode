import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
    private static int[] frequency = null; //用来存放统计好的字符和频度集
    private static int totalFrequency = 0; //原文件中总字符数
    private static Scanner huffmanuserinput = new Scanner(System.in);
    private static String filename = null;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;

    /**
     * 属性初始化
     */
    private void Init(){
        frequency = new int[256];
        totalFrequency = 0;
        filename = null;
        fw = null;
        bw = null;
        InitASCIIArray();
    }

    /**
     * 读取待编码文件内容
     */
    private void ReadFile(){
        System.out.println("[System]Please input encode file's name:");
        filename = huffmanuserinput.nextLine();
        try {
            File file = new File("./src/"+filename+".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));// 读取文件
            String _Read_Currline;
            while((_Read_Currline = br.readLine()) != null){//存入_Read_Currline不为null
                CountASCII(_Read_Currline); //当前行统计字符频度
                //System.out.println(_Read_Currline);
            }
            br.close();
        }catch (IOException e){
            System.out.println("[Error]File don't exist!");
        }
    }

    /**
     * 计算ASCII数量
     */
    private void CountASCII(String currline){
        for (int i = 0; i < currline.length(); i++) {
            frequency[currline.charAt(i)]++;
        }
    }

    /**
     * 初始化Frequency并附初始值0
     */
    private void InitASCIIArray(){
        for(int i = 0;i < 256;i++){
            frequency[i] = 0;
        }
    }

    /**
     * 构建HuffmanTree,返回根节点
     * @param n
     * @return Node
     */
    private Node BuildHuffmanTree(int n){
        // 建堆
        PriorityQueue<Node> minheap= new PriorityQueue<>(n, FREQUENCY_COMPARATOR);

        for (int i = 0; i < n; i++) {
            if (frequency[i] != 0) {
                // 队尾插入元素，调整堆结构
                minheap.add(new Node(i, frequency[i]));
            }
        }

        Node pnode = null;

        // 建树
        while (minheap.size() > 1) {
            //找出两个最小的
            Node lnode = minheap.peek(); // 返回队头元素，调整堆结构
            minheap.poll(); // 删除返回的这个元素
            Node rnode = minheap.peek();
            minheap.poll();
            lnode.setCode("0"); // 左分支标1
            rnode.setCode("1"); // 右分支标1
            pnode = new Node();
            pnode.setASCII(256); // 赋一个初始的unicode码
            pnode.setPriority( lnode.getPriority() + rnode.getPriority());
            pnode.setlChild(lnode);
            pnode.setrChild(rnode);
            minheap.add(pnode);
        }

        Node root = pnode;
        return root;
    }

    // 比较两个char的频率大小
    private static final Comparator<Node> FREQUENCY_COMPARATOR = (Node o1, Node o2) -> (int) (o1.priority-o2.priority);

    /**
     * 遍历HuffmanTree，计算并打印其HuffmanCode
     * @param root 传入分支结点
     */
    private void CalHuffmanCode(Node root){
        if(root == null) return;
        //_SaveHuffmanCode_(); //Preorder traversal & Save in file
        if(root.ASCII < 256) {
            System.out.println("ASCII:"+(char)root.ASCII+" Frequency:"+root.priority+" HuffmanCode:"+root.code);
            SaveHuffmanCode(root);
        }
        root.code = "";
        CalHuffmanCode(root.leftChild,root.code);
        CalHuffmanCode(root.rightChild,root.code);
    }

    /**
     * 遍历HuffmanTree，计算并打印其HuffmanCode
     * @param root 传入分支结点
     * @param code 传入父结点HuffmanCode
     */
    private void CalHuffmanCode(Node root, String code){
        if(root == null) return;
        root.code = code + root.code;
        if(root.ASCII < 256) {
            System.out.println("ASCII:"+(char)root.ASCII+" Frequency:"+root.priority+" HuffmanCode:"+root.code);
            SaveHuffmanCode(root);
        }
        CalHuffmanCode(root.leftChild,root.code);
        CalHuffmanCode(root.rightChild,root.code);
    }

    /**
     * 将HuffmanCode存到txt中
     */
    private void SaveHuffmanCode(Node root) {
        try {
            if(fw == null){
                fw = new FileWriter(new File("./src/" + filename + "_code.txt"));
                bw = new BufferedWriter(fw);
            }
            bw.write("ASCII:"+(char)root.ASCII+" Frequency:"+root.priority+" HuffmanCode:"+root.code+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * HuffmanCoder主逻辑
     */
    public void MainHuffmanTree(){
        Init();
        ReadFile();
        CalHuffmanCode(BuildHuffmanTree(256));
        System.out.println("[System]Store HuffmanCode in file:"+filename+"_code.txt");
        try {
            bw.close();
            fw.close();
        }catch (IOException e){

        }
    }

    private class Node{
        private int ASCII; //结点的ascii值
        private int priority; //频度
        private Node leftChild, rightChild;
        private String code; //该char对应的编码code

        public Node(int ASCII,int priority){
            this.ASCII = ASCII;
            this.priority = priority;
            this.leftChild = null;
            this.rightChild= null;
        }

        //Constructors
        public Node(){

        }

        // 基础建树操作
        public Node create(Node a,Node b)
        {
            this.priority = a.getPriority() + b.getPriority();
            this.ASCII= 256;
            this.leftChild = a;
            this.rightChild = b;
            return this;
        }

        public char getASCII() {
            return (char) ASCII;
        }

        public void setASCII(int ASCII) {
            this.ASCII = ASCII;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Node getrChild() {
            return rightChild;
        }

        public void setrChild(Node rChild) {
            this.rightChild= rChild;
        }

        public Node getlChild() {
            return leftChild;
        }

        public void setlChild(Node lChild) {
            this.leftChild = lChild;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
