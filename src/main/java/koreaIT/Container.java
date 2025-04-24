package koreaIT.util;

import koreaIT.dao.ArticleDao;
import koreaIT.dao.MemberDao;

public class Container {

    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();

    }
}
