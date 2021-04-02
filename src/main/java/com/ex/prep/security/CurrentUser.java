package com.ex.prep.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private String id;
    private String username;
    private boolean annonymous = true;

    public CurrentUser() {
    }

    public String getId() {
        return id;
    }

    public CurrentUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }


    public boolean isAnonymous(){
        return this.username==null;
    }

}
