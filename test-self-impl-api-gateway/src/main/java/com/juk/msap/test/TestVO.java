package com.juk.msap.test;

import org.springframework.stereotype.Component;

@Component
public class TestVO {
    private String USER_ID;
    private String HOST;

    public String getHOST() {
        return HOST;
    }
    public void setHOST(String HOST) {
        this.HOST = HOST;
    }
    public String getUSER_ID() {
        return USER_ID;
    }
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
