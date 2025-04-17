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
        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.startsWith("article modify")){
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
                boolean flag = true;
                if (!articleList.isEmpty()) {
                    for (Article article : articleList) {
                        if (article.getId() == modifyId) {
                            flag = false;
                            System.out.println("기존 제목: " + article.getTitle());
                            System.out.println("기존 내용: " + article.getBody());
                            System.out.print("새 제목 : ");
                            String newtitle = sc.nextLine().trim();
                            System.out.print("새 내용 : ");
                            String newbody = sc.nextLine().trim();

                            String updateDate = Util.getNowDate();

                            article.setTitle(newtitle);
                            article.setBody(newbody);
                            article.setUpdateDate(updateDate);

                            System.out.printf("%d번 게시글이 수정되었습니다.\n", modifyId);

                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.printf("%d번 게시물은 없습니다.\n", modifyId);
                    }
                } else {
                    System.out.println("게시글이 없습니다.");
                }

            }

            else if (cmd.startsWith("article delete")) {
                //parsing starts
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
                //parsing end

                //deleteId로 게시글 찾아보기
                boolean flag = true;
                if (!articleList.isEmpty()) {
                    for (Article article : articleList) {
                        if (article.getId() == deleteId) {
                            flag = false;
                            articleList.remove(article);
                            System.out.printf("%d번 게시글이 삭제되었습니다.\n", deleteId);
                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.printf("%d번 게시물은 없습니다.\n", deleteId);
                    }
                } else {
                    System.out.println("게시글이 없습니다.");
                }

            } else if (cmd.startsWith("article detail")) {
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
                    System.out.println("detail 뒤에 정수를 추가해서 입력해주세요");
                }
                boolean flag = true;
                if (!articleList.isEmpty()) {
                    for (Article article : articleList) {
                        if (article.getId() == detailId) {
                            flag = false;
                            System.out.println("번호: " + article.getId());
                            System.out.println("날짜: " + article.getRegDate());
                            if(article.getUpdateDate() != null){  //String 널체크 문법 체크하기
                                System.out.println("수정날짜: " + article.getUpdateDate());
                            }
                            System.out.println("제목: " + article.getTitle());
                            System.out.println("내용: " + article.getBody());
                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.printf("%d번 게시물은 없습니다.\n", detailId);
                    }
                } else {
                    System.out.println("게시글이 없습니다.");
                }

            } else if (cmd.equals("article list")) {
                System.out.println("번호  /  제목  /  내용");
                System.out.println("=".repeat(30));
                for (int i = articleList.size() - 1; i >= 0; i--) {
                    System.out.printf("%d       %s      %s \n", articleList.get(i).getId(), articleList.get(i).getTitle(), articleList.get(i).getBody());
                }
            } else if (cmd.equals("article write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();


                String regDate = Util.getNowDate();

                lastId++;
                int id = lastId;

                Article addArticle = new Article();

                addArticle.setId(id);
                addArticle.setBody(body);
                addArticle.setTitle(title);
                addArticle.setRegDate(regDate);

                articleList.add(addArticle);

                System.out.printf("%d번 글이 생성되었습니다.\n", id);
            } else if (cmd.equals("exit")) {
                System.out.println("=====프로그램 종료=====");
                break;
            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }

    }
}
