import java.util.Scanner;

public class Main {

    private static Scanner mainuserinput = new Scanner(System.in);

    private static HuffmanTree huffmanTree = new HuffmanTree();

    public static void main(String[] args) {
        Boolean main_exit = false;
        while(!main_exit){
            Menu();
            int menu_choise = 0;
            while(true){
                menu_choise = mainuserinput.nextInt();
                if(menu_choise < 1 || menu_choise > 4){
                    System.out.println("[Error]Wrong input!Please input again:");
                }
                else {
                    break;
                }
            }
            switch (menu_choise){
                case 1:
                    //基本要求
                    System.out.println("Run1");
                    huffmanTree.MainHuffmanTree();
                    break;
                case 2:
                    //中级要求
                    System.out.println("Run2");
                    break;
                case 3:
                    //高级要求
                    System.out.println("Run3");
                    break;
                case 4:
                    main_exit = true;
                    break;
            }
        }
        System.out.println("[System]Program has closed!");
    }

    private static void Menu (){
        System.out.println("----HuffmanCode program----");
        System.out.println("[System]Welcom to use HuffmanCode program! ");
        System.out.println("[System](1)Build huffmantree");
        System.out.println("[System](2)Code textfiles");
        System.out.println("[System](3)Decode textfiles");
        System.out.println("[System](4)Exit program");
        System.out.println("[System]Please input your choice(int) of function:");
    }
}

