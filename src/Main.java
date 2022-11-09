import java.util.Scanner;

public class Main {

    private static Scanner _Main_userinput = new Scanner(System.in);

    public static void main(String[] args) {
        Boolean _Main_exit = false;
        while(!_Main_exit){
            _Menu_();
            int _Menu_choise = 0;
            while(true){
                _Menu_choise = _Main_userinput.nextInt();
                if(_Menu_choise < 1 || _Menu_choise > 4){
                    System.out.println("[Error]Wrong input!Please input again:");
                }
                else {
                    break;
                }
            }
            switch (_Menu_choise){
                case 1:
                    //基本要求
                    System.out.println("Run1");
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
                    _Main_exit = true;
                    break;
            }
        }
        System.out.println("[System]Program has closed!");
    }

    private static void _Menu_ (){
        System.out.println("----HuffmanCode program----");
        System.out.println("[System]Welcom to use HuffmanCode program! ");
        System.out.println("[System](1)Build huffmantree");
        System.out.println("[System](2)Code textfiles");
        System.out.println("[System](3)Decode textfiles");
        System.out.println("[System](4)Exit program");
        System.out.println("[System]Please input your choice(int) of function:");
    }
}

