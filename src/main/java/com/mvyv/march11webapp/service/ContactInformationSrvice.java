package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.ContactInformation;
import com.mvyv.march11webapp.repository.ContactInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactInformationSrvice {

  private final ContactInformationRepository contactInformationRepository;

  @Autowired
  public ContactInformationSrvice(ContactInformationRepository contactInformationRepository) {
    this.contactInformationRepository = contactInformationRepository;
  }

  public ContactInformation getInfo() {
    return contactInformationRepository.findAll().get(0);
  }

  public ContactInformation save(ContactInformation info) {
    return contactInformationRepository.save(info);
  }
}
