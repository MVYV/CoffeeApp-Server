package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.ContactInformation;
import com.mvyv.march11webapp.service.ContactInformationSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class ContactInformationController {

  private final ContactInformationSrvice contactInformationSrvice;

  @Autowired
  public ContactInformationController(ContactInformationSrvice contactInformationSrvice) {
    this.contactInformationSrvice = contactInformationSrvice;
  }

  @GetMapping
  public ResponseEntity<ContactInformation> getInfo() {
    return ResponseEntity.ok(contactInformationSrvice.getInfo());
  }

  @PutMapping
  public ResponseEntity<ContactInformation> updateInfo(@RequestBody ContactInformation contactInformation) {
    ContactInformation db = contactInformationSrvice.getInfo();
    merge(db, contactInformation);
    return ResponseEntity.ok(contactInformationSrvice.save(db));
  }


  private void merge(ContactInformation db, ContactInformation update) {
    db.setContactInfo(update.getContactInfo());
  }
}
