package com.example.application.data.services;

import com.example.application.data.model.Company;
import com.example.application.data.model.Contact;
import com.example.application.data.model.Status;
import com.example.application.data.repos.CompanyRepository;
import com.example.application.data.repos.ContactRepository;
import com.example.application.data.repos.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContacts(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(filterText);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact) {
        if (contact == null) System.err.println("Contact is null");
        contactRepository.save(contact);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Status> findAllStatus() {
        return statusRepository.findAll();
    }
}
