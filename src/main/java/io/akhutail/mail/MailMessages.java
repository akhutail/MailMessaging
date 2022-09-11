package io.akhutail.mail;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.akhutail.mail.folders.Folder;
import io.akhutail.mail.folders.FolderRepository;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class MailMessages {

	@Autowired FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(MailMessages.class, args);
	}

	@RequestMapping("/user")
	public Mono<String> user(@AuthenticationPrincipal Mono<OAuth2User> principal) {
		return principal.map(val -> val.getAttribute("login"));
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
	}	
}
