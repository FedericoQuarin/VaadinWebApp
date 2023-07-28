package com.example.application.data.repos;

import com.example.application.data.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact ,Long> {
}
