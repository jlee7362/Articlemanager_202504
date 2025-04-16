package koreaIT;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("===프로그램 시작===");

        int lastId = 0;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            if(cmd.equals("article list")){
                System.out.println("번호  /  제목  /  내용");
            }
            else if(cmd.equals("article write")){
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                lastId++;

                System.out.printf("%d번 글이 생성되었습니다.\n",lastId);
            }
            else if(cmd.equals("exit")){
                System.out.println("=====프로그램 종료=====");
                break;
            }
            else{
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }

    }
}