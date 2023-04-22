package io.akhutail.mail.folders;

import io.akhutail.mail.folders.stats.StatsRepo;
import io.akhutail.mail.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FolderService {


    @Autowired private FolderRepository folderRepository;
    @Autowired private StatsRepo folderStatsRepo;
    public List<FolderByUser> getFolders(String userId) {

        List<FolderByUser> folders = folderRepository.findAllById(userId);

        /*if (folders.isEmpty()){
            //new user so adding default folders
            folderRepository.save(new FolderByUser(userId, "Inbox", "blue"));
            folderRepository.save(new FolderByUser(userId, "Sent", "purple"));
            folderStatsRepo.save(new FolderStats(userId, "Inbox", 0));
            folderStatsRepo.save(new FolderStats(userId, "Sent", 0));

            folders = folderRepository.findAllById(userId);
        }*/

        return folders;
    }

    public int getNumRead(String userId, String label){
        // using Integer here instead of int since findNumReadByUserIdAndLabel might give null if no row found
        // which happens when no mail with this folder
        Integer num = 0;
        try  {
            num = folderStatsRepo.findNumReadByUserIdAndLabel(userId, label);

        }
        catch (Exception e){
            System.out.println(e);
        }
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

    public void initializeUserIfNeeded(){
        String userId = CommonUtils.getAuthenticatedEmail();

        List<FolderByUser> folders = folderRepository.findAllById(userId);

        if (folders.isEmpty()) {
            //new user so adding defaults
            folderRepository.save(new FolderByUser(userId, "Inbox", "blue"));
            folderRepository.save(new FolderByUser(userId, "Sent", "purple"));
        }
    }


    public void updateStats(String folder) {
        System.out.println(folderStatsRepo.incrementNumReadByUserIdAndLabel(CommonUtils.getAuthenticatedEmail(), folder));
    }
}
