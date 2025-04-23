package koreaIT.controller;

import koreaIT.dto.Member;

public abstract class Controller {

    public abstract void doAction(String methodName, String cmd);

    public Member loginedMember = null;
    public boolean isLogined(){
        return loginedMember != null;
    }


}
