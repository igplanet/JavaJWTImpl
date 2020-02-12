/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ig.javajwtimpl;

import java.util.Date;

/**
 *
 * @author oghomwen.aigbedion
 */
public interface AuthService {

    JWTTokenClaim decrypt(String token) throws Exception;

    TokenResponse loginToken(String sessionId, long userID, long roleID, String userName, String emailAddress, String systemIp) throws Exception;

    public class JWTTokenClaim {

        private String userName;

        private String sessionId;

        private long roleID;

        private long userID;

        private String emailAddress;

        private Date expiryDate;

        private Integer sessionDuration;

        private String systemIP;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public long getUserID() {
            return userID;
        }

        public void setUserID(long userID) {
            this.userID = userID;
        }

        public long getRoleID() {
            return roleID;
        }

        public void setRoleID(long roleID) {
            this.roleID = roleID;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isExpired() {
            Date now = new Date();
            return now.compareTo(expiryDate) == 1;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getSystemIP() {
            return systemIP;
        }

        public void setSystemIP(String systemIP) {
            this.systemIP = systemIP;
        }

        public Integer getSessionDuration() {
            return sessionDuration;
        }

        public void setSessionDuration(Integer sessionDuration) {
            this.sessionDuration = sessionDuration;
        }
    }

}
