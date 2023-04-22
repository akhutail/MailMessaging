package io.akhutail.mail;

import io.akhutail.mail.folders.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;






@Component
public class UserInitializationInterceptor implements HandlerInterceptor {
    FolderService folderService;
    UserInitializationInterceptor(FolderService folderService){
        this.folderService = folderService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        folderService.initializeUserIfNeeded();
        // Proceed in any case.
        return true;
    }

}
