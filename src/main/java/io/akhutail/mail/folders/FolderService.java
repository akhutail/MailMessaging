package io.akhutail.mail.folders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FolderService {


    @Autowired private FolderRepository folderRepository;
    public List<FolderByUser> getFolders(String userId) {

        List<FolderByUser> folders = folderRepository.findAllById(userId);

        if (folders.isEmpty()){
            //new user so adding default folders
            folderRepository.save(new FolderByUser(userId, "Inbox", "blue"));
            folderRepository.save(new FolderByUser(userId, "Sent", "purple"));

            folders = folderRepository.findAllById(userId);
        }

        return folders;
    }
}
