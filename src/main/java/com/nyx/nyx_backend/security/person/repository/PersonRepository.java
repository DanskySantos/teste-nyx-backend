package com.nyx.nyx_backend.security.person.repository;


import com.nyx.nyx_backend.security.person.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findRoleByName(String roleName);
}
