package koreaIT.service;

import koreaIT.dao.ArticleDao;
import koreaIT.dto.Article;
import koreaIT.util.Container;

import java.util.List;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
       articleDao = Container.articleDao;
    }

    public List<Article> getArticle(){
        return articleDao.getArticleList();
    }


}
