package br.com.github.jasson.service;

import br.com.github.jasson.data.dto.PersonDTO;
import br.com.github.jasson.exception.ResourceNotFoundException;
import br.com.github.jasson.model.Person;
import br.com.github.jasson.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.github.jasson.mapper.ObjectMapper.parseListObjects;
import static br.com.github.jasson.mapper.ObjectMapper.parseObject;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");

       var entity = repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Not records found for this ID!"));

       return parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all People!");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDTO){
        logger.info("Create Person!");
        var entity = parseObject(personDTO, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO){
        logger.info("Updating one Person!");
        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not records found for this ID!"));

        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Finding one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not records found for this ID!"));

        repository.delete(entity);
    }
}
