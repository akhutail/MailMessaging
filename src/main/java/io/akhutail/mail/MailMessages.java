package io.akhutail.mail;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.internal.core.type.codec.TimeUuidCodec;

import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.Folder;
import io.akhutail.mail.folders.FolderRepository;

@SpringBootApplication
@RestController
public class MailMessages {

	@Autowired FolderRepository folderRepository;
	@Autowired EmailsByUserFolderRepo emailsByUserFolderRepo;
	@Autowired EmailsRepo emailsRepo;
	public static void main(String[] args) {
		SpringApplication.run(MailMessages.class, args);
	}
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties){
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void init(){
		folderRepository.save(new Folder("akhutail", "Inbox", "blue"));
		folderRepository.save(new Folder("akhutail", "Important", "black"));
		folderRepository.save(new Folder("akhutail", "Sent", "orange"));

		UUID id1 = Uuids.timeBased();
		UUID id2 = Uuids.timeBased();
		UUID id3 = Uuids.timeBased();

		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Important", id1, "sender1", "subject1", false ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Inbox", id2, "sender2", "subject2", true ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Sent", id3, "sender3", "subject3", false ));

		List<String> listOfIds = new ArrayList<String>();
		listOfIds.add("user11"); listOfIds.add("user12");
		
		emailsRepo.save(new EmailsById(id1, "sender1",  listOfIds, "subject1", 
		"1This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));
		emailsRepo.save(new EmailsById(id2, "sender2",  listOfIds, "subject2", 
		"2This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));
		emailsRepo.save(new EmailsById(id3, "sender3",  listOfIds, "subject3", 
		"3This is the mail bodyasdl;fj, klsdfjasjflkas , sadlkfjlkasdjf ,m,asdkjflaskdjfaldf, aklsdflkasdjf "));
	}	
}
