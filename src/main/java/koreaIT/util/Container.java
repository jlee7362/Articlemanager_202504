package koreaIT.util;

import koreaIT.dao.ArticleDao;
import koreaIT.dao.MemberDao;
import koreaIT.dto.Article;

public class Container {

    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();

    }
}
