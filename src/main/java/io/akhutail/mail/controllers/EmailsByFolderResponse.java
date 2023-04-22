package io.akhutail.mail.controllers;

import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;

import java.util.List;

public class EmailsByFolderResponse {
    private int numRead;
    private List<EmailsByUserFolder> emails;

    public int getNumRead() {
        return numRead;
    }

    public void setNumRead(int numRead) {
        this.numRead = numRead;
    }

    public List<EmailsByUserFolder> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailsByUserFolder> emails) {
        this.emails = emails;
    }

    public EmailsByFolderResponse(List<EmailsByUserFolder> allByUserIdAndLabel, int numRead) {
        emails = allByUserIdAndLabel;
        this.numRead = numRead;
    }
}
