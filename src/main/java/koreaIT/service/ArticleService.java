package koreaIT.service;

import koreaIT.dao.ArticleDao;
import koreaIT.dto.Article;
import koreaIT.Container;
import koreaIT.util.Util;

import java.util.List;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
       articleDao = Container.articleDao;
    }

    public List<Article> getArticle(){
        return articleDao.getArticleList();
    }


    public void add(Article addArticle) {
        articleDao.add(addArticle);
    }

    public Article getArticleById(int detailId) {
        return articleDao.getArticleById(detailId);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);

    }


}
