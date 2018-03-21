package ru.mail.park.java.sample22jdbcspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mail.park.java.sample22jdbcspring.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findAllByHouse(Long houseId);

}
