package koreaIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articleList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("===프로그램 시작===");
        makeTestData();

        int lastId = 3;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            if (rq.getFunctionCode().equals("article")) {
                if (rq.getActionCode().equals("modify")) {

                    //modifyId 로 게시글 찾아보기

                    Article modifyArticle = getArticleById(rq.getId());

                    if (modifyArticle != null) {

                        System.out.println("기존 제목: " + modifyArticle.getTitle());
                        System.out.println("기존 내용: " + modifyArticle.getBody());
                        System.out.print("새 제목 : ");
                        String newtitle = sc.nextLine().trim();
                        System.out.print("새 내용 : ");
                        String newbody = sc.nextLine().trim();

                        String updateDate = Util.getNowDate();

                        modifyArticle.setTitle(newtitle);
                        modifyArticle.setBody(newbody);
                        modifyArticle.setUpdateDate(updateDate);

                        System.out.printf("%d번 게시글이 수정되었습니다.\n", rq.getId());
                    } else {
                        System.out.printf("%d번 게시물은 없습니다.\n", rq.getId());
                    }
                }
                else if (rq.getActionCode().equals("delete")) {
                    //deleteId로 게시글 찾기
                    Article deleteArticle = getArticleById(rq.getId());

                    if (deleteArticle != null) {
                        articleList.remove(deleteArticle);
                        System.out.printf("%d번 게시글이 삭제되었습니다.\n", rq.getId());
                    } else {
                        System.out.printf("%d번 게시물은 없습니다.\n", rq.getId());
                    }
                }else if (rq.getActionCode().equals("detail")) {
                    Article detailArticle = getArticleById(rq.getId());
                    if (detailArticle != null) {
                        System.out.println("번호: " + detailArticle.getId());
                        System.out.println("날짜: " + detailArticle.getRegDate());
                        if (detailArticle.getUpdateDate() != null && detailArticle.getUpdateDate().isEmpty()) {  //String 널체크 문법 체크하기
                            System.out.println("수정날짜: " + detailArticle.getUpdateDate());
                        }
                        System.out.println("제목: " + detailArticle.getTitle());
                        System.out.println("내용: " + detailArticle.getBody());
                    } else {
                        System.out.printf("%d번 게시물은 없습니다.\n", rq.getId());
                    }
                }
                else if (rq.getActionCode().equals("list")) {
                    System.out.println("번호  /  제목  /  내용");
                    System.out.println("=".repeat(30));
                    for (int i = articleList.size() - 1; i >= 0; i--) {
                        System.out.printf("%d       %s      %s \n", articleList.get(i).getId(), articleList.get(i).getTitle(), articleList.get(i).getBody());
                    }
                }
                else if (rq.getActionCode().equals("write")) {
                    System.out.print("제목 : ");
                    String title = sc.nextLine();
                    System.out.print("내용 : ");
                    String body = sc.nextLine();

                    String regDate = Util.getNowDate();
                    lastId++;
                    int id = lastId;
                    Article addArticle = new Article(id, title, body, regDate, "");

                    addArticle.setId(id);
                    addArticle.setBody(body);
                    addArticle.setTitle(title);
                    addArticle. setRegDate(regDate);

                    articleList.add(addArticle);

                    System.out.printf("%d번 글이 생성되었습니다.\n", id);
                }
                else if(rq.getActionCode().equals("exit")){
                    System.out.println("=====프로그램 종료=====");
                    break;
                }
            }else{
                System.out. println("사용할 수 없는 명령어입니다.");
            }
        }

    }private static void makeTestData() {
        System.out.println("테스트를 위한 데이터를 생성합니다.");
        articleList.add(new Article(1, "제목1", "내용1", "2025-01-11", Util.getNowDate()));
        articleList.add(new Article(2, "제목2", "내용2", "2025-02-22", Util.getNowDate()));
        articleList.add(new Article(3, "제목3", "내용3", "2025-03-31", Util.getNowDate()));
    }

    private static Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}


