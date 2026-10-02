
package com.cancer.backend.service;

import com.cancer.backend.dto.ContactRequest;
import com.cancer.backend.entity.Contact;
import com.cancer.backend.repository.ContactRepository;

import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(ContactRequest request) {

        Contact contact = new Contact();

        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setSubject(request.getSubject());
        contact.setMessage(request.getMessage());

        return contactRepository.save(contact);
    }
}
