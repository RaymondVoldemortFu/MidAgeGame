import java.util.Scanner;

public class Main {
    public static void print_a_line(){
        for (int i=0;i<50;i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }

    public static boolean checkDestination(String dest){
        if(!(dest.length()>=4&&dest.length()<=16)) return false;
        for(int i=0;i<dest.length();i++){
            int ascii = (int)dest.charAt(i);
            if(!(ascii==20 || (ascii>=65&&ascii<=90) || (ascii>=97&&ascii<=122))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        print_a_line();
        System.out.print("Welcome to the Middle Earth Adventure!\n");
        print_a_line();
        System.out.println("Do you want to Start your adventure? enter y to start, n to quit");
        boolean flag = false;
        do {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 1){
                System.out.println("illegal input! Please enter y to start, any other signal character to quit");
                flag = true;
            }
            else if(input.charAt(0)!='y'){
                System.exit(1);
            }
            else if(input.charAt(0)=='y'){
                flag=false;
            }
        }while (flag);
        System.out.println("Please enter the destination of your adventure. ");
        String dest;
        boolean sign = false;
        do{
            System.out.println("请输入终点，只可包含4-16位英文字母及空格");
            Scanner scanner = new Scanner(System.in);
            dest = scanner.nextLine();
            sign = checkDestination(dest);
            if(!sign) System.out.println("输入不合法，请重新输入");
        }while (!sign);

        Adventure adventure = new Adventure(1,12,dest,new Hobbit());
        print_a_line();
        System.out.println(adventure.toString());
        adventure.runAdventure();
        //adventure.print_ending();
    }
}
