package com.application.repository;

import com.application.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.application.constant.persons.PersonsJpql.*;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {
    @Transactional
    @Modifying
    @Query(UPDATE_PERSON)
    int updatePerson(String fullName,
                         String dateOfBirth,
                         String gender,
                         String email,
                         long phoneNumber,
                         int id);

    List<Persons> findPersonsByFullName(String fullName);

    Persons findPersonsByPersonId(int id);

    @Transactional
    @Modifying
    @Query(DELETE_PERSON)
    int delete(int id);
}
