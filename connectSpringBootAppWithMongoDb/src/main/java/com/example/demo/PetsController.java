package com.example.demo;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/pets")
public class PetsController {

	@Autowired
	private PetsRepository repository;

	@RequestMapping("/pets")
	public List<Pets> getAllPets() {
//		return "Welcome to my First Rest API Through Spring Boot";
		
//		List<Pets>  pets = Arrays.asList(new Pets(ObjectId.get(), "ok", "okr", "okra"), new Pets(ObjectId.get(), "ok1", "okr1", "okra1"));
		return repository.findAll();
//		return pets;

	}
	
	@RequestMapping(value ="/pets/{id}",method= RequestMethod.GET)
	public Pets getPetsById(@PathVariable("id") ObjectId id)
	{
		return repository.findBy_id(id);
	}
	@RequestMapping(value = "/pets/{id}", method = RequestMethod.PUT)
	public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
	  pets.set_id(id);
	  repository.save(pets);
	}
	@RequestMapping(value = "/pets", method = RequestMethod.POST)
	public Pets createPet(@Valid @RequestBody Pets pets) {
	  pets.set_id(ObjectId.get());
	  repository.save(pets);
	  return pets;
	}
	
	@RequestMapping(value = "/pets/{id}", method = RequestMethod.DELETE)
	public void deletePet(@PathVariable ObjectId id) {
	  repository.delete(repository.findBy_id(id));
	}
	
	
		

}
