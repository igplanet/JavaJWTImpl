/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ig.javajwtimpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author oghomwen.aigbedion
 */
public class AuthServiceImpl implements AuthService {

//    @Inject
//    @Property(defaultValue = "T@K@N3C_P")
    // Ensure this is not the same across environments
    private String serverApiToken = "T@K@N3C_P";

//      @Inject
//    @Property (defaultValue = "3600")  
    private AtomicReference<Integer> apiTokenExpirySeconds = new AtomicReference<>(3600);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String PORTAL_USER_NAME = "PUN", EXP_DATE = "ED", ROLE_ID = "RI", SESSION_ID = "SI", SESSION_DURATION = "SSD",
            PORTAL_USER_ID = "PUI", EMAIL_ADDRESS = "EA", DELIMITER = ",", ISSUER = "IG", SYSTEM_IP_ADDRESS = "SIP";

    @Override
    public JWTTokenClaim decrypt(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(serverApiToken);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        JWTTokenClaim claims = new JWTTokenClaim();
        claims.setUserName(jwt.getClaim(PORTAL_USER_NAME).asString());
        claims.setRoleID(jwt.getClaim(ROLE_ID).asLong());
        claims.setUserID(jwt.getClaim(PORTAL_USER_ID).asLong());
        claims.setSessionId(jwt.getClaim(SESSION_ID).asString());
        claims.setEmailAddress(jwt.getClaim(EMAIL_ADDRESS).asString());
        claims.setSystemIP(jwt.getClaim(SYSTEM_IP_ADDRESS).asString());
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = format.parse(jwt.getClaim(EXP_DATE).asString());
        claims.setExpiryDate(startDate);
        claims.setSessionDuration(jwt.getClaim(SESSION_DURATION).asInt());
        return claims;
    }

    @Override
    public TokenResponse loginToken(String sessionId, long userID, long roleID, String portalUserName, String emailAddress, String systemIp) throws Exception {
        return encrypt(sessionId, userID, roleID, emailAddress, portalUserName, systemIp);
    }

    private TokenResponse encrypt(String sessionId, long userID, long roleID, String emailAddress, String portalUserName, String systemIp) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Algorithm algorithm = Algorithm.HMAC256(serverApiToken);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        calendar.add(Calendar.SECOND, apiTokenExpirySeconds.get());
        String expDate = format.format(calendar.getTime());
        TokenResponse tokenResponse = new TokenResponse(ServiceResponse.SUCCESS);
        tokenResponse.setSessionExpiryTime(expDate);
        tokenResponse.setSessionIDExpirySecs(apiTokenExpirySeconds.get());
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim(SESSION_ID, sessionId)
                .withClaim(PORTAL_USER_ID, userID)
                .withClaim(ROLE_ID, roleID)
                .withClaim(EMAIL_ADDRESS, emailAddress)
                .withClaim(EXP_DATE, expDate)
                .withClaim(SESSION_DURATION, apiTokenExpirySeconds.get())
                .withClaim(PORTAL_USER_NAME, portalUserName)
                .withClaim(SYSTEM_IP_ADDRESS, systemIp)
                .sign(algorithm);
        tokenResponse.setSessionID(token);
        return tokenResponse;
    }
}
