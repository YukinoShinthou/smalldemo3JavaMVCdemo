package kendrick.Xml.services;

import jakarta.transaction.Transactional;
import kendrick.Xml.Person.Person;
import kendrick.Xml.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PeopleService {
    private final PersonRepository personRepository;



    @Autowired
    public PeopleService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> findAll(){
        return personRepository.findAll();
    }
    @Transactional
    public Person findById(int id){
        Optional<Person> person = personRepository.findById(id);
        return  person.orElse(null);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(int id , Person updatedPerson){
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }
}
