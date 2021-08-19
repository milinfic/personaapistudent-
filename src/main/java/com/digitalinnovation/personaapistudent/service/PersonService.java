package com.digitalinnovation.personaapistudent.service;

import com.digitalinnovation.personaapistudent.dto.MessageResponseDTO;
import com.digitalinnovation.personaapistudent.dto.request.PersonDTO;
import com.digitalinnovation.personaapistudent.entity.Person;
import com.digitalinnovation.personaapistudent.exception.PersonNotFoundException;
import com.digitalinnovation.personaapistudent.mapper.PersonMapper;
import com.digitalinnovation.personaapistudent.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper =
            PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        /*
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }
        */
        return personMapper.toDTO(person);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }
}
