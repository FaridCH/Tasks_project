package fr.uha.ensisa.ToDoList.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tache {

    @Id
    @GeneratedValue
    private Long id;

	private String title;
    private String state;
    
    Tache() { // jpa only
    }

    public Tache(String title, String state) {
        this.title = title;
        this.state = state;
    }
    
    public void setId(Long id) {
  		this.id = id;
  	}

    public Long getId() {
        return id;
    }

	public String getTitle() {
		return title;
	}

	public String getState() {
		return state;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setState(String state) {
		this.state = state;
	}
}
