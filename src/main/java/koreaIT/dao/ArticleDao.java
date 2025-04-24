package koreaIT.dao;

import koreaIT.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    public List<Article> articleList;

    public ArticleDao() {
        this.articleList = new ArrayList<>();
    }
}
