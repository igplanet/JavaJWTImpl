/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ig.javajwtimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oghomwen.aigbedion
 */
public class TestJWT {

    static TokenResponse token;

    static AuthService jwtService = new AuthServiceImpl();

    public static void main(String[] args) {
        String appUsergetUserName = "ig";
        long appUsergetId = 1;
        long menuRepogetUserFirstRoleappUsergetUserLoginName = 1;
        String appUsergetUserEmail = "ig@gmail.com";
        String systemIp = "192.168.0.1";

        String sessionID = String.format("%s_%s", appUsergetUserName, UUID.randomUUID().toString());
        try {
            token = jwtService.loginToken(sessionID, appUsergetId, menuRepogetUserFirstRoleappUsergetUserLoginName, appUsergetUserName, appUsergetUserEmail, systemIp);

            System.out.println(new ObjectMapper().writeValueAsString(token));
            System.out.println("------------------------------");
            System.out.println(jwtService.decrypt(token.getSessionID()).getEmailAddress());
            System.out.println(jwtService.decrypt(token.getSessionID()).getSystemIP());
            System.out.println(jwtService.decrypt(token.getSessionID()).getSessionId());
            System.out.println(jwtService.decrypt(token.getSessionID()).getUserName());
            System.out.println(jwtService.decrypt(token.getSessionID()).getUserID());
        } catch (Exception ex) {
            Logger.getLogger(TestJWT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
