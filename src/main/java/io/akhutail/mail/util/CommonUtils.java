package io.akhutail.mail.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class CommonUtils {
    public static String getAuthenticatedEmail(){
        return (String) ( (Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getClaims().get("email");
    }
}
