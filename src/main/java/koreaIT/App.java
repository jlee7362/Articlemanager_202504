package koreaIT;

import koreaIT.controller.ArticleController;
import koreaIT.controller.Membercontroller;
import koreaIT.dto.Article;
import koreaIT.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    List<Article> articleList;
    List<Member> memberList;

    public App() {
        articleList = new ArrayList<>();
        memberList = new ArrayList<>();
    }

    public void run() {
        System.out.println("===프로그램 시작===");

        Scanner sc = new Scanner(System.in);

        Membercontroller memberController = new Membercontroller(sc, memberList);
        ArticleController articleController = new ArticleController(sc, articleList);

        articleController.makeTestData();

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.startsWith("member join")) {

                memberController.doJoin();

            } else if (cmd.startsWith("article modify")) {

                articleController.doModify(cmd);

            } else if (cmd.startsWith("article delete")) {

                articleController.doDelete(cmd);

            } else if (cmd.startsWith("article detail")) {

                articleController.showDetail(cmd);

            } else if (cmd.equals("article list")) {

                articleController.showList();

            } else if (cmd.equals("article write")) {

                articleController.doWrite();

            } else if (cmd.equals("exit")) {
                System.out.println("===프로그램 종료===");
                break;
            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }
    }
}

