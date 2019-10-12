package com.mvyv.march11webapp.repository;

import com.mvyv.march11webapp.domain.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Byte> {
}
