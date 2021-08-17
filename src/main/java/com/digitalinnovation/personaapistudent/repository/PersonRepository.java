package com.digitalinnovation.personaapistudent.repository;

import com.digitalinnovation.personaapistudent.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long>{

}
