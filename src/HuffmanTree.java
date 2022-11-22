import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanTree {
    public static int[] frequency = new int[256]; //用来存放统计好的字符和频度集
    public static int totalFrequency = 0; //原文件中总字符数


    //读取文件一行内容,存入_Read_Currline
    private void _ReadFileline_(){
        try {
            File file = new File("./src/test.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));// 读取文件
            String _Read_Currline;
            while((_Read_Currline = br.readLine()) != null){//存入_Read_Currline不为null
                _CountASCII_(); //当前行统计字符频度
                System.out.println(_Read_Currline);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //计算ASCII数量
    private void _CountASCII_(){

    }

    //初始化Frequency并附初始值
    private void _InitASCIIarray_(){
        for(int i = 0;i < 256;i++){
            frequency[i] = 0;
        }
        frequency[97] = 3;
        frequency[98] = 4;
        frequency[99] = 5;
        frequency[100] = 10;
    }

    //构建HuffmanTree,返回根节点
    private Node _BuildHuffmanTree_(int n){
        // 建堆
        PriorityQueue<Node> minheap= new PriorityQueue<>(n, FREQUENCY_COMPARATOR);

        for (int i = 0; i < n; i++) {
            if (frequency[i] != 0) {
                // 队尾插入元素，调整堆结构
                minheap.add(new Node(i, frequency[i]));
            }
        }

        Node z = null;

        // 建树
        while (minheap.size() > 1) {
            //找出两个最小的
            Node x = minheap.peek(); // 返回队头元素，调整堆结构
            minheap.poll(); // 删除返回的这个元素
            Node y = minheap.peek();
            minheap.poll();
            x.setCode("0"); // 左分支标1
            y.setCode("1"); // 右分支标1
            z = new Node();
            z.setASCII(256); // 赋一个初始的unicode码
            z.setPriority( x.getPriority() + y.getPriority());
            z.setlChild(x);
            z.setrChild(y);
            minheap.add(z);
        }

        Node root = z;
        return root;
    }

    // 比较两个char的频率大小
    private static final Comparator<Node> FREQUENCY_COMPARATOR = (Node o1, Node o2) -> (int) (o1.priority-o2.priority);

    //遍历HuffmanTree，将每个Node赋予相应的值
    private void _CalHuffmanCode_(Node root){
        if(root == null) return;
        //_SaveHuffmanCode_(); //Preorder traversal & Save in file
        if(root.ASCII < 256) System.out.println("ASCII:"+(char)root.ASCII+" HuffmanCode:"+root.code);
        root.code = "";
        _CalHuffmanCode_(root.leftChild,root.code);
        _CalHuffmanCode_(root.rightChild,root.code);
    }

    private void _CalHuffmanCode_(Node root,String code){
        if(root == null) return;
        root.code = code + root.code;
        if(root.ASCII < 256) {
            System.out.println("ASCII:"+(char)root.ASCII+" HuffmanCode:"+root.code);
        }
        _CalHuffmanCode_(root.leftChild,root.code);
        _CalHuffmanCode_(root.rightChild,root.code);
    }

    //将HuffmanCode存到txt中
    private void _SaveHuffmanCode_(){

    }

    public void _Main_HuffmanTree(){
        _InitASCIIarray_();
//        for (int i = 0; i < frequency.length; i++) {
//            System.out.println(i+" "+frequency[i]);
//        }
        _ReadFileline_();
        _CalHuffmanCode_(_BuildHuffmanTree_(256));
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
