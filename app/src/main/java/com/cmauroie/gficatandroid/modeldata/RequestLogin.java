package com.cmauroie.gficatandroid.modeldata;

/**
 * Created by Mauricio on 26/8/17.
 */

/*
    {
    "grant_type":"password",
    "client_id":"2_5ZycV0",
    "client_secret":"ZPnHLn2CgYUx_RnUkMt3QHG8UuXOk8Q58JXQ7dd8ekLMMLVE5mXOQ6SsVaA9soDv",
    "username":"cmauroie",
    "password":"3122611700"
}



* */


public class RequestLogin {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String username;
    private String password;


    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
