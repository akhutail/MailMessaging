package io.akhutail.mail;

import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.FolderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailMessages {

	@Autowired FolderRepository folderRepository;
	@Autowired EmailsByUserFolderRepo emailsByUserFolderRepo;
	@Autowired EmailsRepo emailsRepo;
	public static void main(String[] args) {
		SpringApplication.run(MailMessages.class, args);
	}

	@PostConstruct
	public void init(){
		/*folderRepository.save(new Folder("akhutail", "Inbox", "blue"));
		folderRepository.save(new Folder("akhutail", "Important", "black"));
		folderRepository.save(new Folder("akhutail", "Sent", "orange"));

		UUID id1 = Uuids.timeBased();
		UUID id2 = Uuids.timeBased();
		UUID id3 = Uuids.timeBased();

		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Important", id1, "sender1", "subject1", false ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Inbox", id2, "sender2", "subject2", true ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Sent", id3, "sender3", "subject3", false ));

		List<String> listOfIds = new ArrayList<>();
		listOfIds.add("user11"); listOfIds.add("user12");
		
		emailsRepo.save(new EmailsById(id1, "sender1",  listOfIds, "subject1", 
		"1This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));
		emailsRepo.save(new EmailsById(id2, "sender2",  listOfIds, "subject2", 
		"2This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));
		emailsRepo.save(new EmailsById(id3, "sender3",  listOfIds, "subject3", 
		"3This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));

		 */
	}
}
