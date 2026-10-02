package com.cancer.backend.controller;

import com.cancer.backend.dto.ContactRequest;
import com.cancer.backend.entity.Contact;
import com.cancer.backend.service.ContactService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> submitContact(
            @Valid @RequestBody ContactRequest request
    ) {

        Contact contact = contactService.saveContact(request);

        return ResponseEntity.ok(contact);
    }
}