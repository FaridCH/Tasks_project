package fr.uha.ensisa.ToDoList.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.ensisa.ToDoList.model.Tache;
import fr.uha.ensisa.ToDoList.repository.TacheRepository;

/**
 * TacheRestController est la classe qui contient les differentes methodes de
 * gestion des taches à savoir addTache(), updateTache(), deleteTache(),getTache
 * et finalement getTaches()
 */
@RestController
@RequestMapping("/taches")
public class TacheRestController {

	private final TacheRepository tacheRepository;

	// POST => /user/taches :=> pour ajouter une tache
	/**
	 * ajoute une nouvelle tache et la requette se fait à l'url
	 * /{userId}/taches.
	 * 
	 * @param tache
	 *            la tache à ajouter (le corps de la tache à ajouter)
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Collection<Tache> addTache(@RequestBody Tache tache) {
		if(tache.getTitle() != null){
			if(tache.getState() == null){
				tache.setState("En cours");
			}
			tacheRepository.save(tache);
		}
		return tacheRepository.findAll();
	}

	/**
	 * modifier une tache et la requette se fait à l'url
	 * /{userId}/taches/tacheId.
	 * 
	 * @param tacheId
	 *            l'id de la tache (recuperable à partir de l'url)
	 * @param tache
	 *            la tache à modifier (le corps de la tache à modifier)
	 */
	@RequestMapping(value = "/{tacheId}", method = RequestMethod.PUT)
	@ResponseBody
	Collection<Tache> updateTache(@PathVariable Long tacheId, @RequestBody Tache inputTache) {
		Tache updatedT = new Tache(null, null);
		inputTache.setId(tacheId);
		List<Tache> taches = tacheRepository.findAll();
		for(Tache tache:taches)
		{
			if(tacheId.equals(tache.getId()))
			{
				if(inputTache.getTitle() != null)	tache.setTitle(inputTache.getTitle());
				if(inputTache.getState() != null)	tache.setState(inputTache.getState());
				updatedT = tache;
			}
		}
		tacheRepository.save(updatedT);
		return tacheRepository.findAll();
	}

	/**
	 * supprime une tache et la requette se fait à l'url
	 * /{userId}/taches/tacheId.
	 * 
	 * @param tacheId
	 *            l'id de la tache à supprimer (recuperable à partir de l'url)
	 */
	@RequestMapping(value = "/{tacheId}", method = RequestMethod.DELETE)
	@ResponseBody
	Collection<Tache> deleteTache(@PathVariable Long tacheId) {
		tacheRepository.delete(tacheId);
		return tacheRepository.findAll();
	}

	/**
	 * retourne une tache et la requette se fait à l'url
	 * /{userId}/taches/tacheId.
	 * 
	 * @param tacheId
	 *            l'id de la tache à supprimer (recuperable à partir de l'url)
	 */
	@RequestMapping(value = "/{tacheId}", method = RequestMethod.GET)
	Tache getTache(@PathVariable Long tacheId) {
		return tacheRepository.findOne(tacheId);
	}

	/**
	 * retourne toutes les taches et la requette se fait à l'url
	 * /{userId}/taches.
	 * 
	 * @param tacheId
	 *            l'id de la tache à supprimer (recuperable à partir de l'url)
	 */
	@RequestMapping(method = RequestMethod.GET)
	Collection<Tache> getTaches() {
		return this.tacheRepository.findAll();
	}

	/**
	 * Constructeur
	 * 
	 * @param TacheRepository
	 */
	@Autowired
	TacheRestController(TacheRepository tacheRepository) {
		this.tacheRepository = tacheRepository;
	}
}
