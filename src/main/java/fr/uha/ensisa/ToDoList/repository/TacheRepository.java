package fr.uha.ensisa.ToDoList.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import fr.uha.ensisa.ToDoList.model.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {

}