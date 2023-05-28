package io.akhutail.mail.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class CommonUtils {
    public static String getAuthenticatedEmail(){
        return (String) ( (Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getClaims().get("email");
    }

    public static String getBodyBrief(String body) {
        if(body == null){
            return null;
        }
        else if(body.length() > 40){
            return body.substring(0, 40);
        }
        else{
            return body;
        }
    }
}
