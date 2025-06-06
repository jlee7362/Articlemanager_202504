package koreaIT.controller;

import koreaIT.dto.Member;
import koreaIT.service.MemberService;
import koreaIT.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> memberList;

    private int lastMemberId = 3;

    MemberService memberService = new MemberService();


    public MemberController(Scanner sc) {
        this.sc = sc;
        this.memberList = memberService.getMembers();
    }

    @Override
    public void doAction(String methodName, String cmd) {
        switch(methodName) {
            case "logout":
                doLogout();
                break;
            case "login":
                doLogin();
                break;
            case "join":
                doJoin();
                break;
            case "list":
                showMember();
                break;
            default:
                System.out.println("명령어를 확인해주세요.4");
        }
    }



    private void doLogout(){
        loginedMember = null;
        System.out.println("로그아웃 성공.");
    }

    private void doLogin() {
        System.out.println("=== 로그인 ===");
        while(true){
            System.out.print("로그인 아이디 : ");
            String loginId = sc.nextLine();
            Member member = getMemberByLoginId(loginId);
            if (member == null) {
                System.out.println("일치하는 회원이 없습니다.");
                continue;
            }
            System.out.print("로그인 비밀번호 : ");
            String loginPw = sc.nextLine();

            if (member.getLoginPw().equals(loginPw)) {
                System.out.printf("로그인 성공. %s님 환영합니다.\n", member.getName());
                loginedMember = member;
                return;
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }
        }
    }


    private void showMember() {
        for (Member member : memberList) {
            System.out.println(member.toString());
        }
    }

    private void doJoin() {

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

    private Member getMemberByLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeMemberTestData() {
        System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
        memberList.add(new Member(1, "2025-01-11", Util.getNowDate(), "admin", "admin", "관리자"));
        memberList.add(new Member(2, "2025-01-11", Util.getNowDate(), "test1", "test1", "회원1"));
        memberList.add(new Member(3, "2025-01-11", Util.getNowDate(), "test2", "test2", "회원2"));
    }

}
