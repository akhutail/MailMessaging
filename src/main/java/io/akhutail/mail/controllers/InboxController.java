package io.akhutail.mail.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.akhutail.mail.folders.Folder;
import io.akhutail.mail.folders.FolderRepository;


@Controller
public class InboxController {

    @Autowired private FolderRepository folderRepository;
    
    @GetMapping(value = "/")
    public String getHomePage(@AuthenticationPrincipal OAuth2User principal, Model model){
        String userId = principal.getAttribute("login");
        if (principal == null ||  userId == null){
            return "index";
        }
        
        List<Folder> userFolders = folderRepository.findAllById(userId);
        model.addAttribute("userFolders", userFolders);

        return "inbox-page";
    }
}
