package fi.metropolia.jarkkaka.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.metropolia.jarkkaka.prj.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
