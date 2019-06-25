package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.News;
import com.mvyv.march11webapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsService {

  private final NewsRepository newsRepository;

  @Autowired
  public NewsService(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  public List<News> getAll() {
    return newsRepository.findAll();
  }

  public Optional<News> getById(Long id) {
    return Optional.ofNullable(newsRepository.getOne(id));
  }

  public Optional<News> getByTitle(String title) {
    return Optional.ofNullable(newsRepository.findByNewsTitle(title));
  }

  public News save(News news) {
    if (news.getId() == null) news.setNewsDate(new Date());
    else news.setNewsModificationDate(new Date());
    return newsRepository.save(news);
  }

  public void delete(Long id) {
    newsRepository.deleteById(id);
  }
}
