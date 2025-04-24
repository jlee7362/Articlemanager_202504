package koreaIT;

import koreaIT.controller.ArticleController;
import koreaIT.controller.Controller;
import koreaIT.controller.MemberController;

import java.util.Scanner;

public class App {

    public void run() {
        System.out.println("===프로그램 시작===");

        Scanner sc = new Scanner(System.in);

        Controller controller;

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeArticleTestData();
        memberController.makeMemberTestData();

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("===프로그램 종료===");
                break;
            }

            String[] cmdBits = cmd.split(" ");
            String controllerName = cmdBits[0];

            if (cmdBits.length == 1) {
                System.out.println("명령어를 선택해 주세요.");
                continue;
            }
            String methodName = cmdBits[1];

            if (controllerName.equals("article")) {
                controller = articleController;

            } else if (controllerName.equals("member")) {
                controller = memberController;
            }else{
                System.out.println("사용할 수 없는 명령어입니다.");
                continue;
            }

            String loginCheck = controllerName +"/"+ methodName;

            switch (loginCheck){
                case "article/write":
                case "article/modify":
                case "article/delete":
                case "member/logout":
                    if(!controller.isLogined()){
                        System.out.println("로그인을 먼저 해 주세요.");
                        continue;
                    }
                    break;
            }
            //로그아웃이 필요한 메서드
            switch (loginCheck){
                case "member/login":
                case "member/join":
                    if(controller.isLogined()){
                        System.out.println("로그아웃을 먼저 해 주세요.");
                        continue;
                    }
                    break;

            }

            controller.doAction(methodName, cmd);
        }
    }
}
