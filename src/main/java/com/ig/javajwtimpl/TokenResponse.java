/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ig.javajwtimpl;

/**
 *
 * @author oghomwen.aigbedion
 */
public class TokenResponse extends ServiceResponse {

    private String sessionID;

    private Integer sessionIDExpirySecs;

    private String sessionExpiryTime;

    public TokenResponse(int code) {
        super(code);
    }

    public TokenResponse(int code, String description) {
        super(code, description);
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public Integer getSessionIDExpirySecs() {
        return sessionIDExpirySecs;
    }

    public void setSessionIDExpirySecs(Integer sessionIDExpirySecs) {
        this.sessionIDExpirySecs = sessionIDExpirySecs;
    }

    public String getSessionExpiryTime() {
        return sessionExpiryTime;
    }

    public void setSessionExpiryTime(String sessionExpiryTime) {
        this.sessionExpiryTime = sessionExpiryTime;
    }

}
