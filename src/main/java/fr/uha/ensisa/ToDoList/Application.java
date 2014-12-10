package fr.uha.ensisa.ToDoList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.uha.ensisa.ToDoList.model.Tache;
import fr.uha.ensisa.ToDoList.repository.TacheRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init(TacheRepository tacheRepository) {
		return (evt) -> {
							tacheRepository.save(new Tache("Editer le cahier des charges", "Finie"));
							tacheRepository.save(new Tache("developpement des fonctionalités", "En cours"));
						};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
