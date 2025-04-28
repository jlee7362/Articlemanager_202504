package koreaIT.dao;

import koreaIT.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    private List<Article> articleList;

    public ArticleDao() {
        this.articleList = new ArrayList<>();
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void add(Article addArticle) {
        articleList.add(addArticle);
    }

   public Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void remove(Article foundArticle) {
        articleList.remove(foundArticle);
    }

}
