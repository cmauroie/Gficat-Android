package com.cmauroie.gficatandroid.modeldata;

/**
 * Created by Mauricio on 26/8/17.
 */

/*
{
    "token_type": "bearer",
    "refresh_token_expires_in": 5184000,
    "refresh_token": "G1brLcXBRi2BzEOfG2ToLTct6YCJwuGf",
    "scope": "",
    "resource_owner": "cmauroie",
    "expires_in": 3600,
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDM3Njc5NzIsImlzcyI6IjJfNVp5Y1YwIiwicm9sZXMiOlsiVXNlciJdLCJzY29wZXMiOiIiLCJzdWIiOiJ1c2VyL2NtYXVyb2llIn0.dQY8sobuOj-Tvm8HAEiF-Rkdrm3Lae3Su-vPxOyUUAc"
}

*/

public class ResponseLogin {

    private String token_type;
    private String refresh_token_expires_in;
    private String refresh_token;
    private String scope;
    private String resource_owner;
    private String expires_in;
    private String access_token;


    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token_expires_in() {
        return refresh_token_expires_in;
    }

    public void setRefresh_token_expires_in(String refresh_token_expires_in) {
        this.refresh_token_expires_in = refresh_token_expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getResource_owner() {
        return resource_owner;
    }

    public void setResource_owner(String resource_owner) {
        this.resource_owner = resource_owner;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
