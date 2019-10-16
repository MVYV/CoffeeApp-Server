package com.mvyv.march11webapp.repository;

import com.mvyv.march11webapp.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
  News findByNewsTitle(String title);

  List<News> findAllByOrderByNewsDateDesc();

  List<News> findAllByLanguageOrderByNewsDateDesc(@Param("language") String language);
}
