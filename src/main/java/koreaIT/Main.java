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
            String cmd = sc.nextLine().trim();

            if (cmd.startsWith("article detail")){
                String [] cmdBits = cmd.split(" ");

                if (cmdBits.length > 3){
                    System.out.println("명령어를 제대로 입력해주세요.");
                }
                int detailId=-1;
                try{
                    detailId = Integer.parseInt(cmdBits[2]);
                }catch(NumberFormatException e){
                    System.out.println("정수를 제대로 입력해주세요.");
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("detail뒤에 정수를 추가해서 입력해주세요");
                }
                if(!articleList.isEmpty()){
                    for (Article article : articleList){
                        if(article.getId() == detailId){
                            System.out.println("너가 찾는 게시물 있어.");
                        }
                        else{
                            System.out.println("찾는 게시물이 없습니다.");
                        }
                    }
                }else{
                    System.out.println("게시글이 없습니다.");
                }


            }
            else if(cmd.equals("article list")){
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
