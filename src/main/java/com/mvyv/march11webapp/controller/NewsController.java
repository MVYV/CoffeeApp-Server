package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.News;
import com.mvyv.march11webapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

  private final NewsService newsService;

  @Autowired
  public NewsController(NewsService newsService) {
    this.newsService = newsService;
  }

  @GetMapping
  public ResponseEntity<List<News>> getAllNews() {
    return ResponseEntity.ok(newsService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<News> getNewsById(@PathVariable("id") Long id) {
    Optional<News> optionalNews = newsService.getById(id);
    if (optionalNews.isPresent()) {
      return ResponseEntity.ok(optionalNews.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<News> addNewNews(@RequestBody News news) {
    return ResponseEntity.ok(newsService.save(news));
  }

  @PatchMapping
  public ResponseEntity<News> updateNews(@RequestBody News news) {
    Optional<News> optionalNews = newsService.getById(news.getId());
    if (optionalNews.isPresent()) {
      News found = optionalNews.get();
      merge(found, news);
      return ResponseEntity.ok(newsService.save());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNews(@PathVariable("id") Long id) {
    newsService.delete(id);
    return ResponseEntity.noContent().build();
  }


  public void merge(News dbNews, News update) {
    dbNews.setSubText(update.getSubText());
    dbNews.setText(update.getText());
    dbNews.setTitle(update.getTitle());
  }
}
