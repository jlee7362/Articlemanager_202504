package koreaIT.controller;

import koreaIT.dto.Article;
import koreaIT.dto.Member;
import koreaIT.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller{

    private Scanner sc;
    private List<Article> articleList;
    private int lastArticleId = 3;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        this.articleList = new ArrayList<>();
    }

    @Override
    public void doAction(String methodName, String cmd) {
        switch(methodName){
            case "modify":
                if(!isLogined()){
                    System.out.println("로그인 후 이용가능합니다.");
                    return;
                }
                doModify(cmd);
                break;
            case "delete":
                if(!isLogined()){ //항상 true
                    System.out.println("로그인 후 이용가능합니다.");
                    return;
                }
                doDelete(cmd);
                break;
            case "detail":
                showDetail(cmd);
                break;
            case "list":
                showList();
                break;
            case "write":
                if(!isLogined()){ //항상 false
                    System.out.println("로그인 후 이용가능합니다.");
                    return;
                }else{
                    doWrite();
                    break;
                }
            default:
                System.out.println("명령어를 제대로 입력해 주세요");
        }

    }

    private void doModify(String cmd) {

        //parsing starts
        String[] cmdBits = cmd.split(" ");

        if (cmdBits.length > 3) {
            System.out.println("명령어를 제대로 입력해주세요.");
        }
        int modifyId = -1;
        try {
            modifyId = Integer.parseInt(cmdBits[2]);
        } catch (NumberFormatException e) {
            System.out.println("정수를 제대로 입력해주세요.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("modify 뒤에 정수를 추가해서 입력해주세요");
        }
        //parsing end
        //modifyId 로 게시글 찾아보기
        Article foundArticle = getArticleById(modifyId);

        if (foundArticle != null) {
            if(foundArticle.getMemberId() == loginedMember.getId()){
                System.out.println("기존 제목: " + foundArticle.getTitle());
                System.out.println("기존 내용: " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newtitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newbody = sc.nextLine().trim();

                String updateDate = Util.getNowDate();

                foundArticle.setTitle(newtitle);
                foundArticle.setBody(newbody);
                foundArticle.setUpdateDate(updateDate);

                System.out.printf("%d번 게시글이 수정되었습니다.\n", modifyId);
            }else{
                System.out.printf("%d번 게시글을 수정할 권한이 없습니다.\n", modifyId);
            }
        } else {
            System.out.printf("%d번 게시물은 없습니다.\n", modifyId);
        }
    }

    private void doDelete(String cmd) {

        // parsing start
        String[] cmdBits = cmd.split(" ");
        if (cmdBits.length > 3) {
            System.out.println("명령어를 제대로 입력해주세요.");
        }
        int deleteId = -1;
        try {
            deleteId = Integer.parseInt(cmdBits[2]);
        } catch (NumberFormatException e) {
            System.out.println("정수를 제대로 입력해주세요.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("delete 뒤에 정수를 추가해서 입력해주세요");
        }
        // parsing end
        Article foundArticle = getArticleById(deleteId);
        if (foundArticle != null) {
            if(foundArticle.getMemberId()== loginedMember.getId()){
                articleList.remove(foundArticle);
                System.out.printf("%d번 게시글이 삭제되었습니다.\n", deleteId);
            }else{
                System.out.printf("%d번 게시글을 삭제할 권한이 없습니다.\n", deleteId);
            }
        } else {
            System.out.printf("%d번 게시글이 없습니다.\n", deleteId);
        }

    }

    private void showDetail(String cmd) {

        // parsing start
        String[] cmdBits = cmd.split(" ");
        if (cmdBits.length > 3) {
            System.out.println("명령어를 제대로 입력해주세요.");
        }
        int detailId = -1;
        try {
            detailId = Integer.parseInt(cmdBits[2]);
        } catch (NumberFormatException e) {
            System.out.println("정수를 제대로 입력해주세요.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("delete 뒤에 정수를 추가해서 입력해주세요");
        }
        // parsing end
        Article foundArticle = getArticleById(detailId);
        if (foundArticle != null) {
            System.out.println("번호: " + foundArticle.getId());
            System.out.println("날짜: " + foundArticle.getRegDate());
            if (foundArticle.getUpdateDate() != null && !foundArticle.getUpdateDate().isEmpty()) {  //String 널체크 문법 체크하기
                System.out.println("수정날짜: " + foundArticle.getUpdateDate());
            }
            System.out.println("작성자: "+foundArticle.getMemberId());
            System.out.println("제목: " + foundArticle.getTitle());
            System.out.println("내용: " + foundArticle.getBody());
        } else {
            System.out.printf("%d번 게시물은 없습니다.\n", detailId);
        }
    }

    private void showList() {

        System.out.println("번호   /    작성날짜           /    작성자    /    제목");
        System.out.println("=".repeat(30));
        for (int i = articleList.size() - 1; i >= 0; i--) {

            if(Util.getNowDate().split(" ")[0].equals(articleList.get(i).getRegDate().split(" ")[0])){
                System.out.printf("%d        %s          %s           %s \n", articleList.get(i).getId(), articleList.get(i).getRegDate().split(" ")[1], articleList.get(i).getMemberId(), articleList.get(i).getTitle());
            }else{
                System.out.printf("%d        %s          %s           %s \n", articleList.get(i).getId(), articleList.get(i).getRegDate().split(" ")[0], articleList.get(i).getMemberId(), articleList.get(i).getTitle());
            }



        }
    }

    private void doWrite() {

        System.out.print("제목 : ");
        String title = sc.nextLine();
        System.out.print("내용 : ");
        String body = sc.nextLine();
        String regDate = Util.getNowDate();

        lastArticleId++;
        int id = lastArticleId;

        Article addArticle = new Article(id, title, body, regDate, "", loginedMember.getId());

        articleList.add(addArticle);
        System.out.printf("%d번 게시물이 생성되었습니다.\n", lastArticleId);

    }

    private Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void makeArticleTestData() {
        System.out.println("테스트를 위한 데이터를 생성합니다.");
        articleList.add(new Article(1, "제목1","내용1", "2025-01-11 12:12:12", Util.getNowDate(),1));
        articleList.add(new Article(2, "제목2", "내용2", "2025-02-22 12:12:12", Util.getNowDate(),1));
        articleList.add(new Article(3, "제목3", "내용3", "2025-03-31 12:12:12", Util.getNowDate(),2));
    }

}
