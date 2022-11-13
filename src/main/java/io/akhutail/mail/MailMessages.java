package io.akhutail.mail;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import io.akhutail.mail.emails.EmailsByUserFolder;
import io.akhutail.mail.emails.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.Folder;
import io.akhutail.mail.folders.FolderRepository;

@SpringBootApplication
@RestController
public class MailMessages {

	@Autowired FolderRepository folderRepository;
	@Autowired EmailsByUserFolderRepo emailsByUserFolderRepo;

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

		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Important", "123123", "sender1", "subject here1", false ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Inbox", "123", "sender2", "subjec2", true ));
		emailsByUserFolderRepo.save(new EmailsByUserFolder("akhutail", "Sent", "321", "sender3", "subject3e", false ));

	}	
}
