package koreaIT.controller;

import koreaIT.dto.Member;
import koreaIT.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Membercontroller extends Controller{
    private Scanner sc;
    private List<Member> memberList;
    private int lastMemberId = 0;

    public Membercontroller(Scanner sc){
        this.sc = sc;
        this.memberList = new ArrayList<>();
    }

    @Override
    public void doAction(String methodName, String cmd) {
        switch (methodName){
            case "join":
                doJoin();
                break;
            default:
                System.out.println("명령어를 확인해주세요.4");
        }
    }

    public void doJoin() {

        String loginId;
        while (true) {
            System.out.print("로그인 할 아이디: ");
            loginId = sc.nextLine();
            if (!isJoinableLoginId(loginId)) {
                System.out.println("이미 사용 중인 아이디입니다.");
                continue;
            }
            break;
        }
        String loginPw;
        while (true) {
            System.out.print("비밀번호 설정: ");
            String loginPw1 = sc.nextLine();
            System.out.print("비밀번호 재확인: ");
            String loginPw2 = sc.nextLine();
            if (!loginPw1.equals(loginPw2)) {
                System.out.println("비밀번호가 서로 다릅니다. 다시 입력해주세요.");
                continue;
            } else {
                loginPw = loginPw1;
            }
            break;
        }
        System.out.print("이름: ");
        String name = sc.nextLine();

        String regDate = Util.getNowDate();

        lastMemberId++;
        Member addMember = new Member(lastMemberId, regDate, "", loginId, loginPw, name);


        memberList.add(addMember);

        System.out.printf("%d번 회원이 등록되었습니다. %s님 환영합니다.\n", lastMemberId, name);


    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

}
