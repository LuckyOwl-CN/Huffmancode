public class HuffmanTree {

    private String _Read_Currline;

    //使用256长度的Node数组，其在数组中的位置对应了ASCII码的值
    private Node[] ASCIIarray = new Node[256];

    //读取文件一行内容,存入_Read_Currline
    private void _ReadFileline_()throws Exception{
        File file = new File("D:\f1.txt");
    	BufferedReader br = new BufferedReader(new FileReader(file));// 读取文件
    	_Read_Currline = br.readLine();//存入_Read_Currline
    	br.close();
    }

    //计算ASCII数量
    private void _CountASCII_(){

    }

    //初始化ASCIIarray，new出所有的Node，并附初始值
    private void _InitASCIIarray_(){
        for(int i = 0;i < 256;i++){
            ASCIIarray[i] = new Node(i,0);
        }
    }

    //构建HuffmanTree,返回根节点
    private Node _BuildHuffmanTree_(){
        return null;
    }

    //遍历HuffmanTree，将每个Node赋予相应的值
    private void _CalHuffmanCode_(Node root){

    }

    //将HuffmanCode存到txt中
    private void _SaveHuffmanCode_(){

    }

    public void _Main_HuffmanTree(){
        _InitASCIIarray_();
        for (int i = 0; i < ASCIIarray.length; i++) {
            System.out.println(ASCIIarray[i].ASCII+" "+ASCIIarray[i].priority);
        }
    }

    private class Node{
        //结点的ascii值
        int ASCII;
        //频度
        int priority;
        //huffman编码
        String huffmancode;
        Node leftChild;
        Node rightChild;

        public Node(int ASCII,int priority){
            this.ASCII = ASCII;
            this.priority = priority;
        }
    }
}
