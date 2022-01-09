package com.application.service;

import com.application.entity.Persons;
import com.application.exception.InvalidPersonsException;
import com.application.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import static com.application.util.ValidationUtil.isPersonNull;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonsService {
    private final PersonsRepository personsRepository;

    public Persons getPersonById(int id) {
        if (id <= 0) {
            throw new InvalidPersonsException("Id must be positive");
        }
        return personsRepository.findPersonsByPersonId(id);
    }

    public List<Persons> getPersonsByFullName(String fullName) {
        if (fullName == null) {
            throw new InvalidPersonsException("Full name not specified");
        }
        return personsRepository.findPersonsByFullName(fullName);
    }

    public List<Persons> getAllPersons() {
        return personsRepository.findAll(Sort.by(Sort.Direction.ASC, "personId"));
    }

    public Persons savePerson(Persons person) {
        if (isPersonNull(person)) {
            throw new InvalidPersonsException("Person or person's field is null");
        }
        return personsRepository.save(person);
    }

    public int updatePerson(Persons person) {
        if (isPersonNull(person) || person.getPersonId() == 0) {
            throw new InvalidPersonsException("Person or person's field is null");
        }
        return personsRepository.updatePerson(
                person.getFullName(),
                person.getDateOfBirth(),
                person.getGender(),
                person.getEmail(),
                person.getPhoneNumber(),
                person.getPersonId()
        );
    }

    public int deletePerson(int id) {
        if (id <= 0) {
            throw new InvalidPersonsException("Id must be positive");
        }
        return personsRepository.delete(id);
    }
}
