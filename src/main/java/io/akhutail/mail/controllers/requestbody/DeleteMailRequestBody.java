package io.akhutail.mail.controllers.requestbody;

import java.util.List;
import java.util.UUID;

public class DeleteMailRequestBody {
    private String folderName;
    private List<UUID> mailIds;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<UUID> getMailIds() {
        return mailIds;
    }

    public void setMailIds(List<UUID> mailIds) {
        this.mailIds = mailIds;
    }

    public DeleteMailRequestBody(String folderName, List<UUID> mailIds) {
        this.folderName = folderName;
        this.mailIds = mailIds;
    }

    public DeleteMailRequestBody() {
    }

    @Override
    public String toString() {
        return "DeleteMailRequestBody{" +
                "folderName='" + folderName + '\'' +
                ", mailIds=" + mailIds +
                '}';
    }
}
