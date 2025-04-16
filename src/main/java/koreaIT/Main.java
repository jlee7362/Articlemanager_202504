package koreaIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Article> articleList = new ArrayList<>();

        System.out.println("===프로그램 시작===");

        int lastId = 0;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            if(cmd.equals("article list")){
                System.out.println("번호  /  제목  /  내용");
                System.out.println("=".repeat(30));
                for (int i = articleList.size()-1; i>=0; i--){
                    System.out.printf("%d       %s      %s \n", articleList.get(i).getId(), articleList.get(i).getTitle(), articleList.get(i).getBody());
                }
            }
            else if(cmd.equals("article write")){
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                lastId++;
                int id = lastId;

                Article addArticle = new Article();

                addArticle.setId(id);
                addArticle.setBody(body);
                addArticle.setTitle(title);

                articleList.add(addArticle);

                System.out.printf("%d번 글이 생성되었습니다.\n",id);
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
