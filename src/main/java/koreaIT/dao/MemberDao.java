package koreaIT.dao;

import koreaIT.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public List<Member> memberList;

    public MemberDao() {
        this.memberList = new ArrayList<>();
    }
}
