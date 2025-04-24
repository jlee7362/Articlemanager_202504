package koreaIT.service;

import koreaIT.dao.MemberDao;
import koreaIT.dto.Member;
import koreaIT.util.Container;

import java.util.List;

public class MemberService {

    private MemberDao memberDao;

    public MemberService() {
       memberDao = Container.memberDao;
    }

    public List<Member> getMembers(){
        return memberDao.getMemberList();
    }
}
