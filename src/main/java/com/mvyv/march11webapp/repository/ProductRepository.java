package com.mvyv.march11webapp.repository;

import com.mvyv.march11webapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByProductType(@Param("type") String type);

  List<Product> findAllByLanguage(@Param("language") String language);

  List<Product> findAllByLanguageAndProductType(@Param("language") String language,
                                                @Param("type") String type);
}
